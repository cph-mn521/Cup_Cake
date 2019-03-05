





<%-- 
    Document   : shopping
    Created on : Mar 4, 2019, 5:30:54 PM
    Author     : martin
--%>

<%@page import="com.cupcakes.logic.DTO.BottomDTO"%>
<%@page import="com.cupcakes.logic.DTO.ToppingsDTO"%>
<%@page import="com.cupcakes.logic.Controller"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file = "/WEB-INF/jspf/header.jspf" %>
        <%
            Controller cc = new Controller();
        %>
    </head>
    <body>
        <%@ include file = "/WEB-INF/jspf/menu.jspf" %>
        <!--<div class="container">-->
            <center><h1>Vælg indhold: </h1>
                <form action="control" method="get">
                    <input type="hidden" name="origin" value="cart" />
                    <div class="dropdown">
                        <!--<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">-->
                            <!--Vælg Top-->
                    <select id='toppings' name='Toppings' required>
                            <option>Vælg topping</option>
                            <%
                        for (ToppingsDTO topping : cc.fetchToppings()) {
                            out.println("<option value=\"" + topping.getType() + "\">" + topping.getType() + "</option>");
                        }
%>
                        <!--</button>-->
                        <!--<div class="dropdown-menu">-->
                            <%
//                                for (ToppingsDTO topping : cc.fetchToppings()) {
//                                    out.println("<a class=\"dropdown-item\" href=\"#Toppings=" + topping.getType() + "\">" + topping.getType() + "</a>");
//                                    //                    out.println("<option value=\"" + topping.getType() + "\">" + topping.getType() + "</option>");
//                                }
                            %>
                        <!--</div>-->
                    <!--</div>-->
                    <!--<div class="dropdown">-->
                        <!--<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">-->
                            <!--Vælg Bund-->
                        <!--</button>-->
                        <!--<div class="dropdown-menu">-->
                            <%
//                                for (BottomDTO bottom : cc.fetchBottoms()) {
//                                    out.println("<a class=\"dropdown-item\" href=\"#Bottom=" + bottom.getType() + "\">" + bottom.getType() + "</a>");
//                                    //                    out.println("<option value=\"" + topping.getType() + "\">" + topping.getType() + "</option>");
//                                }
                            %>
                        <!--</div>-->
                    <!--</div>-->
                                        <!--<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">-->
                                            <!--Vælg topping-->
                                            <!--<option>Vælg topping</option>-->
                                        <!--</button>-->
                    </select>
                    <select id='bottoms' name='Bottoms' required>
                    <option>Vælg bund</option>
                    <%
                        for (BottomDTO bottom : cc.fetchBottoms()) {
                            out.println("<option value=\"" + bottom.getType() + "\">" + bottom.getType() + "</option>");
                        }
%>

                            <option>Vælg topping</option>
                    </select>
<!--                    <div class="dropdown">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">-->
                            <!--Antal-->
<!--                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="#quantity=1">1</a>
                            <a class="dropdown-item" href="&quantity=2">2</a>
                            <a class="dropdown-item" href="&quantity=3">3</a>
                            <a class="dropdown-item" href="&quantity=4">4</a>
                            <a class="dropdown-item" href="&quantity=5">5</a>
                            <a class="dropdown-item" href="&quantity=6">6</a>
                        </div>
                    </div>-->
                    <select id='quantity' name='quantity' required>
                        <option>Antal</option>
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                        <option>6</option>
                    </select>
                    <br><br>
                    <input type="submit" class="btn btn-success" value="Vælg">
                </form>
            </center><br><br>
        <!--</div>-->
    </body>
</html>
