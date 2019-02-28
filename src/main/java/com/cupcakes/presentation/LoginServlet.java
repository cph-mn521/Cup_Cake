/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.presentation;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cupcakes.logic.Controller;

/**
 *
 * @author nille
 */
@WebServlet(name = "LoginServlet/", urlPatterns
        = {
            "/LoginServlet/*"
        })
public class LoginServlet extends HttpServlet {

    private String msg;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean b = false;

        response.setContentType("text/html;charset=UTF-8");
        /* Check for login and so on... */
        HttpSession session = request.getSession();

        //Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        // if (loggedIn == true) {
        //    return;
        // }
        generateLogin(response, msg);
        try {
            //while (b) {
            b = Controller.loginCheck(request.getParameter("uname"), request.getParameter("psw"));
            session.setAttribute("loggedIn", b);
//}
            loginSuccess(response);

        } catch (Exception e) {
            msg = e.getMessage();
            processRequest(request, response);
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

    public void generateLogin(HttpServletResponse response, String msg) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html");
            out.println("<html>");
            out.println("<head> <title> Login </title></head>");
            out.println("<body>");
            out.println("<form action =\"LoginServlet\">");
            out.println("<div class=\"container\">");
            out.println("<label for=\"uname\"><b>Username</b></label>");
            out.println("<input type=\"text\" placeholder=\"Enter Username\" name=\"uname\" required>");
            out.println("<label for=\"psw\"><b>Password</b></label>");
            out.println("<input type=\"password\" placeholder=\"Enter Password\" name=\"psw\" required>");
            out.println("<button type=\"submit\">Login</button>");
            out.println("</form> </div>");
            out.println("<form action =\"loginServlet\">");
            out.println("<div class=\"container\">");
            out.println("<button type\"submit\">Create User<value=\"createUser\"/>");
            out.println("</form> </div>");
            if (!msg.isEmpty()) {
                out.println("<div>" + msg + "</div>");
            }
            out.println("</body></html>");
        }
    }

    public void loginSuccess(HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html");
            out.println("<html>");
            out.println("<head> <title> Login successful! </title></head>");
            out.println("<body>");
            out.println("login success");
            out.println("</body></html>");
        }
    }
}
