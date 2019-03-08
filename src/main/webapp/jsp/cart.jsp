<%-- 
    Document   : cart
    Created on : Mar 4, 2019, 5:31:16 PM
    Author     : martin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!--Styling and more-->
        <%@ include file = "/WEB-INF/jspf/header.jspf" %>
        
        <!--All java cartcontrolling parts -->
        <%@ include file = "/WEB-INF/jspf/cartController.jspf" %>

    </head>

    <body>
        <!--Page menues-->
        <%@ include file = "/WEB-INF/jspf/menu.jspf" %>
        
        <div class="container">
            <div id="cart_tabel">
                <h1 style="color:#F5FFFA; text-align: center;">Shopping Cart </h1>
                <table class="table table-striped">
                    <thead  style="color:#F5FFFA; text-align: center;">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Number</th>
                            <th scope="col">Top</th>
                            <th scope="col">Bottom</th>
                            <th scope="col">Price</th>
                        </tr>
                    </thead>
                    <tbody  style="color: black; text-align: center;">
                        <%                    int index = 0;
                            if (cart.getLineItems().size() == 0) {
                                out.println("Shopping cart is empty.");
                            } else {
                                out.println("Invoice #" + cart.getLineItems().get(cart.getLineItems().size() - 1).getInvoice_id() + ":         ");
                                for (LineItemsDTO l : cart.getLineItems()) {
                        %>
                        <tr>
                            <%
                                out.println("<th scope=\"row\">" + ++index + "</th>");
                                out.println("<td>" + l.getQuantity() + "</td>");
                                out.println("<td>" + l.getCupcake().getTopping().getType() + "</td>");
                                out.println("<td>" + l.getCupcake().getBottom().getType() + "</td>");
                                out.println("<td>" + (l.getCupcake().getTopping().getPrice()
                                        + l.getCupcake().getBottom().getPrice())
                                        * l.getQuantity() + " kr.</td>");
                            %>
                        </tr>
                        <%}
                            }
                        %>
                    </tbody>
                </table>
            </div>
            <h3 style="color:#F5FFFA; text-align: center;">
                <%
                    out.println("\n"
                            + "<br><br>Total pris: " + cc.fetchTotalPrice(cart) + " kr.");

                %>
            </h3><br><br>
            <center>
                <form  action="control?origin=shop" method="post">
                    <input type="hidden" name="origin" value="shop" />
                    <input type="submit" class="btn btn-primary" value="Keep shopping">
                </form>
                <form  action="control" method="post">
                    <input type="hidden" name="origin" value="cart" />
                    <input type="hidden" name="deal" value="save" />
                    <input type="submit" class="btn btn-danger" value="Payment">
                </form>
                <form  action="control" method="post">
                    <input type="hidden" name="origin" value="cart" />
                    <input type="hidden" name="deal" value="cancel" />
                    <input type="submit" class="btn btn-primary" value="Back">
                </form>
            </center>
        </div>
</html>
