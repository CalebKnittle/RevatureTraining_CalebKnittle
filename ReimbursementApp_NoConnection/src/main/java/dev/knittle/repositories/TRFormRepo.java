package dev.knittle.repositories;

import java.util.List;

import dev.knittle.entities.TRForm;

public interface TRFormRepo {
	
	//CREATE
	public TRForm createTRForm(TRForm form);
	
	//READ
	public TRForm getTRFormByID(int formID);	//ID will first be accessed as lists from employee or approver, then selected from those
	
	public TRForm getTRFormSupplement(TRForm form); //Could make private
	
	public List<TRForm> getTRFormsPendingAction(int emplID); //Call this to see which ones an employee needs to approve
	
	public List<TRForm> getTRFormsForEmployee(int emplID); //View all submitted forms from one employee
	
	public List<TRForm> getAllTRForms();
	
	//UPDATE
	public TRForm updateTRForm(TRForm form);
	
	public TRForm updateTRFormSupplement(TRForm form); //Could make private
	
	//DELETE
	public TRForm deleteTRForm(int formID);

}
