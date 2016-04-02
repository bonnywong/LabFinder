import Models.CourseEntity;
import Persist.JPAStore;

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
public class CoursesServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String message = "null";
        List<CourseEntity> arrayList = new JPAStore().fetchAllCourses();
        CourseEntity[] courselist = new CourseEntity[arrayList.size()];

        int i = 0;
        for(CourseEntity c : arrayList){
            courselist[i] = c;
            i++;
        }
        request.setAttribute("all_courses", courselist);




        String courses_action = request.getParameter("courses_action");
        if (courses_action != null && courses_action.equals("insertNewCourse")) {


            String course_code = request.getParameter("course_code");
            String course_name = request.getParameter("course_name");
            String course_description = request.getParameter("course_description");


            CourseEntity course = new CourseEntity();
            course.setCode(course_code);
            course.setName(course_name);
            course.setDescription(course_description);


            JPAStore db = new JPAStore();
            db.persistCourse(course);
        }





        try {
            RequestDispatcher rd =
                    request.getRequestDispatcher("courses.jsp?message=" + "Debug: " + message);
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
