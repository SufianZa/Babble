package de.unidue.inf.is.Database;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import de.unidue.inf.is.domain.Block;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.utils.DBUtil;
import de.unidue.inf.is.domain.Babble;
import de.unidue.inf.is.domain.Message;


public final class DB_query implements Closeable {


    private Connection connection;
    private boolean complete;

    public DB_query() throws DBTransException, SQLException {

            connection = DBUtil.getExternalConnection("MYBABBLE");
            connection.setAutoCommit(false);
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
                    return searchFor;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addUser(User userToAdd) {

            try(PreparedStatement preparedStatement = connection.prepareStatement("insert into dbp66.BabbleUser (username, name, status, foto) values (?, ?, ?, ?)")){
            preparedStatement.setString(1, userToAdd.getUsername());
            preparedStatement.setString(2, userToAdd.getName());
            preparedStatement.setString(3, userToAdd.getStatus());
            preparedStatement.setString(4, userToAdd.getImage_path());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }

    public Babble getBabble(int babbleid, String activity, Timestamp activityTimestamp) {
        String selectSQL = "SELECT id, text, created, creator FROM dbp66.Babble WHERE id = ? ";
        try (PreparedStatement ps = connection.prepareStatement(selectSQL)) {
            ps.setInt(1, babbleid);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int the_id = rs.getInt(1);
                    String babble_content = rs.getString(2);
                    Timestamp new_Stamp = rs.getTimestamp(3);
                    String author = rs.getString(4);
                    return new Babble(the_id, babble_content, new_Stamp, author, getNumber(the_id,"likes"), getNumber(the_id,"dislikes"), getNumber(the_id,"rebabbles"), activity, activityTimestamp);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


        public ArrayList<Babble> getFriendsBabbles (String username, String eingeloggter_user){
            ArrayList<Babble> result = new ArrayList<>();

            try (PreparedStatement ps = connection.prepareStatement("select b.id, b.creator from dbp66.Babble b,(select followee from dbp66.Follows f where follower = ?) friends where b.creator = friends.followee")) {
                ps.setString(1, username);

                try (ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                    	if(!isBlocked(rs.getString(2),eingeloggter_user).getBlockState()) {
                            result.add(this.getBabble(rs.getInt(1)));
                    		
                    	}
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            Collections.sort(result);
            return result;
        }


        public Babble getBabble ( int babbleid){
            String selectSQL = "SELECT id, text, created, creator FROM dbp66.Babble WHERE id = ? ";
            try (PreparedStatement ps = connection.prepareStatement(selectSQL)) {
                ps.setInt(1, babbleid);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        int the_id = rs.getInt(1);
                        String babble_content = rs.getString(2);
                        Timestamp new_Stamp = rs.getTimestamp(3);
                        String author = rs.getString(4);

                        return new Babble(the_id, babble_content, new_Stamp, author, getNumber(the_id,"likes"), getNumber(the_id,"dislikes"), getNumber(the_id,"rebabbles"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        public void makeBabble (Babble babble){
            try (PreparedStatement preparedStatement = connection.prepareStatement("insert into dbp66.Babble (creator,text) values (?, ?)")) {
                preparedStatement.setString(1, babble.getAuthor());
                preparedStatement.setString(2, babble.getInhalt());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    public int getNumber (int id, String activity){
        int number = 0;
        String selectSQL ="";
        switch (activity){
            case "likes":
                selectSQL = "SELECT count(user) FROM dbp66.likesbabble WHERE babble = ? and type='like'";
                break;
            case "dislikes":
                selectSQL = "SELECT count(user) FROM dbp66.likesbabble WHERE babble = ? and type='dislike'";
                break;
            case "rebabbles":
                selectSQL = "SELECT count(user) FROM dbp66.rebabble WHERE babble = ?";
                break;
        }
        try (PreparedStatement ps = connection.prepareStatement(selectSQL)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    number = rs.getInt(1);
                } else {
                    number = 0;
                }
            } catch (SQLException e) {
                number = 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return number;
    }


    public void doAction ( int babbleid, String username, String todo){
        String selectSQL = "";
        switch (todo){
            case "like":
                if(babbleActivity(username,babbleid,"disliked")){
                    selectSQL = "UPDATE dbp66.LikesBabble l set l.TYPE = 'like' WHERE l.user=? and l.babble =?";
                }else{
                    selectSQL = "insert into dbp66.LikesBabble  (user, babble ,type) values (?, ?, 'like')";
                }
                break;
            case "dislike":
                if(babbleActivity(username,babbleid,"liked")){
                    selectSQL = "UPDATE dbp66.LikesBabble l set l.TYPE = 'dislike' WHERE l.user=? and l.babble =?";
                }else{
                    selectSQL = "insert into dbp66.LikesBabble  (user, babble ,type) values (?, ?, 'dislike')";
                }
                break;
            case "rebabble":
                selectSQL = "insert into dbp66.ReBabble (user, babble) values (?, ?)";
                break;
            case "unlike":
                selectSQL = "DELETE from dbp66.LikesBabble l where l.user = ? and l.babble = ? and l.type ='like'";
                break;
            case "undislike":
                selectSQL = "DELETE from dbp66.LikesBabble d where d.user = ? and d.babble = ? and d.type ='dislike'";
                break;
            case "unrebabble":
                selectSQL = "DELETE from dbp66.ReBabble r where r.user = ? and r.babble = ?";
                break;
            case "delete":
                selectSQL = "DELETE from dbp66.babble r where r.creator = ? and r.id = ?";
                break;
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, babbleid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


        public void follow (String follower, String followee){
            String sql = "insert into dbp66.Follows (follower, followee) values (?, ?)";
            Block block1 = isBlocked(follower, followee);
            Block block2 = isBlocked(followee, follower);
            if (!block1.getBlockState() && !block2.getBlockState()) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, follower);
                    preparedStatement.setString(2, followee);
                    preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        public void unfollow (String follower, String followee){
            try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM dbp66.Follows  WHERE follower = ? and followee=?")) {
                preparedStatement.setString(1, follower);
                preparedStatement.setString(2, followee);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void block (String blocker, String blockee, String reason){

            try (PreparedStatement preparedStatement = connection.prepareStatement("insert into dbp66.Blocks (blocker, blockee, reason) values (?, ?,?)")) {
                preparedStatement.setString(1, blocker);
                preparedStatement.setString(2, blockee);
                preparedStatement.setString(3, reason);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void unblock (String blocker, String blockee){
            try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM dbp66.Blocks where blocker=? AND blockee=?")) {
                preparedStatement.setString(1, blocker);
                preparedStatement.setString(2, blockee);
                preparedStatement.executeUpdate();
                } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        	public void complete() {
        		this.complete =true;
        	}
        	
        public Block isBlocked(String blocker, String blockee){
            Block block = new Block();
            String selectSQL = "SELECT reason FROM dbp66.Blocks WHERE Blocker=? and Blockee=? ";
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


        public boolean isFollowed (String follower, String followee){

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

        //get true or false if user has liked, disliked or rebabbled a babble
    public boolean babbleActivity (String username, int id, String activity){
        String selectSQL = "";
        switch (activity){
            case "liked":
                 selectSQL = "SELECT * FROM dbp66.LikesBabble b WHERE b.user = ? and b.babble=? and b.type ='like'";
                break;
            case "disliked":
                selectSQL = "SELECT * FROM dbp66.LikesBabble b WHERE b.user = ? and b.babble=? and b.type ='dislike'";
                break;
            case "rebabbled":
                selectSQL = "SELECT * FROM dbp66.ReBabble b WHERE b.user = ? and b.babble=?";
                break;
        }
        try (PreparedStatement ps = connection.prepareStatement(selectSQL)) {
            ps.setString(1, username);
            ps.setInt(2, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

        public ArrayList<Babble> getInteraction (String username, String eingeloggter_user){

            ArrayList<Babble> result = new ArrayList<>();
            try (PreparedStatement ps = connection.prepareStatement("select b.id, lb.created, b.creator from dbp66.babble b, dbp66.likesbabble lb where lb.user = ? and lb.babble = b.id and lb.type = 'like'")) {
                ps.setString(1, username);

                try (ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                    	if(!isBlocked(rs.getString(3),eingeloggter_user).getBlockState()) {
                        result.add(this.getBabble(rs.getInt(1), "liked", rs.getTimestamp(2)));
                    	}
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            try (PreparedStatement ps = connection.prepareStatement("select b.id, rb.created, b.creator from dbp66.babble b, dbp66.rebabble rb where rb.user = ? and rb.babble = b.id")) {
                ps.setString(1, username);

                try (ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                    	if(!isBlocked(rs.getString(3), eingeloggter_user).getBlockState()) {
                        result.add(this.getBabble(rs.getInt(1), "rebabbled", rs.getTimestamp(2)));
                    	}
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            Collections.sort(result, new Comparator<Babble>() {
                public int compare(Babble b1, Babble b2) {
                    return b2.getActivityTimestamp().compareTo(b1.getActivityTimestamp());
                }
            });

            return result;
        }

        public ArrayList<Babble> getOwnBabble (String username){

            ArrayList<Babble> result = new ArrayList<>();
            try (PreparedStatement ps = connection.prepareStatement("select b.id from dbp66.babble b where b.creator =?")) {
                ps.setString(1, username);

                try (ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                        result.add(this.getBabble(rs.getInt(1)));
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            Collections.sort(result);

            return result;
        }

        public ArrayList<Babble> getSearch(String searchTerm, String user){

			String searchT = searchTerm
			.replace("!", "!!")
			.replace("%", "!%")
			.replace("_", "!_")
			.replace("[", "![");


            ArrayList<Babble> result = new ArrayList<>();
            try (PreparedStatement ps = connection.prepareStatement("SELECT id, text,created, creator from dbp66.babble where text like ?")) {
                ps.setString(1, "%" + searchT + "%");

                try (ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                        String writer = rs.getString(4);
                        if (!isBlocked(user, writer).getBlockState()) {
                            result.add(new Babble(rs.getInt(1), rs.getString(2), rs.getTimestamp(3), rs.getString(4), getNumber(rs.getInt(1),"likes"), getNumber(rs.getInt(1),"dislikes"), getNumber(rs.getInt(1),"rebabbles")));
                        }
                    }
                    return result;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
        
        public ArrayList<Babble> getTop5 (){
			
			String sqltop = "select count(l.user) as num, b.id from dbp66.babble b, dbp66.likesbabble l where b.id = l.babble group by b.id order by num desc fetch first 5 rows only";

            ArrayList<Babble> result = new ArrayList<>();
            try (PreparedStatement ps = connection.prepareStatement(sqltop)) {
                
                try (ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                        result.add(this.getBabble(rs.getInt(2)));
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return result;
        }


        public void writeMessage (String writer, String receiver, String text){
			
			String sql = "insert into dbp66.Babblemessage (sender, recipient, text) values (?, ?, ?)";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, writer);
                preparedStatement.setString(2, receiver);
                preparedStatement.setString(3, text);
                preparedStatement.executeUpdate();
         
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        public ArrayList<Message> getMessages(String writer, String receiver){
			
			String sql = "select m.text, m.created, m.id from DBP66.Babblemessage m where m.sender = ? and m.recipient = ?";
			
			ArrayList<Message> result = new ArrayList<>();
			
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setString(1, writer);
				preparedStatement.setString(2, receiver);
				
				try(ResultSet rs = preparedStatement.executeQuery()){
					while(rs.next()){
						result.add(new Message(rs.getInt(3), rs.getString(1), writer , receiver, rs.getTimestamp(2)));
					}
					
				} catch(SQLException e){
					e.printStackTrace();
				}
			} catch(SQLException e){
				e.printStackTrace();
			}
			
			
			String sql2 = "select m.text, m.created, m.id from DBP66.Babblemessage m where m.sender = ? and m.recipient = ?";
			
			try(PreparedStatement preparedStatement2 = connection.prepareStatement(sql2)) {
				preparedStatement2.setString(1, receiver);
				preparedStatement2.setString(2, writer);
				
				try(ResultSet rs = preparedStatement2.executeQuery()){
					while(rs.next()){
						result.add(new Message(rs.getInt(3), rs.getString(1), receiver, writer , rs.getTimestamp(2)));
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


        @Override
        public void close () throws IOException {
        	 if (connection != null) {
                 try {
                     if (complete) {
                         connection.commit();
                     }
                     else {
                         connection.rollback();
                     }
                 }
                 catch (SQLException e) {
                     throw new DBTransException(e);
                 }
                 finally {
                     try {
                         connection.close();
                     }
                     catch (SQLException e) {
                         e.printStackTrace();
                     }
                 }
             }
        }


    }
