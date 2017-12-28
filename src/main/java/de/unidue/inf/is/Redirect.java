package de.unidue.inf.is;

import de.unidue.inf.is.Database.DB_query;
import de.unidue.inf.is.domain.Babble;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.stores.UserStore;

import javax.servlet.ServletContext;
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


public final class Redirect extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("sessionID",request.getParameter("sessionID"));
        String sessionID = (String) session.getAttribute("sessionID");
        System.out.println("logged in as "+ sessionID);

        request.setAttribute("userProfile",sessionID);
        request.getRequestDispatcher("/redirect.ftl").forward(request, response);

    }
}

