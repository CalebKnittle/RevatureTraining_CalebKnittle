package dev.knittle.repositories;

import java.util.List;

import dev.knittle.entities.Employee;

public interface EmployeeRepo {
	
	//CREATE
	public Employee createEmployee(Employee empl);
	
	//READ
	public Employee getEmplByID(int emplID);	
	
	public Employee getEmplByUsername(String username);
	
	public Employee checkDeptHead (Employee empl);
		
	public Employee getDeptHead(Employee empl);
	
	public List<Employee> getAllEmployees();
	
	//UPDATE
	public Employee updateEmployee(Employee empl);
	
	//DELETE
	public Employee deleteEmployee(int emplID);

}
