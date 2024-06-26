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
import model.idGen;

public class RegisterServlet extends HttpServlet {
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
            
            dbConnection = DriverManager.getConnection(dbURL, context.getInitParameter("dbUName"), context.getInitParameter("dbPass"));
            System.out.println("[Debug] Established connection to database: " + dbURL);
        }
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println("[!] A connection to the database could not be established.");
            throw new ServletException();
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
        String answer = request.getParameter("captchaAnswer");
        
        //Resetting messages
        cUser.setAttribute("message", null);

        Captcha captcha = (Captcha) cUser.getAttribute(Captcha.NAME);
        if (!captcha.isCorrect(answer)) {//Checks if Captcha is correct
            request.getSession().setAttribute("message", "Captcha verification failed. Please try again.");
            response.sendRedirect("register.jsp");
            return;
        }
        try {
            //Checks if user exists
            String dbQuery = "SELECT * FROM USERS WHERE USER_NAME = ? OR USER_EMAIL = ?";
            PreparedStatement pState = dbConnection.prepareStatement(dbQuery);            
            pState.setString(1, Crypto.encrypt(enteredUN, publicKey, cip));
            pState.setString(2, Crypto.encrypt(enteredEm, publicKey, cip));
            ResultSet rs = pState.executeQuery();

            if (rs.next()) {
                cUser.setAttribute("message", "The specified Email/Username is already in use.");
                response.sendRedirect("register.jsp");
                return;
            }
            rs.close();
            
            //Checks if id has been used already
            dbQuery = "SELECT * FROM USERS WHERE USER_ID = ?";
            pState = dbConnection.prepareStatement(dbQuery);
            pState.setString(1, userid);
            rs = pState.executeQuery();
            while(rs.next()){
                rs.close();
                userid = idG.generateUserId();
                dbQuery = "SELECT * FROM USERS WHERE USER_ID = ?";
                pState = dbConnection.prepareStatement(dbQuery);
                pState.setString(1, userid);
                rs = pState.executeQuery();
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
            
            cUser.setAttribute("EMAIL", enteredEm);
            cUser.setAttribute("USERNAME", enteredUN);
            cUser.setAttribute("ADDRESS", enteredAd);
            cUser.setAttribute("ID", userid);
                        
            dbQuery = "SELECT STOCK.STOCK_IMG, ORDERS.ORDER_ID, STOCK.STOCK_ID, STOCK.STOCK_NAME, STOCK.STOCK_PRICE FROM ORDERS LEFT JOIN STOCK ON ORDERS.STOCK_ID=STOCK.STOCK_ID WHERE ORDERS.USER_ID=?";
            pState = dbConnection.prepareStatement(dbQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pState.setString(1, userid);
            rs = pState.executeQuery();
            cUser.setAttribute("ORDERS", rs);
            
            dbQuery = "SELECT * FROM STOCK";
            pState = dbConnection.prepareStatement(dbQuery);
            rs = pState.executeQuery();
            ShopInitializer si = new ShopInitializer();
            HashMap stock = si.initStock(rs);
            cUser.setAttribute("STOCK", stock);
            
            cUser.removeAttribute("message");
            response.sendRedirect("profile.jsp");
            
        } catch (SQLException sqlex) {
            System.out.println("[!] Update could not be performed.");
            throw new ServletException();
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
