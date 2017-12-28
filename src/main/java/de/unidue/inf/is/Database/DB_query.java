package de.unidue.inf.is.Database;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.*;

import java.util.List;
import java.util.ArrayList;

import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.utils.DBUtil;
import de.unidue.inf.is.domain.Babble;



public final class DB_query implements Closeable{
    
    
    private Connection connection;
    private boolean complete;
    
    public DB_query() throws DBTransException {
		
		try {
            connection = DBUtil.getExternalConnection("MYBABBLE");
            connection.setAutoCommit(false);
        }
        catch (SQLException e) {
            //throw new DBTransException(e);
            // Mach was hier!!
            
            System.err.println("Constructor failed");
            System.err.println("SQLState: " + e.getSQLState());
			System.err.println("Error Code: " + e.getErrorCode());
			System.err.println("Message: " + e.getMessage());
            
        }
    }

    //Get personal information

    //Get timeline activity ...
    
    
   public User getUser(String username_i) throws DBTransException {
		
		User searchFor = new User();
		
		String new_name = new String(username_i);
		
		//String 
		try{
		
		String selectSQL = "SELECT status FROM BabbleUser WHERE username = 'dbuser'";
		PreparedStatement ps = connection.prepareStatement(selectSQL); //Im Moment ist connection null
		//ps.setString(1, "dbuser");
		ResultSet rs = ps.executeQuery();
		
		
		if(rs.first()){
			
			searchFor.setUsername(rs.getString("username"));
			searchFor.setName(rs.getString("name"));
			searchFor.setStatus(rs.getString("status"));
			searchFor.setImage_path(rs.getString("foto"));
			
			return searchFor;
			
			} else {  //Überlegen was, wenn es den Benutzer nicht gibt
				
			searchFor.setUsername("DKnuth1");
			searchFor.setName("Donald Knuth else");
			searchFor.setStatus("Hallo Welt");
			searchFor.setImage_path(" ");	
				
			return searchFor;
				
				}
				
		} catch(SQLException e){
			
			
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("Error Code: " + e.getErrorCode());
			System.err.println("Message: " + e.getMessage());
			
			//e.printStackTrace();
			
			searchFor.setUsername("DKnuth2");
			searchFor.setName("Donald Knuth except");
			searchFor.setStatus("Hallo Welt");
			searchFor.setImage_path(" ");	
			
			return searchFor;
						
			}
		
	}
		
	public void addUser(User userToAdd){
		try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into BabbleUser (username, name, status) values (?, ?, ?)");
            preparedStatement.setString(1, userToAdd.getUsername());
            preparedStatement.setString(2, userToAdd.getName());
            preparedStatement.setString(3, userToAdd.getStatus());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            //throw new DBTransException(e);
        }
	}
		
	public Babble getBabble(int babbleid){
		
		/*
		User searchFor = new User();
		
		//String 
		
		
		String selectSQL = "SELECT username, name, status, foto FROM BabbleUser WHERE username = ?";
		PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSQL);
		preparedStatement.setString(1, username_i);
		ResultSet rs = preparedStatement.executeQuery(selectSQL );
		
		while (rs.next()) {
			String userid = rs.getString("USER_ID");
			String username = rs.getString("USERNAME");
		}
		
		if(rs.first()){
			
			searchFor.setUsername(username_i);
			searchFor.setName(rs.getString("name"));
			searchFor.setStatus(rs.getString("status"));
			searchFor.setImage_path(rs.getString("foto"));
			
			return searchFor;
			
			} else {
				
			return searchFor;
				
				}
				* 
				* */
				
				return null;
		
		}
	
		
	public void makeBabble(String username, String text){
			
		try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into Babble (text,creator) values (?, ?)");
            preparedStatement.setString(1, text);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            //throw new DBTransException(e);
        }
			
		
	}
		
	public int getLikes(int id){
		
		int likes;
		
		
		try{
		
		String selectSQL = "SELECT count(user) FROM likesbabble WHERE babble = ? and type='like'";
		PreparedStatement ps = connection.prepareStatement(selectSQL);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		
		
		if(rs.first()){
			
			likes = rs.getInteger("count(user)");
				
		} else {  //Überlegen was, wenn es den Benutzer nicht gibt
				
			likes = 0;
			
				}
				
		} catch(SQLException e){
			
			likes = 0;
						
			}
		
		
		return likes;
	}
		
	public int getDislikes(int id){
		
		int dislikes;
		
		
		try{
		
		String selectSQL = "SELECT count(user) FROM likesbabble WHERE babble = ? and type='dislike'";
		PreparedStatement ps = connection.prepareStatement(selectSQL);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		
		
		if(rs.first()){
			
			dislikes = rs.getInteger("count(user)");
				
		} else {  //Überlegen was, wenn es den Benutzer nicht gibt
				
			dislikes = 0;
			
				}
				
		} catch(SQLException e){
			
			dislikes = 0;
						
			}
		
		
		return dislikes;
		}
    
    
    public int getRebabbles(int id){
		
		int rebabbles;
		
		
		try{
		
		String selectSQL = "SELECT count(user) FROM rebabble WHERE babble = ?";
		PreparedStatement ps = connection.prepareStatement(selectSQL);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		
		
		if(rs.first()){
			
			rebabbles = rs.getInteger("count(user)");
				
		} else {  //Überlegen was, wenn es den Benutzer nicht gibt
				
			rebabbles = 0;
			
				}
				
		} catch(SQLException e){
			
			rebabbles = 0;
						
			}
				
		return rebabbles;
		
	}
		
	
	public void Like(int babbleid, String username){
		
		try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into LikesBabble (user, babble ,type) values (?, ?, 'like')");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, babbleid);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            //throw new DBTransException(e);
        }
		
		}
		
	public void Dislike(int babbleid, String username){
		
		try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into LikesBabble (user, babble ,type) values (?, ?, 'dislike')");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, babbleid);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            //throw new DBTransException(e);
        }
	}
		
	public void rebabble(int babbleid, String username){
		
		try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into ReBabble (user, babble) values (?, ?)");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, babbleid);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            //throw new DBTransException(e);
        }
        
	}
		
		
	public void follow(String he_who_follows, String he_who_is_followed){
		
		try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into Follows (follower, followee) values (?, ?)");
            preparedStatement.setString(1, he_who_follows);
            preparedStatement.setString(2, he_who_is_followed);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            //throw new DBTransException(e);
        }
        
	}
	
	public void unfollow(String he_who_follows, String he_who_is_followed){
		
		}
		
	public void block(String blocker, String blockee){
		
		try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into Blocks (blocker, blockee) values (?, ?)");
            preparedStatement.setString(1, blocker);
            preparedStatement.setString(2, blockee);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            //throw new DBTransException(e);
        }
		}
		
	public void unblock(String blocker, String blockee){
		
		}
		
	public boolean isBlocked(String blocker, String blockee){
			return false;
		}	
		
		
	public ArrayList<Babble> getTimeLine(String username){
			return null;
		}
		
		
	public ArrayList<Babble> getSearch(String searchTerm, String user){
			return null;
		}
		
		
	public void writeMessage(String writer, String receiver, String text){
		
		}
		
	//public ArrayList<Messages> getMessages(String writer, String receiver){
		
	//	}
		
    
    @Override
    public void close() throws IOException {
        if (connection != null) {
            try {
                connection.commit();
                
            }
            catch (SQLException e) {
                throw new DBTransException(e);
            }
            finally {
                try {
                    connection.close();
                }
                catch (SQLException e) {
                    throw new DBTransException(e);
                }
            }
        }
    }
    
    

}
