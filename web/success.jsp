<%@page import="java.text.NumberFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Successful!</title>      
        <link rel="stylesheet" href="${pageContext.request.contextPath}/sources/succstyles.css">
    </head>
    
    <body>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Expires", "0");     

            if (session.getAttribute("userID") == null) {
                response.sendRedirect("landing.jsp");
            }
            NumberFormat formatter = NumberFormat.getInstance();
            formatter.setMaximumFractionDigits(2);
            formatter.setMinimumFractionDigits(2);            
        %>        
        <header>
            <nav class="nav-bar">                
                <img id="site-logo" src="${pageContext.request.contextPath}/sources/basketlogowhite.png">              
            </nav>
        </header>
        
        <main>
            <div class="main-wrapper">
                <h2>Your bountiful basket is on its way!</h2>
                <img id="site-logo" src="${pageContext.request.contextPath}/sources/delivery.png">
                <p class="body-text">Thank you for shopping at the <span class="emphasized">Bountiful Basket</span>! Your order valued at <span class="emphasized">₱<%out.print(formatter.format(session.getAttribute("totalCost")));%></span> has been placed successfully. We hope you enjoy the fruits of our hardworking farmers' labor!</p>
                <p class="body-text">You can choose to <span class="emphasized">Continue Shopping</span> or <span class="emphasized">Exit and Log Out</span> using the buttons below.</p>
                <div class="button-holder">
                    <form action="Order.done" method="post">
                        <button class="common-button" type="submit" name="continueShop" value="lemmeback">Continue Shopping</button>
                        <button class="special-button" type="submit" name="logoutExit" value="broke">Log Out and Exit</button>
                    </form>
                </div>
            </div>
        </main>
                
        <footer>
            <div class="footer-text"><p>This website was created in fulfillment of the course requirements for ICS2608.</p></div>
            <div class="footer-text"><p>Made By: Articulo, De Leon, T., Macuja, Robles, J. - 2CSB</p></div>      
        </footer>        
    </body>
    
</html>
