<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>

${error}
<h2>请重新登录！</h2>
<form action="${pageContext.request.contextPath}/login" method="get">

    姓名 ：<input type="text" name="username" value=""><br />
    密码： <input type="text" name="password" value=""><br />

    <input type="submit" value="登录">
</form>
