<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iceCOOL.co - Register</title>      
        <link rel="stylesheet" href="${pageContext.request.contextPath}/sources/styles/regstyles.css">
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
                <p class="body-text">Are you prepared to beat the heat? Read closely and follow along then you'll be ready in no time!</p>                
                <p class="body-text">To start, select a unique <span class="emphasized">email</span> and a super secret and super secure 
                    <span class="emphasized">password</span> which will help us identify who you are whenever you log into our website.
                    Make sure you NEVER tell anyone your password. Don't worry, we won't know it too.</p>
                <p class="body-text">After that, choose a very cool and unique <span class="emphasized">username</span> which we will use to refer to you 
                    whenever you use our services. Aside from that, let us know your <span class="emphasized">address</span> so that we know where to send 
                    your wonderful <span class="emphasized">iceCOOL.co</span> products whenever you place an order!</p>
                <p class="body-text">Finally, answer the given Captcha so that we know you're an actual human and not a robot trying to pose as one.</p>
                
            </div>
            <div class="half-side">
                <div class="form-container">                    
                    <form action="Register" method="post">                           
                        <div class="field">
                            <h2>Email</h2>
                            <input type="text" id="username" name="email" class="text-entry" maxlength="20" required>
                        </div>
                        <div class="field">
                            <h2>Password</h2>
                            <input type="password" id="password" name="password" class="text-entry" maxlength="20" required>
                        </div>
                        <div class="field">
                            <h2>Username</h2>
                            <input type="text" id="username" name="username" class="text-entry" maxlength="20" required>
                        </div>
                        <div class="field">
                            <h2>Address</h2>
                            <input type="text" id="username" name="address" class="text-entry" maxlength="100" required>
                        </div>
                        <img id="captcha" src="./CustomCaptcha"><br>
                        <div class="field">
                            <h2>Captcha:</h2>
                            <input type="text" class="text-entry" id="captchaAnswer" name="captchaAnswer" maxlength="5" required>
                        </div>
                        <input type="submit" class="special-button" value="Register">                                        
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
