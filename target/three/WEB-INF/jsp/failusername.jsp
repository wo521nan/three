<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>

${error}
<h2>请重新注册!</h2>
<form action="${pageContext.request.contextPath}/reg" method="get">

    姓名 ：<input type="text" name="username" value=""><br />
    密码： <input type="text" name="password" value=""><br />

    <input type="submit" value="注册">
</form>