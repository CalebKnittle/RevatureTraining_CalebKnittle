package dev.knittle.services;

import dev.knittle.entities.SuperUser;
import dev.knittle.entities.User;

public interface SuperUserService extends UserService {
	
	public boolean registerSuperUser(String username, String password, String firstName, String lastName);
	
	public SuperUser loginSuperUser(String username, String password);
	
	public void viewAllUsers();
	
	public void viewAllAccountsAllUsers();
	
	public User selectUser(int userID);
	
	public boolean createNewUser();
	
	public boolean updateUser(int userID);
	
	public boolean deleteUser(int UserID);

}
