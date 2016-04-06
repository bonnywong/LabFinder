import Models.CourseEntity;
import Models.EnrollEntity;
import Models.UserEntity;
import Persist.JPAStore;
import facebook4j.Facebook;
import facebook4j.FacebookException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by swebo_000 on 2016-03-29.
 */
public class SettingsServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        String settings_action = request.getParameter("settings_action");
        String message = "null";


        if (settings_action != null && settings_action.equals("enrollInCourse")) {

            String course_id = request.getParameter("courses");
            String ambition = request.getParameter("ambition");
            String user_id = request.getParameter("user_id");

            System.out.println("New enrollment, uid: " + user_id + ", cid: " + course_id + ", ambition: " + ambition);

            EnrollEntity enrollment = new EnrollEntity();
            enrollment.setCourse_id(Integer.parseInt(course_id));
            enrollment.setAmbition(Integer.parseInt(ambition));
            enrollment.setUser_id(Integer.parseInt(user_id));


            if(!ServlAux.courseIsEnrolled(enrollment, request, response)){
                JPAStore db = new JPAStore();
                db.persistEnroll(enrollment);
            }
        }

        if (settings_action != null && settings_action.equals("unenrollInCourse")) {

            String course_id = request.getParameter("courses");
            String user_id = request.getParameter("user_id");

            EnrollEntity enrollment = new EnrollEntity();
            enrollment.setCourse_id(Integer.parseInt(course_id));
            enrollment.setAmbition(1);
            enrollment.setUser_id(Integer.parseInt(user_id));

            if(ServlAux.courseIsEnrolled(enrollment, request, response)){
                JPAStore db = new JPAStore();
                db.removeEnroll(enrollment);
            }
        }

        ServlAux.attachMyCourses(request, response);
        ServlAux.attachAllCourses(request, response);

        try {
            RequestDispatcher rd =
                    request.getRequestDispatcher("settings.jsp?message=" + "Debug: " + message);
            rd.forward(request, response);
        }
        catch(ServletException e){
            System.out.print(e.getMessage());
        }
        catch(IOException e){
            System.out.print(e.getMessage());
        }
    }





    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String message = "null";

        ServlAux.attachMyCourses(request, response);
        ServlAux.attachAllCourses(request, response);

        try {
            RequestDispatcher rd =
                    request.getRequestDispatcher("settings.jsp?message=" + "Debug: " + message);
            rd.forward(request, response);
        }
        catch(ServletException e){
            System.out.print(e.getMessage());
        }
        catch(IOException e){
            System.out.print(e.getMessage());
        }
    }

















}
