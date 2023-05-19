<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>500 Error</title>      
        <link rel="stylesheet" href="${pageContext.request.contextPath}/sources/styles/errorstyles.css">
        <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/sources/icons/icfav.png">
    </head>
    <body>
        <header>
            <nav class="nav-bar">                
                <img id="logo-text" src="${pageContext.request.contextPath}/sources/icons/iceCOOL_NAME_W.png">                   
            </nav>
        </header>
        <main>
            <div class="parent-holder">
                <div class="content-holder">
                    <img id="error-picture" src="${pageContext.request.contextPath}/sources/icons/server.png">
                </div>   
                <div class="content-holder">
                    <h1 class="error-type">500... That's an error!</h1>
                    <p class="error-info">An error occurred within the server.</p>
                    <p class="error-info">This may have been caused by an unaccounted programming error the developers were not aware of... or maybe the session died.</p>
                    <p class="error-info">In any case, you can recover from this error and reset by pressing the button below.</p>
                    <form action="Logout" method="post">                                        
                        <input type="submit" class="reset-button" value="Return to Home">
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
