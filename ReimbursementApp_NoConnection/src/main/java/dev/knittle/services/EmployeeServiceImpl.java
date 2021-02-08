package dev.knittle.services;

import dev.knittle.entities.Employee;
import dev.knittle.repositories.EmployeeRepo;
import dev.knittle.repositories.EmployeeRepoImpl;

public class EmployeeServiceImpl implements EmployeeService {
	
	public EmployeeRepo emplRepo = new EmployeeRepoImpl();

	@Override
	public Employee login(String username, String password) {

		Employee currentEmpl = emplRepo.getEmplByUsername(username);
		
		try {
			if(currentEmpl != null) {
		
				//System.out.println("Input pass = " + password); //Testing
				//System.out.println("Received pass = " + currentUser.getPassword()); //Testing
				if(password.equals(currentEmpl.getPassword())) {
					
					System.out.println("Login Success!");
					return currentEmpl;
					
				} else {
					System.out.println("Incorrect Password");//Testing
					throw new Exception(); //OR IncorrectPasswordException or BadLoginException (maybe should NOT be specific, like real websites)
				}
			} else {
				//Check here if SuperUser (or check above)
				System.out.println("Username not found");//Testing
				throw new Exception(); //OR InvalidUsernameException OR overall BadLoginException
			}
			
		} catch(Exception e) {
			System.out.println("Invalid Username or Password"); //Temporary->Throw exception to calling code, throw exception here, or message-out here
		}
		
		return null;
	}

	@Override
	public Employee logout(Employee empl) { //Could alternately end the session at controller level(?)

		System.out.println("Logging out...");
		empl = null;
		
		return empl;
	}

	@Override
	public boolean isSubmitterNextApprover() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isApproverNextApprover() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDeptHead(Employee empl) { //May not be as useful, "super-powers" mainly for BenCo, optionally for CEO
												//Powers may include: registering new employees, changing TR amounts, approving BenCo actions
		if(emplRepo.checkDeptHead(empl) != null) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean isCEO(Employee empl) {

		//Employee tempEmpl = emplRepo.getEmplByID(empl.getEmplID());
		if(empl.getDeptID() == 1) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean isBenCo(Employee empl) { //Most useful

		if(empl.getDeptID() == 2) {
			return true;
		}
		
		return false;
	}

	@Override
	public Employee getEmplByID(int emplID) {
		return emplRepo.getEmplByID(emplID);
	}


}
