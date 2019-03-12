<%-- 
    Document   : admin
    Created on : Mar 5, 2019, 6:39:11 PM
    Author     : martin
--%>

<%@page import="com.cupcakes.logic.DTO.LineItemsDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file = "/WEB-INF/jspf/header.jspf" %>

        <!--java init stuff -->
        <%@ include file = "/WEB-INF/jspf/adminJavaHeader.jspf" %>

        <!--importing table creating and sorting javascript-->
        <script src="./js/makeTables.js"></script>

        <!--map java invoiceList to javascript array-->
        <%@ include file = "/WEB-INF/jspf/java2javascriptAllInvoices.jspf" %>
    </head>

    <body>
        <!--importing top of page and menu buttons-->
        <%@ include file = "/WEB-INF/jspf/menu.jspf" %>
        <h1 style="color:#F5FFFA; text-align: center;">Admin</h1>

        <div class="container">
            <div class="row">
                <div class="col-sm">
                    <!--Inserting place for user invoice table-->
                    <table id="tableAllInvoices" ></table>
                </div>
                <div class="col-sm">
                    <!--fectching user info.....and yes it has to be here!-->
                    <%
                        if (invoice_id > 0)
                        {
                    %>
                    <h4>Faktura# <%=invoice_id%></h4>
                    <h5>Køber: <%=userInvoice.getUsername()%></h5>
                    <h5>Email: <%=userInvoice.getEmail()%></h5>
                    <h5>Balance: <%=userInvoice.getBalance()%></h5>
                    <h5>Købsdato: <%=userInvoice.getInvoice_date()%></h5>
                    <%
                        }
                    %>

                    <!--Inserting place for user invoice table-->
                    <table id="tableUserInvoice" ></table>

                    <!--creating user invoice table...arrays comes from header jspf file-->
                    <!--must be at the bottom after html tags has been plaves-->
                </div>
            </div>
        </div>
        <script>
            createTable(header, invoiceArray, 'tableAllInvoices');
            createTable(headerAll, userInvoiceArray, 'tableUserInvoice');
        </script>
    </body>
</html>