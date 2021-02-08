package dev.knittle.services;

import dev.knittle.entities.Employee;

public interface EmployeeService {
	
	public Employee login(String username, String password);
	
	public Employee logout(Employee empl);
	
	public boolean isSubmitterNextApprover();
	
	public boolean isApproverNextApprover();
	
	//public boolean checkSpecialEmployee(); //Split into "isDeptHead", "isCEO", and "isBenCo"
	//Maybe check at login and then direct to appropriate resource
	
	public boolean isDeptHead(Employee empl);
	
	public boolean isCEO(Employee empl);
	
	public boolean isBenCo(Employee empl);
	
	public Employee getEmplByID(int emplID);

}
