<%-- 
    Document   : validate
    Created on : 06-Mar-2019, 12:46:07
    Author     : nille
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="com.cupcakes.presentation.Authenticator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <%@ include file = "/WEB-INF/jspf/header.jspf" %>
    </head>
    <body>
        <%@ include file = "/WEB-INF/jspf/menu.jspf" %>"
        
        <%
            HttpSession Session = request.getSession();
            String uname = request.getParameter("username");   
            String pw = request.getParameter("password");
            
            try{
                
                String status = Authenticator.authenticate(uname,pw);
                    
                       if(status.equals("success")){
                           Session.setAttribute("login", "True");
                       }
                
            }catch(SQLException e){
                out.print("Wrong info.");
            }

        %>
    </body>
</html>
