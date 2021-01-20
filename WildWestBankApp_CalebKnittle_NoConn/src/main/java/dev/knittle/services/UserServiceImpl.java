package dev.knittle.services;

import dev.knittle.daos.SuperUserDAO;
import dev.knittle.daos.SuperUserDAOImpl;
import dev.knittle.daos.UserDAO;
import dev.knittle.daos.UserDAOImpl;
import dev.knittle.entities.User;

public class UserServiceImpl implements UserService {
	
	public UserDAO udao= new UserDAOImpl();
	public SuperUserDAO sudao = new SuperUserDAOImpl();

	//Main Console
	public boolean registerUser(String username, String password, String firstName, String lastName) {
		User tempUser = new User(username, password, firstName, lastName);		
		
		return udao.createUser(tempUser);
	}

	//Main Console
	public User login(String username, String password) {
		User currentUser = udao.getUserByUsername(username);
		try {
			if(currentUser != null) {
		
				//System.out.println("Input pass = " + password); //Testing
				//System.out.println("Received pass = " + currentUser.getPassword()); //Testing
				if(password.equals(currentUser.getPassword())) {
					
					return currentUser;
					
				} else {
					System.out.println("Bad Password");//Testing
					throw new Exception(); //OR IncorrectPasswordException or BadLoginException (maybe should NOT be specific, like real websites)
				}
			} else {
				//Check here if SuperUser (or check above)
				System.out.println("Bad Username");//Testing
				throw new Exception(); //OR InvalidUsernameException OR overall BadLoginException
			}
			
		} catch(Exception e) {
			System.out.println("Invalid Username or Password"); //Temporary->Throw exception to calling code, throw exception here, or message-out here
		}
	
		return null;
	}

	//User Console
	public User logout(User user) {
		System.out.println("Logging out...");
		user = null;
		
		return user;

	}

	//User Console
	public void viewProfile(User user) { //Mostly just a more-formatted toString()
		System.out.println("-User Profile-");
		System.out.println("User ID: " + user.getUserID());
		System.out.println("Username: " + user.getUsername());
		System.out.println("Name: " + user.getFirstName() + " " + user.getLastName());
				
	}

	//User Console
	public void viewDetailedProfile(User user) {
		// TODO Auto-generated method stub
		System.out.println("-Detailed User Profile-");
		System.out.println("User ID: 		" + user.getUserID());
		System.out.println("Username: 		" + user.getUsername());
		System.out.println("Name: 			" + user.getFirstName() + " " + user.getLastName());
		System.out.println("Address: 		" + user.getAddress());
		System.out.println("Birthdate: 		" + user.getBirthdate());
		System.out.println("Member Since: 		" + user.getDateJoined());

	}

	//User Console
	public boolean updateProfile(User user) {

		if(udao.updateUser(user)) {
			System.out.println("Update Success!");
			return true;
		}
		
		return false;
	}

	//User Console
	public boolean deleteProfile(User user) {
		// TODO Auto-generated method stub
		if(udao.deleteUser(user.getUserID())) {
			System.out.println("Delete Success!");
			return true;
		}
		System.out.println("Delete Unsuccessful.");
		return false;
	}


	@Override
	public boolean checkUniqueUsername(String username) {
		// Still need to handle for SuperUser
		if(udao.getUserByUsername(username) == null) {
			return true;
		}
		
		return false; //Not a unique username
	}

	@Override
	public boolean checkSuperUser(User user) {
		// TODO Auto-generated method stub
		if(sudao.getSuperUserByUsername(user.getUsername()) == null) {
			return false;
		}
		
		return true;
	}

}
