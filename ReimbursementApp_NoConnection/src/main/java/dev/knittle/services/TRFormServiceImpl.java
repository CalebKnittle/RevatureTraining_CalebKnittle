package dev.knittle.services;

import java.util.List;

import dev.knittle.entities.Employee;
import dev.knittle.entities.Event;
import dev.knittle.entities.Grade;
import dev.knittle.entities.TRForm;
import dev.knittle.repositories.EmployeeRepo;
import dev.knittle.repositories.EmployeeRepoImpl;
import dev.knittle.repositories.GradeRepo;
import dev.knittle.repositories.GradeRepoImpl;
import dev.knittle.repositories.TRFormRepo;
import dev.knittle.repositories.TRFormRepoImpl;

public class TRFormServiceImpl implements TRFormService {
	
	TRFormRepo formRepo = new TRFormRepoImpl();
	EmployeeRepo emplRepo = new EmployeeRepoImpl();
	GradeRepo gradeRepo = new GradeRepoImpl();

	@Override
	public TRForm approve(TRForm form) { //Will use updateFormStatus()/updateNextApprover()

		System.out.println("Before Running approve:" + form);
		form = this.updateFormStatus(form);	
		System.out.println("Here, after up");
		form = this.updateNextApprover(form);
		//form.setActionEmplID(actionEmplID);
		form = formRepo.updateTRForm(form);
		System.out.println("After Running approve:" + form);
		
		
		return form;
	}

	@Override
	public TRForm deny(TRForm form) {
		// TODO Auto-generated method stub
		form.setStatusCode(0);
		form.setActionEmplID(0);
		
		form = formRepo.updateTRForm(form);
		
		return form;
	}

	@Override
	public TRForm createNewForm(TRForm form) {
		return formRepo.createTRForm(form);
	}

	@Override
	public List<TRForm> viewPendingForms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TRForm> viewActionableForms(Employee empl) {	
		return formRepo.getTRFormsPendingAction(empl.getEmplID());
	}

	@Override
	public TRForm updateFormStatus(TRForm form) {
		// TODO Auto-generated method stub
		
		int code = form.getStatusCode();
		
		switch(code) {
		//10 = Submitted, Need Supervisor Approval
		case 10: {
			code = 20; //20 = Supervisor Approved
			break;
		}
		case 20: {
			code = 30; //30 = Department Head Approved
			break;
		}
		case 30: {
			code = 40; //40 = Benefits Coordinator Approved
			break;
		}
		case 40: {
			code = 1; //1 = Grade Approved, Awarded Reimbursement Request
			break;
		}
		default: {
			System.out.println("Invalid Status Code");
			//return null; //doing this might break whole system
			break;
		}
		}
		
		form.setStatusCode(code);
		
		return form;
	}

	@Override
	public TRForm updateNextApprover(TRForm form) {

		boolean complete = false;
		int code = form.getStatusCode(); //Should be update for this instance
		int tempActionEmplID = form.getActionEmplID();
		Employee empl = emplRepo.getEmplByID(form.getEmplID());
		
		while(complete == false) {
			switch(code) {
			case 10: { //To Supervisor
				if(empl.getEmplID() == empl.getSupervisorID()) {
					form = this.updateFormStatus(form);
					break;
				}
				form.setActionEmplID(empl.getSupervisorID());
				complete = true;
				break;
			}
			case 20: { //To Dept Head
				Employee deptHead = emplRepo.getDeptHead(empl);
				if(tempActionEmplID == deptHead.getEmplID() || deptHead.getEmplID() == empl.getEmplID()) {
					form = this.updateFormStatus(form);
					break;
				}
				form.setActionEmplID(deptHead.getEmplID());
				complete = true;
				break;
			}
			case 30: { //To BenCo
				if(empl.getEmplID() == 2) {
					form = this.updateFormStatus(form);
					break;
				}
				form.setActionEmplID(2);
				complete = true;
				break;
			}
			case 40: {
				Grade grade = gradeRepo.getGradeByID(form.getFormID());
				System.out.println("Grade Format ID:" + grade.getFormatID());
				if(empl.getEmplID() == 2) {
					form.setActionEmplID(1);
					complete = true;
					break;
				}
				
				if(grade.getFormatID() == 1) { //For A-F Grading
					form.setActionEmplID(2);
					complete = true;
					break;
				} else /*if(grade.getFormatID() == 2)*/ { //For Presentation Grade
					form.setActionEmplID(empl.getSupervisorID());
					complete = true;
					break;
				}
			}
			case 1: {
				form.setActionEmplID(0);
				complete = true;
				break;
			}
			default: {
				System.out.println("Invalid Status Code");
				complete = true;
				break;
			}		
			}
		}
		return form;
		//is Submitter next approver? Does emplID = actionEmplID? (if Benco, set actionEmplID = 1; ->CEO to approve Grade)
		//(CEO is own supervisor, Dept Head is own Dept Head)
		//is Approver next approver? Does tempActionEmplID = actionEmplID?			
	}

	@Override
	public Grade submitGrade(Grade grade) {
		return gradeRepo.updateGrade(grade) ;
	}

	@Override
	public Event createEvent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Grade getDefaultPassGrade(Grade grade) {
		return gradeRepo.getDefaultPassGrade(grade);
	}

	@Override
	public TRForm getFormByID(int formID) {
		// TODO Auto-generated method stub
		return formRepo.getTRFormByID(formID);
	}
	

}
