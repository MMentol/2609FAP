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
            </nav>
        </header>
                
        <main>                
            <div class="parent-holder">                
                <div class="content-holder">
                    <img id="check-picture" src="${pageContext.request.contextPath}/sources/icons/checkout.png">
                    <h2>Thank you for your purchase!</h2>
                </div>   
                <div class="content-holder">
                    <p class="body-text">Your order has been confirmed and is now being processed.</p>
                    <p class="body-text">An invoice containing full details regarding your order can be obtained by pressing the <span class="emphasized">Check Invoice</span> button below.</p>
                    <p class="body-text">You can also get this invoice and manage your other orders through your <span class="emphasized">Profile</span>.</p>
                    <p class="body-text">You may choose to <span class="emphasized">Continue Shopping</span> or <span class="emphasized">Log Out and Exit</span> by selecting the
                        corresponding buttons below.</p>
                    <div class="button-holder">
                        <form action="Invoice" target="_blank" method="post">     
                            <button class="function-button" type="submit" id="order-id" name="order-id" value="<%=session.getAttribute("order-id")%>">Check Invoice</button>
                        </form>
                        <a href="shop.jsp"><button id="shopb" class="function-button">Continue Shopping</button></a>                        
                        <form action="Logout" method="post">                                        
                            <button class="function-button" id="logb" type="submit">Log Out and Exit</button>
                        </form>
                    </div>
                </div>
            </div>
        </main>
                
        <footer>
            <div class="footer-text"><p>Final Academic Project (FAP) for ICS2609.</p></div>
            <div class="footer-text"><p>Made By: Articulo, De Leon, T., Robles, J. - 2CSB</p></div>      
        </footer>        
    </body>
    
</html>

