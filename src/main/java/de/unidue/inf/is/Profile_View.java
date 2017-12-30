package de.unidue.inf.is;

import de.unidue.inf.is.Database.DB_query;
import de.unidue.inf.is.domain.Babble;
import de.unidue.inf.is.domain.Block;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * Einfaches Beispiel, das die Verwendung des {@link UserStore}s zeigt.
 */


public final class Profile_View extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static List<Babble> babbles = new ArrayList<>();
    private static List<User> userlist = new ArrayList<>();
    private String blockState = "Block";
    private String followState = "Follow";
    private String blockedContent = "default";

    static {
        babbles.add(new Babble(5,"Peace ♥ ",new Timestamp(System.currentTimeMillis()),"Sufian",0,58,20));
        babbles.add(new Babble(2,"I hate u all",new Timestamp(System.currentTimeMillis()),"wisee",55,3,1));

        userlist.add(new User("Sufian","SufianZa","Hello im usering babble","no Pic"));
        userlist.add(new User("Mark","Markus","my life sucks","no Pic"));

    }

    DB_query db_query = new DB_query();
    



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        resetAll();
        //get logged in person
        HttpSession session = request.getSession();
        String sessionID = (String) session.getAttribute("sessionID");
        session.setAttribute("sessionID",sessionID);
        request.setAttribute("loggedUser",sessionID);


        //get the profile page after clicking a name
        StringBuffer url = request.getRequestURL();
        String profile = url.substring(url.lastIndexOf("/")+1);
        request.setAttribute("profile",profile);

        //create logged in user and  user of the visited profile page
        User eingeloggter_user= db_query.getUser(sessionID);
        User besuchter_user= db_query.getUser(profile);

        System.out.println("logged in as " + sessionID);
        System.out.println("profile page of " + profile);
        request.setAttribute("babble", babbles);
        request.setAttribute("blockContent",blockedContent);
        //check if logged in user is NOT visiting his own profile page
        if(!eingeloggter_user.getUsername().equals(besuchter_user.getUsername())){
            Block b_Blocks_a  = db_query.isBlocked(besuchter_user.getUsername(),eingeloggter_user.getUsername());
            Block a_Blocks_b  = db_query.isBlocked(eingeloggter_user.getUsername(),besuchter_user.getUsername());
            if(b_Blocks_a.isBlocked()){
                this.blockedContent = "blocked";
                request.setAttribute("blockContent",  this.blockedContent);
                request.setAttribute("reason", b_Blocks_a.getReason());
            }
            if(a_Blocks_b.isBlocked()){
                this.blockState = "Unblock";
            }

            request.setAttribute("blockState",  this.blockState);
            if(db_query.isFollowed(eingeloggter_user.getUsername(),besuchter_user.getUsername())){
                this.followState = "Unfollow";
            }
            request.setAttribute("followState",  this.followState);
        }


        request.setAttribute("name",besuchter_user.getName());
        request.setAttribute("status",besuchter_user.getStatus());
        request.setAttribute("username", besuchter_user.getUsername());
        
        //db_query.getUser("dbuser");
        db_query.getBabble(2);


        request.getRequestDispatcher("/profile_view.ftl").forward(request, response);

    }

    private void resetAll() {
        this.followState="Follow";
        this.blockState ="Block";
        this.blockedContent="default";
    }
}

