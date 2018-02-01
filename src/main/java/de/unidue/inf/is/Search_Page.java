package de.unidue.inf.is;


import de.unidue.inf.is.Database.DB_query;
import de.unidue.inf.is.domain.Babble;
import de.unidue.inf.is.domain.Block;
import de.unidue.inf.is.domain.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class Search_Page extends HttpServlet {


    ArrayList<Babble> result;
    DB_query db_query;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String sessionId = (String) session.getAttribute("sessionID");
        session.setAttribute("sessionID", sessionId);

        String searched = request.getParameter("searched");

        try {
            db_query = new DB_query();
            User user = db_query.getUser(sessionId);
            if(user != null) {
                if (searched != null && !searched.equals("")) {
                    result = db_query.getSearch(searched, sessionId);
                    if (result == null || result.isEmpty()) {
                        request.setAttribute("resultbabble", result);
                        request.setAttribute("ss", "nothing");
                        request.setAttribute("searchedFor", searched);
                    } else {
                        request.setAttribute("resultbabble", result);
                        request.setAttribute("ss", "world");
                        request.setAttribute("searchedFor", searched);
                    }
                } else {
                    request.setAttribute("ss", "first");
                    request.setAttribute("resultbabble", result);
                }
                request.setAttribute("loggedUser", sessionId);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/search_page.ftl");
                requestDispatcher.forward(request, response);
            }else{
                response.sendRedirect("/");
            }
        } catch (SQLException e) {
            request.getRequestDispatcher("/bad_requests/db_fail_connect.ftl").forward(request,response);
            e.printStackTrace();
        }finally {
            db_query.close();
        }
    }
}
