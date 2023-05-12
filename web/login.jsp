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
        <%--%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Expires", "0");        

            if (session.getAttribute("userID") != null) {
                response.sendRedirect("profile.jsp");
            }
        %--%>
        <header>
            <nav class="nav-bar">                
                <a class="toLanding" href="landing.jsp"><img id="logo-text" src="${pageContext.request.contextPath}/sources/icons/iceCOOL_NAME_W.png"></a>   
                <ul class="nav-list">                                        
                    <li class="nav-item"><a href="landing.jsp">HOME</a></li>
                </ul>
            </nav>
        </header>
                
        <main>
            <div class="half-side">
                <img id="site-logo" src="${pageContext.request.contextPath}/sources/icons/iceCOOL_MAIN.png">
                <p class="body-text">The cooling and refreshment's so close, you can almost feel it!</p>
                <p class="body-text">Just enter either your <span class="emphasized">username</span> or <span class="emphasized">email</span>
                    and <span class="emphasized">password</span> in the respective fields provided then accomplish the Captcha to proceed.</p>
            </div>
            <div class="half-side right">
                <div class="form-container">
                    <form action="Login" method="post">                    
                        <h2>USERNAME/EMAIL</h2>
                        <input type="text" id="username" name="username" class="text-entry" maxlength="20" required>
                        <div class="spacer"></div>
                        <h2>PASSWORD</h2>
                        <input type="password" id="password" name="password" class="text-entry" maxlength="20" required>
                        <div class="spacer"></div>
                        <img id="captcha" src="./CustomCaptcha"><br>
                        <h2>Validate Captcha:</h2>
                        <input type="text" name="captchaAnswer" maxlength="20" required>
                        <input type="submit" class="special-button" value="Log In">                                        
                    </form>          
                    <h3 class="error-text"></h3>
                </div>
            </div>                 
        </main>
        
        <footer>
            <div class="footer-text"><p>Final Academic Project (FAP) for ICS2609.</p></div>
            <div class="footer-text"><p>Made By: Articulo, De Leon, T., Robles, J. - 2CSB</p></div>      
        </footer>
    </body>
</html>