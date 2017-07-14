<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/14
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

姓名： ${username}<br>
密码： ${password}
<form action="${pageContext.request.contextPath}/dengchu">
    <input type="submit" value="退出">
</form>