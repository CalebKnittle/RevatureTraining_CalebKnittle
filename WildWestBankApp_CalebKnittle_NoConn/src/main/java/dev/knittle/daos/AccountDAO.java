package dev.knittle.daos;

import java.util.List;

import dev.knittle.entities.Account;
import dev.knittle.entities.Transaction;
import dev.knittle.entities.User;

public interface AccountDAO {

		//CREATE
		public boolean createAccount(Account account);
		
		//public Account createAccount(Account account, int userID); //SuperUser only - create account for someone else 
			
		//READ
		public Account getAccountByID(int accountID);
		
		public List<Account> getAllAccountsForUser(User user);
		
		public List<Account> getAllAccounts(); //SuperUser only, Get all accounts across all users
			
		//public Set<Account> getTopAccounts(int minBalance); //SuperUser only
		
		//UPDATE
		public boolean updateAccount(Account account); //SuperUser can update any account											
			
		//DELETE
		public boolean deleteAccount(int accountID); //User can only delete own accounts, SuperUser can delete any
		
		//TRANSACTIONS
		public boolean createTransaction(Transaction trscn);
		
		public List<Transaction> getUserTransactions(User user);
}
