package de.unidue.inf.is;

import de.unidue.inf.is.Database.DB_query;
import de.unidue.inf.is.domain.Babble;
import de.unidue.inf.is.domain.Message;
import de.unidue.inf.is.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;


public final class Babble_Message extends HttpServlet {

    private static final long serialVersionUID = 1L;

    DB_query db_query;
    User user;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            db_query = new DB_query();
            HttpSession session = request.getSession();
            String sessionId = (String) session.getAttribute("sessionID");
            String profile = (String) session.getAttribute("profile");

            //reset the token
            session.setAttribute("sessionID", sessionId);
            session.setAttribute("profile", profile);

            user = db_query.getUser(sessionId);
            User user2 = db_query.getUser(profile);
            if (user != null && user2 != null) {
                //System.err.println("Session und Profil: " + sessionId + " " + profile);
                ArrayList<Message> mlist = db_query.getMessages(sessionId, profile);

                request.setAttribute("loggedUser", sessionId);
                request.setAttribute("profile", profile);
                request.setAttribute("messages", mlist);

                request.getRequestDispatcher("/babble_message.ftl").forward(request, response);
            } else {
                response.sendRedirect("/");
            }
        } catch (SQLException e) {
            request.getRequestDispatcher("/bad_requests/db_fail_connect.ftl").forward(request, response);
            e.printStackTrace();
        } finally {
            db_query.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String sessionId = (String) session.getAttribute("sessionID");
        String profile = (String) session.getAttribute("profile");
        String url =  request.getRequestURI();

        try {
            db_query = new DB_query();
            User user = db_query.getUser(sessionId);
            if (user != null) {
                String message = request.getParameter("message");
                db_query.writeMessage(sessionId, profile, message);
                response.sendRedirect(url);
            } else {
                response.sendRedirect("/");
            }
            db_query.complete();
        } catch (SQLException e) {
            request.getRequestDispatcher("/bad_requests/db_fail_connect.ftl").forward(request, response);
            e.printStackTrace();
        } finally {
            db_query.close();
        }
    }
}
