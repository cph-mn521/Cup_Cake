<%-- 
    Document   : admin
    Created on : Mar 5, 2019, 6:39:11 PM
    Author     : martin
--%>

<%@page import="com.cupcakes.logic.DTO.UserDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Enumeration"%>
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
            int cart_id = 0;
            Invoice userInvoice;
            List<Invoice> invoiceList = cc.fetchInvoiceList();

            Enumeration paramAdmin = request.getParameterNames();

            while (paramAdmin.hasMoreElements())
            {
                String paramName = (String) paramAdmin.nextElement();
                String paramValue = request.getParameter(paramName);

                if (paramName.equals("cart_id"))
                {
                    cart_id = Integer.parseInt(paramValue);
                    if (cart_id > 0)
                    {%>

        <!--map java users invoice to javascript array-->
        <%@ include file = "/WEB-INF/jspf/java2javascriptUserInvoice.jspf" %>
        <%
                    }
                }
            }
        %>

        <script src="./js/makeTables.js"></script>

        <!--map java invoiceList to javascript array-->
        <%@ include file = "/WEB-INF/jspf/java2javascriptAllInvoices.jspf" %>


    </head>

    <body>
        <%@ include file = "/WEB-INF/jspf/menu.jspf" %>
        <h1 style="color:#F5FFFA; text-align: center;">Admin</h1>

        <div class="container">
            <div class="row">
                <div class="col-sm">
                    <table id="tableAllInvoices" ></table>
                </div>

                <script>
//                    header and array comes from java2javascriptAllInvoices.jspf
                    createTable(header, invoiceArray, 'tableAllInvoices');
                </script>


                <div class="col-sm">
                    <%
                        if (cart_id > 0)
                        {
                            List<LineItemsDTO> cartList = cc.fetchCart(cart_id);
                            userInvoice = cc.fetchInvoice(cart_id);
                    %>
                    <h4>Faktura# <%=cart_id%></h4>
                    <h5>Køber: <%=userInvoice.getUsername()%></h5>
                    <h5>Email: <%=userInvoice.getEmail()%></h5>
                    <h5>Balance: <%=userInvoice.getBalance()%></h5>
                    <h5>Købsdato: <%=userInvoice.getInvoice_date()%></h5>

                    <div id="cart_tabel" style="text-align: left; width: 45%;">
                        <%
                            }
                        %>
                    </div>

                    <div class="col-sm">
                        <table id="tableUserInvoice" ></table>
                    </div>
                    <script>
                        createTable(headerAll, userInvoiceArray, 'tableUserInvoice');
                    </script>
                </div>
            </div>
        </div>
    </body>
</html>