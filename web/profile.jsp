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
    UserEntity currentUser = (UserEntity) db.fetchUser(Long.parseLong(facebook.getId()));

%>
        <br>
Message: <%=request.getParameter("message")%>
<br>
HERE WILL BE SETTINGS.
<form action ="profile">
    <input type="hidden" name="profile_action" value="insertProfileUpdate">

    <input type="hidden" name="user_name" value=${facebook.name}>
    <input type="hidden" name="user_fb_id" value=${facebook.id}>

    Current Email: <%currentUser.getEmail();%>
    <input type="text" name="email">
    <br>

    Current Program: <%currentUser.getProgram();%>
    <input type="text" name="program">
    <br>

    Current Master: <%currentUser.getMaster();%>
    <input type="text" name="master">
    <br>

    Current Comment: <%currentUser.getComments();%>
    <input type="text" name="comments">
    <br>

    <input type="submit" value="Submit.">

</form>
</body>
</html>
