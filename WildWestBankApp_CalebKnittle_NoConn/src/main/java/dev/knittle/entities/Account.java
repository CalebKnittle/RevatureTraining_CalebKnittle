package dev.knittle.entities;

public class Account {
	
	//Fields
	//private static int masterAcctID = 1; //Keeps track of next account ID
	
	private int accountID;
	private int ownerID; //Refers to User ID of account creator, not sure if I'll need
	private double balance; //$USD in the Account
	private String acctType; //Checking, Savings, etc. -> may make classes
	//boolean premiumAccount; //Add later possibly -> Maybe not, 2NF -> could figure from given data
	
	//Constructors
	//Default Constructor
	public Account() {
		super();
//		this.accountID = masterAcctID;
//		masterAcctID++;
		this.balance = 0;
		this.acctType = "Savings"; //Default type is savings
	}
	
	//Primary Constructor to Use
	public Account(int ownerID, String acctType) {
		super();
//		this.accountID = masterAcctID;
//		masterAcctID++;
		this.ownerID = ownerID;
		this.balance = 0;
		this.acctType = acctType;
	}

	//Should not use this Constructor so that a user can't set their own ID and balance
	public Account(int accountID, int ownerID, double balance, String acctType) {
		super();
		this.accountID = accountID;
		this.ownerID = ownerID;
		this.balance = balance;
		this.acctType = acctType;
	}
	
	//Getters/Setters
	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

//	public static int getMasterAcctID() {
//		return masterAcctID;
//	}
//
//	public static void setMasterAcctID(int masterAcctID) {
//		Account.masterAcctID = masterAcctID;
//	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAcctType() {
		return acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}

	public int getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}

	//toString
	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", balance=" + balance + ", acctType=" + acctType + "]";
	}	

}
