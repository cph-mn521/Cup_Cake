<%-- 
    Document   : cart
    Created on : Mar 4, 2019, 5:31:16 PM
    Author     : martin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
Controller cc = new Controller();
        String topping = null;
        String bottom = null;
        int quantity = 0;
        Enumeration params = request.getParameterNames();
        StandardHTMLStrings html = new StandardHTMLStrings();

        while (params.hasMoreElements()) {
            String paramName = (String) params.nextElement();
            String paramValue = request.getParameter(paramName);

            /**
             * Check that there's no default values passed on
             */
            if (paramValue.equals("Vælg topping")
                    || paramValue.equals("Vælg bund")
                    || paramValue.equals("Antal")) {
                request.getRequestDispatcher("index.html").forward(request, response);
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
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
        }

        //
        /**
         * hvis der er parameter værdier så laves et nye ShoppingCart "cart"
         * objekt.
         * OBS invoice_id værdi opfundet til lejligheden
         */
        if (topping != null && !topping.isEmpty()
                && bottom != null && !topping.isEmpty()
                && quantity != 0) {

            /**
             * Add to list of lineitems if not duplicate. Quantity is added to
             * lineitem if duplicate
             */
            CupcakeDTO cake = new CupcakeDTO(
                    new ToppingsDTO(topping),
                    new BottomDTO(bottom));
            if (cc.isCupCakeDuplicate(cart, cake, quantity)) {
                System.out.println("Kages antal opdateret i lineitems liste");
            } else{
                
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

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

//            out.println("<%@ include file = \"WEB-INF/jspf/header.jspf\" %>");
            out.println(html.standardHeader());
            out.println("<body>");
//            out.println("<%@ include file = \"menu.jspf\" %>");
            out.println(html.standardMenu());
            out.println("<center>");
            out.println("<h1>Din shopping carts indhold: </h1>");
            for (LineItemsDTO l : cart.getLineItems()) {
                out.println("Invoice #" + l.getInvoice_id() + ":         ");
                out.println("" + l.getQuantity() + " stk  ");
                out.println(l.getCupcake().getTopping().getType() + " med ");
                out.println(l.getCupcake().getBottom().getType() + ", pris: ");
                out.println((l.getCupcake().getTopping().getPrice()
                        + l.getCupcake().getBottom().getPrice())
                        * l.getQuantity() + "kr. <br>");
            }
            out.println("<br><br>Total price: " + cc.fetchTotalPrice(cart)
                    + " kr.<br><br>");
            out.println("<form action=\"control\">");
            out.println("<input type=\"hidden\" name=\"origin\" value=\"shop\" />");
            out.println("<input type=\"submit\" value=\"Shop videre\">");
            out.println("</form>");
            out.println("</center><br><br><br><br>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        }
