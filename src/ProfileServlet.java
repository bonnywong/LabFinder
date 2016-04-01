import Models.UserEntity;
import Persist.JPAStore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by swebo_000 on 2016-03-29.
 */
public class ProfileServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
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

}
