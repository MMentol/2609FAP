<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Database Error</title>      
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
                    <img id="error-picture" src="${pageContext.request.contextPath}/sources/icons/move.png">
                </div>   
                <div class="content-holder">
                    <h1 class="error-type">A database error occurred.</h1>
                    <p class="error-info">A problem was encountered when trying to connect to, query, or update the application's database or load a page requiring content from the application's database.</p>
                    <p class="error-info">Please ensure that you are connected to the database and it is configured correctly. The database should be named "iceCoolDB" (case-sensitive) 
                        and should have the tables listed in the project README file.</p>
                    <p class="error-info">After following the steps above, you can reset the application by pressing the button below.</p>
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
