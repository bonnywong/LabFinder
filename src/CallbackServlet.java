import Models.UserEntity;
import Persist.JPAStore;
import facebook4j.Facebook;
import facebook4j.FacebookException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by swebo_000 on 2016-03-27.
 */
public class CallbackServlet extends HttpServlet{

    JPAStore db = new JPAStore();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("In Callback.");
        Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
        String OAuthCode = request.getParameter("code");
        System.out.println("OAuthCode: " + OAuthCode);
        try {
            facebook.getOAuthAccessToken(OAuthCode);
            request.getSession().setAttribute("name", facebook.getName());
            System.out.println("Context Path: " + request.getContextPath());
        } catch (Exception e) {
            System.out.println("Callback failed.");
            System.out.println(e.getMessage());
        }

        //TODO: Persist the user here.
        UserEntity user = new UserEntity();
        try {
            user.setName(facebook.getName());
            user.setFacebookId(Long.parseLong(facebook.getId()));
            db.persistUser(user);
        } catch (FacebookException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
