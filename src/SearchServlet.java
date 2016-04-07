import Models.CourseEntity;
import Models.EnrollEntity;
import Models.ProposalEntity;
import Models.UserEntity;
import Persist.JPAStore;
import com.sun.deploy.util.ArrayUtil;
import facebook4j.Facebook;
import facebook4j.FacebookException;

import javax.persistence.Query;
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
public class SearchServlet extends HttpServlet {
    JPAStore db = new JPAStore();

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
                System.out.println("\n\n\n\nUSER " + facebook.getName() + " CREATED A NEW PROPOSAL!\n\n\n\n");
            } catch (FacebookException e) {
                e.printStackTrace();
            }
        }

        //POST PROCESSING, EVERYTHING UNCONDITIONAL TO BE DONE IN PREPARATION OF THE SEARCH VIEW
        ServlAux.attachMyCourses(request, response);
        ServlAux.attachAllCourses(request, response);
        ServlAux.attachAllAmbitions(request, response);
        attachUserProposals(request, response);

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
            String grade = request.getParameter("grade");
            String school = request.getParameter("school");
            String program = request.getParameter("program");

            CourseEntity current_course = null;
            for(CourseEntity c : db.fetchAllCourses()){
                if(c.getCourse_id() == Integer.parseInt(course_id)){
                    current_course = c;
                    break;
                }
            }


            UserEntity[] users = filter(course_id, grade, school, program, user_id);

            request.setAttribute("course_users", users);
            request.setAttribute("current_course", current_course);
        }

        ServlAux.attachMyCourses(request, response);
        ServlAux.attachAllCourses(request, response);
        ServlAux.attachAllAmbitions(request, response);
        attachUserProposals(request, response);


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

    /**
     * Filtering list of enrolled users based on course, grade, school and program.
     */

    private UserEntity[] filter(String course_id, String grade, String school, String program, String user_id) {

        //Used for filtering/ambition
        List<UserEntity> filteredUsers = new ArrayList<UserEntity>();
        List<UserEntity> tempUsers = new ArrayList<UserEntity>();
        List<EnrollEntity> tempEnroll = new ArrayList<EnrollEntity>();
        List<EnrollEntity> enrollments = db.fetchAllEnrollsByCourse(Integer.parseInt(course_id));


        //Filter on ambition
        if (!grade.equals("all")) {
            for (EnrollEntity e : enrollments) {
                if ((e.getAmbition() != Integer.parseInt(grade))) {
                    tempEnroll.add(e); //Find grades that doesn't match.
                }
            }
            enrollments.removeAll(tempEnroll);
        }
        //Filter on school and program. Required to fetch the user for this part.
        for (EnrollEntity e : enrollments) {
            filteredUsers.add(db.fetchUser(e.getUser_id()));
        }

        if (!school.equals("all")) {
            for (UserEntity u : filteredUsers) {
                if (!u.getSchool().equals(school)) {
                    tempUsers.add(u); //Find all users that doesn't match.
                }
            }
            filteredUsers.removeAll(tempUsers);
        }

        if (!program.equals("all")) {
            for (UserEntity u : filteredUsers) {
                if (!u.getProgram().equals(program)) {
                    tempUsers.add(u); //Find all users that doesn't match.
                }
            }
            filteredUsers.removeAll(tempUsers);
        }

        //Removing the requester from the list.
        for (UserEntity e : filteredUsers) {
            if (e.getId() == Integer.parseInt(user_id)) {
                tempUsers.add(e);
            }
        }

        filteredUsers.removeAll(tempUsers);

        UserEntity[] userArray = new UserEntity[filteredUsers.size()];
        userArray = filteredUsers.toArray(userArray);
        for(int i = 0; i < userArray.length; i++){
            db.addAmbitionTag(userArray[i], Integer.parseInt(course_id));
        }

        return userArray;
    }

    /**
     * Get all proposals that include us as a user
     * @return
     */
    private void attachUserProposals(HttpServletRequest request, HttpServletResponse response){
        JPAStore db = new JPAStore();

        List<ProposalEntity> rp = db.fetchAllReceivedProposals(ServlAux.getUserId(request, response));
        List<ProposalEntity> sp = db.fetchAllSentProposals(ServlAux.getUserId(request, response));
        ProposalEntity[] received_proposals = new ProposalEntity[rp.size()];
        ProposalEntity[] sent_proposals = new ProposalEntity[sp.size()];
        received_proposals = rp.toArray(received_proposals);
        sent_proposals = sp.toArray(sent_proposals);
        request.setAttribute("received_proposals", received_proposals);
        request.setAttribute("sent_proposals", sent_proposals);

    }




}






