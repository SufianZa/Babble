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
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public final class Redirect extends HttpServlet {

    private static final long serialVersionUID = 1L;

    User eingeloggter_user;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fieldText = request.getParameter("sessionID");
        DB_query db_query = null;
        try {
            db_query = new DB_query();
            eingeloggter_user = db_query.getUser(fieldText);
            if (eingeloggter_user == null) {
                request.setAttribute("user", "wrong");
                request.getRequestDispatcher("/home_login.ftl").forward(request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("sessionID", fieldText);
                String sessionID = (String) session.getAttribute("sessionID");
                System.out.println("logged in as " + sessionID);


                request.setAttribute("userProfile", sessionID);
                request.getRequestDispatcher("/redirect.ftl").forward(request, response);
            }
        } catch (SQLException e) {
            request.getRequestDispatcher("/bad_requests/db_fail_connect.ftl").forward(request,response);
            e.printStackTrace();
        }
    }
}

