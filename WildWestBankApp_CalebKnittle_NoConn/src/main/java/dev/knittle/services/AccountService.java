package dev.knittle.services;

import java.util.List;

import dev.knittle.entities.Account;
import dev.knittle.entities.User;

public interface AccountService {

	public List<Account> getAllAccountsAllUsers();
	
	public List<Account> viewUserAccounts(User user);
	
	public Account selectAccount(int acctID);
	
	public boolean addNewAccount(int ownerID, String type);
	
	public boolean deleteEmptyAccount(Account acct);
	
	public Account withdraw(Account acct, double amount);
	
	public Account deposit(Account acct, double amount);
	
	public boolean viewTransactions(User user);
}
