package dev.knittle.app;

import java.util.Scanner;

import dev.knittle.entities.SuperUser;
import dev.knittle.entities.User;
import dev.knittle.services.SuperUserService;
import dev.knittle.services.SuperUserServiceImpl;
//import dev.knittle.services.UserService;
//import dev.knittle.services.UserServiceImpl;

public class SuperUserConsole {

	private static Scanner scan = MainConsole.scan;
	private static SuperUserService sus = new SuperUserServiceImpl();
	//private static UserService us = new UserServiceImpl();
	//private static AccountService as = new AccountServiceImpl();
	private static String option = "";
	
	public static User runSuperUserConsole(User superuser) {
		
		
		System.out.println("Extra Howdy, " + superuser.getFirstName() + " " + superuser.getLastName() + "!");
		sus.viewProfile(superuser);
		
		while(superuser != null) {
			System.out.println("\nSUPER-USER MENU");
			System.out.println("What would you like to do, " + superuser.getFirstName() +"?");
			System.out.println("1: User Functions for Self, 2: View All Users 3: View All Accounts for All Users,"
					+ "\n4: Create New User, 5: Select User (to View/Update/Delete/Manage), 9: Logout, 0: Exit App");
			System.out.println("Note: Logging out while in a User menu will return you to here.");
			option = scan.nextLine();
			
			switch(option) {
			
			//Self-User Functions
			case "1": {
				UserConsole.runUserConsole(superuser);
			}
				break;
				
			//View All Users
			case "2": {
				sus.viewAllUsers();
			}
				break;
				
			//View All Accounts (All Users)
			case "3": { //Need
				sus.viewAllAccountsAllUsers();
			}
				break;
				
			//Create User
			case "4": {
				if(sus.createNewUser()) {
					System.out.println("Super-User created a User");
				}
			}
				break;
				
			//Select User
			case "5": {
				System.out.println("Enter the User ID of the profile to select:");
				try {
					int input = Integer.parseInt(scan.nextLine());
					User tempUser = sus.selectUser(input);
					if(tempUser != null) {
						UserConsole.runUserConsole(tempUser);
					} else {
						System.out.println("Could not find user with the specified ID");
					}
					
				} catch(NumberFormatException nfe) {
					//nfe.printStackTrace();
					System.out.println("Invalid Entry - must be a number");
				}
			}
				break;
//OLD FUNCTIONS				
//			//Update User
//			case "6": { 
//				System.out.println("Enter the User ID of the profile to update:");
//				String input = scan.nextLine();
//				
//				try {
//					if(sus.updateUser(Integer.parseInt(input))) {
//						System.out.println("User Successfully Updated");
//					}
//				
//				} catch(NumberFormatException nfe) {
//					nfe.printStackTrace();
//					System.out.println("Invalid Entry - must be a number");
//				}
//			}
//				break;
//				
//			//Delete User
//			case "7": { //Need
//				System.out.println("Enter the User ID of the profile to delete:");
//				String toDelete = scan.nextLine();
//				try {
//					if(sus.deleteUser(Integer.parseInt(toDelete))) {
//						System.out.println("This town wasn't big enough for the two of us! - Delete Successful!");
//					}
//				} catch(NumberFormatException nfe) {
//					nfe.printStackTrace();
//					System.out.println("Invalid Entry - must be a number");
//				}
//			}
//				break;
				
			//Logout
			case "9": {
				superuser = (SuperUser) sus.logout(superuser);
			}
				break;
				
			//EXIT APP
			case "0": {
				System.out.println(MainConsole.goodbye);
				System.exit(1);
			}
				break;
				
			default: System.out.println("Invalid Input");
				break;
			}
			
			
		}//While1
		
		return superuser;
	}//runSuperUserConsole
	
}//SuperUserConsole
