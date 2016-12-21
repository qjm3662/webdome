<%--
  Created by IntelliJ IDEA.
  User: qjm3662
  Date: 2016/12/20
  Time: 22:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册界面</title>
</head>
<body>
<!--输出错误提示-->
<span style="color:darkred; font-weight: bold">
    <%
        if(request.getAttribute("err") != null){
            out.println(request.getAttribute("err") + "<br/>");
        }
    %>
</span>
<form id="register" method="post" action="${pageContext.request.contextPath}\register">
    用户名：<input type="text" name="username"><br/>
    密&nbsp;&nbsp;码：  <input type="password" name="password"><br/>
    <input type="submit" value="注册"><br/>
</form>
</body>
</html>
