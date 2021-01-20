package dev.knittle.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import java.util.Set;

//import dev.knittle.entities.SuperUser;
import dev.knittle.entities.User;
import dev.knittle.utilities.JDBCConnection;

public class UserDAOImpl implements UserDAO {
	
	public static Connection conn = JDBCConnection.getConnection();

	//Read------------------------------------------------------------------------------------

	public User getUserByID(int userID) {
		
		try {

			String sql = "SELECT * FROM users WHERE user_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(userID));

			ResultSet rs = ps.executeQuery();

			// Test if anything in the return
			if (rs.next()) {

				User tempUser = new User();
				tempUser.setUserID(rs.getInt("USER_ID"));
				tempUser.setUsername(rs.getString("USERNAME"));
				tempUser.setPassword(rs.getString("PASSWORD"));
				tempUser.setFirstName(rs.getString("FIRST_NAME"));
				tempUser.setLastName(rs.getString("LAST_NAME"));

				tempUser = getUserDetails(tempUser);//Get info from user_details table
				
				return tempUser;
			}

		} catch (SQLException e) { // will complain "unreachable catch block"
			e.printStackTrace();
		}

		return null;
	}

	public User getUserByUsername(String username) {
		
		try {

			String sql = "SELECT * FROM users WHERE username = ?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				User tempUser = new User();
				tempUser.setUserID(rs.getInt("USER_ID"));
				tempUser.setUsername(rs.getString("USERNAME"));
				tempUser.setPassword(rs.getString("PASSWORD"));
				tempUser.setFirstName(rs.getString("FIRST_NAME"));
				tempUser.setLastName(rs.getString("LAST_NAME"));
				
				//Adding to get extra user details
				tempUser = getUserDetails(tempUser);

				return tempUser;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	public User getUserDetails(User tempUser) {
		
		try {
			String sql = "SELECT * FROM user_details WHERE user_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(tempUser.getUserID())); // 1-Indexed, convert to string

			ResultSet rs = ps.executeQuery(); // Returns a result set

			// Test if anything in the return
			if (rs.next()) {
				
				tempUser.setAddress(rs.getString("ADDRESS"));// (rs.getInt("ID")); //Suggested to use all caps
				tempUser.setBirthdate(rs.getString("BIRTHDATE"));
				tempUser.setDateJoined(rs.getDate("DATE_JOINED"));
				
				return tempUser;
			}
			
		} catch(SQLException e) {
			
		}
		
		return tempUser;		
	}

	public List<User> getAllUsers() {
		
		List<User> allUsers = new ArrayList<User>();
		
		try {
			
			String sql = "SELECT * FROM users";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				User tempUser = new User();
				tempUser.setUserID(rs.getInt("USER_ID"));
				tempUser.setUsername(rs.getString("USERNAME"));
				tempUser.setPassword(rs.getString("PASSWORD"));
				tempUser.setFirstName(rs.getString("FIRST_NAME"));
				tempUser.setLastName(rs.getString("LAST_NAME"));
				
				tempUser = getUserDetails(tempUser);
				
				allUsers.add(tempUser);				
			}
			return allUsers;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//Create----------------------------------------------------------------------------------
	
	public boolean createUser(User user) {

		try {
			
			String sql = "CALL add_user(?, ?, ?, ?)";
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setString(1, user.getUsername());
			cs.setString(2, user.getPassword());
			cs.setString(3, user.getFirstName());
			cs.setString(4, user.getLastName());
			
			cs.execute();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	
	//Update----------------------------------------------------------------------------------

	public boolean updateUser(User user) {
		
		try {

			String sql = "UPDATE users SET username = ?, password = ?, first_name = ?, last_name = ? WHERE user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getLastName());
			ps.setString(5, Integer.toString(user.getUserID()));

			ps.executeQuery();
			
			updateUserDetails(user);

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean updateUserDetails(User user) {
		
		try {
			
			String sql = "UPDATE user_details SET address = ?, birthdate = ? WHERE user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, user.getAddress());
			ps.setString(2, user.getBirthdate());
			ps.setString(3, Integer.toString(user.getUserID()));
			
			ps.executeQuery();
			
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	//Delete----------------------------------------------------------------------------------

	public boolean deleteUser(int userID) {

		try {

			String sql = "DELETE users WHERE user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(userID));
			// ps.setInt(1, userID);

			ps.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return false;
	}

}
