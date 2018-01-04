package de.unidue.inf.is;

import de.unidue.inf.is.Database.DB_query;
import de.unidue.inf.is.domain.Babble;
import de.unidue.inf.is.domain.Massage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;


public final class Babble_Massage extends HttpServlet {

    private static final long serialVersionUID = 1L;

    DB_query db_query = new DB_query();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Massage> list = new ArrayList<>();
        Massage m = new Massage(1,"hi dbUserdbUserdbUserdbUserdbUserdbUserdbUserdbUserdbUserdbUserdbUserdbUserdbUserdbUserdbUserdbUserdbUserdbUser","dbuser","student_1",new Timestamp(System.currentTimeMillis()));
        Massage m2 = new Massage(2,"sub","student_1","dbuser",new Timestamp(System.currentTimeMillis()));
        Massage m3 = new Massage(3,"hi hoooh","student_1","dbuser",new Timestamp(System.currentTimeMillis()));
        Massage m4 = new Massage(4,"hi sdfsadfasdfasdfasdfasdf","student_1","dbuser",new Timestamp(System.currentTimeMillis()));
        Massage m5 = new Massage(6,"hallo","dbuser","student_1",new Timestamp(System.currentTimeMillis()));
            list.add(m);
            list.add(m2);
            list.add(m3);
            list.add(m4);
            list.add(m5);
        String sessionId = (String) session.getAttribute("sessionID");
        request.setAttribute("loggedUser", sessionId);
        request.setAttribute("massages", list);

        request.getRequestDispatcher("/babble_massage.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
