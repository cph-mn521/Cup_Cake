<%-- 
    Document   : regValidator
    Created on : 06-Mar-2019, 13:46:02
    Author     : nille
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cupcakes.logic.Controller"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User</title>
        <%@ include file = "/WEB-INF/jspf/header.jspf" %>
    </head>
    <body>
        <%@ include file = "/WEB-INF/jspf/menu.jspf" %>"
        <center>           
        <%
            Controller c = new Controller();
            String uname = request.getParameter("username");
            String pw = request.getParameter("password");
            String email = request.getParameter("email");
            
            try{
                c.cuser(uname,pw,email);
                
            }catch(Exception e){
                out.print(e.getMessage());
                out.print(e.getStackTrace());
                out.print( '\n' + uname + pw + email);
                %>
                <p>REEEEEEEEEEEEEEe</p>
                
                <%
            }
        %>
        </center>
    </body>
</html>
