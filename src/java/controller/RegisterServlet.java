/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

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
import model.idGen;

/**
 *
 * @author Joseph Robles
 */
public class RegisterServlet extends HttpServlet {

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
        //Init classes
        idGen idG = new idGen();
        HttpSession cUser = request.getSession();
        
        //Init variables
        String enteredUN = request.getParameter("username");
        String enteredPW = request.getParameter("password");
        String enteredEm = request.getParameter("email");
        String enteredAd = request.getParameter("address");
        String userid = idG.generateUserId();
        String answer = request.getParameter("captcha-input");
        
        //Resetting messages
        cUser.setAttribute("unconnectMsg", null);
        cUser.setAttribute("userExists", null);

        Captcha captcha = (Captcha) request.getSession().getAttribute(Captcha.NAME);
        if (!captcha.isCorrect(answer)) {//Checks if Captcha is correct

            request.getSession().setAttribute("failCaptcha", "Captcha verification failed, please try again!");
            response.sendRedirect("register.jsp");
            return;
        }
        try {
            
            //Checks if user exists
            String dbQuery = "SELECT * FROM USERS WHERE USER_NAME = ? OR USER_EMAIL = ?";
            PreparedStatement pState = dbConnection.prepareStatement(dbQuery);
            pState.setString(1, enteredUN);
            pState.setString(2, enteredEm);
            ResultSet rs = pState.executeQuery();

            if (rs.next()) {
                cUser.setAttribute("userExists", "This user already exists!");
                response.sendRedirect("adminpage.jsp");
                return;
            }
            rs.close();
            
            //Adding user into db
            dbQuery = "INSERT INTO USERS(USER_ID, USER_NAME, USER_EMAIL, USER_PASS, USER_ADDRESS) VALUES(?,?,?,?,?)";
            pState = dbConnection.prepareStatement(dbQuery);
            pState.setString(1, userid);
            pState.setString(2, Crypto.encrypt(enteredUN, publicKey, cip));
            pState.setString(3, Crypto.encrypt(enteredEm, publicKey, cip));
            pState.setString(4, Crypto.encrypt(enteredPW, publicKey, cip));
            pState.setString(5, Crypto.encrypt(enteredAd, publicKey, cip));
            pState.executeUpdate();
            cUser.setAttribute("successMsg", "You have successfully registered!");
            response.sendRedirect("login.jsp");
            
           
        } catch (Exception ex) {
            
            cUser.setAttribute("unconnectMsg", "Unable to connect to database.");
            response.sendRedirect("adminpage.jsp");
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
