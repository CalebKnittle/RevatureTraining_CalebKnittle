package dev.knittle.app;

import java.util.Scanner;

import dev.knittle.entities.Account;
import dev.knittle.services.AccountService;
import dev.knittle.services.AccountServiceImpl;

public class AccountConsole {

	private static Scanner scan = MainConsole.scan;
	private static AccountService as = new AccountServiceImpl();
	
	public static Account runAccountConsole(Account account) {
		
		while(account != null) {
			System.out.println("\nACCOUNT MENU");
			viewAcctDetails(account);
			
			System.out.println("What would you like to do?");
			System.out.println("1: Make a Deposit, 2: Make a Withdraw, 3: Delete This Account, 4: Return to User Menu");
			String input = scan.nextLine();
			
			switch(input) {
			case "1": {
				System.out.println("How much money (USD) would you like to deposit?");
				double amount = inputAmount();
				account = as.deposit(account, amount);
				
			}
				break;
				
			case "2": {
				System.out.println("How much money (USD) would you like to withdraw?");
				double amount = inputAmount();
				amount = checkWithdraw(amount, account.getBalance());
				account = as.withdraw(account, amount);
			}
				break;
				
			case "3": {
				System.out.println("Deleting Account - Any remaining balance will be withdrawn.");
				if(as.deleteEmptyAccount(account)) {
					System.out.println("Account deleted successfully - returning to User Console");
					account = null;
				}
			}
				break;
				
			case "4": {
				System.out.println("Returning to User Console...");
				account = null;
			}
				break;
				
			default: System.out.println("Invalid Input");
				break;
			
			}//Switch
			
			
			
		}//While1
			
		return account;
	}
	
	private static void viewAcctDetails(Account account) {
		System.out.println("Account Details:");
		System.out.println("Current Account (ID): " + account.getAccountID());
		System.out.println("Owner ID: " + account.getOwnerID());
		System.out.println("Account Type: " + account.getAcctType());
		System.out.println("Current Balance: " + account.getBalance());
	}
	
	private static double inputAmount() {
		
		double amount;
		
		while(true) {
			try {
				System.out.println("Enter a dollar amount, exclude the dollar sign:");
				amount = Double.parseDouble(scan.nextLine());
				if (amount <= 0.00) {
					System.out.println("Amount must be greater than $0");
				} else {
					return amount;
				}			
			} catch(NumberFormatException nfe) {
				System.out.println("Invalid Input - Enter a Number");
			}
		}
	}
	
	private static double checkWithdraw(double amount, double balance) {
		
		if(amount > balance) {
			System.out.println("Amount to withdraw exceeds current balance.\nWithdrawing remaining balance...");
			amount = balance;
		}
		return amount;
	}
	
}
