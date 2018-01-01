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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



public class Search_Page extends HttpServlet {
    String searcbed;
    
    ArrayList<Babble> result;
    
    DB_query db_query = new DB_query();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String sessionId = (String) session.getAttribute("sessionID");
        
        request.setAttribute("searchedFor", "Warum?");
        
        searcbed = request.getParameter("searched");
        
                
        if(searcbed != null && !searcbed.equals("")){
			result = db_query.getSearch(searcbed, sessionId);
		} else {
			result = new ArrayList<>();
		}
		
        if(result == null || result.isEmpty()){
			request.setAttribute("ss"," y");
			//searcbed ="";
			request.setAttribute("resultbabble", null);
			request.setAttribute("searchedFor", " ");
		} else {
			//System.err.println("ResultBabble: " + result.get(0).getInhalt());
			request.setAttribute("resultbabble", result);
			request.setAttribute("ss", "world");
			request.setAttribute("searchedFor", searcbed);
		}

        request.setAttribute("loggedUser",sessionId);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/search_page.ftl");
        requestDispatcher.forward(request,response);
    }
}
