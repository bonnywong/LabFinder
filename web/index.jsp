<%--
  Created by IntelliJ IDEA.
  User: swebo_000
  Date: 2016-03-27
  Time: 05:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<html>
  <head>
    <title>LabFinder</title>
  </head>
  <body>
    <tag:notlogged>
    <a href="login">Login using Facebook!</a>
    </tag:notlogged>
    <tag:logged>
      You have logged in!
      <br>
      Your name: ${facebook.name}.
      <br>
      Your ID: ${facebook.id}.
      <br>
      <a href="">Display more info.</a>
      <br>
      <a href="logout">Log out.</a>
    </tag:logged>
  </body>
</html>
