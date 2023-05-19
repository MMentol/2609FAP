<%@page import="model.ShopItem"%>
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
            <div class="parent-holder">
                <%
                    ShopItem loadedItem = (ShopItem) session.getAttribute("chosen-item");
                %>
                <div class="content-holder">
                    <img id="product-picture" src="${pageContext.request.contextPath}/sources/img/<%= loadedItem.getPic()%>">
                </div>   
                <div class="content-holder">
                    <h1><%= loadedItem.getName()%></h1>
                    <h3>₱<%= loadedItem.getPrice()%></h3>
                    <%--TO DO: link to joseph's adding servlet idk --%>
                    <form action="" method="post">
                        <fieldset>
                            <legend>Payment Method</legend>
                            <input type="radio" id="codb" name="payment" value="COD">
                            <label for="codb">Cash on Delivery</label>
                            <input type="radio" id="creditb" name="payment" value="Credit">
                            <label for="creditb">Credit Card</label>
                        </fieldset>
                        <fieldset>
                            <legend>Monthly Installment</legend>
                            <input type="radio" id="ye" name="install" value="true">
                            <label for="ye">Yes</label>
                            <input type="radio" id="nah" name="install" value="false">
                            <label for="nah">No</label>
                        </fieldset>
                        <button class="proceedButton" type="submit">Place Order</button>
                    </form>
                </div>
            </div>
        </main>
                
        <footer>
            <div class="footer-text"><p>Final Academic Project (FAP) for ICS2609.</p></div>
            <div class="footer-text"><p>Made By: Articulo, De Leon, T., Robles, J. - 2CSB</p></div>      
        </footer>
    </body>
</html>