<%-- 
    Document   : Account
    Created on : 08-Mar-2019, 12:19:24
    Author     : Martin Wulff
--%>

<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@page import="com.cupcakes.logic.DTO.UserDTO"%>
<%@page import="com.cupcakes.logic.DTO.Invoice"%>
<%
    UserDTO user = (UserDTO) request.getSession().getAttribute("user");
    ArrayList<Invoice> Invoices = (ArrayList<Invoice>) request.getSession().getAttribute("invoices");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file = "/WEB-INF/jspf/header.jspf" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account</title>
    </head>
    <body>
    <center>
        <%@ include file = "/WEB-INF/jspf/menu.jspf" %>"
        <h1>Hello <%= user.getName()%> This is page displays info about your current session! </h1>
        <h2>Your current balance is on <%=user.getBalance()%> </h2>
    
        <p> Current invoices: <p>
            <%  
                
                StringBuilder table = new StringBuilder();
                if(Invoices != null){
                table.append("<table style=\"width:100%\">");
                table.append("<th>Cart Id</tr>"
                        + "<th>C Invoice Balance</tr>"
                        + "<th>Invoice Date<tr>");
                for (Invoice inv : Invoices) {
                    table.append("<tr>"
                            + "<td>" + inv.getCart_id() + "</th>"
                            + "<td>" + inv.getBalance() + "</th>"
                            + "<td>" + inv.getInvoice_date().toString() + "</th>"
                            + "</tr>");

                }
                table.append("</table>");
                }
                else table.append("No current Invoices");
            %>
            <%= table.toString()%>
        <form action="control?origin=addMoneyBank" method="post">
            Enter amount to add: <input type="text" name="added" placeholder="Amount" required> <BR>
            <input type="submit" value="Add Balance"/>
        </form>


    </center>
</body>
</html>
