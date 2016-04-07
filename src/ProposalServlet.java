import Models.ProposalEntity;
import Persist.JPAStore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Keim on 2016-04-07.
 */

public class ProposalServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Proposal Servlet was run!");
        String message = "null";

        ServlAux.attachMyCourses(request, response);
        attachUserProposals(request, response);

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
