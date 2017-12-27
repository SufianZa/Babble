package de.unidue.inf.is;

import de.unidue.inf.is.Database.DB_query;
import de.unidue.inf.is.domain.Babble;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.stores.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Einfaches Beispiel, das die Verwendung des {@link UserStore}s zeigt.
 */


public final class Profile_View extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static List<Babble> babbles = new ArrayList<>();


    private User eingeloggter_user= new User();
    DB_query db_query = new DB_query();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("sessionID",request.getParameter("sessionID"));
        String sessionID = (String) session.getAttribute("sessionID");
        eingeloggter_user.setUsername(sessionID); //TODO get Information by name for logged in user
        System.out.println("logged in as "+ sessionID);

        request.setAttribute("loggedUser",sessionID);
        request.setAttribute("babble", babbles);
        request.setAttribute("name","hahah");
        request.setAttribute("status","asdasdasd");
        request.setAttribute("username","asdasdasdasdasdas");

        request.getRequestDispatcher("/profile_view.ftl").forward(request, response);

    }
}

