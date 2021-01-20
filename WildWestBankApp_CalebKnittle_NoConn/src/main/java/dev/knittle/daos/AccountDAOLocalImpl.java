package dev.knittle.daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.knittle.entities.Account;
import dev.knittle.entities.Transaction;
import dev.knittle.entities.User;

public class AccountDAOLocalImpl implements AccountDAO {
	private static Map<Integer, Account> account_table = new HashMap<Integer, Account>();
	//private static Map<Integer, Integer> useraccount_table = new HashMap<Integer, Integer>();
	//Again, I don't want to use two collections, but I need to use them to represent a primary
	//key and a foreign key during testing (foreign key references user ID)

	public boolean createAccount(Account account) {
		int accountID = account.getAccountID();
		account_table.put(accountID, account); //Replace with SQL Commands
		return true;
		//INSERT INTO account_table VALUES (?, ?, ...);
		//? = account.getAccountID, .getUserID, etc.
	}

	public Account getAccountByID(int accountID) {
		Account tempAccount = account_table.get(accountID);
		return tempAccount;
		//SELECT * FROM account_table WHERE accountID = ?;
	}

	public List<Account> getAllAccountsForUser(User user) { //Change
		//int userID = user.getUserID();
		List<Account> userAccounts = new ArrayList<Account>(account_table.values());
		return userAccounts;
		//SELECT * FROM account_table WHERE userID = ?; //Or ownerID
		//? = user.getUserID();
	}

	public List<Account> getAllAccounts() {
		List<Account> allAccounts = new ArrayList<Account>(account_table.values());
		return allAccounts;
		//SELECT * FROM account_table;
	}

	public boolean updateAccount(Account account) {
		account_table.put(account.getAccountID(), account);
		return true;
		//UPDATE account_table SET ? = ? WHERE accountID = ?;
		//?, ?, ? = each field, value for each field, account.getAccountID()
	}

	public boolean deleteAccount(int accountID) {
		if(account_table.remove(accountID) != null) {
			return true;
		}
		return false;
		//DELETE account_table WHERE accountID = ?;
		//? = accountID
	}

	@Override
	public boolean createTransaction(Transaction trscn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Transaction> getUserTransactions(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
