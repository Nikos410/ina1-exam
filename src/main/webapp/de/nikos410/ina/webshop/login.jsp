<%@ page import="static java.util.Objects.nonNull" %>
<%@ page import="static de.nikos410.ina.webshop.controller.helper.LoginControllerHelper.USERNAME_PARAMETER_NAME" %>
<%@ page import="static de.nikos410.ina.webshop.controller.helper.LoginControllerHelper.PASSWORD_PARAMETER_NAME" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form method="post" action="login">
    <label>
        <span>Username</span>
        <input name="<%= USERNAME_PARAMETER_NAME %>" type="text" required="required">
    </label>
    <label>
        <span>Password</span>
        <input name="<%= PASSWORD_PARAMETER_NAME %>" type="password" required="required">
    </label>
    <button type="submit">
        Login
    </button>

    <%
        final String error = request.getParameter("error");
        if (nonNull(error)) {
            out.println("<p style=\"color:red\">Bad credentials.</p>");
        }
    %>

    <hr/>

    <a href="register"><p>Register</p></a>
</form>
</body>
</html>
