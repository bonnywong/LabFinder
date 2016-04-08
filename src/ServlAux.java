import Models.AmbitionEntity;
import Models.CourseEntity;
import Models.EnrollEntity;
import Models.UserEntity;
import Persist.JPAStore;
import facebook4j.Facebook;
import facebook4j.FacebookException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Henraisse on 2016-04-06.
 */
public class ServlAux {


    public static CourseEntity[] getUserEnrolledCourses(HttpServletRequest request, HttpServletResponse response){
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


    public static boolean courseIsEnrolled(EnrollEntity c, HttpServletRequest request, HttpServletResponse response){

        CourseEntity[] courses = getUserEnrolledCourses(request, response);
        if (courses.length == 0) {
            return false;
        }
        if(courses[0].getCode().equals("FAILCOURSE")){
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

    public static void attachAllCourses(HttpServletRequest request, HttpServletResponse response) {
        //Here we attach the list of all courses to be returned to the jsp
        List<CourseEntity> arrayList = new JPAStore().fetchAllCourses();
        CourseEntity[] courselist = new CourseEntity[arrayList.size()];
        courselist = arrayList.toArray(courselist);
        request.setAttribute("all_courses", courselist);
    }


    public static void attachMyCourses(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("my_courses", getUserEnrolledCourses(request, response));
    }


    public static int getUserId(HttpServletRequest request, HttpServletResponse response){

        try {
            JPAStore db = new JPAStore();
            Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
            UserEntity currentUser = null;
            currentUser = (UserEntity) db.fetchUser(Long.parseLong(facebook.getId()));
            int user_id = currentUser.getId();
            return user_id;
        } catch (FacebookException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void attachAllAmbitions(HttpServletRequest request, HttpServletResponse response) {
        JPAStore db = new JPAStore();
        List<AmbitionEntity> ambitions = db.fetchAllAmbitions();
        AmbitionEntity[] ambs = new AmbitionEntity[ambitions.size()];
        ambs = ambitions.toArray(ambs);
        System.out.println("\n\n\n\n list size of ambitions is: " + ambitions.size() + "\n\n\n\n");
        request.setAttribute("ambitions", ambs);
    }
}
