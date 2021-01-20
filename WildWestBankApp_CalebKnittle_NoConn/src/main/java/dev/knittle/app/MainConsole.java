package dev.knittle.app;

import java.util.Scanner;

import dev.knittle.entities.SuperUser;
import dev.knittle.entities.User;
import dev.knittle.services.SuperUserService;
import dev.knittle.services.SuperUserServiceImpl;
import dev.knittle.services.UserService;
import dev.knittle.services.UserServiceImpl;

public class MainConsole {
	
	public static Scanner scan = new Scanner(System.in); //Keep private? Make final?
	private static UserService us = new UserServiceImpl();
	private static SuperUserService sus = new SuperUserServiceImpl();
	private static User loggedInUser = null;
	//private static SuperUser loggedInSuperUser = null;
	//private static SuperUser loggedInSuperUser = null;
	
	//Temporary Variables
	private static String username = "";
	public static String greeting = "HOWDY, PARDNER! WELCOME TO THE TUMBLEWEED BANKING AND TRUST (TB&T) APP!";
	public static String goodbye = "Thanks for using our app! Y'all come back now, ya hear?";
	private static boolean isSuperUser = false;
	private static boolean isRunning = true;
	

	public static void main(String[] args) {
		
		System.out.println(greeting);

		while(isRunning) {
		
		while(loggedInUser == null /*&& loggedInSuperUser == null*/) {
			
			System.out.println("\nMAIN MENU");
			System.out.println("What would you like to do? Please make a selection by typing a number. Then hit Enter:");
			System.out.println("1: Login, 2: Register, 3: App Info, 99: (Admin Only), 0: EXIT");
			
			String option = scan.nextLine();
			//us.checkOption(option); //Create method once happy path established
			
			switch(option) {
			//Login
			case "1": {
				System.out.println("Logging in...");
				System.out.println("Input Username:");
				username = scan.nextLine();
				System.out.println("Input Password:");
				String password = scan.nextLine();
				
				loggedInUser = us.login(username, password);
				
				if(loggedInUser != null) {
					System.out.println("Login Successful!");
				} else {
					System.out.println("Login Unsuccessful - Returning to Main Menu");
				}
			}
				break;
			
			//Register
			case "2": {
				System.out.println("Registering new user...");
				username = createUsername();
				String password = createPassword();
				String firstName = createFirstName();
				String lastName = createLastName();
				
				if(us.registerUser(username, password, firstName, lastName)) {
					System.out.println("User Registered Successfully - Returning to Home Menu");
				} else {
					System.out.println("There was a problem registering the user. Returning to Home Menu");
				}				
				
			}
				break;
				
			//App Info
			case "3": {
				System.out.println("Here's a little history about this-here bank...");
			}
				break;
				
			//SuperUser Login (Hidden) (DELETE)
//			case "98": {
//				System.out.println("Super-User Login");
//				System.out.println("Input Username:");
//				username = scan.nextLine();
//				System.out.println("Input Password:");
//				String password = scan.nextLine();
//				
//				loggedInUser = sus.loginSuperUser(username, password);
//								
//			}
//				break;
				
			//Register SuperUser (Hidden)
			case "99": {
				System.out.println("Register SuperUser (for TB&T Employees, Administrators, and Database Managers)");
				if(!checkSuperUserCredentials()) {
					System.out.println("Improper Credentials - \"The way of the eagle is not the way of the fish, [Lone Ranger]\" - Tonto");
					break;
				} else {
					System.out.println("It seems you are worthy of being a Super-User, now finish registering.");
					
					username = createUsername();
					String password = createPassword();
					String firstName = createFirstName();
					String lastName = createLastName();
					
					if(sus.registerSuperUser(username, password, firstName, lastName)) {
						System.out.println("Super-User Registered Successfully - Returning to Home Menu, login as normal");
					} else {
						System.out.println("There was a problem registering the super-user. Returning to Home Menu");
					}
				}
				
			}
				break;
			
			//Exit App
			case "0": {
				System.out.println(goodbye);
			}
				System.exit(1);
			
			//Check Input
			default: System.out.println("Invalid input");
				break;
			}//Switch1			
		
		}//While2
		
		//System.out.println(loggedInUser.getClass());
		
		
		
		if(loggedInUser != null) {//Send to method and loop there													
			isSuperUser = us.checkSuperUser(loggedInUser);
			if(!isSuperUser) {
				loggedInUser = UserConsole.runUserConsole(loggedInUser); //Send to User Console				
			} else if (isSuperUser) {
				loggedInUser = SuperUserConsole.runSuperUserConsole(loggedInUser); //Send to Super-User Console		
			}
			if(loggedInUser == null) {
				System.out.println("Logged out successfully!");
			}
		}
		
//		} else if(loggedInUser != null && loggedInUser.getClass() == SuperUser.class) {
//			loggedInUser = SuperUserConsole.runSuperUserConsole((SuperUser) loggedInUser);
//		} else if (loggedInUser != null) {
//			System.out.println("Unknown User Type");
//			isRunning = false;
//		}
		
		//if(loggedInUser != null && loggedInUser.getClass() == SuperUser.class)
		
		//System.out.println(loggedInUser);
		
		}//While1 (Main)
				
	}//Main
	
	
	//Main Console Methods
	public static String createUsername() {
		boolean complete = false;
		String username = "";
		
		while(complete == false) {
			System.out.println("Please type a username (no spaces):");
			username = scan.nextLine();
			//us.checkExit(username);
			
			if(us.checkUniqueUsername(username) == false) {
				System.out.println("Username is taken, try again, partner.");
				continue;
			} else if(username.contains(" ")) {
				System.out.println("Invalid username - contains space(s)");
				continue;
			}
			complete = true;
		}//While1
		
		return username;
	}
		
	public static String createPassword() {
		boolean complete = false;
		String password = "";
		
		while(complete == false) {
			
				System.out.println("Please type a new password (must be at least 8 characters):");
				password = scan.nextLine();
				//us.checkExit(password);
				
				if(password.length() < 8) {
					System.out.println("Password too short; must be at least 8 characters");
					continue;
				}
				
				//Maybe add a "re-type password" check or make these a method: boolean checkPassword(password)
				complete = true;	
		}//While2
		return password;
	}
		
	public static String createFirstName() {
		System.out.println("What is your first name?:");
		String firstName = scan.nextLine();
		return firstName;
	}
		
	public static String createLastName() {
		System.out.println("What is your last name?:");
		String lastName = scan.nextLine();
		return lastName;
	}
	
	private static boolean checkSuperUserCredentials() {
		System.out.println("To register as a Super-User 'round these parts, you must first enter the correct password:");
		String checkCode = scan.nextLine();
		
		if(checkCode.equals(SuperUser.getMasterPassword())) {
			return true;
		}
		
		return false;
	}
		


}//MainConsole
