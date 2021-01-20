package dev.knittle.services;

//import java.util.ArrayList;
import java.util.List;

import dev.knittle.daos.AccountDAO;
import dev.knittle.daos.AccountDAOImpl;
import dev.knittle.entities.Account;
import dev.knittle.entities.Transaction;
import dev.knittle.entities.User;

public class AccountServiceImpl implements AccountService {

	AccountDAO adao = new AccountDAOImpl();
	
	@Override
	public List<Account> getAllAccountsAllUsers() {
		// TODO Auto-generated method stub
		List<Account> allAcctList = adao.getAllAccounts();
		
		System.out.println("ALL ACCOUNTS ACROSS ALL USERS:");
		System.out.println("Account ID\tOwner ID\tAcct Type\tBalance");
		
		for(int i = 0; i < allAcctList.size(); i++) {
			Account tempAcct = allAcctList.get(i);
			
			System.out.println(tempAcct.getAccountID() + "\t" + tempAcct.getOwnerID() + "\t"
					+ tempAcct.getAcctType() + "\t" + tempAcct.getBalance());
			
		}
			
		return null;
	}

	@Override
	public List<Account> viewUserAccounts(User user) {
		
		List<Account> acctList = adao.getAllAccountsForUser(user);
		
		System.out.println("ALL ACCOUNTS FOR " + user.getFirstName() + " " + user.getLastName() + " (User ID: " + user.getUserID() + "):");
		System.out.println("Acct ID\t\tAccount Type\tBalance");
		
		for(int i = 0; i < acctList.size(); i++) {
			Account tempAcct = acctList.get(i);
			
			System.out.println(tempAcct.getAccountID() + "\t\t"
					+ tempAcct.getAcctType() + "\t\t" + tempAcct.getBalance());
			
		}
		
		return null;
	}

	@Override
	public Account selectAccount(int acctID) {
		
		return adao.getAccountByID(acctID);
	}

	@Override
	public boolean addNewAccount(int ownerID, String type) {
		// TODO Auto-generated method stub
		Account tempAcct = new Account(ownerID, type);
		
		return adao.createAccount(tempAcct);
	}

	@Override
	public boolean deleteEmptyAccount(Account acct) {
		// TODO Auto-generated method stub
		if(acct.getBalance() > 0) {
			acct = withdraw(acct, acct.getBalance());
		}
		
		return adao.deleteAccount(acct.getAccountID());
	}

	@Override
	public Account withdraw(Account acct, double amount) {

		acct.setBalance(acct.getBalance() - amount);
		if(adao.updateAccount(acct)) {
			System.out.println("$" + amount + " has been withdrawn.");
			Transaction transaction = new Transaction(acct.getAccountID(), acct.getOwnerID(), amount, "Withdraw");
			adao.createTransaction(transaction);
		}
		
		return acct;
	}

	@Override
	public Account deposit(Account acct, double amount) {
		
		acct.setBalance(acct.getBalance() + amount);
		if(adao.updateAccount(acct)) {
			System.out.println("$" + amount + " has been deposited.");
			Transaction transaction = new Transaction(acct.getAccountID(), acct.getOwnerID(), amount, "Deposit");
			adao.createTransaction(transaction);
		}
		
		return acct;
	}

	@Override
	public boolean viewTransactions(User user) {
		// TODO Auto-generated method stub
		List<Transaction> transactionList = adao.getUserTransactions(user);
		
		if(transactionList == null) {
			return false;
		}
		
		System.out.println("\nUser's Transactions:");
		for(int i = 0; i < transactionList.size(); i++) {
			System.out.println(transactionList.get(i));
		}
		
		return true;
	}
	
	

}
