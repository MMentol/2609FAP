package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nl.captcha.Captcha;
import model.Crypto;
import model.ShopInitializer;

public class LoginServlet extends HttpServlet {
    private static byte[] publicKey;
    private static String cip;
    Connection dbConnection;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = getServletContext();
        publicKey = context.getInitParameter("pubKey").getBytes();
        cip = context.getInitParameter("cipher");
        
        try {
            Class.forName(context.getInitParameter("dbClass"));
            
            String dbURL = context.getInitParameter("dbDriver") +
                    "://" + context.getInitParameter("dbHost") +
                    ":" + context.getInitParameter("dbPort") +
                    "/" + context.getInitParameter("dbName");
            System.out.println("[Debug] Established connection to database: " + dbURL);
                                   
            dbConnection = DriverManager.getConnection(dbURL, context.getInitParameter("dbUName"), context.getInitParameter("dbPass"));
        }
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println("A connection to the database could not be established.");
            // To change: specfiy an exception to throw and a corresponding error page.
        }     
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String enteredUN = (String) request.getParameter("username");
        String enteredPW = (String) request.getParameter("password");      
        String answer = (String) request.getParameter("captchaAnswer");
        
        HttpSession cUser = request.getSession();        
        Captcha verify = (Captcha) cUser.getAttribute(Captcha.NAME);
                
        if (!verify.isCorrect(answer)) {
            response.sendRedirect("login.jsp");
            request.getSession().setAttribute("message", "Captcha verification failed, please try again!");
            return;
        }

        try {
            // Create and Execute the query
            String dbQuery = "SELECT * FROM USERS WHERE (USER_NAME=? OR USER_EMAIL=?) AND USER_PASS=?";
            PreparedStatement ps = dbConnection.prepareStatement(dbQuery);
            ps.setString(1, Crypto.encrypt(enteredUN, publicKey, cip));
            ps.setString(2, Crypto.encrypt(enteredUN, publicKey, cip));
            ps.setString(3, Crypto.encrypt(enteredPW, publicKey, cip));
            ResultSet rs = ps.executeQuery();

            HttpSession session = request.getSession();
            while (rs.next()) {
                //if login attempt is valid
                
                session.setAttribute("EMAIL", Crypto.decrypt(rs.getString("USER_EMAIL"), publicKey, cip));
                session.setAttribute("USERNAME", Crypto.decrypt(rs.getString("USER_NAME"), publicKey, cip));
                session.setAttribute("ADDRESS", Crypto.decrypt(rs.getString("USER_ADDRESS"), publicKey, cip));
                String uid = rs.getString("USER_ID");
                session.setAttribute("ID", uid);
                
                
                dbQuery = "SELECT STOCK.STOCK_IMG, ORDERS.ORDER_ID, STOCK.STOCK_ID, STOCK.STOCK_NAME, STOCK.STOCK_PRICE FROM ORDERS LEFT JOIN STOCK ON ORDERS.STOCK_ID=STOCK.STOCK_ID WHERE ORDERS.USER_ID=?";
                ps = dbConnection.prepareStatement(dbQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ps.setString(1, uid);
                rs = ps.executeQuery();
                session.setAttribute("ORDERS", rs);
                
                
                dbQuery = "SELECT * FROM STOCK";
                ps = dbConnection.prepareStatement(dbQuery);
                rs = ps.executeQuery();
                ShopInitializer si = new ShopInitializer();
                HashMap stock = si.initStock(rs);
                session.setAttribute("STOCK", stock);
                
                
                session.removeAttribute("message");
                response.sendRedirect("profile.jsp");
                return;
            }
            cUser.setAttribute("message", "Email/Username or Password is incorrect.\nPlease try again.");
            response.sendRedirect("login.jsp");
        } catch (SQLException e) {
            cUser.setAttribute("message", "Unable to connect to database.");
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
