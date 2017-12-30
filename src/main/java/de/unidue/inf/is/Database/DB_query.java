package de.unidue.inf.is.Database;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import com.sun.org.apache.xpath.internal.SourceTree;
import de.unidue.inf.is.domain.Block;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.utils.DBUtil;
import de.unidue.inf.is.domain.Babble;
import org.apache.commons.io.IOUtils;
import org.omg.IOP.TransactionService;


public final class DB_query implements Closeable {


    private Connection connection;
    private boolean complete;

    public DB_query() throws DBTransException {

        try {
            connection = DBUtil.getExternalConnection("MYBABBLE");
            connection.setAutoCommit(false);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public User getUser(String username_i) throws DBTransException {

        User searchFor = new User();

        String selectSQL = "SELECT username, name, status, foto FROM dbp66.BabbleUser WHERE username = ?";
        try (PreparedStatement ps = connection.prepareStatement(selectSQL)) {
            ps.setString(1, username_i);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    searchFor.setUsername(rs.getString(1));
                    searchFor.setName(rs.getString(2));
                    searchFor.setStatus(rs.getString(3));
                    searchFor.setImage_path(rs.getString(4));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return searchFor;
    }

    public void addUser(User userToAdd) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into dbp66.BabbleUser (username, name, status) values (?, ?, ?)");
            preparedStatement.setString(1, userToAdd.getUsername());
            preparedStatement.setString(2, userToAdd.getName());
            preparedStatement.setString(3, userToAdd.getStatus());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            //throw new DBTransException(e);
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());

        }
    }

    public Babble getBabble(int babbleid) {

        //int searched_babble = babbleid

        //String
        try {

            String selectSQL = "SELECT id, text, created, creator FROM dbp66.Babble WHERE id = ? ";
            PreparedStatement ps = connection.prepareStatement(selectSQL);
            ps.setInt(1, babbleid);
            ResultSet rs = ps.executeQuery();


            if (rs.next()) {


                int the_id = rs.getInt(1);
                //Clob text = rs.getClob(2);
                String babble_content = rs.getString(2);
                Timestamp new_Stamp = rs.getTimestamp(3);
                String author = rs.getString(4);

                //String babble_content = new String();
                //babble_content.add(text.getSubString(1, (int) text.length()));

                return new Babble(the_id, babble_content, new_Stamp, author, getLikes(the_id), getDislikes(the_id), getRebabbles(the_id));

            } else {  //Überlegen was, wenn es den Benutzer nicht gibt

               // System.err.println("ResultSet war leer");
			
			/*
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = dateFormat.parse("23/09/2007");
			long time = date.getTime();
			Timestamp x = new Timestamp(time);
			*/

                return new Babble(0, " ", new Timestamp(System.currentTimeMillis()), " ", 0, 0, 0);

            }

        } catch (SQLException e) {


            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());

            //e.printStackTrace();
			/*
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = dateFormat.parse("23/09/2007");
			long time = date.getTime();
			Timestamp x = new Timestamp(time);
			*/

            return new Babble(0, " ", null, " ", 0, 0, 0);

        }

    }


    public void makeBabble(Babble babble) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into dbp66.Babble (creator,text) values (?, ?)")) {
            preparedStatement.setString(1, babble.getAuthor());
            preparedStatement.setString(2, babble.getInhalt());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getLikes(int id) {

        int likes = 0;


        try {

            String selectSQL = "SELECT count(user) FROM dbp66.likesbabble WHERE babble = ? and type='like' ";
            PreparedStatement ps = connection.prepareStatement(selectSQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();


            if (rs.next()) {

                likes = rs.getInt(1);
                
                //System.err.println("Was ist hier los? : " + likes);
                
                return likes;

            } else {  //Überlegen was, wenn es den Benutzer nicht gibt

                likes = 0;

            }

        } catch (SQLException e) {
			
			e.printStackTrace();

            likes = 0;

        }


        return likes;
    }

    public int getDislikes(int id) {

        int dislikes;


        try {

            String selectSQL = "SELECT count(user) FROM likesbabble WHERE babble = ? and type='dislike'";
            PreparedStatement ps = connection.prepareStatement(selectSQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();


            if (rs.next()) {

                dislikes = rs.getInt(1);

            } else {  //Überlegen was, wenn es den Benutzer nicht gibt

                dislikes = 0;

            }

        } catch (SQLException e) {

            dislikes = 0;

        }


        return dislikes;
    }


    public int getRebabbles(int id) {

        int rebabbles;


        try {

            String selectSQL = "SELECT count(user) FROM rebabble WHERE babble = ?";
            PreparedStatement ps = connection.prepareStatement(selectSQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();


            if (rs.next()) {

                rebabbles = rs.getInt(1);

            } else {  //Überlegen was, wenn es den Benutzer nicht gibt

                rebabbles = 0;

            }

        } catch (SQLException e) {

            rebabbles = 0;

        }

        return rebabbles;

    }


    public void Like(int babbleid, String username) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into LikesBabble (user, babble ,type) values (?, ?, 'like')");
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, babbleid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            //throw new DBTransException(e);
        }

    }

    public void Dislike(int babbleid, String username) {

        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into LikesBabble (user, babble ,type) values (?, ?, 'dislike')")) {
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, babbleid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            //throw new DBTransException(e);
        }
    }

    public void rebabble(int babbleid, String username) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into ReBabble (user, babble) values (?, ?)");
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, babbleid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            //throw new DBTransException(e);
        }

    }

    //TODO Sufian
    public void follow(String follower, String followee) {
        String sql = "insert into dbp66.Follows (follower, followee) values (?, ?)";
        Block block = isBlocked(follower,followee);
        if(!block.getBlockState()){
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, follower);
            preparedStatement.setString(2, followee);
            preparedStatement.executeUpdate();
            connection.commit();
            System.err.println(follower + " has followed " + followee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }
    }

    public void unfollow(String follower, String followee) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM dbp66.Follows  WHERE follower = ? and followee=?")) {
            preparedStatement.setString(1, follower);
            preparedStatement.setString(2, followee);
            preparedStatement.executeUpdate();
            connection.commit();
            System.err.println(follower + " has unfollowed " + followee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void block(String blocker, String blockee, String reason) {

        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into dbp66.Blocks (blocker, blockee, reason) values (?, ?,?)")) {
            preparedStatement.setString(1, blocker);
            preparedStatement.setString(2, blockee);
            preparedStatement.setString(3, reason);
            preparedStatement.executeUpdate();
            connection.commit();
            System.err.println(blocker + " has blocked " + blockee + " reason " + reason);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void unblock(String blocker, String blockee) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM dbp66.Blocks where blocker=? AND blockee=?")) {
            preparedStatement.setString(1, blocker);
            preparedStatement.setString(2, blockee);
            preparedStatement.executeUpdate();
            connection.commit();
            System.err.println(blocker + " has unblocked " + blockee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Block isBlocked(String blocker, String blockee) {
        Block block = new Block();
        String selectSQL = "SELECT reason FROM dbp66.Blocks WHERE Blocker = ? and Blockee=?";
        try (PreparedStatement ps = connection.prepareStatement(selectSQL)) {
            ps.setString(1, blocker);
            ps.setString(2, blockee);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String reason = rs.getString(1);
                    block.setReason(reason);
                    block.setState(true);
                    System.out.println(reason);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return block;
    }


    public boolean isFollowed(String follower, String followee) {

        String selectSQL = "SELECT * FROM dbp66.Follows WHERE follower = ? and followee=?";
        try (PreparedStatement ps = connection.prepareStatement(selectSQL)) {
            ps.setString(1, follower);
            ps.setString(2, followee);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println(follower + " Follows " + followee);
                    return true;
                }
            } catch (SQLException e) {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //TODO Sufian


    public ArrayList<Babble> getTimeLine(String username) {
		
		ArrayList<Babble> result = new ArrayList<>();
		
		try (PreparedStatement ps = connection.prepareStatement("select b.id from dbp66.babble b where b.creator = ? union select b.id from dbp66.babble b, dbp66.likesbabble lb where lb.user = ? and lb.babble = b.id and lb.type = 'like' union select b.id from dbp66.babble b, dbp66.rebabble rb where rb.user = ? and rb.babble = b.id")){
			ps.setString(1, username);
            ps.setString(2, username);
            ps.setString(3, username);
            
            try(ResultSet rs = ps.executeQuery()) {
				
					while(rs.next()){
							result.add(this.getBabble(rs.getInt(1)));
						}
					
				} catch(SQLException e){
					e.printStackTrace();
				}
					
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		Collections.sort(result);
		return result;
    }


    public ArrayList<Babble> getSearch(String searchTerm, String user) {
        String sql = "SELECT id, text,created, creator from dbp66.babble where text like %?%";


        return null;
    }


    public void writeMessage(String writer, String receiver, String text) {

    }

    //public ArrayList<Messages> getMessages(String writer, String receiver){

    //	}


    @Override
    public void close() throws IOException {
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                throw new DBTransException(e);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new DBTransException(e);
                }
            }
        }
    }

    
   /* 
    /*********************************************************************************************
 * From CLOB to String
 * @return string representation of clob
 *********************************************************************************************/
    //Dieser Code ist von https://stackoverflow.com/questions/2169732/most-efficient-solution-for-reading-clob-to-string-and-string-to-clob-in-java übernommen
    //Dort wurde er vom Benutzer Stan Sokolov veröffentlicht
 /*
private String clobToString(java.sql.Clob data)
{
    final StringBuilder sb = new StringBuilder();

    try
    {
        final Reader         reader = data.getCharacterStream();
        final BufferedReader br     = new BufferedReader(reader);

        int b;
        while(-1 != (b = br.read()))
        {
            sb.append((char)b);
        }

        br.close();
    }
    catch (SQLException e)
    {
        log.error("SQL. Could not convert CLOB to string",e);
        return e.toString();
    }
    catch (IOException e)
    {
        log.error("IO. Could not convert CLOB to string",e);
        return e.toString();
    }

    return sb.toString();
}
    */

}
