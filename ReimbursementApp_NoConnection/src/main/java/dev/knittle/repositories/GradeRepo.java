package dev.knittle.repositories;

import java.util.List;

import dev.knittle.entities.Grade;
//import dev.knittle.entities.TRForm;

public interface GradeRepo {

	//CREATE
	public Grade createGrade(Grade grade);
	
	//READ
	public Grade getGradeByID(int gradeID);
	
	public Grade getDefaultPassGrade(Grade grade);
	
	//public List<Grade> getGradesForEmployee(int gradeID);
	
	public List<Grade> getAllGrades();
	
	//UPDATE
	public Grade updateGrade(Grade grade);
	
	//DELETE
	public Grade deleteGrade(int gradeID);
	
	
}
