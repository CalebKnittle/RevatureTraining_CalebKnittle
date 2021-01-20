package dev.knittle.daos;

import java.util.List;
//import java.util.Set;

import dev.knittle.entities.SuperUser;
import dev.knittle.entities.User;

public interface UserDAO {
	
	//CREATE
	public boolean createUser(User user); //Could pass parameters? Available to SuperUser
										//and User at registration only
	
		
	//READ
	public User getUserByID(int userID); //Maybe only available to SuperUser?	
	
	public User getUserByUsername(String username);
		
	public User getUserDetails(User user);
	
	public List<User> getAllUsers(); //Should only be available to SuperUser, but in UserDAO because deals with NonSuper-Users
	
	//Maybe: public Set<String> getAllUsernames(); //Utility method to use in service to check if a user-name is unique
	//Or can use with getAllUsers() and get user-names from that set of users
		
	//public Set<User> getTopUsers(int minBal); //Handle in Service Layer
	
	//UPDATE
	public boolean updateUser(User user); //User can only update name and password, SuperUser can update any
	
	public boolean updateUserDetails(User user);
		
	//DELETE
	public boolean deleteUser(int userID); //User can only delete self, SuperUser can delete any/all

}
