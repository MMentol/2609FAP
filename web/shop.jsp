<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iceCOOL.co - Shop</title>      
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
                <% if (session.getAttribute("EMAIL") != null) { %>
                <form action="Logout" method="post">                                        
                    <input type="submit" class="header-button" value="HOME">                    
                </form>
                <% } 
                else { %>
                <ul class="nav-list">                                        
                    <li class="nav-item"><a href="landing.jsp">HOME</a></li>
                </ul>
                <%}%>
            </nav>
        </header>
                
        <main>                
            <h1>shop page</h1><br>
            <h3>USERNAME: </h3><p>${USERNAME}</p>
        </main>
                
        <footer>
            <div class="footer-text"><p>Final Academic Project (FAP) for ICS2609.</p></div>
            <div class="footer-text"><p>Made By: Articulo, De Leon, T., Robles, J. - 2CSB</p></div>      
        </footer>
    </body>
</html>
