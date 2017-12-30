package de.unidue.inf.is;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Search_Page extends HttpServlet {
    String searcbed;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String sessionId = (String) session.getAttribute("sessionID");
        searcbed = request.getParameter("searched");

        request.setAttribute("ss"," y");
        searcbed ="";
        request.setAttribute("loggedUser",sessionId);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/search_page.ftl");
        requestDispatcher.forward(request,response);
    }
}
