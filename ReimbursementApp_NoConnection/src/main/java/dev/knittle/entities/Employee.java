package dev.knittle.entities;

public class Employee {

	//Fields
	private int emplID = 0;
	private int supervisorID = 0;
	private int deptID = 0;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String title;
	
	//Constructors
	public Employee() {
		super();
	}
	
	public Employee(int emplID, int supervisorID, int deptID, String username, String password, String firstName,
			String lastName, String email, String title) {
		super();
		this.emplID = emplID;
		this.supervisorID = supervisorID;
		this.deptID = deptID;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.title = title;
	}
	
	//ID-Less
	public Employee(int supervisorID, int deptID, String username, String password, String firstName, String lastName,
			String email, String title) {
		super();
		this.supervisorID = supervisorID;
		this.deptID = deptID;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.title = title;
	}

	//Getters/Setters
	public int getEmplID() {
		return emplID;
	}

	public void setEmplID(int emplID) {
		this.emplID = emplID;
	}

	public int getSupervisorID() {
		return supervisorID;
	}

	public void setSupervisorID(int supervisorID) {
		this.supervisorID = supervisorID;
	}

	public int getDeptID() {
		return deptID;
	}

	public void setDeptID(int deptID) {
		this.deptID = deptID;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	//To String
	@Override
	public String toString() {
		return "Employee [emplID=" + emplID + ",\nsupervisorID=" + supervisorID + ",\ndeptID=" + deptID + ",\nusername="
				+ username + ",\npassword=" + password + ",\nfirstName=" + firstName + ",\nlastName=" + lastName
				+ ",\nemail=" + email + ",\ntitle=" + title + "]";
	}
	
	
	
	
	
	
	
}
