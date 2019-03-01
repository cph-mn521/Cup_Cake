/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.presentation;

import com.cupcakes.data.IngredientDTO;
import com.cupcakes.data.CupcakeDTO;
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
public class LoginCommand extends Command {

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

//        Controller c = new Controller(); ?
//        HttpSession session = request.getSession(); Det her virker rigtigt.
        // Creating switch statement to check what login page should be loaded:
        HttpSession session = request.getSession();
        if (session.getAttribute("login") == null) {
            firstlogin(response);
        } else {
            Authenticator A = new Authenticator();
            String loggedin = (String) session.getAttribute("login");
            String username = (String) request.getAttribute("username");
            String password = "" + ((int) request.getSession().getAttribute("password"));
            try {
                String Attempt = A.authenticate(username, password);

                switch (Attempt) {
                    case "success":
                        loginSucces(response, session, username);
                        break;
                    case "failure":
                        loginFailed(response);
                        break;
                }
            } catch (Exception e) {
                // user not found exeption
            }
        }

        response.setContentType("text/html;charset=UTF-8");

    }

    private void firstlogin(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        /* TODO output your page here. You may use following sample code. */
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Login</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<br><br>");
        out.println("<form action=\"cakes\" method=\"post\">");
        out.println("	Enter username : <input type=\"text\" name=\"username\"> <BR>");
        out.println("	Enter password : <input type=\"password\" name=\"password\"> <BR>");
        out.println("	<input type=\"submit\" />");
        out.println("</form>");
        out.println("<body>");
        out.println("</body>");
        out.println("</html>");

    }

    private void loginSucces(HttpServletResponse response, HttpSession session, String username) throws IOException {
        session.setAttribute("login", "true");
        session.setAttribute("username", username);
        PrintWriter out = response.getWriter();
        /* TODO output your page here. You may use following sample code. */
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Unknown</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Succesfull log in, now logged in as: " + username + " </h1>");
        out.println("<img src=\"data/sorbet.jpg\" width=\"175px\"/>");
        out.println("</body>");
        out.println("</html>");

    }

    private void loginFailed(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        /* TODO output your page here. You may use following sample code. */
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Login Failed, try again</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<br><br>");
        out.println("<form action=\"cakes\" method=\"post\">");
        out.println("	Enter username : <input type=\"text\" name=\"username\"> <BR>");
        out.println("	Enter password : <input type=\"password\" name=\"password\"> <BR>");
        out.println("	<input type=\"submit\" />");
        out.println("</form>");
        out.println("<body>");
        out.println("</body>");
        out.println("</html>");
    }

}
