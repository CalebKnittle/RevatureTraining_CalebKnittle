package dev.knittle.entities;

public class SuperUser extends User {

	//Fields
	private static String masterPassword = "KemoSabe";
	
	//Constructors
	public SuperUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SuperUser(String username, String password, String firstName, String lastName) {
		super(username, password, firstName, lastName);
		// TODO Auto-generated constructor stub
	}

	//Getters/Setters
	public static String getMasterPassword() {
		return masterPassword;
	}

	public static void setMasterPassword(String masterPassword) {
		SuperUser.masterPassword = masterPassword;
	}
	
	

	
	
}
