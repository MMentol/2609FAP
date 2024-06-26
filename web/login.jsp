<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iceCOOL.co - Login</title>      
        <link rel="stylesheet" href="${pageContext.request.contextPath}/sources/styles/logstyles.css">
        <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/sources/icons/icfav.png">
    </head>
    <body>        
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Expires", "0");        

            if (session.getAttribute("USERNAME") != null) {
                response.sendRedirect("profile.jsp");
            }
        %>
        <header>
            <nav class="nav-bar">                
                <img id="logo-text" src="${pageContext.request.contextPath}/sources/icons/iceCOOL_NAME_W.png">   
                <% if (session.getAttribute("message") != null) { %>
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
            <div class="half-side">
                <img id="site-logo" src="${pageContext.request.contextPath}/sources/icons/iceCOOL_MAIN.png">
                <p class="body-text">The cooling and refreshment's so close, you can almost feel it!</p>
                <p class="body-text">Just enter either your <span class="emphasized">username</span> or <span class="emphasized">email</span>
                    and <span class="emphasized">password</span> in the respective fields provided then accomplish the Captcha to proceed.</p>
            </div>
            <div class="half-side">
                <div class="form-container">                    
                    <form action="Login" method="post">                           
                        <div class="field">
                            <h2>Username or Email</h2>
                            <input type="text" id="username" name="username" class="text-entry" maxlength="20" required>
                        </div>
                        <div class="field">
                            <h2>Password</h2>
                            <input type="password" id="password" name="password" class="text-entry" maxlength="20" required>
                        </div>
                        <img id="captcha" src="./CustomCaptcha"><br>
                        <div class="field">
                            <h2>Captcha:</h2>
                            <input type="text" class="text-entry" id="captchaAnswer" name="captchaAnswer" maxlength="5" required>
                        </div>
                        <input type="submit" class="special-button" value="Log In">                                        
                    </form>      
                </div>
                <%
                if (session.getAttribute("message") != null) {
                %>
                    <h3 class="error-text">${message}</h3>
                <%
                }
                %>
            </div>                 
        </main>
        
        <footer>
            <div class="footer-text"><p>Final Academic Project (FAP) for ICS2609.</p></div>
            <div class="footer-text"><p>Made By: Articulo, De Leon, T., Robles, J. - 2CSB</p></div>      
        </footer>
    </body>
</html>