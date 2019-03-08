<%-- 
    Document   : validate
    Created on : 06-Mar-2019, 12:46:07
    Author     : nille
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="com.cupcakes.presentation.Authenticator"%>
<%@page import="com.cupcakes.logic.Controller"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <%@ include file = "/WEB-INF/jspf/header.jspf" %>
    </head>
    <body>
        <%@ include file = "/WEB-INF/jspf/menu.jspf" %>"
            <center> 
        <%
            Controller c = new Controller();
            HttpSession Session = request.getSession();
            String uname = request.getParameter("username");   
            String pw = request.getParameter("password");
                
                String status = Authenticator.authenticate(uname,pw);
                    
                       if(status.equals("success")){
                           Session.setAttribute("login", "True");
                           Session.setAttribute("user", c.fetchUser(uname));
                         %>
    
            <h4>Login Successful.</h4>
    
                         <%
                       }else{   

                       try{

                        c.fetchUser(uname);
}catch(SQLException e){
%>
<h4>No user in database with that username.</hr4
<p>Would you like to create a user?</p>
<button class="buttonBlue" onclick="window.location.href = 'control?origin=registration'">Create User</button><br>
<p>Try again?</p>
<button class="buttonGreen" onclick="window.location.href = 'control?origin=login'">Login</button>
<%
    
}
                        %>            
          <h4>Login failed</h4>                 
          <%}
          %>
        </center>
    </body>
</html>
