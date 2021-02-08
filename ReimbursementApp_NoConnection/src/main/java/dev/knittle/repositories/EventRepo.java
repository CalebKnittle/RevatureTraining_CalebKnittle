package dev.knittle.repositories;

import java.util.List;

import dev.knittle.entities.Event;

public interface EventRepo {
	
	//CREATE
	public Event createEvent(Event event);
	
	//READ
	public Event getEventByID(int eventID);	//Not sure how possible
	
	//public List<Event> getEventsForEmployee(int emplID);
	
	//public List<Event> getEmployeesForEvent(int eventID);
	
	public List<Event> getAllEvents();
	
	//UPDATE
	public Event updateEvent(Event event);
	
	//DELETE
	public Event deleteEvent(int eventID);

}
