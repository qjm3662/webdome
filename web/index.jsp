<%--
  Created by IntelliJ IDEA.
  User: qjm253
  Date: 2016/12/17 0017
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
  public int a = 5;
%>
<html>
  <head>
    <title>Welcome</title>
  </head>
  <body>
    <a href="<%=request.getContextPath()%>/HelloWorld">HelloWord</a><br/>
    <a href="<%=request.getContextPath()%>/login.jsp">login</a>
  </body>
</html>
