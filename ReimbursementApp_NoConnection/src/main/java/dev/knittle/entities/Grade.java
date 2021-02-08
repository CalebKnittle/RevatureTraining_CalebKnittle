package dev.knittle.entities;

//import java.io.File;

public class Grade {

	//Fields
	private int gradeID; //Will match corresponding formID
	private int formatID;
	private int passingGrade;
	private byte[] submittedWork;
	private int finalGrade;
	
	//Constructors
	public Grade() {
		super();
	}
	
	//Not creating ID-less Constructor because gradeID needs to equal an existing formID
	public Grade(int gradeID, int formatID, int passingGrade, byte[] submittedWork, int finalGrade) {
		super();
		this.gradeID = gradeID;
		this.formatID = formatID;
		this.passingGrade = passingGrade;
		this.submittedWork = submittedWork;
		this.finalGrade = finalGrade;
	}

	//Getters/Setters
	public int getGradeID() {
		return gradeID;
	}

	public void setGradeID(int gradeID) {
		this.gradeID = gradeID;
	}

	public int getFormatID() {
		return formatID;
	}

	public void setFormatID(int formatID) {
		this.formatID = formatID;
	}

	public int getPassingGrade() {
		return passingGrade;
	}

	public void setPassingGrade(int passingGrade) {
		this.passingGrade = passingGrade;
	}

	public byte[] getSubmittedWork() {
		return submittedWork;
	}

	public void setSubmittedWork(byte[] submittedWork) {
		this.submittedWork = submittedWork;
	}

	public int getFinalGrade() {
		return finalGrade;
	}

	public void setFinalGrade(int finalGrade) {
		this.finalGrade = finalGrade;
	}

	//To String
	@Override
	public String toString() {
		return "Grade [gradeID=" + gradeID + ",\nformatID=" + formatID + ",\npassingGrade=" + passingGrade
				+ ",\nsubmittedWork=" + submittedWork + ",\nfinalGrade=" + finalGrade + "]";
	}
	
	
	
	
	
	
	
}
