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
                }
            }

        %>

        <!--javascript syntax error handler-->
        <!--<script src="./js/syntaxErrorHandler.js"></script>-->
        <script src="./js/makeTables.js"></script>

    </head>




    <body>
        <!--Fill javascript array from Java-->
        <script>


//            document.getElementById("demo").innerHTML = createTable(invoiceArray);

//            a.sort(sortFunction);            
        </script>


        <%@ include file = "/WEB-INF/jspf/menu.jspf" %>
        <h1 style="color:#F5FFFA; text-align: center;">Admin</h1>
        <!--<p id="demo">test</p>-->
        <script>
            <% // Java declaration
                String indexInvoice;
                String invoiceDate;
                String insertlink; %>
            var invoiceArray = [<% for (int i = 0; i < invoiceList.size(); i++)
                {
                    insertlink = "<a href=\"control?origin=admin&invoice_id=";
                    indexInvoice = insertlink + invoiceList.get(i).getCart_id() + ">" + invoiceList.get(i).getCart_id() + "</a>";
                    invoiceDate = insertlink + invoiceList.get(i).getCart_id() + ">" + invoiceList.get(i).getInvoice_date() + "</a>";
            %>[<%=indexInvoice%>, '<%= invoiceDate%>']<%= i + 1 < invoiceList.size() ? "," : ""%><%} %>];
//            document.getElementById("demo").innerHTML = makeTableHTML(invoiceArray);
//            document.write(makeTableHTML(invoiceArray));
        </script>

<!--        <div class="container">
            <h2>Striped Rows</h2>
            <p>The .table-striped class adds zebra-stripes to a table:</p>            
            <table class="table table-striped">
                <script>
                    document.write(makeNodeTable(invoiceArray));
                </script>
            </table>
        </div>-->
        <!--<h1>------------------------------------</h1>-->
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
                                <%  int index = 0;
                                    for (Invoice i : cc.fetchInvoiceList())
                                    {
                                %>
                                <tr>
                                    <th scope="row"><a href="control?origin=admin&cart_id=<%=i.getCart_id()%>"><%=i.getCart_id()%></a></th>
                                    <td><a href="control?origin=admin&cart_id=<%=i.getCart_id()%>"><%=i.getInvoice_date()%></a></td>
                                </tr> 
                                <% }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="col-sm">
                    <%
                        List<LineItemsDTO> cartList = cc.fetchCart(cart_id);
                        if (cart_id > 0)
                        {
                            userInvoice = cc.fetchInvoice(cart_id);
                    %>

                    <h4>Faktura# <%=cart_id%></h4>
                    <h5>Køber: <%=userInvoice.getUsername()%></h5>
                    <h5>Email: <%=userInvoice.getEmail()%></h5>
                    <h5>Balance: <%=userInvoice.getBalance()%></h5>
                    <h5>Købsdato: <%=userInvoice.getInvoice_date()%></h5>
                    <div id="cart_tabel" style="text-align: left; width: 45%;">
                        <table class="table table-striped">
                            <thead  style="color:#F5FFFA; text-align: center;">
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Top</th>
                                    <th scope="col">Bund</th>
                                    <th scope="col">Antal</th>
                                </tr>
                            </thead>
                            <tbody  style="color: black; text-align: center;">
                                <%

                                    int index2 = 0;
                                    for (LineItemsDTO l : cartList)
                                    {
                                %>
                                <tr>
                                    <th scope="row"><%=++index2%></th>
                                    <td><%=l.getCupcake().getTopping().getType()%></td>
                                    <td><%=l.getCupcake().getBottom().getType()%></td>
                                    <td><%=l.getQuantity()%></td>
                                </tr>
                                <%
                                        };
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>