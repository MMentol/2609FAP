
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Bountiful Basket - Shop</title>      
        <link rel="stylesheet" href="${pageContext.request.contextPath}/sources/styles/regstyles.css">
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
            <div class="side-wrapper">
                <div class="left-side">
                    <h1>Our Products</h1>
                    <hr class="line-break">
                   <div class="stock-holder">                                                
                        
                        <%      
                           
                          HashMap<String,ShopItem> stockList= (HashMap)session.getAttribute("STOCK");
                           
                            
                        //    for (int i = 0; i < stockList.size(); i++) {
                          //      ShopItem currentItem = stockList.get(i);
                                
                            for (Map.Entry<String,ShopItem> i : stockList.entrySet()) {
                           
                                 ShopItem currentItem = i.getValue();
                                 
                        %>
                        <div class="shop-item">
                            <img class="thumbnail" src="${pageContext.request.contextPath}/sources/img/<%=currentItem.getPic()%>">
                            <h3><%out.print(currentItem.getName());%></h3>
                            <p>₱<%out.print(currentItem.getPrice());%></p>
                           
                            <form method="post">         
                                <button class="addCartButton" type="submit" name="selectedItem" value="<%=currentItem.getID()%>"><img class="thumbnail" src="${pageContext.request.contextPath}/sources/img/<%=currentItem.getPic()%>"></button>             
                            </form>
                            
                        </div>
                                <%  
                            } %>
                        
                    </div>                       
                </div>
               
            </div>
        </main>
                
        <footer>
           <div class="footer-text"><p>Final Academic Project (FAP) for ICS2609.</p></div>
            <div class="footer-text"><p>Made By: Articulo, De Leon, T., Robles, J. - 2CSB</p></div>      
        </footer>
                
    </body> 
</html>