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


            if(!courseIsEnrolled(enrollment, request, response)){
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

            if(courseIsEnrolled(enrollment, request, response)){
                JPAStore db = new JPAStore();
                db.removeEnroll(enrollment);
            }
        }

        attachMyCourses(request, response);
        attachAllCourses(request, response);

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

        attachMyCourses(request, response);
        attachAllCourses(request, response);

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




    private void attachAllCourses(HttpServletRequest request, HttpServletResponse response) {
        //Here we attach the list of all courses to be returned to the jsp
        List<CourseEntity> arrayList = new JPAStore().fetchAllCourses();
        CourseEntity[] courselist = new CourseEntity[arrayList.size()];
        courselist = arrayList.toArray(courselist);
//        int i = 0;
//        for(CourseEntity c : arrayList){
//            courselist[i] = c;
//            i++;
//            System.out.println("Course list: " + c.getName());
//        }
        request.setAttribute("all_courses", courselist);
    }

    private void attachMyCourses(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("my_courses", getUserEnrolledCourses(request, response));
    }

    private boolean courseIsEnrolled(EnrollEntity c, HttpServletRequest request, HttpServletResponse response){
        CourseEntity[] courses = getUserEnrolledCourses(request, response);

        if(courses == null || courses.length == 0 || courses[0].getCode().equals("FAILCOURSE")){
            return false;
        }
        else{
            for(CourseEntity ce : courses){
                if(ce.getCourse_id() == c.getCourse_id()){
                    return true;
                }
            }
            return false;
        }
    }


    private CourseEntity[] getUserEnrolledCourses(HttpServletRequest request, HttpServletResponse response){
        try {
            JPAStore db = new JPAStore();
            Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
            UserEntity currentUser = null;
            currentUser = (UserEntity) db.fetchUser(Long.parseLong(facebook.getId()));
            int user_id = currentUser.getId();
            List<EnrollEntity> enrolls = new JPAStore().fetchAllEnrolls( user_id);
            List<CourseEntity> courses = new JPAStore().fetchAllCourses();
            ArrayList<CourseEntity> enrolledCourses = new ArrayList<CourseEntity>();

            CourseEntity[] courselist = new CourseEntity[enrolls.size()];
            int i = 0;
            for(EnrollEntity e : enrolls){
                for(CourseEntity c : courses){
                    if(e.getCourse_id() == c.getCourse_id()){
                        enrolledCourses.add(c);
                        break;
                    }
                }
            }
            return enrolledCourses.toArray(courselist);

        } catch (FacebookException e) {
            e.printStackTrace();
        }
        CourseEntity dummy = new CourseEntity();
        dummy.setCode("FAILCOURSE");

        return new CourseEntity[]{dummy};
    }







}
