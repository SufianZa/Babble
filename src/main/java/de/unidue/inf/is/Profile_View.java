package de.unidue.inf.is;

import de.unidue.inf.is.Database.DB_query;
import de.unidue.inf.is.domain.Babble;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.stores.UserStore;
import org.apache.commons.net.util.Base64;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Einfaches Beispiel, das die Verwendung des {@link UserStore}s zeigt.
 */


public final class Profile_View extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static List<Babble> babbles = new ArrayList<>();
    private static List<User> userlist = new ArrayList<>();
    static {
        babbles.add(new Babble("Peace ♥ ","11.21.2012","Sufian",0,58,20));
        babbles.add(new Babble("I hate u all","12.2.2011","Mark",55,3,1));

        userlist.add(new User("Sufian","SufianZa","Hello im usering babble","no Pic"));
        userlist.add(new User("Mark","Markus","my life sucks","no Pic"));

    }



    DB_query db_query = new DB_query();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get logged in person
        HttpSession session = request.getSession();
        String sessionID = (String) session.getAttribute("sessionID");
        session.setAttribute("sessionID",sessionID);
        request.setAttribute("loggedUser",sessionID);

        //get the profile page after clicking a name
        StringBuffer url = request.getRequestURL();
        String profile_name = url.substring(url.lastIndexOf("/")+1);
        request.setAttribute("profile",profile_name);


        //load requested page information
        request.setAttribute("name",getUser(profile_name).getName());
        request.setAttribute("status",getUser(profile_name).getStatus());
        request.setAttribute("username",getUser(profile_name).getUsername());
        //load image


        //load all activities for user (Like/Redirect-babbles)
        //..
        request.setAttribute("babble",babbles);

        //load self made babbles

        //..
        request.getRequestDispatcher("/profile_view.ftl").forward(request, response);

    }

    User getUser(String name){
        for( User u : userlist){
            if(u.getUsername().equals(name))
                return u;
        }

    return null;
    }

}

