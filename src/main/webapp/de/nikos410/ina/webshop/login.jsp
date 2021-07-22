<%@ page import="static java.util.Objects.nonNull" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form method="post" action="login">
    <label>
        <span>Username</span>
        <input name="username" type="text" required="required">
    </label>
    <label>
        <span>Password</span>
        <input name="password" type="password" required="required">
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
</form>
</body>
</html>
