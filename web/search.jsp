<%@ page import="Persist.JPAStore" %>
<%@ page import="Models.UserEntity" %>
<%@ page import="facebook4j.Facebook" %>
<%@ page import="Models.CourseEntity" %>
<%@ page import="Models.ProposalEntity" %><%--
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
    request.setAttribute("currentUser_id", currentUser.getId());
    String facebookId = facebook.getId();
    String profile_img = new String("http://graph.facebook.com/" + facebookId + "/picture");
    //String profile_img = new String("http://graph.facebook.com/" + facebookId + "/picture?type=square");
    String primg = "https://scontent.xx.fbcdn.net/hprofile-xta1/v/t1.0-1/p50x50/11046478_10152321835823078_2224262110139808625_n.jpg?oh=6602521d12b107d4906eb79495c21e5a&oe=578C303F";

    UserEntity[] course_users = (UserEntity[])request.getAttribute("course_users");
    request.setAttribute("course_users", course_users);
    CourseEntity current_course = (CourseEntity) request.getAttribute("current_course");
    request.setAttribute("current_course", current_course);

    ProposalEntity[] received_proposals = (ProposalEntity[]) request.getAttribute("received_proposals");
    ProposalEntity[] sent_proposals = (ProposalEntity[]) request.getAttribute("sent_proposals");
%>

<head>
    <link rel="stylesheet" type="text/css" href="search.css">
    <title>Searchsite</title>
    <script src="http://www.kryogenix.org/code/browser/sorttable/sorttable.js" type="text/javascript"></script>
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
            <div id="searchpartleft">
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
                    <select name="grade">
                        <option selected value="all">Search for all</option>
                        <option value="1">AAAAAAAAAA!!!!!</option>
                        <option value="2">Prepared to work for a good grade.</option>
                        <option value="3">C sounds fine to me</option>
                        <option value="4">Maybe something above E at least</option>
                        <option value="5">Minimal effort, just want to pass</option>
                    </select><br>
                    <text>Select school:</text><br>
                    <select name="school">
                        <option selected value="all">Search for all</option>
                        <option value="A">KTH</option>
                        <option value="B">SU</option>
                    </select><br>
                    <text>Select program:</text><br>
                    <select name="program">
                        <option selected value="all">Search for all</option>
                        <option value="A">Information technology</option>
                        <option value="B">Computer science</option>
                        <option value="C">Media technology</option>
                    </select>
                    <input type="submit" value="Find">
                </form>

            </div>
            <div id="searchpartright"><!-- HERE WE ARE INSERTING THE LIST OF ALL AVAILABLE PROPOSALS WE WISH TO RETRACT AND ACCEPT-->
                Course: ${current_course.code} - ${current_course.name}
                <table id="t01" class="sortable">
                    <tr>
                        <th>Name</th>
                        <th>Course</th>
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


                                <form action ="search" method="post">
                                    <input type="hidden" name="propose" value="yes">
                                    <input type="hidden" name="proposed_user_id" value="${user.id}">
                                    <input type="hidden" name="course_id" value="${current_course.course_id}">
                                    <input type="submit" value="Propose">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>

                </table>
            </div>
        </div>
        <div id="resultpart">
            <h2>Result </h2>
            Course: ${current_course.code} - ${current_course.name}
            <table id="t01" class="sortable">
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
                            <form action ="search" method="post">
                                <input type="hidden" name="propose" value="yes">
                                <input type="hidden" name="proposed_user_id" value="${user.id}">
                                <input type="hidden" name="course_id" value="${current_course.course_id}">
                                <input type="submit" value="Propose">
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
