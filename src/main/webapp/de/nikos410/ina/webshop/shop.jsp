<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="de.nikos410.ina.webshop.model.entity.User" %>
<%@ page import="de.nikos410.ina.webshop.util.AuthenticationUtils" %>
<html>
<head>
    <title>Shop</title>
</head>
<body>
<h1>Willkommen, <%=
    AuthenticationUtils.getAuthenticatedUser(request)
            .map(User::getFullName)
            .orElseThrow(() -> new IllegalStateException("No authenticated user found."))
%>!</h1>
</body>
</html>
