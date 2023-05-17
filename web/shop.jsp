<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="model.ShopItem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iceCOOL.co - Shop</title>      
        <link rel="stylesheet" href="${pageContext.request.contextPath}/sources/styles/shopstyles.css">
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
            <h1>shop page</h1><br>
            <h3>USERNAME: </h3><p>${USERNAME}</p>
            <div class="left-side">
                <h1>Our Products</h1>
                <hr class="line-break">
                <div class="stock-holder">                                                

                    <%
                        HashMap<String, ShopItem> stockList = (HashMap) session.getAttribute("STOCK");

                        for (Map.Entry<String, ShopItem> i : stockList.entrySet()) {
                            ShopItem currentItem = i.getValue();

                    %>
                    <div class="shop-item">                        
                        <%-- to do: add action to handle appropriate item forwarding to load checkout page correctly --%>
                        <form method="post">         
                            <button class="addCartButton" type="submit" name="selectedItem" value="<%=currentItem.getID()%>"><img class="thumbnail" src="${pageContext.request.contextPath}/sources/img/<%=currentItem.getPic()%>"></button>             
                        </form>

                    </div>
                    <% }%>

                </div>                       
            </div>
        </main>
                
        <footer>
            <div class="footer-text"><p>Final Academic Project (FAP) for ICS2609.</p></div>
            <div class="footer-text"><p>Made By: Articulo, De Leon, T., Robles, J. - 2CSB</p></div>      
        </footer>
    </body>
</html>
