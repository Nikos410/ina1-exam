<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="de.nikos410.ina.webshop.model.entity.StockArticle" %>
<%@ page import="java.util.Collection" %>
<%@ page
        import="static de.nikos410.ina.webshop.controller.helper.ManageStockArticlesControllerHelper.ALL_ARTICLES_ATTRIBUTE_NAME" %>
<%@ page
        import="static de.nikos410.ina.webshop.controller.helper.ManageStockArticlesAddControllerHelper.NAME_PARAMETER_NAME" %>
<%@ page
        import="static de.nikos410.ina.webshop.controller.helper.ManageStockArticlesAddControllerHelper.DESCRIPTION_PARAMETER_NAME" %>
<%@ page import="static de.nikos410.ina.webshop.controller.helper.ManageStockArticlesAddControllerHelper.*" %>
<%@ page import="static java.util.Objects.nonNull" %>
<%@ page import="de.nikos410.ina.webshop.controller.helper.ManageStockArticlesDeleteControllerHelper" %>
<%@ page import="de.nikos410.ina.webshop.controller.helper.ManageStockArticlesChangeQuantityControllerHelper" %>
<html>
<head>
    <title>Manage articles</title>
</head>
<body>
<h1>Manage articles</h1>

<%
    final String error = request.getParameter("error");
    if (nonNull(error)) {
        out.println("<h3 style=\"color:red\">" + error + "</h3>");
    }
%>

<h2>Add new article</h2>
<form action="manage-stock-articles/add" method="post">
    <label>
        <span>Name</span>
        <input name="<%= NAME_PARAMETER_NAME %>" type="text" required="required">
    </label>
    <br/>
    <label>
        <span>Description</span>
        <input name="<%= DESCRIPTION_PARAMETER_NAME %>" type="text" required="required">
    </label>
    <br/>
    <label>
        <span>Quantity</span>
        <input name="<%= QUANTITY_PARAMETER_NAME %>" type="number" required="required">
    </label>
    <br/>
    <button type="submit">
        Add
    </button>
</form>

<h2>Manage existing articles</h2>
<%
    final Collection<StockArticle> allArticles =
            (Collection<StockArticle>) request.getSession().getAttribute(ALL_ARTICLES_ATTRIBUTE_NAME);

    for (StockArticle article : allArticles) {
        out.print("<strong>");
        out.print(article.getName());
        out.println("</strong>");

        out.println("<form method=\"post\" action=\"manage-stock-articles/delete\">");
        out.println("<input name=\"" + ManageStockArticlesDeleteControllerHelper.ARTICLE_PARAMETER_NAME + "\" type=\"hidden\" value=\"" + article.getId() + "\">");
        out.println("<button type=\"submit\">Delete</button>");
        out.println("</form>");

        out.println("<form method=\"post\" action=\"manage-stock-articles/quantity\">");
        out.println("<input name=\"" + ManageStockArticlesChangeQuantityControllerHelper.ARTICLE_PARAMETER_NAME + "\" type=\"hidden\" value=\"" + article.getId() + "\">");
        out.println("<input name=\"" + ManageStockArticlesChangeQuantityControllerHelper.QUANTITY_PARAMETER_NAME + "\" type=\"number\" min=\"0\" value=\"" + article.getStockQuantity() + "\">");
        out.println("<button type=\"submit\">Change stock quantity</button>");
        out.println("</form>");

        out.println("<hr/>");
    }
%>

<a href="shop"><p>Back to shop</p></a>

</body>
</html>
