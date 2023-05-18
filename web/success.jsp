<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iceCOOL.co - Order Placed!</title>      
        <link rel="stylesheet" href="${pageContext.request.contextPath}/sources/styles/successstyles.css">
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
            <div class="main-wrapper">
                <img id="site-logo" src="${pageContext.request.contextPath}/sources/delivery.png">
                <h2>Thank you for your purchase!</h2>
                <p class="body-text">Your order has been confirmed and is being processed.</p>
                <p class="body-text">To see a detailed summary of your order, you may view the order invoice by pressing the “Check Invoice” button below.</p>
                <p class="body-text">You may choose to place another order by shopping again or log out and exit by choosing either of the buttons below.</p>
                <div class="button-holder">
                    <form action="Order.done" method="post">
                        <button class="check-button" type="submit" name="checkInvoice" value="gimmemonez">Check Invoice</button>
                        <button class="common-button" type="submit" name="continueShop" value="lemmeback">Shop Again</button>
                        <button class="special-button" type="submit" name="logoutExit" value="broke">Log Out and Exit</button>
                    </form>
                </div>
            </div>
        </main>
                
        <footer>
            <div class="footer-text"><p>Final Academic Project (FAP) for ICS2609.</p></div>
            <div class="footer-text"><p>Made By: Articulo, De Leon, T., Robles, J. - 2CSB</p></div>      
        </footer>        
    </body>
    
</html>

