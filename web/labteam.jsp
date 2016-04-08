<%@ page import="Persist.JPAStore" %>
<%@ page import="facebook4j.Facebook" %>
<%@ page import="Models.*" %>
<%--
  Created by IntelliJ IDEA.
  User: Keim
  Date: 2016-04-07
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%
    LabTeamHelper[] received_proposals = (LabTeamHelper[]) request.getAttribute("labteams");
%>
<head>
    <link rel="stylesheet" type="text/css" href="proposals.css">
    <title>proposals</title>
</head>
<body>
<center>
    <ul>
        <li><a href="#home">Home</a></li>
        <li><a href="search">Search partner</a></li>
        <li><a href="settings">Settings</a> </li>
        <li><a href="profile">Profile</a></li>
        <li><a href="proposals">My proposals</a></li>
        <li><a class="active">My teams</a></li>
        <li style="float:right"><a class="about" href="#about">About</a></li>
        <li style="float:right"><a class="courses" href="courses">All Courses</a></li>
        <li style="float:right"><a class="logout" href="logout">Logout</a></li>
    </ul>

    <div id="wrapper">
        <h1> My teams</h1>
        <div id="proposalfromme">
            <table>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Course</th>
                </tr>
                <c:forEach items="${labteams}" var="team">
                    <tr>
                        <td>${team.userA}</td>
                        <td>${team.userAmail}</td>
                        <td>${team.userB}</td>
                        <td>${team.userBmail}</td>
                        <td>${team.courseCode} - ${team.courseName}</td>
                        <td>
                            <form action ="labteams" method="post">
                                <input type="hidden" name="team_id" value="${team.teamLabID}">
                                <input type="submit" name="action" value="Leave">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
</center>
</body>
</html>
