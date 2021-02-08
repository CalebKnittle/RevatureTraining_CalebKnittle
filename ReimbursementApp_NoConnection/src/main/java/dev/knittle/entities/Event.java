package dev.knittle.entities;

//import java.util.Date; //If use Date, may need to replace this with the sql version

public class Event {
	
	//Fields
	private int eventID;
	private int typeID;
	private String eventName;
	private String eventDate;
	private String eventTime;
//	private Date eventDate; //Can replace with Date or Calendar later if possible
//	private Date eventTime;
	private String location;
	private String description;
	private double cost;
	
	//Constructors
	public Event() {
		super();
	}
	
	public Event(int eventID, int typeID, String eventDate, String eventTime, String location, String description,
			double cost, String eventName) {
		super();
		this.eventID = eventID;
		this.typeID = typeID;
		this.eventName = eventName;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.location = location;
		this.description = description;
		this.cost = cost;
	}
	
	public Event(int typeID, String eventName, String eventDate, String eventTime, String location, String description, double cost) {
		super();
		this.typeID = typeID;
		this.eventName = eventName;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.location = location;
		this.description = description;
		this.cost = cost;
	}

	//Getters/Setters
	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
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

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	//To String
	@Override
	public String toString() {
		return "Event [eventID=" + eventID + ",\ntypeID=" + typeID + ",\neventDate=" + eventDate + ",\neventTime="
				+ eventTime + ",\nlocation=" + location + ",\ndescription=" + description + ",\ncost=" + cost + "]";
	}
	
	
	
	
	

}
