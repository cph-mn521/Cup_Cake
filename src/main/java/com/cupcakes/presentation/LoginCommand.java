/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcakes.presentation;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginCommand
 *
 * @author martin bøgh
 */
public class LoginCommand implements Command {

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
            firstlogin(response, session);
        } else {
            String loggedin = (String) session.getAttribute("login");

            if (loggedin.equals("true")) {

                loginSucces(response, session, (String) session.getAttribute("username"));
            } else {

                Authenticator A = new Authenticator();

                String username = (String) request.getAttribute("username");
                String password = (String) request.getAttribute("password");
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

        }

        response.setContentType("text/html;charset=UTF-8");

    }

    private void firstlogin(HttpServletResponse response, HttpSession session) throws IOException {
        PrintWriter out = response.getWriter();
        session.setAttribute("login", "false");
        /* TODO output your page here. You may use following sample code. */
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Login</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<br><br>");
        out.println("<form action=\"log\" method=\"post\">");
        out.println("	Enter username : <input type=\"text\" name=\"username\" required> <BR>");
        out.println("	Enter password : <input type=\"password\" name=\"password\" required> <BR>");
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
        out.println("<title>Login Succesful</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Succesfull log in, now logged in as: " + username + " </h1>");
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
        out.println("<h1>Failed log in </h1>");
        out.println("<br><br>");
        out.println("<form action=\"log\" method=\"post\">");
        out.println("	Enter username : <input type=\"text\" name=\"username\" required> <BR>");
        out.println("	Enter password : <input type=\"password\" name=\"password\" required> <BR>");
        out.println("	<input type=\"submit\" />");
        out.println("</form>");
        out.println("<body>");
        out.println("</body>");
        out.println("</html>");
    }

}
