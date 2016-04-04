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
public class ProfileServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Profile Servlet was run!");
        String message = "null";

        attachMyCourses(request, response);

        try {
            RequestDispatcher rd = request.getRequestDispatcher("profile.jsp?message=" + "Debug: " + message);
            rd.forward(request, response);
        }
        catch(ServletException e){
            System.out.print(e.getMessage());
        }
        catch(IOException e){
            System.out.print(e.getMessage());
        }
    }




    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Profile Servlet was run!");
        String message = "null";

        String profile_action = request.getParameter("profile_action");
        if (profile_action != null && profile_action.equals("insertProfileUpdate")) {

            String name = request.getParameter("user_name");
            Long fb_id = Long.parseLong(request.getParameter("user_fb_id"));
            String email = request.getParameter("email");
            String school = request.getParameter("school");
            String program = request.getParameter("program");
            String master = request.getParameter("master");
            String comments = request.getParameter("comments");

            UserEntity user = new UserEntity();
            user.setName(name);
            user.setEmail(email);
            user.setComments(comments);
            user.setFacebookId(fb_id);
            user.setSchool(school);
            user.setProgram(program);
            user.setMaster(master);

            JPAStore db = new JPAStore();
            db.updateUser(user);
        }






        attachMyCourses(request, response);

        try {
            RequestDispatcher rd = request.getRequestDispatcher("profile.jsp?message=" + "Debug: " + message);
            rd.forward(request, response);
        }
        catch(ServletException e){
            System.out.print(e.getMessage());
        }
        catch(IOException e){
            System.out.print(e.getMessage());
        }
    }




    private void attachMyCourses(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("my_courses", getUserEnrolledCourses(request, response));
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
