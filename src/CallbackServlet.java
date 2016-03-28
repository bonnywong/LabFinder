import facebook4j.Facebook;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by swebo_000 on 2016-03-27.
 */
public class CallbackServlet extends HttpServlet{

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

        response.sendRedirect(request.getContextPath() + "/");
    }
}
