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
    </head>
    <body>
        <%
            Controller c = new Controller();
            
            String uname = request.getParameter("username");
            String pw = request.getParameter("password");
            String email = request.getParameter("email");
            
            try{
                c.createUser(uname,pw,pw,email);
            }catch(Exception e){
                
            }

        %>
        
    </body>
</html>
