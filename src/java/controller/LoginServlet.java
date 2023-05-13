package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nl.captcha.Captcha;
import model.Crypto;
import java.util.ArrayList;
import java.util.List;

public class LoginServlet extends HttpServlet {

    private static byte[] publicKey;
    private static String cip;
    Connection dbConnection;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        publicKey = config.getInitParameter("pubkey").getBytes();
        cip = config.getInitParameter("cipher");
        
        try {
            Class.forName(config.getInitParameter("dbClass"));
            
            String dbURL = config.getInitParameter("dbDriver") +
                    "://" + config.getInitParameter("dbHost") +
                    ":" + config.getInitParameter("dbPort") +
                    "/" + config.getInitParameter("dbName");
            System.out.println("[Debug] Established connection to database: " + dbURL);
                                   
            dbConnection = DriverManager.getConnection(dbURL, config.getInitParameter("dbUName"), config.getInitParameter("dbPass"));
        }
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println("A connection to the database could not be established.");
        }     
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String enteredUN = (String) request.getParameter("username");
        String enteredPW = (String) request.getParameter("password");      
        String answer = (String) request.getParameter("captchaAnswer");
        
        HttpSession cUser = request.getSession();        
        Captcha verify = (Captcha) cUser.getAttribute(Captcha.NAME);
        
        /*
            To add:
                Turn public key retrieved from servlet context into a byte array.
                Obtain encryption mode (AES).
        
                Method to check login attempts before sending user back to landing.
        
            Logic:
                Use a prepared statement to select the user with the given email OR username from users table.
                * Username/Email is ENCRYPTED so it must be decrypted first before comparing.
        
                Check if captcha is correct before checking for matches.
                If captcha is correct and a match is found, decrypt the password and compare it to the password entered by the user.
                
                If any of these checks fail, inform user what they got wrong (username/email, password, or captcha) then 
                reduce user's login attempts before having them try again.
        
                Login attempts should ideally not be handled with a session but brain smol and that's what I did lmfao
                basically if login attempts will be reduced, just include the number of attempts left as an attribute then add logic to see if it's 0 alr                      
        
                Note: "What if there are users with the same username in the system since it's not a primary key and we're using it
                to find users in the DB???"
                - Register servlet will prevent users from having the same usernames/emails.
                    Statement will always return exactly one user if the entered credentials are correct.
        */
      if (!verify.isCorrect(answer)) {
            
            response.sendRedirect("login.jsp");
            request.getSession().setAttribute("failCaptcha", "Captcha verification failed, please try again!");
            return;
        }
        
        try {
            // Create and Execute the query
            PreparedStatement ps= dbConnection.prepareStatement("SELECT * FROM USERS WHERE USER_NAME=? AND USER_PASS=?");
            ps.setString(1,Crypto.encrypt(enteredUN, publicKey, cip));
            ps.setString(2,Crypto.encrypt(enteredPW, publicKey, cip));
            ResultSet rs = ps.executeQuery();
            
            HttpSession session = request.getSession();
            while(rs.next())
            {
                    //if login attempt is valid
                    session.setAttribute("EMAIL", Crypto.decrypt(rs.getString("USER_EMAIL"), publicKey, cip) );
                    session.setAttribute("PASSWORD", Crypto.decrypt(rs.getString("USER_PASS"), publicKey, cip) );
                    session.setAttribute("USERNAME", Crypto.decrypt(rs.getString("USER_NAME"), publicKey, cip) );
                    response.sendRedirect("shop.jsp");
                    return;
                
            }
            cUser.setAttribute("incorrectMsg", "Your username or password is incorrect.");
            response.sendRedirect("login.jsp"); 
            }
            catch (SQLException e) {
                cUser.setAttribute("unconnectMsg", "Unable to connect to database.");
                response.sendRedirect("login.jsp");
            }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
