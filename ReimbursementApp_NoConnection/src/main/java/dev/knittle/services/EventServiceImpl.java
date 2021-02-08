package dev.knittle.services;

import dev.knittle.entities.Event;
import dev.knittle.repositories.EventRepo;
import dev.knittle.repositories.EventRepoImpl;

public class EventServiceImpl implements EventService {

	EventRepo er = new EventRepoImpl();
	
	@Override
	public Event createEvent(Event event) {
		return er.createEvent(event);
	}

	@Override
	public Event getEventByID(int eventID) {
		return er.getEventByID(eventID);
	}

}
