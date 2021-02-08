package dev.knittle.services;

import java.util.List;

import dev.knittle.entities.Employee;
import dev.knittle.entities.Event;
import dev.knittle.entities.Grade;
import dev.knittle.entities.TRForm;

public interface TRFormService {
	
	public TRForm approve(TRForm form);
	
	public TRForm deny(TRForm form);
	
	public TRForm createNewForm(TRForm form);
	
	public TRForm getFormByID(int formID);
	
	public List<TRForm> viewPendingForms();
	
	public List<TRForm> viewActionableForms(Employee empl);
	
	public TRForm updateFormStatus(TRForm form);
	
	public TRForm updateNextApprover(TRForm form);
	
	public Grade submitGrade(Grade grade);
	
	public Grade getDefaultPassGrade (Grade grade);
	
	public Event createEvent();

}
