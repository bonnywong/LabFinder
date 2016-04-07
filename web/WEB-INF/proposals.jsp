<%@ page import="Persist.JPAStore" %>
<%@ page import="Models.UserEntity" %>
<%@ page import="facebook4j.Facebook" %>
<%@ page import="Models.CourseEntity" %>
<%@ page import="Models.ProposalEntity" %>
<%@ page import="Models.AmbitionEntity" %>
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
    JPAStore db = new JPAStore();
    Facebook facebook = (Facebook) session.getAttribute("facebook");
    UserEntity currentUser = (UserEntity) db.fetchUser(Long.parseLong(facebook.getId()));
    request.setAttribute("currentUser_id", currentUser.getId());
    String facebookId = facebook.getId();
    String profile_img = new String("http://graph.facebook.com/" + facebookId + "/picture");
    String primg = "https://scontent.xx.fbcdn.net/hprofile-xta1/v/t1.0-1/p50x50/11046478_10152321835823078_2224262110139808625_n.jpg?oh=6602521d12b107d4906eb79495c21e5a&oe=578C303F";

    UserEntity[] course_users = (UserEntity[])request.getAttribute("course_users");
    request.setAttribute("course_users", course_users);
    CourseEntity current_course = (CourseEntity) request.getAttribute("current_course");
    request.setAttribute("current_course", current_course);


    AmbitionEntity[] ambitions = (AmbitionEntity[]) request.getAttribute("ambitions");
    request.setAttribute("ambitions", ambitions);

    ProposalEntity[] received_proposals = (ProposalEntity[]) request.getAttribute("received_proposals");
    ProposalEntity[] sent_proposals = (ProposalEntity[]) request.getAttribute("sent_proposals");
%>
<head>
    <link rel="stylesheet" type="text/css" href="proposals.css">
    <title>Manage proposals</title>
</head>
<body>
    <center>
        <ul>
            <li><a href="#home">Home</a></li>
            <li><a href="search">Search partner</a></li>
            <li><a href="settings">Settings</a> </li>
            <li><a href="profile">Profile</a></li>
            <li><a class="active">My proposals</a></li>
            <li style="float:right"><a class="about" href="#about">About</a></li>
            <li style="float:right"><a class="courses" href="courses">All Courses</a></li>
            <li style="float:right"><a class="logout" href="#logout">Logout</a></li>
        </ul>

        <div id="wrapper">
            <div id="proposaltome">

            </div>
            <div id="proposalfromme">

            </div>
        </div>
    </center>
</body>
</html>
