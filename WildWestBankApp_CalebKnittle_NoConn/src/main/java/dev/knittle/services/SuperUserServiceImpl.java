package dev.knittle.services;

import java.util.ArrayList;
import java.util.List;

import dev.knittle.app.MainConsole;
import dev.knittle.app.UserConsole;
import dev.knittle.daos.SuperUserDAO;
import dev.knittle.daos.SuperUserDAOImpl;
//import dev.knittle.daos.UserDAO;
//import dev.knittle.daos.UserDAOImpl;
import dev.knittle.entities.SuperUser;
import dev.knittle.entities.User;

public class SuperUserServiceImpl extends UserServiceImpl implements SuperUserService {

	private SuperUserDAO sudao= new SuperUserDAOImpl();	
	public UserService us = new UserServiceImpl();
	private AccountService as = new AccountServiceImpl();
	
	@Override
	public boolean registerSuperUser(String username, String password, String firstName, String lastName) {
		// TODO Auto-generated method stub
		SuperUser tempSuperUser = new SuperUser(username, password, firstName, lastName);
		
		return sudao.createSuperUser(tempSuperUser);
	}
	
	public SuperUser loginSuperUser(String username, String password) {
		SuperUser superuser = sudao.getSuperUserByUsername(username);
		
		return superuser;
	}

	@Override
	public void viewAllUsers() {
		// TODO Auto-generated method stub
		List<User> allUsers = new ArrayList<User>();
		allUsers = udao.getAllUsers();
		
		System.out.println("ALL USERS:");
		System.out.println("ID\tUsername\tName");
		
		for(int i = 0; i < allUsers.size(); i++) {
			User tempUser = allUsers.get(i);
			System.out.println(tempUser.getUserID() + "\t" + tempUser.getUsername() + "\t"
					+ tempUser.getFirstName() + " " + tempUser.getLastName());
		}

	}

	@Override
	public void viewAllAccountsAllUsers() { //Use AccountDAO
		// TODO Auto-generated method stub
		as.getAllAccountsAllUsers();
	}

	@Override
	public boolean createNewUser() {
		
		System.out.println("Registering new user...");
		String username = MainConsole.createUsername();
		String password = MainConsole.createPassword();
		String firstName = MainConsole.createFirstName();
		String lastName = MainConsole.createLastName();
		
		if(us.registerUser(username, password, firstName, lastName)) {
			System.out.println("User Registered Successfully");
			return true;
		} else {
			System.out.println("There was a problem registering the user. Returning to Home Menu");
			return false;
		}
	}

	@Override
	public boolean updateUser(int userID) {
		User tempUser = udao.getUserByID(userID);
		
		if(tempUser == null) {
			System.out.println("User not found.");
			return false;
		}
		
		tempUser = UserConsole.prepUserUpdate(tempUser);
		if(udao.updateUser(tempUser)) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean deleteUser(int userID) {
		User tempUser = udao.getUserByID(userID);
		
		if(tempUser == null) {
			System.out.println("User not found.");
			return false;
		}	
		
		return us.deleteProfile(tempUser);
	}

	@Override
	public User selectUser(int userID) {
		
		return udao.getUserByID(userID);
	}

}
