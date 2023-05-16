
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@page import="model.*" %>--%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Bountiful Basket - Shop</title>      
        <link rel="stylesheet" href="${pageContext.request.contextPath}/sources/shopstyles.css">
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
                <% if (session.getAttribute("userID") != null) { %>
                    <img id="site-logo" src="${pageContext.request.contextPath}/sources/basketlogowhite.png">
                <% }
                else { %>
                    <a class="toLanding" href="landing.jsp"><img id="site-logo" src="${pageContext.request.contextPath}/sources/basketlogowhite.png"></a>
                <%}%>                
                <div class="profile-holder">
                    <img id="user-logo" src="${pageContext.request.contextPath}/sources/user.png">
                    <h1>
                        <% if (session.getAttribute("userID") != null) { %>
                        ${userID}
                        <% } 
                        else { %>
                        Browsing as Guest
                        <%}%>
                    </h1>
                    <% if (session.getAttribute("userID") != null) { %>
                    <form action="UserLogout.do" method="post">                                        
                        <input type="submit" class="header-button" value="Log Out">                    
                    </form>
                    <% } %>
                </div>
            </nav>
        </header>
            
        <main>
            <div class="side-wrapper">
                <div class="left-side">
                    <h1>Our Products</h1>
                    <hr class="line-break">
                    <div class="stock-holder">                                                
                        
                        <%                        
//                            ArrayList<ShopItem> stockList = new ShopInitializer().getStock();
//                            ArrayList<ShopItem> currentCart = (ArrayList<ShopItem>) session.getAttribute("userCart");
//                            CartUtils utility = new CartUtils();
//                            for (int i = 0; i < stockList.size(); i++) {
//                                ShopItem currentItem = stockList.get(i);
                        %>
                        <div class="shop-item">
                            <img class="thumbnail" src="${pageContext.request.contextPath}/sources/img/<%=currentItem.getPic()%>">
                            <h3><%out.print(currentItem.getName());%></h3>
                            <p>₱<%out.print(currentItem.getPrice());%></p>
                            <%
//                                if ( (session.getAttribute("userID") != null) && !utility.inList(currentCart, currentItem.getID()) ){
                            %>
                            <form action="CartProcess.do" method="post">         
                                <button class="addCartButton" type="submit" name="selectedItem" value="<%=currentItem.getID()%>">Add to Cart</button>             
                            </form>
                            <%  }
//                                else if ((session.getAttribute("userID") != null) && utility.inList(currentCart, currentItem.getID())) { %>
                            <button class="addCartButton" disabled>In Cart</button>
                            <%  } %>
                        </div>
                        <%  } %>
                        
                    </div>                       
                </div>
                <% if (session.getAttribute("userID") != null) { %>
               
                <% }%>
            </div>
        </main>
                
        <footer>
            <div class="footer-text"><p>This website was created in fulfillment of the course requirements for ICS2608.</p></div>
            <div class="footer-text"><p>Made By: Articulo, De Leon, T., Macuja, Robles, J. - 2CSB</p></div>      
        </footer>
                
    </body> 
</html>