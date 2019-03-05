<%-- 
    Document   : admin
    Created on : Mar 5, 2019, 6:39:11 PM
    Author     : martin
--%>

<%@page import="com.cupcakes.logic.DTO.Invoice"%>
<%@page import="com.cupcakes.logic.DTO.LineItemsDTO"%>
<%@page import="com.cupcakes.logic.DTO.BottomDTO"%>
<%@page import="com.cupcakes.logic.DTO.ToppingsDTO"%>
<%@page import="com.cupcakes.logic.Controller"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file = "/WEB-INF/jspf/header.jspf" %>
        <%
            Controller cc = new Controller();
            int invoice_id =0;
            invoice_id = Integer.parseInt(request.getParameter("invoice_id"));
        %>
    </head>
    <body>
        <%@ include file = "/WEB-INF/jspf/menu.jspf" %>
        <h1 style="color:#F5FFFA; text-align: center;">Admin</h1>
        <div class="container">
            <div class="row">
                <div class="col-sm">
                    <div id="cart_tabel" style="text-align: left; width: 45%;">
                        <table class="table table-striped">
                            <thead  style="color:#F5FFFA; text-align: center;">
                                <tr>
                                    <th scope="col">Faktura#</th>
                                    <th scope="col">Dato</th>
                                </tr>
                            </thead>
                            <tbody  style="color: black; text-align: center;">
                                <%                    int index = 0;
                                    for (Invoice i : cc.fetchInvoiceList()) {
                                %>
                                <tr>
                                    <th scope="row"><a href="control?origin=admin&invoice_id=<%=i.getInvoice_id()%>"><%=i.getInvoice_id()%></a></th>
                                    <td><a href="control?origin=admin&invoice_id=<%=i.getInvoice_id()%>"><%=i.getInvoice_date()%></a></td>
                                </tr>
                                <%}
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="col-sm">
                    <%
                        if(invoice_id>0){
                            cc.fetchCart().getLineItems();
                        }
                        %>
                </div>
            </div>
        </div>
    </body>
</html>