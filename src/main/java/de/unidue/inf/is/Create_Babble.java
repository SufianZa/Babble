package de.unidue.inf.is;

import de.unidue.inf.is.Database.DB_query;
import de.unidue.inf.is.domain.Babble;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.utils.DBUtil;
import org.apache.commons.net.ntp.TimeStamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;


public final class Create_Babble extends HttpServlet {

    private static final long serialVersionUID = 1L;

    DB_query db_query;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String sessionId = (String) session.getAttribute("sessionID");
        try {
            db_query = new DB_query();
            User user = db_query.getUser(sessionId);
            if (user != null) {
                //reset token
                session.setAttribute("sessionID", sessionId);

                request.setAttribute("loggedUser", sessionId);
                request.getRequestDispatcher("create_babble.ftl").forward(request, response);
            }else{
                response.sendRedirect("/");
            }
        } catch (SQLException e) {
            request.getRequestDispatcher("/bad_requests/db_fail_connect.ftl").forward(request, response);
            e.printStackTrace();
            db_query.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String sessionId = (String) session.getAttribute("sessionID");
        try {
            db_query = new DB_query();

            User user = db_query.getUser(sessionId);
            if (user != null) {
                //reset token
                session.setAttribute("sessionID", sessionId);

                String author = sessionId;
                String inhalt = req.getParameter("babble");
                Babble babble = new Babble(author, inhalt);
                db_query.makeBabble(babble);
                db_query.complete();
                db_query.close();
                resp.sendRedirect("profile_view/" + sessionId);
            } else {
                resp.sendRedirect("/");
            }
        } catch (SQLException e) {
            req.getRequestDispatcher("/bad_requests/db_fail_connect.ftl").forward(req, resp);
            e.printStackTrace();
            db_query.close();
        }
    }
}
