<%-- 
    Document   : pay
    Created on : 08-Mar-2019, 12:31:13
    Author     : nille
--%>

<%@page import="com.cupcakes.logic.Controller"%>
<%@page import="com.cupcakes.logic.DTO.LineItemsDTO"%>
<%@page import="com.cupcakes.logic.DTO.ShoppingCart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file = "/WEB-INF/jspf/header.jspf" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CupCake Confirmation</title>
    </head>
    <body>
        <%@ include file = "/WEB-INF/jspf/menu.jspf" %>
    <center>
        
        <%
          ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
          UserDTO user = (UserDTO) session.getAttribute("user");
          Controller c = new Controller();
          
          float p = 0;
          
          
          for(LineItemsDTO l: cart.getLineItems()){
              p += l.getCupcake().getTotalPrice() * l.getQuantity();
          }
          if( p == 0) return;
          
          if(p > user.getBalance()){
              %>
              <h1>Insufficient funds</h1>
              <%
          }else{
          //Doesn't actuallyy do anything.

            %>
            <h1>Order placed. <br>Please pay at the counter.</h1>
              
              <%
            }
          
         
           %>
        
        
                <%
            


            %>
            
        
    </center>
    </body>
</html>
