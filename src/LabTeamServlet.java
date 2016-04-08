import Models.*;
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
 * Created by swebo_000 on 2016-04-08.
 */
public class LabTeamServlet extends HttpServlet {
    JPAStore db = new JPAStore();

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        String action = request.getParameter("action");

        if (action != null && action.equals("Leave")) {
            String id = request.getParameter("team_id");
            db.removeLabTeam(Integer.parseInt(id));
        }

        attachLabs(request, response);

        try {
            RequestDispatcher rd =
                    request.getRequestDispatcher("labteam.jsp");
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

        attachLabs(request, response);

        try {
            RequestDispatcher rd = request.getRequestDispatcher("labteam.jsp");
            rd.forward(request, response);
        }
        catch(ServletException e){
            System.out.print(e.getMessage());
        }
        catch(IOException e){
            System.out.print(e.getMessage());
        }
    }

    private void attachLabs(HttpServletRequest request, HttpServletResponse response) {
        List<LabTeamEntity> teams = new ArrayList<LabTeamEntity>();
        List<LabTeamHelper> helper = new ArrayList<LabTeamHelper>();

        try {
            Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
            UserEntity currentUser = (UserEntity) db.fetchUser(Long.parseLong(facebook.getId()));
            teams = db.fetchOwnLabTeams(currentUser.getId());

        } catch (FacebookException e) {
            e.printStackTrace();
        }

        for (LabTeamEntity t : teams) {
            UserEntity userA = db.fetchUser(t.getUserAId());
            UserEntity userB = db.fetchUser(t.getUserBId());
            CourseEntity course = db.fetchCourse(t.getCourseId());
            LabTeamHelper h = new LabTeamHelper(userA.getName(), userB.getName(), userA.getEmail(), userB.getEmail(), course.getName(), course.getCode(), t.getId());
            helper.add(h);
        }

        LabTeamHelper[] labteams = new LabTeamHelper[helper.size()];
        labteams = helper.toArray(labteams);
        request.setAttribute("labteams", labteams);
    }
}
