package de.unidue.inf.is;

import de.unidue.inf.is.Database.DB_query;
import de.unidue.inf.is.domain.Babble;
import de.unidue.inf.is.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


public final class Babble_Details extends HttpServlet {

    private static final long serialVersionUID = 1L;

    DB_query db_query;
    Babble babble;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String sessionId = (String) session.getAttribute("sessionID");

        try {
            db_query = new DB_query();
            User user = db_query.getUser(sessionId);
            if(user != null) {
                //reset token
                session.setAttribute("sessionID",sessionId);

                StringBuffer url = request.getRequestURL();
                int id = Integer.parseInt(url.substring(url.lastIndexOf("/") + 1));
                request.setAttribute("loggedUser", sessionId);
                babble = db_query.getBabble(id);
                System.out.println(id);
                if (babble != null) {
                    if (!db_query.isBlocked(babble.getAuthor(), sessionId).getBlockState()) {
                        request.setAttribute("author", babble.getAuthor());
                        request.setAttribute("id", babble.getId());
                        request.setAttribute("inhalt", babble.getInhalt());
                        request.setAttribute("shared", babble.getShared());
                        request.setAttribute("likes", babble.getLikes());
                        request.setAttribute("dislikes", babble.getDislikes());
                        request.setAttribute("datum", babble.getDatum());
                        request.setAttribute("likeBtn", String.valueOf(db_query.babbleActivity(sessionId, id, "liked")));
                        request.setAttribute("dislikeBtn", String.valueOf(db_query.babbleActivity(sessionId, id, "disliked")));
                        request.setAttribute("rebabbleBtn", String.valueOf(db_query.babbleActivity(sessionId, id, "rebabbled")));

                        request.getRequestDispatcher("/babble_details.ftl").forward(request, response);
                    } else {
                        request.getRequestDispatcher("/bad_requests/acc_denied.ftl").forward(request, response);
                    }
                } else {
                    request.getRequestDispatcher("/bad_requests/bad_req.ftl").forward(request, response);
                }
            }else{
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("act");
        HttpSession session = req.getSession();
        String sessionId = (String) session.getAttribute("sessionID");
        try {
            db_query = new DB_query();
            User user = db_query.getUser(sessionId);
            if(user != null) {
                StringBuffer url = req.getRequestURL();
                int id = Integer.parseInt(url.substring(url.lastIndexOf("/") + 1));
                switch (action) {
                    case "Like":
                        if (db_query.babbleActivity(sessionId, id, "liked")) {
                            db_query.doAction(id, sessionId, "unlike");
                        } else {
                            db_query.doAction(id, sessionId, "like");
                        }
                        break;
                    case "Dislike":
                        if (db_query.babbleActivity(sessionId, id, "disliked")) {
                            db_query.doAction(id, sessionId, "undislike");
                        } else {
                            db_query.doAction(id, sessionId, "dislike");
                        }
                        break;
                    case "Rebabble":
                        if (db_query.babbleActivity(sessionId, id, "rebabbled")) {
                            db_query.doAction(id, sessionId, "unrebabble");
                        } else {
                            db_query.doAction(id, sessionId, "rebabble");
                        }
                        break;
                    case "Delete":
                        db_query.doAction(id, sessionId, "delete");
                        resp.getWriter().write("<div style=\"font-size: 30px; text-align: center\"> <i class=\"fa fa-check-circle-o\" aria-hidden=\"true\" style=\"color: #348037; font-size: 90px;\"></i> <br> <p>Babble has been deleted </p></div>");
                        break;
                }
                db_query.complete();
            }else {
                resp.sendRedirect("/");
            }

        } catch (SQLException e) {
            req.getRequestDispatcher("/bad_requests/db_fail_connect.ftl").forward(req,resp);
            e.printStackTrace();
        } finally {
            db_query.close();
        }
    }
}
