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
 private static byte[] key;
    private static String cip;
    Connection dbConnection;
    int totalAttempts;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  
    
    //Establishing Connection
    public void init(ServletConfig config) throws ServletException {
        key = config.getInitParameter("key").getBytes();
        cip = config.getInitParameter("cipher");
        super.init(config);
        totalAttempts = 0;
        
        try {
            Class.forName(config.getInitParameter("dbClass"));
            StringBuffer dbURL = new StringBuffer(config.getInitParameter("dbDriver")) 
                    .append("://")
                    .append(config.getInitParameter("dbHost"))
                    .append(":")
                    .append(config.getInitParameter("dbPort"))
                    .append("/")
                    .append(config.getInitParameter("dbName"));
            System.out.println("DbUrl: " + dbURL);
            dbConnection = DriverManager.getConnection(dbURL.toString(), config.getInitParameter("dbUName"), config.getInitParameter("dbPass"));
        }
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println("A connection to the database could not be established.");
        }
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String uname = request.getParameter("uname");
        String pass = request.getParameter("pass");
        HttpSession cUser = request.getSession();
        
        Captcha captcha = (Captcha) request.getSession().getAttribute(Captcha.NAME);
        String answer = request.getParameter("captcha");
        if (!captcha.isCorrect(answer)) {
            
            response.sendRedirect("login.jsp");
            request.getSession().setAttribute("failCaptcha", "Captcha verification failed, please try again!");
            return;
        }
        if (totalAttempts >= 3 || (cUser.getAttribute("attempts") == null)) {
            totalAttempts = 0;
        }
        try {
            // Create and Execute the query
            Statement state = dbConnection.createStatement();
            String dbQuery = "SELECT * FROM USERS ORDER BY EMAIL";
            ResultSet rs = state.executeQuery(dbQuery);
            
            HttpSession session = request.getSession();
            while(rs.next())
            {
                if (uname.equals(rs.getString("EMAIL")) && pass.equals(Crypto.decrypt(rs.getString("PASSWORD"), key, cip)))
                {
                    //if login attempt is valid
                    session.setAttribute("EMAIL", rs.getString("EMAIL") );
                    session.setAttribute("PASSWORD", rs.getString("PASSWORD") );
                    session.setAttribute("ROLE", rs.getString("USERROLE") );
                    totalAttempts = 0;
                    if (rs.getString("USERROLE").equals("admin")){
                        response.sendRedirect("adminpage.jsp");
                        rs.close(); 
                        
                    } else if(rs.getString("USERROLE").equals("guest")){
                        response.sendRedirect("userpage.jsp");
                        rs.close(); 
                        
                    }
                    return;
                }
                
                
                    
            }
            totalAttempts++;
            cUser.setAttribute("attempts", totalAttempts);
            cUser.setAttribute("incorrectMsg", "Your username or password is incorrect. You have " + (3 - totalAttempts) + " attempts remaining.");
            response.sendRedirect("inputerror.jsp"); 
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
