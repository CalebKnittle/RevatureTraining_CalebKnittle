package dev.knittle.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import java.util.Set;

import dev.knittle.entities.Account;
import dev.knittle.entities.Transaction;
import dev.knittle.entities.User;
import dev.knittle.utilities.JDBCConnection;

public class AccountDAOImpl implements AccountDAO{

	public static Connection conn = JDBCConnection.getConnection();
	
	//CREATE------------------------------------------------------------------------------	
	public boolean createAccount(Account account) {
		
		try {	
			String sql = "CALL add_account(?, ?)";
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setString(1, Integer.toString(account.getOwnerID()));
			cs.setString(2, account.getAcctType());

			
			cs.execute();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
			
			return false;
		}

	//READ-------------------------------------------------------------------------------
	public Account getAccountByID(int accountID) {
		
		try {

			String sql = "SELECT * FROM accounts WHERE account_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(accountID));

			ResultSet rs = ps.executeQuery();

			if(rs.next()) {

				Account tempAccount = new Account();
				tempAccount.setAccountID(rs.getInt("ACCOUNT_ID"));
				tempAccount.setOwnerID(rs.getInt("USER_ID"));
				tempAccount.setBalance(rs.getDouble("BALANCE"));
				tempAccount.setAcctType(rs.getString("TYPE"));
			
				return tempAccount;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public List<Account> getAllAccountsForUser(User user) {
		// TODO Auto-generated method stub
		List<Account> userAcctList = new ArrayList<Account>();
		
		try {

			String sql = "SELECT * FROM accounts WHERE user_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(user.getUserID()));

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {

				Account tempAccount = new Account();
				tempAccount.setAccountID(rs.getInt("ACCOUNT_ID"));
				tempAccount.setOwnerID(rs.getInt("USER_ID"));
				tempAccount.setBalance(rs.getDouble("BALANCE"));
				tempAccount.setAcctType(rs.getString("TYPE"));
			
				userAcctList.add(tempAccount);
			}
			return userAcctList;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public List<Account> getAllAccounts() {
		
		List<Account> allAcctList = new ArrayList<Account>();
			
		try {

			String sql = "SELECT * FROM accounts";

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {

				Account tempAccount = new Account();
				tempAccount.setAccountID(rs.getInt("ACCOUNT_ID"));
				tempAccount.setOwnerID(rs.getInt("USER_ID"));
				tempAccount.setBalance(rs.getDouble("BALANCE"));
				tempAccount.setAcctType(rs.getString("TYPE"));
			
				allAcctList.add(tempAccount);
			}
			return allAcctList;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	//UPDATE------------------------------------------------------------------------------
	public boolean updateAccount(Account account) {

		try {

			String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Double.toString(account.getBalance()));
			ps.setString(2, Integer.toString(account.getAccountID()));

			ps.executeQuery();
			
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	
	//DELETE------------------------------------------------------------------------------
	public boolean deleteAccount(int accountID) {

		try {

			String sql = "DELETE accounts WHERE account_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(accountID));

			ps.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean createTransaction(Transaction trscn) {
		
		try {	
			String sql = "CALL add_transaction(?, ?, ?, ?)";
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setString(1, Integer.toString(trscn.getAccountID()));
			cs.setString(2, Integer.toString(trscn.getUserID()));
			cs.setString(3, Double.toString(trscn.getAmount()));
			cs.setString(4, trscn.getType());
			
			cs.execute();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public List<Transaction> getUserTransactions(User user) {
		// TODO Auto-generated method stub
		
		List<Transaction> transactionList = new ArrayList<Transaction>();
		
		try {

			String sql = "SELECT * FROM transactions WHERE user_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(user.getUserID()));

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {

				Transaction tempTransaction = new Transaction();
				tempTransaction.setTransactionID(rs.getInt("TRANSACTION_ID"));
				tempTransaction.setAccountID(rs.getInt("ACCOUNT_ID"));
				tempTransaction.setUserID(rs.getInt("USER_ID"));
				tempTransaction.setAmount(rs.getDouble("AMOUNT"));
				tempTransaction.setType(rs.getString("TYPE"));
				tempTransaction.setTime(rs.getTimestamp("TIME").toString());
			
				transactionList.add(tempTransaction);
			}
			return transactionList;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
