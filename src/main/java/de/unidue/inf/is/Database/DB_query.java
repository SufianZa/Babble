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
            connection = DBUtil.getExternalConnection("mybabble");
            connection.setAutoCommit(false);
        }
        catch (SQLException e) {
            //throw new DBTransException(e);
            // Mach was hier!!
        }
    }

    //Get personal information

    //Get timeline activity ...
    
    
    public User getUser(String username_i) throws DBTransException {
		
		User searchFor = new User();
		
		//String 
		try{
		
		String selectSQL = "SELECT username, name, status, foto FROM BabbleUser WHERE username = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
		preparedStatement.setString(1, username_i);
		ResultSet rs = preparedStatement.executeQuery(selectSQL );
		/*
		while (rs.next()) {
			String userid = rs.getString("USER_ID");
			String username = rs.getString("USERNAME");
		}*/
		
		if(rs.first()){
			
			searchFor.setUsername(username_i);
			searchFor.setName(rs.getString("name"));
			searchFor.setStatus(rs.getString("status"));
			searchFor.setImage_path(rs.getString("foto"));
			
			return searchFor;
			
			} else {  //Überlegen was, wenn es den Benutzer nicht gibt
				
			searchFor.setUsername(username_i);
			searchFor.setName("Donald Knuth");
			searchFor.setStatus("Hallo Welt");
			searchFor.setImage_path(" ");	
				
			return searchFor;
				
				}
				
		} catch(SQLException e){
			
			
			//System.err.println("SQLState: " + e.getSQLState());
			//System.err.println("Error Code: " + e.getErrorCode());
			//System.err.println("Message: " + e.getMessage());
			
			searchFor.setUsername(username_i);
			searchFor.setName("Donald Knuth");
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
		
	public int getLikes(int id){
			return 0;
		}
		
	public int getDislikes(int id){
			return 0;
		}
    
    
    public int getRebabbles(int id){
			return 0;
		}
		
	
	public void Like(int babbleid, String username){
		
		}
		
	public void Dislike(int babbleid, String username){
		
		}
		
	public void rebabble(int babbleid, String username){
		
		}
		
		
	public void follow(String he_who_follows, String he_who_is_followed){
		
		}
	
	public void unfollow(String he_who_follows, String he_who_is_followed){
		
		}
		
	public void block(String blocker, String blockee){
		
		}
		
	public void unblock(String blocker, String blockee){
		
		}
		
	public boolean isBlocked(String blocker, String blockee){
			return false;
		}	
	
		
	public void makeBabble(String username, String text){
		
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
