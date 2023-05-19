package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CancelServlet extends HttpServlet {
    Connection dbConnection;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = getServletContext();
        
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
        HttpSession cUser = request.getSession();
        String dbQuery;
        
        // Get corresponding orderID for item being deleted and the uid of the current user.
        String orderID = (String) request.getParameter("order-id");
        String cUID = (String) cUser.getAttribute("ID");
        
        try {
            // Delete order with specified ID in database.
            dbQuery = "DELETE FROM ORDERS WHERE ORDER_ID=?";
            PreparedStatement pState = dbConnection.prepareStatement(dbQuery);
            pState.setString(1, orderID);
            pState.executeUpdate();
            
            // Refresh order list for displaying back to user.
            dbQuery = "SELECT STOCK.STOCK_IMG, ORDERS.ORDER_ID, STOCK.STOCK_ID, STOCK.STOCK_NAME, STOCK.STOCK_PRICE FROM ORDERS LEFT JOIN STOCK ON ORDERS.STOCK_ID=STOCK.STOCK_ID WHERE ORDERS.USER_ID=?";
            pState = dbConnection.prepareStatement(dbQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pState.setString(1, cUID);
            ResultSet rs = pState.executeQuery();
            cUser.setAttribute("ORDERS", rs);
            
            response.sendRedirect("profile.jsp");
        } 
        catch (SQLException sqle) {
            System.out.println("[!] Query could not be performed. L + Ratio + Database fell off lmaoooo");
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
