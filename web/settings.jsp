<%--
  Created by IntelliJ IDEA.
  User: swebo_000
  Date: 2016-03-29
  Time: 03:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>User Settings.</title>
</head>
<body>
Hello, ${facebook.name} (${facebook.id}).
<br>
Message: <%=request.getParameter("message")%>
<br>
HERE WILL BE SETTINGS.
<form>
    Email:<br>
    <input type="text" name="email"><br>
    Program:<br>
    <input type="text" name="program">
</form>
</body>
</html>
