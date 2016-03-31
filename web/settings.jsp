<%@ page import="Persist.JPAStore" %>
<%@ page import="Models.UserEntity" %>
<%@ page import="facebook4j.Facebook" %><%--
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
<%
    JPAStore db = new JPAStore();
    Facebook facebook = (Facebook) session.getAttribute("facebook");
    UserEntity currentUser = (UserEntity) db.fetchUser(%>${facebook.id}<%);

%>
        <br>
Message: <%=request.getParameter("message")%>
<br>
HERE WILL BE SETTINGS.
<form>
    Current Email: <%currentUser.getEmail();%>
    <br>
    <input type="text" name="email"><br>

    Current Program: <%currentUser.getProgram();%>
    <br>
    <input type="text" name="program">

    Current Master: <%currentUser.getMaster();%>
    <br>
    <input type="text" name="program">

    Current Comment: <%currentUser.getComments();%>
    <br>
    <input type="text" name="program">

    <input type="submit" value="Submit.">

</form>
</body>
</html>
