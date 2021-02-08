package dev.knittle.entities;

//Note: there is a Java.io DataOutput class
public class DataOutput {
	
	private String requestor;
	private String eventName;
	private String eventDate;
	private Double cost; //Why do I have this as an object? It doesn't seem to hurt anything though.
	private int id;
	
	public DataOutput() {
		super();
	}

	public String getRequestor() {
		return requestor;
	}
	public void setRequestor(String requestor) {
		this.requestor = requestor;
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
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "DataOutput [requestor=" + requestor + ", eventName=" + eventName + ", eventDate=" + eventDate
				+ ", cost=" + cost + ", id=" + id + "]";
	}

	

}
