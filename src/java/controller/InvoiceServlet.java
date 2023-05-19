package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.InvoiceGenerator;

public class InvoiceServlet extends HttpServlet {
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
        String fileLocation = getServletContext().getRealPath("\\Reports");
        String dbQuery;        
        InvoiceGenerator receipt = new InvoiceGenerator();

        // Get corresponding orderID for item being processed.
        String orderID = (String) request.getParameter("order-id");
        
        // Get necessary information required to create receipt.
        String cUsername = (String) cUser.getAttribute("USERNAME");
        String cEmail = (String) cUser.getAttribute("EMAIL");
        String cUID = (String) cUser.getAttribute("ID");
        String cAddress = (String) cUser.getAttribute("ADDRESS");
        
        try {
            dbQuery = "SELECT ORDERS.ORDER_ID, STOCK.STOCK_ID, STOCK.STOCK_NAME, STOCK.STOCK_PRICE, ORDERS.ORDER_PAYMENT, ORDERS.ORDER_INSTALL FROM ORDERS LEFT JOIN STOCK ON ORDERS.STOCK_ID=STOCK.STOCK_ID WHERE ORDERS.ORDER_ID=?";
            PreparedStatement pState = dbConnection.prepareStatement(dbQuery);
            pState.setString(1, orderID);
            ResultSet orderDetails = pState.executeQuery();
            
            // generate the report by calling invoice generator
            receipt.setValues(request.getServletContext().getInitParameter("generalPhone"), 
                    request.getServletContext().getInitParameter("supportPhone"), 
                    request.getServletContext().getInitParameter("generalMail"),
                    request.getServletContext().getInitParameter("supportMail"));
            receipt.printInvoice(cUID, cUsername, cEmail, cAddress, orderDetails, fileLocation);
            
            // Load the content into the webpage, which SHOULD be in a new tab.
            ServletOutputStream servOut = response.getOutputStream();
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "inline; filename=invoice.pdf");
            BufferedInputStream iStream = null;
            BufferedOutputStream oStream = null;
            
            try {
                InputStream docIS = request.getServletContext().getResourceAsStream("/Reports/invoice.pdf");
                iStream = new BufferedInputStream(docIS);
                oStream = new BufferedOutputStream(servOut);
                byte[] buffer = new byte[2048];
                int readBytes;
                while ((readBytes = iStream.read(buffer, 0, buffer.length)) != -1) {
                    oStream.write(buffer, 0, readBytes);
                }
            }
            catch (IOException ioe) {
                System.out.println("[!] Failed to load the invoice file.");
            }
            finally {
                if (iStream != null) {
                    iStream.close();
                }
                if (oStream != null) {
                    oStream.close();
                }
            }
        }
        catch (SQLException e) {
            System.out.println("[!] Query could not be performed. L + Ratio + Database fell off lmaoooo");
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
