<%@ page import="Persist.JPAStore" %>
<%@ page import="Models.UserEntity" %>
<%@ page import="facebook4j.Facebook" %>
<%@ page import="Models.CourseEntity" %><%--
  Created by IntelliJ IDEA.
  User: swebo_000
  Date: 2016-03-29
  Time: 03:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<%
    JPAStore db = new JPAStore();
    Facebook facebook = (Facebook) session.getAttribute("facebook");
    UserEntity currentUser = (UserEntity) db.fetchUser(Long.parseLong(facebook.getId()));
    String facebookId = facebook.getId();
    String profile_img = new String("http://graph.facebook.com/" + facebookId + "/picture");
    //String profile_img = new String("http://graph.facebook.com/" + facebookId + "/picture?type=square");
    String primg = "https://scontent.xx.fbcdn.net/hprofile-xta1/v/t1.0-1/p50x50/11046478_10152321835823078_2224262110139808625_n.jpg?oh=6602521d12b107d4906eb79495c21e5a&oe=578C303F";
%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <style>

        #wrapper{
            line-height:30px;
            /*background-color: white;*/
            height:600px;
            width:1000px;
            float:center;
            border-radius: 10px 10px 0px 0px;
        }
        #wrapperleft{
            line-height:30px;
            background-color: #06845b;
            height:570px;
            width:670px;
            float: left;
            border-radius: 10px 10px 10px 10px;
        }
        #wrapperright{
            line-height:30px;
            background-color: #06845b;
            height:570px;
            width:300px;
            float:right;
            border-radius: 10px 10px 10px 10px;
        }

        #course {
            line-height:30px;
            background-color:#eeeeee;
            height:520px;
            width:250px;
            float:center;
            text-align:0px;
            margin:20px 20px 20px 20px;
            /*border-radius: 10px 10px 10px 10px; */
        }
        #name {
            background-color:#eeeeee;
            width:400px;
            float:left;
            padding:10px;
            margin:20px 20px 20px 20px;
        }
        #description{
            background-color:#eeeeee;
            width:602px;
            height: 270px;
            float:left;
            padding:10px;
            margin:10px 20px 20px 20px;
            text-align: left;
        }
        #footer {
            background-color:#eeeeee;
            color:black;
            clear:both;
            text-align:center;
            padding:5px;
            width: 990px;
            border-radius: 10px 10px 10px 10px;
        }

        h1 {
            color: #1b201e;
            font-family: 'Raleway',sans-serif;
            font-size: 50px;
            font-weight: 800;
            line-height: 50px;
            margin: 0 0 24px;
            text-align: left;
        }
        h2 {
            padding: 1px;
            text-align: left;
            font-family: 'Raleway',sans-serif;
            font-size: 17px;
            font-weight: 10;
            margin: 0 0 0 0px;
        }

        h3 {
            color: #1b201e;
            font-family: 'Raleway',sans-serif;
            font-size: 50px;
            font-weight: 800;
            line-height: 50px;
            margin: 0 0 24px;
            text-align: center;
        }
        h4 {
            color: #1b201e;
            font-family: 'Raleway',sans-serif;
            font-size: 12px;
            font-weight: 10;
            margin: 0 0 0 0px;
            text-align: center;
        }

        smallh {
            color: #1b201e;
            font-family: 'Raleway',sans-serif;
            font-size: 20px;
            font-weight: 10;
            margin: 10px 0 0 0px;
        }
        text {
            text-align: left;
            font-family: 'Raleway',sans-serif;
            font-size: 15px;
            font-weight: 10;
            margin: 0 0 0 0px;
        }

        img {
            border: 1px solid #ddd;
            border-radius: 4px;
            padding: 5px;
            float: left;
            hight: 150px;
            width: 150px;
            margin:20px 20px 20px 20px;
        }



        ul {
            list-style-type: none;
            padding: 0;
            overflow: hidden;
            background-color: #333;
            width: 1000px;
            margin:20px 20px 20px 20px;
        }

        li {
            float: left;
        }

        li a {
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;

        }

        li a:hover:not(.active) {
            background-color: #111;
        }

        .active {
            background-color: #4CAF50;
        }

        body{
            background-color: #87dfc2;
        }
    </style>
</head>
<body>




<center>
    <ul>
        <li><a href="search">Search partner</a></li>
        <li><a href="settings">Settings</a></li>
        <li><a class="active">Profile</a></li>
        <li><a href="proposals">My proposals</a></li>
        <li><a href="labteams">My teams</a></li>
        <li style="float:right"><a class="courses" href="courses">All Courses</a></li>
        <li style="float:right"><a class="logout" href="logout">Logout</a></li>
    </ul>



    <div id="wrapper">
        <div id="wrapperleft">
            <img src="<%=profile_img%>"/>

            <div id="name">
                <h3>${facebook.name}</h3>
                <h4><%=currentUser.getProgram() %> - <%=currentUser.getMaster() %> </h4>
                <h4><%=currentUser.getSchool() %></h4>
                </br>
            </div>

            <div id="description">
                <text><%=currentUser.getComments() %></text>
            </div>
        </div>

        <div id=wrapperright>
            <div id="course">
                <%
                    CourseEntity[] my_courses = (CourseEntity[])request.getAttribute("my_courses");
                    request.setAttribute("my_courses", my_courses);
                %>

                <h4>All courses I'm taking:</h4>
                <c:forEach items="${my_courses}" var="course">
                    <text><c:out value="${course.code}" /> - <c:out value="${course.name}" /></text>
                </c:forEach>
            </div>
        </div>
    </div>
</center>
</body>
</html>



