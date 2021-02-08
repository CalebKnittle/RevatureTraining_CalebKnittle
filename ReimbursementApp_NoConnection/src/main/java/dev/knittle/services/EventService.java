package dev.knittle.services;

import dev.knittle.entities.Event;

public interface EventService {
	
	public Event createEvent(Event event);
	public Event getEventByID(int eventID);

}
