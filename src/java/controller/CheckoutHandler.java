package controller;


import java.io.IOException;
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
import model.ShopItem;
import model.idGen;

public class CheckoutHandler extends HttpServlet {
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
        HttpSession cUser = request.getSession();
        
        ShopItem item = (ShopItem) cUser.getAttribute("chosen-item");
        String stockID = item.getID();
        String orderPayment = request.getParameter("payment");
        String install = request.getParameter("install");
        String userID = (String) cUser.getAttribute("ID");
        idGen iG = new idGen();
        String orderID = iG.generateOrderId();
        
        cUser.setAttribute("unconnectMsg", null);
        cUser.setAttribute("userExists", null);
        
        

        try {
            String dbQuery = "SELECT * FROM ORDERS WHERE ORDER_ID = ?";
            PreparedStatement pState = dbConnection.prepareStatement(dbQuery);
            pState.setString(1, orderID);
            ResultSet rs = pState.executeQuery();
            while(rs.next()){
                rs.close();
                orderID = iG.generateUserId();
                dbQuery = "SELECT * FROM ORDERS WHERE ORDER_ID = ?";
                pState = dbConnection.prepareStatement(dbQuery);
                pState.setString(1, orderID);
                rs = pState.executeQuery();
            }
            rs.close();
            
            
            dbQuery = "INSERT INTO ORDERS(ORDER_ID, STOCK_ID, ORDER_PAYMENT, ORDER_INSTALL, USER_ID) VALUES(?,?,?,?,?)";
            pState = dbConnection.prepareStatement(dbQuery);
            pState.setString(1, iG.generateOrderId());
            pState.setString(2, stockID);
            pState.setString(3, orderPayment);
            pState.setString(4, install);
            pState.setString(5, userID);
            pState.executeUpdate();
            cUser.setAttribute("successMsg", "Order processed!");
            response.sendRedirect("success.jsp");
            
           
        } catch (Exception ex) {
            System.out.println(ex);
            cUser.setAttribute("unconnectMsg", "Unable to connect to database.");
            response.sendRedirect("checkout.jsp");
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
