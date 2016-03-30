import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by swebo_000 on 2016-03-29.
 */
public class SettingsServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        String message = "null";
        if (action != null) {

        } else {
            message = "Refresh";
        }

        try {
            RequestDispatcher rd =
                    request.getRequestDispatcher("settings.jsp?message=" + "Debug: " + message);
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
