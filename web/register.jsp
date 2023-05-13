
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>iceCOOL.co - Registration</title>      
        <link rel="stylesheet" href="${pageContext.request.contextPath}/sources/loginstyles.css">
    </head>
    
    <body>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Expires", "0");        

            if (session.getAttribute("userID") != null) {
                response.sendRedirect("shop.jsp");
            }
        %>
        <header>
            <nav class="nav-bar">                
                <a class="toLanding" href="landing.jsp"><img id="site-logo" src="${pageContext.request.contextPath}/sources/iceCOOL_MAIN.png"></a>
                 <ul class="nav-list">                                        
                    <li class="nav-item"><a href="#home">HOME</a></li>
                    
                </ul>
            </nav>
        </header>
            
        <main>
            <div class="half-side">
                <img id="site-logo" src="${pageContext.request.contextPath}/sources/iceCOOL_MAIN.png">
                <p class="body-text">You’re only one step away from accessing the wonderful stores of air conditioners to beat the summer heat!</p>
                <p class="body-text">Create an account by selecting a <span class="emphasized">username</span> and <span class="emphasized">password</span> then accomplish the <span class="emphasized"> captcha</span> to proceed.</p>
            </div>
            <hr class="line-break">
            <div class="half-side">
                <form action="UserLogin.do" method="post">                    
                    <h2>EMAIL</h2>
                    <input type="text" id="email" name="email" class="text-entry" maxlength="20" required>
                    <div></div>
                    <h2>USERNAME</h2>
                    <input type="text" id="username" name="username" class="text-entry" maxlength="20" required>
                    <div class="spacer"></div>
                    <h2>PASSWORD</h2>
                    <input type="password" id="password" name="password" class="text-entry" maxlength="20" required>
                    <div class="spacer"></div>
                    <h2>ADDRESS</h2>
                    <input type="text" id="address" name="address" class="text-entry" maxlength="20" required>
                    <div></div>
                    <img src="SimpleCaptcha" alt="Captcha"/><br>
                    <h1>Enter Captcha:</h1>
                    <input type="text" name="captcha-input" required>
                    <div class="spacer"></div>
                    <input type="submit" class="special-button" value="Sign Up">                    
                    <h3 class="error-text"></h3>
                </form>                
            </div>                 
        </main>
                
        <footer>
            <div class="footer-text"><p>This website was created in fulfillment of the course requirements for ICS2609.</p></div>
            <div class="footer-text"><p>Made By: Articulo, De Leon, T., Robles, J. - 2CSB</p></div>      
        </footer>
                
    </body>    
</html>

       
