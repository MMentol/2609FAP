<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iceCOOL.co - Profile</title>      
        <link rel="stylesheet" href="${pageContext.request.contextPath}/sources/styles/profstyles.css">
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
            <div class="info-container">
                <h1>User Profile</h1>
                <hr>
                <div id="info-holder">
                    <div class="in-liner left">
                        <img id="user-img" src="${pageContext.request.contextPath}/sources/icons/user.png">
                    </div>
                    <div class="in-liner right">
                        <div id="text-holder">
                            <h4>Username </h4>
                            <p id="value-text">${USERNAME}</p>
                        </div>
                        <div id="text-holder">
                            <h4>Address </h4>
                            <p id="value-text">${ADDRESS}</p>
                        </div>
                    </div>
                </div>
            </div>
                        
            <div class="order-holder">
                <h1>Your Orders</h1>
                <hr>
            </div>
           
           <a href="shop.jsp"><button class="shop-button">Go to Shop</button></a>
        </main>
                
        <footer>
            <div class="footer-text"><p>Final Academic Project (FAP) for ICS2609.</p></div>
            <div class="footer-text"><p>Made By: Articulo, De Leon, T., Robles, J. - 2CSB</p></div>      
        </footer>
    </body>
</html>
