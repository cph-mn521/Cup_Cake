/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.presentation;

import com.cupcakes.data.BottomDTO;
import com.cupcakes.data.LineItems;
import com.cupcakes.data.ShoppingCart;
import com.cupcakes.data.ToppingsDTO;
import com.cupcakes.logic.Controller;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author martin bøgh
 */
public class CartCommand extends Command {

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

        Controller cc = new Controller();
        String topping = request.getParameter("Toppings");
        String bottom = request.getParameter("Bottoms");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
        }
        //OBS invoice_id værdi opfundet til lejligheden
        cart.addLineItem(new LineItems(
                new ToppingsDTO(topping),
                new BottomDTO(bottom),
                quantity,
                11));

        session.setAttribute("cart", cart);
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
            for (LineItems l : cart.getLineItems()) {
                out.println("<h3>Invoice #" + l.getInvoice_id() + ":         ");
                out.println("" + l.getQuantity() + " stk  ");
                out.println(l.getToppings().getType() + " med ");
                out.println(l.getBottom().getType() + ", pris: ");
                out.println((l.getToppings().getPrice() + l.getBottom().getPrice())
                        * l.getQuantity() + "kr. </h3>");
            }
            out.println("<form action=\"shop\">");
            out.println("<input type=\"submit\" value=\"Shop videre\">");
            out.println("</form>");
            out.println("<br><br><br><br>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
