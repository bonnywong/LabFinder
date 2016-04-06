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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <style>
        table#t01 tr:nth-child(even) {
            background-color: #94EAB4;
        }
        table#t01 tr:nth-child(odd) {
            background-color: #D9FFD7;
        }
        table#t01 th {
            color: black;
            background-color: #40AE89;
        }
        #wrapper{
            line-height:30px;
            background-color: #199E72;
            height:1170px;
            width:1000px;
            float:center;
            border-radius: 10px 10px 10px 10px;
        }

        #searchtextpart{
            background-color:#eeeeee;
            width:940px;
            height: 90px;
            float:left;
            padding:10px;
            margin:20px 20px 20px 20px;
            text-align: left;
        }

        #searchpart{
            background-color:#eeeeee;
            width:940px;
            height: 250px;
            float:left;
            padding:10px;
            margin: 0 20px 20px 20px;
            text-align: left;
        }

        #resultpart{
            background-color:#eeeeee;
            width:940px;
            height: 690px;
            float:left;
            padding:10px;
            margin: 0 20px 20px 20px;
            text-align: left;
        }

        #hit{
            background-color:white;
            width:880px;
            height: 100px;
            float:left;
            padding:10px;
            margin: 0px 20px 20px 20px;
            text-align: left;
        }
        #requestbuttonpart{
            /*background-color:lightblue;*/
            width:50px;
            height: 50px;
            float:right;
            margin: 20px 20px 20px 0px;
        }

        h1 {
            color: #1b201e;
            font-family: 'Raleway',sans-serif;
            font-size: 62px;
            font-weight: 800;
            line-height: 72px;
            margin: 0 20px 24px ;
            text-align: left;
        }
        h2 {
            color: #1b201e;
            font-family: 'Raleway',sans-serif;
            font-size: 30px;
            font-weight: 800;
            line-height: 72px;
            margin: 0 20px 24px ;
            text-align: left;
        }

        h3 {
            color: #1b201e;
            font-family: 'Raleway',sans-serif;
            font-size: 20px;
            margin: 0px 0 -30px 0;
        }

        h4 {
            color: #1b201e;
            font-family: 'Raleway',sans-serif;
            font-size: 12px;
            font-weight: 10;
            margin: 0 0px 0px 10px;
        }

        text {
            text-align: left;
            font-family: 'Raleway',sans-serif;
            font-size: 15px;
            font-weight: 10;
            margin: 0 0 0 0px;
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
    <title>Searchsite</title>
</head>
<body>
<center>
    <ul>
        <li><a href="#home">Home</a></li>
        <li><a class="active">Search partner</a></li>
        <li><a href="settings">Settings</a> </li>
        <li><a href="profile">Profile</a></li>
        <li style="float:right"><a class="about" href="#about">About</a></li>
        <li style="float:right"><a class="courses" href="courses">All Courses</a></li>
        <li style="float:right"><a class="logout" href="#logout">Logout</a></li>
    </ul>

    <div id="wrapper">
        <div id="searchtextpart">
            <h1> Search for a labpartner! </h1>
        </div>
        <div id="searchpart">
            <%
                CourseEntity[] all_courses = (CourseEntity[])request.getAttribute("all_courses");
                request.setAttribute("all_courses", all_courses);
            %>

            <form action ="search" method="get">
                <input type="hidden" name="search_action" value="findPotentialPartners">
                <input type="hidden" name="user_id" value="<%=currentUser.getId()%>">


                <text>Select a course:</text><br>
                <select name="courses">
                    <c:forEach items="${my_courses}" var="course">
                        <option value="${course.course_id}">${course.code} - ${course.name}</option>
                    </c:forEach>
                </select><br>
                <text>Select ambition:</text><br>
                <select>
                    <option selected>Search for all</option>
                    <option value="A">AAAAAAAAAA!!!!!</option>
                    <option value="B">Prepared to work for a good grade.</option>
                    <option value="C">C sounds fine to me</option>
                    <option value="D">Maybe something above E at least</option>
                    <option value="E">Minimal effort, just want to pass</option>
                </select><br>
                <text>Select school:</text><br>
                <select>
                    <option selected>Search for all</option>
                    <option value="A">KTH</option>
                    <option value="B">SU</option>
                </select><br>
                <text>Select program:</text><br>
                <select>
                    <option selected>Search for all</option>
                    <option value="A">Information technology</option>
                    <option value="B">Computer science</option>
                    <option value="C">Media technology</option>
                </select>
                <input type="submit" value="Find">
            </form>


        </div>
        <div id="resultpart">
            <h2>Result </h2>
            <select>
                <option selected>Filter result</option>
                <option value="Name">Name</option>
                <option value="Ambition">Ambition</option>

            </select>
            <%
                UserEntity[] course_users = (UserEntity[])request.getAttribute("course_users");
                request.setAttribute("course_users", course_users);
                CourseEntity current_course = (CourseEntity) request.getAttribute("current_course");
                request.setAttribute("current_course", current_course);
            %>

            Course: ${current_course.code} - ${current_course.name}
            <table id="t01">
                <tr>
                    <th>Name</th>
                    <th>School</th>
                    <th>Program</th>
                    <th>Ambition</th>
                    <th>Settings</th>
                </tr>
                <c:forEach items="${course_users}" var="user">
                    <tr>
                        <td>${user.name}</td>
                        <td>${user.school}</td>
                        <td>${user.program}</td>
                        <td>${user.ambition}</td>
                        <td>
                            <form action ="search" method="get">
                                <input type="submit" value="Propose">
                            </form>
                            <form action ="search" method="get">
                                <input type="submit" value="Retract">
                            </form>
                            <form action ="search" method="get">
                                <input type="submit" value="Accept">
                            </form>
                        </td>
                    </tr>
                </c:forEach>

            </table>
        </div>
    </div>
</center>
</body>
</html>
