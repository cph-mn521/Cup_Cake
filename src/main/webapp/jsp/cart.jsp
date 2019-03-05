<%-- 
    Document   : cart
    Created on : Mar 4, 2019, 5:31:16 PM
    Author     : martin
--%>

<%@page import="com.cupcakes.logic.DTO.LineItemsDTO"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.cupcakes.logic.DTO.UserDTO"%>
<%@page import="com.cupcakes.logic.DTO.BottomDTO"%>
<%@page import="com.cupcakes.logic.DTO.ToppingsDTO"%>
<%@page import="com.cupcakes.logic.DTO.CupcakeDTO"%>
<%@page import="com.cupcakes.logic.DTO.ShoppingCart"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.cupcakes.logic.Controller"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file = "/WEB-INF/jspf/header.jspf" %>
        <%
            Controller cc = new Controller();

            String topping = null;
            String bottom = null;
            int quantity = 0;
            Enumeration params = request.getParameterNames();

            while (params.hasMoreElements()) {
                String paramName = (String) params.nextElement();
                String paramValue = request.getParameter(paramName);

                /**
                 * Check that there's no default values passed on
                 */
                if (paramValue.equals("Vælg topping")
                        || paramValue.equals("Vælg bund")
                        || paramValue.equals("Antal")) {
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                    return;
                }
                switch (paramName) {
                    case "Toppings":
                        topping = paramValue;
                        break;

                    case "Bottoms":
                        bottom = paramValue;
                        break;

                    case "quantity":
                        quantity = Integer.parseInt(paramValue);
                        break;

                    default:
                        break;
                }
            }

            /**
             * henter cart objektet fra session eller laver et hvis det ikke
             * eksisterer
             */
            session = request.getSession();
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            if (cart == null) {
                cart = new ShoppingCart();
            }

            //
            /**
             * hvis der er parameter værdier så laves et nye ShoppingCart "cart"
             * objekt. OBS invoice_id værdi opfundet til lejligheden
             */
            if (topping != null && !topping.isEmpty()
                    && bottom != null && !topping.isEmpty()
                    && quantity != 0) {

                /**
                 * Add to list of lineitems if not duplicate. Quantity is added
                 * to lineitem if duplicate
                 */
                CupcakeDTO cake = new CupcakeDTO(
                        new ToppingsDTO(topping),
                        new BottomDTO(bottom));
                if (cc.isCupCakeDuplicate(cart, cake, quantity)) {
                    System.out.println("Kages antal opdateret i lineitems liste");
                } else {

                    // Fake user
                    UserDTO user = null;
                    try {
                        user = cc.fetchUser("bittie_bertha");
                    } catch (SQLException ex) {
                        System.out.println("Kunne ikke finde user: " + ex);
                    }
                    int invoiceID = cc.putCartInDB(cart, user);
                    cart.addLineItem(new LineItemsDTO(cake, quantity, invoiceID));
                    System.out.println("Ny kage tilføjet");
                }

                session.setAttribute("cart", cart);
            }

        %>
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link href="css/standard.css" rel="stylesheet">
    </head>

    <body>
        <%@ include file = "/WEB-INF/jspf/menu.jspf" %>
        <div class="container">
            <div id="cart_tabel">
                <h1 style="color:#F5FFFA; text-align: center;">Indkøbsvogn: </h1>
                <table class="table table-striped">
                    <thead  style="color:#F5FFFA; text-align: center;">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Antal</th>
                            <th scope="col">Toppe</th>
                            <th scope="col">Bunde</th>
                            <th scope="col">Pris</th>
                        </tr>
                    </thead>
                    <tbody  style="color: black; text-align: center;">
                        <%                    int index = 0;
                            //out.println("Invoice #" + l.getInvoice_id() + ":         ");
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
            <form  style="color:F5FFFA; text-align: center;" action="control" method="post">
                <input type="hidden" name="origin" value="shop" />
                <input type="submit" class="btn btn-primary" value="Shop videre">
            </form>
        </div>
    </body>
</html>
