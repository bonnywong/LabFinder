import Models.CourseEntity;
import Models.EnrollEntity;
import Models.ProposalEntity;
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


/**
 * Created by swebo_000 on 2016-03-29.
 */
public class SearchServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        String settings_action = request.getParameter("settings_action");
        String message = "null";

        String propose = request.getParameter("propose");
        if(propose != null && propose.equals("yes")){
            String proposed_user_id = request.getParameter("proposed_user_id");
            String proposer_user_id = "";
            String course_id = request.getParameter("course_id");
            try {
                JPAStore db = new JPAStore();
                Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
                UserEntity currentUser = (UserEntity) db.fetchUser(Long.parseLong(facebook.getId()));
                proposer_user_id = Integer.toString(currentUser.getId());
                db.persistProposal(proposed_user_id, proposer_user_id, course_id);
            } catch (FacebookException e) {
                e.printStackTrace();
            }
        }

        ServlAux.attachMyCourses(request, response);
        ServlAux.attachAllCourses(request, response);

        try {
            RequestDispatcher rd =
                    request.getRequestDispatcher("search.jsp?message=" + "Debug: " + message);
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
        String search_action = request.getParameter("search_action");

        if (search_action != null && search_action.equals("findPotentialPartners")) {
            String user_id = request.getParameter("user_id");
            String course_id = request.getParameter("courses");

            JPAStore db = new JPAStore();
            UserEntity[] users = db.getUsersWithCourse(course_id);

            CourseEntity current_course = null;
            for(CourseEntity c : db.fetchAllCourses()){
                if(c.getCourse_id() == Integer.parseInt(course_id)){
                    current_course = c;
                    break;
                }
            }

            request.setAttribute("course_users", users);
            request.setAttribute("current_course", current_course);
        }

        ServlAux.attachMyCourses(request, response);
        ServlAux.attachAllCourses(request, response);

        try {
            RequestDispatcher rd =
                    request.getRequestDispatcher("search.jsp");
            rd.forward(request, response);
        }
        catch(ServletException e){
            System.out.print(e.getMessage());
        }
        catch(IOException e){
            System.out.print(e.getMessage());
        }
    }
















    private ProposalEntity[] getUserProposals(){
        return null;
    }




}
