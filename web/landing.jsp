<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iceCOOL.co - Home</title>      
        <link rel="stylesheet" href="${pageContext.request.contextPath}/sources/styles/homestyles.css">
        <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/sources/icons/icfav.png">
    </head>
    
    <body>        
        <header>
            <nav class="nav-bar">                
                <a class="toLanding" href="landing.jsp"><img id="logo-text" src="${pageContext.request.contextPath}/sources/icons/iceCOOL_NAME_W.png"></a>   
                <ul class="nav-list">                                        
                    <li class="nav-item"><a href="#site-information-a">INFO</a></li>
                    <li class="nav-item"><a href="#product-offerings-a">PRODUCTS</a></li>
                    <li class="nav-item"><a href="#contact-info-a">CONTACT</a></li>
                </ul>
            </nav>
        </header>
            
        <main>
            <div class="main-container">
                <div class="site-greetings">
                    <div class="logo-holder"> 
                        <img id="site-logo" src="${pageContext.request.contextPath}/sources/icons/iceCOOL_MAIN.png">
                        <img id="logo-text" src="${pageContext.request.contextPath}/sources/icons/iceCOOL_NAME_B.png">
                        <p class="tagline">Calm down and cool down.</p>
                    </div>
                </div>    
                <br>
                <div class="button-holder">
                    <a class="toLogin" href="login.jsp"><button class="common-button">Log into an Account</button></a>
                    <a class="toShop" href="register.jsp"><button class="common-button">Sign Up for an Account</button></a>
                    <%-->href="${pageContext.request.contextPath}/sources/documentation.pdf"<--%>
                    <a class="toShop"  target="_blank"><button class="special-button common-button">Check Documentation</button></a>
                </div>     
                <hr class="line-break">
                <div id="site-information-a"></div>
                <div class="site-information" id="site-information">
                    <h2 class="header-text">What is iceCOOL.co?</h2>
                    <p class="body-text"><span class="emphasized">iceCOOL.co</span> (i-se-kool dot koh) is a small start-up resale company founded by a group of friends 
                        offering premium cooling and air-conditioning electronic products of the same quality as the manufacturers themselves but at a lower price!</p>
                    <br>
                    <p class="body-text"><span class="emphasized">iceCOOL.co</span> prides itself with its user-friendly service which provides benefits such as free 
                        shipping, delivery, and in-house installation along with credit payment options to all our customers, available right from the comfort of their homes 
                        without the need to go to any physical store whatsoever.</p>
                    <br>
                    <p class="body-text">Currently, we only have a limited stock of air-conditioning units to help beat the heat. However, we will be able to expand our 
                        inventory and provide even more and even BETTER products with your continued support and patronage!</p>
                </div>
                <hr class="line-break">
                <div id="product-offerings-a"></div>
                <div class="product-offerings" id="product-offerings">
                    <h2 class="header-text">What does iceCOOL.co have?</h2>                    
                    <div class="gallery-container">
                        <img id="product-sample" alt="an air conditioning unit." src="${pageContext.request.contextPath}/sources/img/samsung.webp">
                        <img id="product-sample" alt="another air conditioning unit." src="${pageContext.request.contextPath}/sources/img/panasonic1.png">
                        <img id="product-sample" alt="yet another air conditioning unit." src="${pageContext.request.contextPath}/sources/img/lg.webp">
                        <img id="product-sample" alt="it's... another air conditioning unit." src="${pageContext.request.contextPath}/sources/img/fuji.jpg">
                        <img id="product-sample" alt="WAIT... it's another air conditioning unit." src="${pageContext.request.contextPath}/sources/img/carrier.webp">
                        <img id="product-sample" alt="NO WAY... it's ANOTHER air conditioning unit..." src="${pageContext.request.contextPath}/sources/img/panasonic2.jpg">
                    </div>            
                    <p class="body-text showcase">Here are some of the available products we currently have on stock!</p>
                </div>
                <hr class="line-break">
                <div id="contact-info-a"></div>
                <div class="contact-info" id="contact-info">
                    <h2 class="header-text">How do you reach iceCOOL.co?</h2>
                    <p class="body-text">You can contact us by calling <span class="emphasized"><%= pageContext.getServletContext().getInitParameter("generalPhone").toString() %></span> or by sending us an email 
                        at <span class="emphasized"><%= pageContext.getServletContext().getInitParameter("generalMail").toString() %></span> for any general concerns or queries you have about <span class="emphasized">iceCOOL.co</span>.</p>
                    <br>
                    <p class="body-text">For any concerns with your <span class="emphasized">iceCOOL.co</span> products, please call our customer support hotline at <span class="emphasized"><%= pageContext.getServletContext().getInitParameter("supportPhone").toString() %></span> 
                        or send an email to <span class="emphasized"><%= pageContext.getServletContext().getInitParameter("supportMail").toString() %></span> to get in touch with a service representative.</p>
                </div>
            </div>
        </main>
                
        <footer>
            <div class="footer-text"><p>Final Academic Project (FAP) for ICS2609.</p></div>
            <div class="footer-text"><p>Made By: Articulo, De Leon, T., Robles, J. - 2CSB</p></div>      
        </footer>
                
    </body>
    
</html>