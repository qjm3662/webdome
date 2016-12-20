<%--
  Created by IntelliJ IDEA.
  User: qjm253
  Date: 2016/12/20 0020
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LoginTest</title>
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
<form id="login" method="post" action="login">
    用户名：<input type="text" name="username"><br/>
    密&nbsp;&nbsp;码：  <input type="password" name="password"><br/>
    <input type="submit" value="登录"><br/>
</form>
</body>
</html>
