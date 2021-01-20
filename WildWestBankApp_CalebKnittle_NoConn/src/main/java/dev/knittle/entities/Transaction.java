package dev.knittle.entities;

//import java.util.Calendar;
//import java.util.Date;

//Bonus Requirement
public class Transaction {
	
	//Fields
	//private static int masterTransactionID = 1;
	
	private int transactionID;
	private int accountID; //Account on which transaction made (Optional, can get from Account)
	private int userID; //User that made the transaction (Optional, can get from User)
	private double amount;
	private String type; //Type of Transaction: Deposit, Withdrawal, etc.
	private String time;
	//private Calendar date; //Date and time of transaction
	
	//Constructors
	public Transaction() {
		super();
	}
	
	public Transaction(int transactionID, double amount, String type) {
		super();
		this.transactionID = transactionID;
		this.amount = amount;
		this.type = type;
	}

	//Primarily Used
	public Transaction(int accountID, int userID, double amount, String type) {
		super();
		this.accountID = accountID;
		this.userID = userID;
		this.amount = amount;
		this.type = type;
	}

	//Getters/Setters
//	public static int getMasterTransactionID() {
//		return masterTransactionID;
//	}
//
//	public static void setMasterTransactionID(int masterTransactionID) {
//		Transaction.masterTransactionID = masterTransactionID;
//	}

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	//toString
	@Override
	public String toString() {
		return "Transaction [transactionID= " + transactionID + ", accountID= " + accountID + ", userID= " + userID
				+ ", amount= " + amount + ", type= " + type + ", time= " + time + "]";
	}
	
	
	

}
