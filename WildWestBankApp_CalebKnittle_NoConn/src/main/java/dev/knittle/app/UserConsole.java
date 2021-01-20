package dev.knittle.app;

import java.util.Scanner;

import dev.knittle.entities.Account;
import dev.knittle.entities.User;
import dev.knittle.services.AccountService;
import dev.knittle.services.AccountServiceImpl;
import dev.knittle.services.UserService;
import dev.knittle.services.UserServiceImpl;

public class UserConsole {

	public UserConsole() {
		super();
	}	
	
	private static Scanner scan = MainConsole.scan;
	private static UserService us = new UserServiceImpl();
	private static AccountService as = new AccountServiceImpl();
	private static String option = "";
	
	public static User runUserConsole(User user) {
		
		
		System.out.println("Welcome/Howdy, " + user.getFirstName() + " " + user.getLastName() + "!");
		us.viewProfile(user);
		
		while(user != null) {
			
			System.out.println("\nUSER MENU");
			System.out.println("What would you like to do, " + user.getFirstName() +"?");
			System.out.println("1: View Detailed Profile, 2: Update Profile 3: View Your Accounts, 4: Select Account,\n5: Create Account, "
					+ "6: View Your Transactions, 7: Delete Profile, 9: Logout, 0: EXIT App");
			option = scan.nextLine();
			
			switch(option) {
			//Detailed Profile
			case "1": {
				System.out.println("Fetching Detailed Profile...");//Temporary Output
				us.viewDetailedProfile(user);
			}
				break;
				
			//Update Profile
			case "2": {
				System.out.println("Updating Profile...");
				user = prepUserUpdate(user);
				us.updateProfile(user);
				
			}
				break;
			//View All Accounts
			case "3": {
				System.out.println("Fetching your accounts...");
				as.viewUserAccounts(user);
			}			
				break;
				
			//Select Account
			case "4": {
				System.out.println("Please enter the ID of the Account you would like to use:");
				int accountID = Integer.parseInt(scan.nextLine()); //Move to a try/catch block
				Account tempAcct = as.selectAccount(accountID); //-> AccountConsole.runAccountConsole(Account);
				if(tempAcct == null) {
					System.out.println("There was a problem selecting the account...");
				} else {
					tempAcct = AccountConsole.runAccountConsole(tempAcct);
				}
			}
				break;
				
			//Create Account
			case "5": {
				String acctType = selectAcctType();
				System.out.println("Creating New " + acctType + " Account...");
				as.addNewAccount(user.getUserID(), acctType);
			}
				break;
			
			//View All Transactions
			case "6": {
				System.out.println("Fetching User Transactions...");
				as.viewTransactions(user);
			}
				break;
				
			//Delete Profile
			case "7": {
				if(us.deleteProfile(user)) {
					System.out.println("User deleted, now logging out.");
					user = us.logout(user);
				}								
			}
				break;
				
			case "8":
				break;
				
			//Logout
			case "9": user = us.logout(user);
				break;
				
			//Exit App
			case "0": {
				System.out.println(MainConsole.goodbye);
				System.exit(1);		
			}
				break;
			
			//Check Input
			default: System.out.println("Invalid Input");
				break;			
			}//Switch 1		
		
		}//While1 (Outer)
		
		return user;
	}
	
	//User Console Methods
	public static User prepUserUpdate(User user) {
		boolean finished = false;
		
		while(!finished) {
			System.out.println("What would you like to change?:");
			System.out.println("1: Username\n2: Password\n3: Name\n4: Address\n5: Birthdate\n6: Finish Updating");
			String option = scan.nextLine();
			
			switch(option) {
			//Username
			case "1": {
				System.out.println("Updating Username...");
				user.setUsername(MainConsole.createUsername());
			}
				break;
				
			//Password
			case "2": {
				System.out.println("Updating Password...");
				user.setPassword(MainConsole.createPassword());
			}
				break;
				
			//Name
			case "3": {
				System.out.println("Updating Name...");
				user.setFirstName(MainConsole.createFirstName());
				user.setLastName(MainConsole.createLastName());
			}
				break;
				
			//Address
			case "4": {
				System.out.println("Updating Address...");
				System.out.println("Enter Address:");
				String address = scan.nextLine();
				user.setAddress(address);
			}
				break;
				
			//Birthdate
			case "5": {
				System.out.println("Updating Birthdate...");
				System.out.println("Enter Birthdate:");
				String birthdate = scan.nextLine();
				user.setBirthdate(birthdate);
			}
				break;
				
			//Finish Updating
			case "6":{
				finished = true;
			}
				break;
				
			default: {
				System.out.println("Invalid Input");
			}
				break;
			}//Switch
		}//While
		
		return user;
	}
	
	public static String selectAcctType() {
		System.out.println("What type of account would you like to make?:");
		System.out.println("1: Savings, 2: Checking, 3: Investment, 4: Retirement");
		String input = scan.nextLine();
		String type = "";
		
		while (type.equals("")) {
			switch(input) {
			case "1": type = "Savings";
				break;
				
			case "2": type = "Checking";
				break;
				
			case "3": type = "Investment";
				break;
				
			case "4": type = "Retirement";
				break;
				
			default: System.out.println("Invalid Input, Try Again");
				break;		
			}
		}
		
		return type;
	}
	
	
	
}
