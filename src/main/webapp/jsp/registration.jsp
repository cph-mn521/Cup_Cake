<%-- 
    Document   : login
    Created on : Feb 28, 2019, 11:59:18 AM
    Author     : martin
--%>
<html>
    <head>
        <%@ include file = "/WEB-INF/jspf/header.jspf" %>
    </head>
    <body>
        <%@ include file = "/WEB-INF/jspf/menu.jspf" %>
        <br>
    <center>
        <fieldset>
            <legend>Registrering:</legend>
            <form action="registration" method="post">
                Indtast brugernavn: <input type="text" name="username"> <BR>
                Indtast kodeord : <input type="password" name="password"> <BR>
                Indtast email : <input type="email" name="email"> <BR>
                <input type="submit" />
            </form>
        </fieldset>
    </center>
</body>
</html>
