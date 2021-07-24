<%
    final String contextPath = request.getContextPath();
    final String redirectDestination = contextPath + "/shop";
    response.sendRedirect(redirectDestination);
%>
