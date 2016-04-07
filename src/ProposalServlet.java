import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Keim on 2016-04-07.
 */

public class ProposalServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Proposal Servlet was run!");
        String message = "null";

        ServlAux.attachMyCourses(request, response);

        try {
            RequestDispatcher rd = request.getRequestDispatcher("proposals.jsp?message=" + "Debug: " + message);
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
