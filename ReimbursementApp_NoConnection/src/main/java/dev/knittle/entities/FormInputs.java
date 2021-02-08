package dev.knittle.entities;

public class FormInputs {
	
	private String eventName = null;
    private int eventType = 0;
    private String eventDate = null;
    private String location = null;
    private String eventTime = null;
    private String description = null;
    private double eventCost = 0;
    private int emplID = 0;
    private String justification = null;
    private int passingGrade = 0;
    private int gradeType = 0;
	
    public FormInputs() {
		super();
	}
    
    
    
	public FormInputs(String eventName, int eventType, String eventDate, String location, String eventTime, String description,
			double eventCost, int emplID, String justification, int passingGrade, int gradeType) {
		super();
		this.eventName = eventName;
		this.eventType = eventType;
		this.eventDate = eventDate;
		this.location = location;
		this.eventTime = eventTime;
		this.description = description;
		this.eventCost = eventCost;
		this.emplID = emplID;
		this.justification = justification;
		this.passingGrade = passingGrade;
	}



	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public int getEventType() {
		return eventType;
	}
	public void setEventType(int eventType) {
		this.eventType = eventType;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getEventCost() {
		return eventCost;
	}
	public void setEventCost(double eventCost) {
		this.eventCost = eventCost;
	}
	public int getEmplID() {
		return emplID;
	}
	public void setEmplID(int emplID) {
		this.emplID = emplID;
	}
	public String getJustification() {
		return justification;
	}
	public void setJustification(String justification) {
		this.justification = justification;
	}
	public int getPassingGrade() {
		return passingGrade;
	}
	public void setPassingGrade(int passingGrade) {
		this.passingGrade = passingGrade;
	}
	public String getEventTime() {
		return eventTime;
	}
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	public int getGradeType() {
		return gradeType;
	}
	public void setGradeType(int gradeType) {
		this.gradeType = gradeType;
	}

	@Override
	public String toString() {
		return "FormInputs [eventName=" + eventName + ", eventType=" + eventType + ", eventDate=" + eventDate
				+ ", location=" + location + ", eventTime=" + eventTime + ", description=" + description
				+ ", eventCost=" + eventCost + ", emplID=" + emplID + ", justification=" + justification
				+ ", passingGrade=" + passingGrade + ", gradeType=" + gradeType + "]";
	}  

}
