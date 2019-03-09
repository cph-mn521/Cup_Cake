/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.presentation;

import com.cupcakes.logic.Controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet controlling the flow GET and POST commands from homepage
 *
 * @author martin bøgh
 */
@WebServlet(name = "Control", urlPatterns = {"/control"})
public class NewServlet extends HttpServlet {

    /**
     * Object declaration for use when java dynamic pages needs to be created
     */
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Controller C = new Controller();
        response.setContentType("text/html;charset=UTF-8");
        String origin = request.getParameter("origin");

        /**
         * Depending on the value of parameter "origin" dispatches to different
         * pages or java classes
         */
        switch (origin) {
            /**
             * Dispatched to jsp page
             */
            case "login":
                if (Controller.loginCheck(request.getParameter("username"), request.getParameter("password"), request)) {
                    response.sendRedirect("/CupCake");
                } else {
                    request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
                }
                break;

            case "validate":
                request.getRequestDispatcher("jsp/validate.jsp").forward(request, response);
                break;

            /**
             * Starter registration page
             */
            case "registration":
                if (Controller.createUser(request.getParameter("username"), request.getParameter("password"),
                        request.getParameter("email"), request)) {
                    response.sendRedirect("/CupCake");
                } else {
                    request.getRequestDispatcher("jsp/registration.jsp").forward(request, response);
                }
                break;

            /**
             * admin page -
             */
            case "admin":
                request.getRequestDispatcher("jsp/admin.jsp").include(request, response);
                //bruges indtil der laves noget på admin siden. admin siden tror den er i root folderen
                //men den er i /admin folderen
                
//                response.sendRedirect("admin/index.html");
                break;

            /**
             * Page for combining cupcakes - Java dynamic page starts
             */
            case "shop":
                request.getRequestDispatcher("jsp/shopping.jsp").forward(request, response);
                break;
            case "account":
                request.getRequestDispatcher("jsp/Account.jsp").forward(request, response);
                break;
            /**
             * Page for summing up the shopping cart Java dynamic page starts
             */
            case "cart":
                request.getRequestDispatcher("jsp/cart.jsp").forward(request, response);
                break;

            case "pay":
                request.getRequestDispatcher("jsp/pay.jsp").forward(request, response);
                break;
            
               

            /**
             *
             */
            default:
                throw new AssertionError();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
