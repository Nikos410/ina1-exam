<%@ page import="static java.util.Objects.nonNull" %>
<%@ page import="static de.nikos410.ina.webshop.controller.helper.RegisterControllerHelper.USERNAME_PARAMETER_NAME" %>
<%@ page import="static de.nikos410.ina.webshop.controller.helper.RegisterControllerHelper.PASSWORD_PARAMETER_NAME" %>
<%@ page import="static de.nikos410.ina.webshop.controller.helper.RegisterControllerHelper.EMAIL_ADDRESS_PARAMETER_NAME" %>
<%@ page import="static de.nikos410.ina.webshop.controller.helper.RegisterControllerHelper.FULL_NAME_PARAMETER_NAME" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form method="post" action="register">
    <label>
        <span>Username</span>
        <input name="<%= USERNAME_PARAMETER_NAME %>" type="text" required="required">
    </label>
    <br/>
    <label>
        <span>Password</span>
        <input name="<%= PASSWORD_PARAMETER_NAME %>" type="password" required="required">
    </label>
    <br/>
    <label>
        <span>E-Mail address</span>
        <input name="<%= EMAIL_ADDRESS_PARAMETER_NAME %>" type="email" required="required">
    </label>
    <br/>
    <label>
        <span>Full Name</span>
        <input name="<%= FULL_NAME_PARAMETER_NAME %>" type="text">
    </label>
    <br/>
    <button type="submit">
        Register
    </button>

    <%
        final String error = request.getParameter("error");
        if (nonNull(error)) {
            out.println("<p style=\"color:red\">User with the specified username already exists.</p>");
        }
    %>
</form>
</body>
</html>
