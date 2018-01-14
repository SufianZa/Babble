package de.unidue.inf.is;

import de.unidue.inf.is.Database.DB_query;
import de.unidue.inf.is.domain.Babble;
import de.unidue.inf.is.domain.Message;

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            db_query = new DB_query();
            HttpSession session = request.getSession();
            ArrayList<Message> list = new ArrayList<>();
            Message m = new Message(1, "hi dbUserdbUserdbUserdbUserdbUserdbUserdbUserdbUserdbUserdbUserdbUserdbUserdbUserdbUserdbUserdbUserdbUserdbUser", "dbuser", "student_1", new Timestamp(System.currentTimeMillis()));
            Message m2 = new Message(2, "sub", "student_1", "dbuser", new Timestamp(System.currentTimeMillis()));
            Message m3 = new Message(3, "hi hoooh", "student_1", "dbuser", new Timestamp(System.currentTimeMillis()));
            Message m4 = new Message(4, "hi sdfsadfasdfasdfasdfasdf", "student_1", "dbuser", new Timestamp(System.currentTimeMillis()));
            Message m5 = new Message(6, "hallo", "dbuser", "student_1", new Timestamp(System.currentTimeMillis()));
            list.add(m);
            list.add(m2);
            list.add(m3);
            list.add(m4);
            list.add(m5);
            
            String sessionId = (String) session.getAttribute("sessionID");
            String profile = (String) session.getAttribute("profile");
            
            //System.err.println("Session und Profil: " + sessionId + " " + profile);
            ArrayList<Message> mlist = db_query.getMessages(sessionId, profile);

            request.setAttribute("loggedUser", sessionId);
            request.setAttribute("profile", profile);
            request.setAttribute("messages", mlist);

            request.getRequestDispatcher("/babble_message.ftl").forward(request, response);
        } catch (SQLException e) {
            request.getRequestDispatcher("/bad_requests/db_fail_connect.ftl").forward(request,response);
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
