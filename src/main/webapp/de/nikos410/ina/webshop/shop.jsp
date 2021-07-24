<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="de.nikos410.ina.webshop.model.entity.User" %>
<%@ page import="de.nikos410.ina.webshop.util.AuthenticationUtils" %>
<%@ page import="de.nikos410.ina.webshop.model.entity.StockArticle" %>
<%@ page import="java.util.Collection" %>
<%@ page import="de.nikos410.ina.webshop.controller.helper.ShopControllerHelper" %>
<%@ page import="de.nikos410.ina.webshop.model.ShoppingCartArticle" %>
<%@ page import="de.nikos410.ina.webshop.controller.helper.ShoppingCartControllerHelper" %>
<%@ page import="static java.util.Objects.isNull" %>
<%@ page import="static java.util.Objects.nonNull" %>
<%@ page
        import="static de.nikos410.ina.webshop.controller.helper.ShoppingCartControllerHelper.SELECTED_ARTICLE_PARAMETER_NAME" %>
<%@ page
        import="static de.nikos410.ina.webshop.controller.helper.ShoppingCartControllerHelper.SELECTED_QUANTITY_PARAMETER_NAME" %>
<html>
<head>
    <title>Shop</title>
</head>
<body>
<h1 style="text-align: center">
        Welcome, <%=
    AuthenticationUtils.getAuthenticatedUser(request)
            .map(User::getFullName)
            .orElseThrow(() -> new IllegalStateException("No authenticated user found."))
    %>! <a href="logout">Logout</a>
</h1>

<%
    final String error = request.getParameter("error");
    if (nonNull(error)) {
        out.println("<h3 style=\"color:red\">" + error + "</h3>");
    }
%>

<div style="display: flex">
    <div style="flex: 50%; padding: 1%">
        <h2 style="text-align: center">Available articles</h2>

        <%
            final Collection<StockArticle> availableArticles =
                    (Collection<StockArticle>) request.getSession().getAttribute(ShopControllerHelper.AVAILABLE_ARTICLES_ATTRIBUTE_NAME);

            for (StockArticle article : availableArticles) {
                out.print("<strong>");
                out.print(article.getName());
                out.println("</strong>");

                out.print("<p><em>");
                out.print(article.getDescription());
                out.println("</em></p>");

                out.println("<form method=\"post\" action=\"add-to-cart\">");
                out.println("<input name=\"" + SELECTED_ARTICLE_PARAMETER_NAME + "\" type=\"hidden\" value=\"" + article.getId() + "\">");
                out.println("<label><input name=\"" + SELECTED_QUANTITY_PARAMETER_NAME + "\" type=\"number\" min=\"0\" max=\"" + article.getStockQuantity() + "\" placeholder=\"Quantity\" required=\"required\"><span>" + article.getStockQuantity() + " in stock</span></label>");
                out.println("<button type=\"submit\">Add to cart</button>");
                out.println("</form>");

                out.println("<hr/>");
            }
        %>
    </div>

    <div style="flex: 50%; padding: 1%">
        <h2 style="text-align: center">Cart</h2>

        <%
            final String ordered = request.getParameter("ordered");
            if (nonNull(ordered)) {
                out.println("<h2 style=\"color:green; text-align:center\">Order successful. Have fun!</h2>");
                return;
            }

            final Collection<ShoppingCartArticle> shoppingCart =
                    (Collection<ShoppingCartArticle>) request.getSession().getAttribute(ShoppingCartControllerHelper.SHOPPING_CART_ATTRIBUTE_NAME);
            if (isNull(shoppingCart)) {
                return;
            }

            for (ShoppingCartArticle article : shoppingCart) {
                out.print("<strong>");
                out.print(article.article().getName());
                out.print("</strong>");
                out.print("<p><em>");
                out.print("Quantity: " + article.quantity());
                out.print("</em></p>");

                out.println("<hr/>");
            }

            if (!shoppingCart.isEmpty()) {
                out.println("<form method=\"post\" action=\"order\">");
                out.println("<button type=\"submit\">Order</button>");
                out.println("</form>");
            }
        %>
    </div>
</div>

</body>
</html>
