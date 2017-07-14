<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/14
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>注册失败</title>
</head>
<body>
${msg}
<h2>请重新注册</h2>
<form action="${pageContext.request.contextPath}/reg" method="get">

    姓名 ：<input type="text" name="username" value=""><br />
    密码： <input type="text" name="password" value=""><br />

    <input type="submit" value="注册">
</form>
</body>
</html>
