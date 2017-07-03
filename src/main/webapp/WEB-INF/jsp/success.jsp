<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/28
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

姓名： ${user.username}<br>
密码： ${user.password}
<form action="/dengchu.do">
    <input type="submit" value="退出">
</form>