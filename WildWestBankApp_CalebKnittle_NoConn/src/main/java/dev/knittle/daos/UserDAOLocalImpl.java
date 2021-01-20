package dev.knittle.daos;

import java.util.ArrayList;
import java.util.HashMap;
//import java.util.HashSet;
import java.util.List;
import java.util.Map;
//import java.util.Set;

import dev.knittle.entities.User;

public class UserDAOLocalImpl implements UserDAO {
	
	private static Map<Integer, User> user_table = new HashMap<Integer, User>();
	private static Map<String, User> username_table = new HashMap<String, User>();
	//Normally would not want a 2nd Map here, but will do for testing to avoid
	//using extra logic control statements in DAOs
	//User-names are still unique, but aren't a foreign key

	public boolean createUser(User user) { //Previously returned user
		int userID = user.getUserID();
		String username = user.getUsername();
		
		user_table.put(userID, user); //Replace with SQL Commands
		username_table.put(username, user);		
		return true;
		//INSERT INTO user_table VALUES (?, ?, ...);
		//? = user.get...; for each field/value
	}

	public User getUserByID(int userID) {
		// TODO Auto-generated method stub
		User tempUser = user_table.get(userID); 
		return tempUser;
		//SELECT * FROM user_table WHERE userID = ?;
		//? = userID;
	}
	
	//May not need, can probably convert to userID in service layer, then pass to DAO
	public User getUserByUsername(String username) {
		User tempUser = username_table.get(username);
		return tempUser;
		//SELECT * FROM user_table WHERE username = ?;
		//? = username;
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		//Set<User> allUsers = new HashSet<User>(user_table.values()); //Replace (SELECT * FROM (user_table))
		List<User> allUsers = new ArrayList<User>(user_table.values());
		return allUsers;
		//SELECT * FROM user_table;
	}
	
//	public Set<User> getTopUsers(int minBalance){ //Intended to return all users with accounts totaling greater than some amount
//		Set<User> topUsers = new HashSet<User>();
//		Set<User> allUsers = getAllUsers();
//		return null;
//	}

	public boolean updateUser(User user) { //Previously returned User
		// TODO Auto-generated method stub
		user_table.put(user.getUserID(), user);		
		return true;
		//UPDATE user_table SET ? = ? WHERE userID = ?;
		//?, ?, ? = each field, value for each field, user.getUserID()
	}

	public boolean deleteUser(int userID) {
		// TODO Auto-generated method stub
		if(user_table.remove(userID) != null) { //.remove() returns the object removed or null
			return true;
		}
		return false;
		//DELETE user_table WHERE userID = ?;
		//? = userID
	}

	@Override
	public User getUserDetails(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateUserDetails(User user) {
		// TODO Auto-generated method stub
		return false;
	}


}
