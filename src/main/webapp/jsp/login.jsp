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
         <%@ include file = "/WEB-INF/jspf/header.jspf" %>
    </head>
    <body>
        <%@ include file = "/WEB-INF/jspf/menu.jspf" %>"
       
        <center>
            <form action="control?origin=validate" method="post">
                Enter username : <input type="text" name="username" placeholder="Username" required> <BR>
                Enter password : <input type="password" name="password" placeholder="Password" required> <BR>
                <input type="submit" value="Login"/>
            </form>
        </center>
        
    </body>
</html>
