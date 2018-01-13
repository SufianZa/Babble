package de.unidue.inf.is;

import de.unidue.inf.is.Database.DBTransException;
import de.unidue.inf.is.Database.DB_query;
import de.unidue.inf.is.domain.Babble;
import de.unidue.inf.is.domain.Block;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.stores.UserStore;

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


public final class Profile_View extends HttpServlet {

    private static final long serialVersionUID = 1L;


    private String image_p;

    DB_query db_query;
    User eingeloggter_user;
    User besuchter_user;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {
            db_query = new DB_query();


            //default values
            String blockState = "Block";
            String followState = "Follow";
            String sendMessage = "no";
            String blockedContent = "default";

            //get logged in person
            HttpSession session = request.getSession();
            String sessionID = (String) session.getAttribute("sessionID");
            session.setAttribute("sessionID", sessionID);
            request.setAttribute("loggedUser", sessionID);


            //get the profile page after clicking a name
            StringBuffer url = request.getRequestURL();
            String profile = url.substring(url.lastIndexOf("/") + 1);


            //create logged in user and user of the visited profile page

            eingeloggter_user = db_query.getUser(sessionID);
            besuchter_user = db_query.getUser(profile);
            if (besuchter_user == null) {
                request.getRequestDispatcher("/bad_requests/acc_denied_profile.ftl").forward(request, response);
            } else {
                request.setAttribute("profile", profile);

                //get all activities
                ArrayList<Babble> own_babble = db_query.getOwnBabble(besuchter_user.getUsername());
                ArrayList<Babble> friends_babble = db_query.getFriendsBabbles(besuchter_user.getUsername(), eingeloggter_user.getUsername());
                ArrayList<Babble> interaction_babble = db_query.getInteraction(besuchter_user.getUsername(), eingeloggter_user.getUsername());

                if (besuchter_user.getImage_path() == null) {
                    image_p = "http://www.qatarliving.com/sites/all/themes/qatarliving_v3/images/avatar.jpeg";
                } else {
                    image_p = besuchter_user.getImage_path();
                }

                request.setAttribute("own_babble", own_babble);
                request.setAttribute("friends_babble", friends_babble);
                request.setAttribute("interaction_babble", interaction_babble);


                request.setAttribute("blockContent", blockedContent);

                //check if logged in user is NOT visiting his own profile page
                if (!eingeloggter_user.getUsername().equals(besuchter_user.getUsername())) {
                    Block b_Blocks_a = db_query.isBlocked(besuchter_user.getUsername(), eingeloggter_user.getUsername());
                    Block a_Blocks_b = db_query.isBlocked(eingeloggter_user.getUsername(), besuchter_user.getUsername());

                    if (b_Blocks_a.getBlockState()) {
                        blockedContent = "blocked";
                        request.setAttribute("blockContent", blockedContent);
                        request.setAttribute("reason", b_Blocks_a.getReason());
                    }
                    if (a_Blocks_b.getBlockState()) {
                        blockState = "Unblock";
                    }

                    if (db_query.isFollowed(eingeloggter_user.getUsername(), besuchter_user.getUsername())) {
                        followState = "Unfollow";
                        if (db_query.isFollowed(besuchter_user.getUsername(), eingeloggter_user.getUsername())) {
                            sendMessage = "send";
                            session.setAttribute("profile", profile);
                        }
                    }

                    //set top menu items
                    request.setAttribute("blockState", blockState);
                    request.setAttribute("sendMessage", sendMessage);
                    request.setAttribute("followState", followState);
                }

                //personal information
                request.setAttribute("name", besuchter_user.getName());
                request.setAttribute("status", besuchter_user.getStatus());
                request.setAttribute("username", besuchter_user.getUsername());
                request.setAttribute("Profile_Image_Path", image_p);

                request.getRequestDispatcher("/profile_view.ftl").forward(request, response);
            }
        } catch (SQLException e) {
            request.getRequestDispatcher("/bad_requests/bad_req.ftl").forward(request, response);
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("act");
        String reason = req.getParameter("reason");


        try {
            db_query = new DB_query();
        switch (action) {
            case "Follow":
                db_query.follow(eingeloggter_user.getUsername(), besuchter_user.getUsername());
                break;
            case "Unfollow":
                db_query.unfollow(eingeloggter_user.getUsername(), besuchter_user.getUsername());
                break;
            case "Block":
                db_query.block(eingeloggter_user.getUsername(), besuchter_user.getUsername(), reason);
                break;
            case "Unblock":
                db_query.unblock(eingeloggter_user.getUsername(), besuchter_user.getUsername());
                break;
            default:
        }
        db_query.complete();
        db_query.close();
        } catch (SQLException e) {
            req.getRequestDispatcher("/bad_requests/db_fail_connect.ftl");
            e.printStackTrace();
        }
    }


}

