<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iceCOOL.co - Confirm Order</title>      
        <link rel="stylesheet" href="${pageContext.request.contextPath}/sources/styles/checkstyles.css">
        <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/sources/icons/icfav.png">
    </head>
    <body>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Expires", "0");        

            if (session.getAttribute("EMAIL") == null) {
                response.sendRedirect("landing.jsp");
            }
        %>
        <header>
            <nav class="nav-bar">                
                <img id="logo-text" src="${pageContext.request.contextPath}/sources/icons/iceCOOL_NAME_W.png">   
                <form action="Logout" method="post">                                        
                    <input type="submit" class="logout-button" value="LOG OUT">
                </form>
            </nav>
        </header>
                
        <main>                
             <div class="order-holder">
                <h1>Your Orders</h1>
                <hr>
                <div class="user-orders">
                    <table id="order-items">
                        <tr>
                            <th></th>
                            <th>Order Code</th>
                            <th>Item Code</th>
                            <th>Item Name</th>
                            <th>Price</th>
                            <th></th>
                        </tr>
                        <%
                            
                            ResultSet orders = (ResultSet) session.getAttribute("ORDERS");
                            
                            while(orders.next()) {
                        %>
                        <tr>
                            <td><img id="small-thumb" src="${pageContext.request.contextPath}/sources/img/<%=orders.getString("STOCK_IMG")%>"></td>
                            <td><p id="table-text"><%=orders.getString("ORDER_ID")%></p></td>
                            <td><p id="table-text"><%=orders.getString("STOCK_ID")%></p></td>
                            <td><p id="table-text"><%=orders.getString("STOCK_NAME")%></p></td>
                            <td><p id="table-text">₱<%=orders.getString("STOCK_PRICE")%></p></td>
                            <td>
                               
                            </td>
                        </tr>
                        <%
                            }
                            if (orders.isAfterLast()) {
                                orders.beforeFirst();
                            }
                        %>
                    </table>
                    </div>
                   <div class="after-items">
                    <hr class="line-break">
                    <table class="bottom-table">
                        <tr>
                            <td><h3>Total Order Cost:</h3></td>
                            <td><p>₱<%= formatter.format(session.getAttribute("totalCost"))%></p></td>
                        </tr>                            
                        <tr>
                            <form action="Checkout.do" method="post">
                            <td><button class="common-button" type="submit" name="returnShop" value="capitalism">Cancel Order</button></td>
                            <td><button class="special-button" type="submit" name="proceedCheckout" value="brokenow">Proceed and Place Order</button></td>
                            </form>
                        </tr>
                    </table>
                </div>
            </div>
        </main>
                
        <footer>
            <div class="footer-text"><p>Final Academic Project (FAP) for ICS2609.</p></div>
            <div class="footer-text"><p>Made By: Articulo, De Leon, T., Robles, J. - 2CSB</p></div>      
        </footer>
    </body>
</html>