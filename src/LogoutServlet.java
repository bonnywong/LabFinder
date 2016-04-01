import facebook4j.Facebook;
import facebook4j.internal.http.HttpRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by swebo_000 on 2016-03-27.
 */
public class LogoutServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Logout Servlet Called!");
        Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
        String accessToken = "";
        try {
            accessToken = facebook.getOAuthAccessToken().getToken();

            //Remove app permissions
            facebook.deleteAllPermissions();

            //Facebook Logout
            StringBuffer next = request.getRequestURL();
            int index = next.lastIndexOf("/");
            next.replace(index+1, next.length(), "");
            response.sendRedirect("http://www.facebook.com/logout.php?next=" + next.toString() + "&access_token=" + accessToken);
            response.sendRedirect("localhost:8080/labfinder/"); //Not working?
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        request.getSession().invalidate();
    }
}
