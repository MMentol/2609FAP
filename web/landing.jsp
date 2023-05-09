<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iceCOOL.co - Home</title>      
        <link rel="stylesheet" href="${pageContext.request.contextPath}/sources/styles/homestyles.css">
    </head>
    
    <body>        
        <header>
            <nav class="nav-bar">                
                <a class="toLanding" href="landing.jsp">temporary</a>               
            </nav>
        </header>
            
        <main>
            <div class="main-container">
                <div class="site-greetings">
                    <h2>Welcome to...</h2>
                    <div class="logo-holder">                        
                        <div class="logo-text-holder"><h1>iceCOOL.co</h1></div>
                    </div>
                </div>    
                <br>
                <div class="button-holder">
                    <a class="toLogin" href="login.jsp"><button class="common-button">User Log In</button></a>
                    <a class="toShop" href="shop.jsp"><button class="common-button">Browse Shop</button></a>
                    <!-->href="${pageContext.request.contextPath}/sources/documentation.pdf"<-->
                    <a class="toShop"  target="_blank"><button class="special-button common-button">Check Documentation</button></a>
                </div>     
                <hr class="line-break">
                <div class="site-information">
                    <h2>placeholder</h2>
                    <p class="body-text"><span class="emphasized">iceCOOL.co</span> is an aircon shop.</p>
                    <p class="body-text">By shopping at <span class="emphasized">iceCOOL.co</span>, u can get aircon</p>
                    <p class="body-text">We hope you have a wonderful time exploring our large selection of aircons!</p>
                </div>
            </div>
        </main>
                
        <footer>
            <div class="footer-text"><p>This website was created in fulfillment of the course requirements for ICS2609.</p></div>
            <div class="footer-text"><p>Made By: Articulo, De Leon, T., Robles, J. - 2CSB</p></div>      
        </footer>
                
    </body>
    
</html>