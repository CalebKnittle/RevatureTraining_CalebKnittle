package dev.knittle.entities;

//import java.io.File;

/**
 * This class outlines a Tuition Reimbursement Form for an employee to complete in order to 
 * request tuition reimbursement. Fields are separated into two separate tables in the database.
 * @author knitt_000
 *
 */
public class TRForm {
	
	//Fields
	//Main Table
	private int formID;
	private int emplID;
	private int eventID;
	private int actionEmplID;
	private int statusCode;
	private String justification;
	
	//Supplement Table
	private byte[] eventAttachments = null; //May alternately use File instead of Byte Array? (see PubHub)
	private byte[] preApproval = null;
	private String approvalType = null;
	private String timeMissed = null;
	
	//Constructors
	public TRForm() {
		super();
	}
	
	//All Fields
	public TRForm(int formID, int emplID, int eventID, int actionEmplID, int statusCode, String justification,
			byte[] eventAttachments, byte[] preApproval, String approvalType, String timeMissed) {
		super();
		this.formID = formID;
		this.emplID = emplID;
		this.eventID = eventID;
		this.actionEmplID = actionEmplID;
		this.statusCode = statusCode;
		this.justification = justification;
		this.eventAttachments = eventAttachments;
		this.preApproval = preApproval;
		this.approvalType = approvalType;
		this.timeMissed = timeMissed;
	}
	
	//ID-Less, all other fields
	public TRForm(int emplID, int eventID, int actionEmplID, int statusCode, String justification,
			byte[] eventAttachments, byte[] preApproval, String approvalType, String timeMissed) {
		super();
		this.emplID = emplID;
		this.eventID = eventID;
		this.actionEmplID = actionEmplID;
		this.statusCode = statusCode;
		this.justification = justification;
		this.eventAttachments = eventAttachments;
		this.preApproval = preApproval;
		this.approvalType = approvalType;
		this.timeMissed = timeMissed;
	}

	//ID-Less, Required fields only
	public TRForm(int emplID, int eventID, int actionEmplID, int statusCode, String justification) {
		super();
		this.emplID = emplID;
		this.eventID = eventID;
		this.actionEmplID = actionEmplID;
		this.statusCode = statusCode;
		this.justification = justification;
	}

	//Getters/Setters
	public int getFormID() {
		return formID;
	}

	public void setFormID(int formID) {
		this.formID = formID;
	}

	public int getEmplID() {
		return emplID;
	}

	public void setEmplID(int emplID) {
		this.emplID = emplID;
	}

	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public int getActionEmplID() {
		return actionEmplID;
	}

	public void setActionEmplID(int actionEmplID) {
		this.actionEmplID = actionEmplID;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public byte[] getEventAttachments() {
		return eventAttachments;
	}

	public void setEventAttachments(byte[] eventAttachments) {
		this.eventAttachments = eventAttachments;
	}

	public byte[] getPreApproval() {
		return preApproval;
	}

	public void setPreApproval(byte[] preApproval) {
		this.preApproval = preApproval;
	}

	public String getApprovalType() {
		return approvalType;
	}

	public void setApprovalType(String approvalType) {
		this.approvalType = approvalType;
	}

	public String getTimeMissed() {
		return timeMissed;
	}

	public void setTimeMissed(String timeMissed) {
		this.timeMissed = timeMissed;
	}

	//To String (Full and Shortened Versions)
	public String toStringFull() {
		return "TRForm [formID=" + formID + ", emplID=" + emplID + ", eventID=" + eventID + ", actionEmplID="
				+ actionEmplID + ", statusCode=" + statusCode + ", justification=" + justification
				/*+ ", eventAttachments=" + eventAttachments + ", preApproval=" + preApproval */+ ", approvalType="
				+ approvalType + ", timeMissed=" + timeMissed + "]";
	}

	@Override
	public String toString() {
		return "TRForm [formID=" + formID + ",\nemplID=" + emplID + ",\neventID=" + eventID + ",\nactionEmplID="
				+ actionEmplID + ",\nstatusCode=" + statusCode + ",\njustification=" + justification + "]";
	}
	
	
	

}
