<%@ page import="Persist.JPAStore" %>
<%@ page import="Models.UserEntity" %>
<%@ page import="facebook4j.Facebook" %>
<%@ page import="Models.CourseEntity" %>
<%@ page import="Models.AmbitionEntity" %><%--
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

    AmbitionEntity[] ambitions = (AmbitionEntity[]) request.getAttribute("ambitions");
    request.setAttribute("ambitions", ambitions);
%>



<head>
    <link rel="stylesheet" type="text/css" href="settings.css">
</head>
<body>




<center>
    <ul>
        <li><a href="search">Search partner</a></li>
        <li><a class="active">Settings</a></li>
        <li><a href="profile">Profile</a></li>
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
                <form action ="profile" method="post">
                    <input type="hidden" name="profile_action" value="insertProfileUpdate">

                    <input type="hidden" name="user_name" value=${facebook.name}>
                    <input type="hidden" name="user_fb_id" value=${facebook.id}>
                    <h5>
                    Current Email:
                    <input type="text" name="email" value="<%=currentUser.getEmail()%>">
                    <br>

                    Current School:
                    <input type="text" name="school" value="<%=currentUser.getSchool()%>">
                    <br>

                    Current Program:
                    <input type="text" name="program" value="<%=currentUser.getProgram()%>">
                    <br>

                    Current Master:
                    <input type="text" name="master" value="<%=currentUser.getMaster()%>">
                    <br>

                    Current Comment:
                    <input type="text" name="comments" value="<%=currentUser.getComments()%>">
                    <br>
                    </h5>
                    <input type="submit" value="Submit.">

                </form>


                <%
                    CourseEntity[] all_courses = (CourseEntity[])request.getAttribute("all_courses");
                    request.setAttribute("all_courses", all_courses);
                %>


                <h5>
                    <form action ="settings" method="post">
                    <input type="hidden" name="settings_action" value="enrollInCourse">
                    <input type="hidden" name="user_id" value="<%=currentUser.getId()%>">
                    Enroll in course:
                    <select name="courses">
                    <c:forEach items="${all_courses}" var="course">
                        <option value="${course.course_id}">${course.code} - ${course.name}</option>
                    </c:forEach>
                    </select>


                        <select name="ambition">
                            <c:forEach items="${ambitions}" var="ambition">
                                <option selected value="${ambition.id}">${ambition.id} - ${ambition.description}</option>
                            </c:forEach>
                        </select><br>


                    <input type="submit" value="Enroll">
                </form>
                </h5>


                <h5>
                    <form action ="settings" method="post">
                    <input type="hidden" name="settings_action" value="unenrollInCourse">
                    <input type="hidden" name="user_id" value="<%=currentUser.getId()%>">
                    Unenroll in course:
                    <select name="courses">
                        <c:forEach items="${my_courses}" var="course">
                            <option value="${course.course_id}">${course.code} - ${course.name}</option>
                        </c:forEach>
                    </select>

                    <input type="submit" value="Unenroll">
                </form>
                </h5>

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