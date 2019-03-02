/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.presentation;

import com.cupcakes.logic.DTO.BottomDTO;
import com.cupcakes.logic.DTO.CupcakeDTO;
import com.cupcakes.logic.DTO.LineItemsDTO;
import com.cupcakes.data.ShoppingCart;
import com.cupcakes.logic.DTO.ToppingsDTO;
import com.cupcakes.logic.Controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author martin bøgh
 */
public class CartCommand implements Command {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String topping = null;
        String bottom = null;
        int quantity = 0;
        Enumeration params = request.getParameterNames();

        while (params.hasMoreElements()) {
            String paramName = (String) params.nextElement();
            String paramValue = request.getParameter(paramName);
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
         * henter cart objektet fra session eller laver et hvis det ikke eksisterer
         */
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
        }

        //
        /**
         * hvis der er parameter værdier så laves et nye ShoppingCart "cart" objekt.
         * OBS invoice_id værdi opfundet til lejligheden
         */
        if (topping != null && !topping.isEmpty()
                && bottom != null && !topping.isEmpty()
                && quantity != 0) {

            cart.addLineItem(new LineItemsDTO(
                    new CupcakeDTO(
                            new ToppingsDTO(topping),
                            new BottomDTO(bottom)),
                    quantity,
                    11));

            session.setAttribute("cart", cart);
        }

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Shopping cart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<br><br>");
            out.println("<h1>Din shopping carts indhold: </h1>");
            for (LineItemsDTO l : cart.getLineItems()) {
                out.println("<h3>Invoice #" + l.getInvoice_id() + ":         ");
                out.println("" + l.getQuantity() + " stk  ");
                out.println(l.getCupcake().getTopping().getType() + " med ");
                out.println(l.getCupcake().getBottom().getType() + ", pris: ");
                out.println((l.getCupcake().getTopping().getPrice()
                        + l.getCupcake().getBottom().getPrice())
                        * l.getQuantity() + "kr. </h3>");
            }
            out.println("<form action=\"control\">");
            out.println("<input type=\"hidden\" name=\"origin\" value=\"shop\" />");
            out.println("<input type=\"submit\" value=\"Shop videre\">");
            out.println("</form>");
            out.println("<br><br><br><br>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
