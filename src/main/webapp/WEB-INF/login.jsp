<%-- 
    Document   : login
    Created on : Feb 28, 2019, 11:59:18 AM
    Author     : martin
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Login</title>
        <%@ include file = "jspf/header.jspf" %>
    </head>
    <body>
        <%@ include file = "jspf/menu.jspf" %>"
        <center>
            <form action="LoginController" method="post">
                Enter username : <input type="text" name="username"> <BR>
                Enter password : <input type="password" name="password"> <BR>
                <input type="submit" />
            </form>
        </center>
    </body>
</html>