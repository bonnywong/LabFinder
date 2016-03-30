import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by swebo_000 on 2016-03-29.
 */
public class MainServlet extends HttpServlet{

    public void init() {
        //TODO: Create and init the JPAStore
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String message = "";
        String action = request.getParameter("action");

        if (action != null) {
            if (action.equals("settings")) {
                //TODO: Logic for settings.
                try {
                    response.sendRedirect(request.getContextPath() + "/settings.jsp");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            message = "Not parameter provided.";
        }
    }
}
