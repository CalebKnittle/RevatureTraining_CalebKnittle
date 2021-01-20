package dev.knittle.services;

import dev.knittle.entities.User;

public interface UserService {
	
	public boolean registerUser(String username, String password, String firstName, String lastName);
	
	public User login(String username, String password);	
	
	public User logout(User user);
	
	public void viewProfile(User user);
	
	public void viewDetailedProfile(User user);
	
	public boolean updateProfile(User user);
	
	public boolean deleteProfile(User user);	
	
	public boolean checkUniqueUsername(String username);
	
	public boolean checkSuperUser(User user);

}
