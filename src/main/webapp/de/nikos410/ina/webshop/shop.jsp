<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="de.nikos410.ina.webshop.model.entity.User" %>
<%@ page import="de.nikos410.ina.webshop.util.AuthenticationUtils" %>
<%@ page import="de.nikos410.ina.webshop.model.entity.StockArticle" %>
<%@ page import="java.util.Collection" %>
<%@ page import="de.nikos410.ina.webshop.controller.helper.ShopControllerHelper" %>
<html>
<head>
    <title>Shop</title>
</head>
<body>
<h1 style="text-align: center">
    Willkommen, <%=
AuthenticationUtils.getAuthenticatedUser(request)
        .map(User::getFullName)
        .orElseThrow(() -> new IllegalStateException("No authenticated user found."))
%>!
</h1>

<div style="display: flex">
    <div style="flex: 50%; padding: 1%">
        <h2 style="text-align: center">Verfügbare Artikel</h2>

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

                out.print("<p>");
                out.print(article.getStockQuantity() + " in stock.");
                out.println("</p>");

                out.println("<hr/>");
            }
        %>
    </div>

    <div style="flex: 50%; padding: 1%">
        <h2 style="text-align: center">Warenkorb</h2>

    </div>
</div>

</body>
</html>
