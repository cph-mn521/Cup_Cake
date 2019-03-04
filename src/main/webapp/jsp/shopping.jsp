<%-- 
    Document   : shopping
    Created on : Mar 4, 2019, 5:30:54 PM
    Author     : martin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
Controller cc = new Controller();
        StandardHTMLStrings html = new StandardHTMLStrings();
        
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(html.standardHeader());
            out.println("<body>");
            out.println(html.standardMenu());
            out.println("<center><h1>Vælg indhold: </h1>");
            out.println("<form action=\"control\">");
            out.println("<input type=\"hidden\" name=\"origin\" value=\"cart\" />");
            out.println("<select id='toppings' name='Toppings' required>");
            out.println("<option>Vælg topping</option>");
            for (ToppingsDTO topping : cc.fetchToppings()) {
                out.println("<option value=\"" + topping.getType() + "\">" + topping.getType() + "</option>");
            }
            out.println("/<select>");
//            out.println("<br><br>");
            out.println("<select id='bottoms' name='Bottoms' required>");
            out.println("<option>Vælg bund</option>");
            for (BottomDTO bottom : cc.fetchBottoms()) {
                out.println("<option value=\"" + bottom.getType() + "\">" + bottom.getType() + "</option>");
            }
            out.println("</select>");
//            out.println("<br><br>");
            out.println("<select id='quantity' name='quantity' required>");
            out.println("<option>Antal</option>");
            out.println("<option>1</option>");
            out.println("<option>2</option>");
            out.println("<option>3</option>");
            out.println("<option>4</option>");
            out.println("<option>5</option>");
            out.println("<option>6</option>");
            out.println("</select>");
            out.println("<br><br>");
            out.println("<input type=\"submit\" value=\"Vælg\">");
            out.println("</form>");
            out.println("</center><br><br>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        }
</html>
