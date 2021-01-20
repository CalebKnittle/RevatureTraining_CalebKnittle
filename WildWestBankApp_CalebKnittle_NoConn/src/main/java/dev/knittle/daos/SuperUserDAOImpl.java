package dev.knittle.daos;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dev.knittle.entities.SuperUser;
//import dev.knittle.entities.User;

public class SuperUserDAOImpl extends UserDAOImpl implements SuperUserDAO {

	//CREATE------------------------------------------------------------------
	public boolean createSuperUser(SuperUser superuser) {
		
		try {	
			String sql = "CALL add_superuser(?, ?, ?, ?)";
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setString(1, superuser.getUsername());
			cs.setString(2, superuser.getPassword());
			cs.setString(3, superuser.getFirstName());
			cs.setString(4, superuser.getLastName());
			
			cs.execute();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
			
			return false;
		}	
	
	//READ-----------------------------------------------------------------------
	@Override
	public SuperUser getSuperUserByID(int userID) {
		// TODO Auto-generated method stub
		
		try {

			String sql = "SELECT * FROM superusers WHERE user_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(userID)); // 1-Indexed, convert to string

			ResultSet rs = ps.executeQuery(); // Returns a result set

			// Test if anything in the return
			if (rs.next()) {

				SuperUser tempSuperUser = new SuperUser();
				tempSuperUser.setUserID(rs.getInt("USER_ID"));// (rs.getInt("ID")); //Suggested to use all caps
				tempSuperUser.setUsername(rs.getString("USERNAME")); // match the type and provide column name
				tempSuperUser.setPassword(rs.getString("PASSWORD"));
				tempSuperUser.setFirstName(rs.getString("FIRST_NAME"));
				tempSuperUser.setLastName(rs.getString("LAST_NAME"));

				//tempUser = getUserDetails(tempUser);//Get info from user_details table
				
				return tempSuperUser;
			}

		} catch (SQLException e) { // will complain "unreachable catch block"
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public SuperUser getSuperUserByUsername(String username) {
		// TODO Auto-generated method stub
		
		try {

			String sql = "SELECT * FROM superusers WHERE username = ?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, username); // 1-Indexed, convert to string

			ResultSet rs = ps.executeQuery(); // Returns a result set

			// Test if anything in the return
			if (rs.next()) {

				SuperUser tempSuperUser = new SuperUser();
				tempSuperUser.setUserID(rs.getInt("USER_ID"));// (rs.getInt("ID")); //Suggested to use all caps
				tempSuperUser.setUsername(rs.getString("USERNAME")); // match the type and provide column name
				tempSuperUser.setPassword(rs.getString("PASSWORD"));
				tempSuperUser.setFirstName(rs.getString("FIRST_NAME"));
				tempSuperUser.setLastName(rs.getString("LAST_NAME"));

				//tempUser = getUserDetails(tempUser);//Get info from user_details table
				
				return tempSuperUser;
			}

		} catch (SQLException e) { // will complain "unreachable catch block"
			e.printStackTrace();
		}
		
		return null;
	}

}
