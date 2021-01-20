package dev.knittle.entities;

import java.util.Date;

public class User {

	//Fields
//	private static int masterUserID = 1; //Will replace with database sequences
	
	private int userID = 0;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String address = "";
	private String birthdate = "";
	private Date dateJoined = null;
	
	//Constructors
	public User() {
		super();
//		this.userID = 0;
//		this.userID = masterUserID;
//		masterUserID++;
	}
		
	//User ID not based on constructor input to avoid possible duplicates
	public User(String username, String password, String firstName, String lastName) {
		super();
//		this.userID = masterUserID;
//		masterUserID++;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	//Getters/Setters
//	public static int getMasterUserID() {
//		return masterUserID;
//	}
//
//	//Should not use
//	public static void setMasterUserID(int masterUserID) {
//		User.masterUserID = masterUserID;
//	}

	public int getUserID() {
		return userID;
	}

	//Should not use
	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthDate) {
		this.birthdate = birthDate;
	}

	public Date getDateJoined() {
		return dateJoined;
	}

	public void setDateJoined(Date dateJoined) {
		this.dateJoined = dateJoined;
	}

	//toString
	@Override
	public String toString() {
		return "User [userID=" + userID + ", username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
}
