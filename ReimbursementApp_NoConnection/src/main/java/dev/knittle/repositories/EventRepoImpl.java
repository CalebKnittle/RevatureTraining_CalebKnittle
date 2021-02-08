package dev.knittle.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.util.Date;
import java.util.List;

//import dev.knittle.entities.Employee;
import dev.knittle.entities.Event;
import dev.knittle.utilities.JDBCConnection;

public class EventRepoImpl implements EventRepo {

	public static Connection conn = JDBCConnection.getConnection();
	
	@Override
	public Event createEvent(Event event) {//Needed

		try {

			String sql = "CALL add_event(?, ?, ?, ?, ?, ?, ?)";
			CallableStatement cs = conn.prepareCall(sql);

			cs.setString(1, Integer.toString(event.getTypeID()));
			cs.setString(2, event.getEventName());
			cs.setString(3, event.getEventDate());
			cs.setString(4, event.getEventTime());
			cs.setString(5, event.getLocation());
			cs.setString(6, event.getDescription());
			cs.setString(7, Double.toString(event.getCost()));		

			cs.execute();
			
			String sql2 = "SELECT * FROM event_store";
			PreparedStatement ps = conn.prepareStatement(sql2);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				event.setEventID(rs.getInt("STORED_VALUE"));
				System.out.println("Returned new Event ID: " + event.getEventID());
			}
			
			return event;

		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}

	@Override
	public Event getEventByID(int eventID) {//Needed
		
		try {

			String sql = "SELECT * FROM event WHERE event_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(eventID));
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				Event tempEvent = new Event();
				tempEvent.setEventID(rs.getInt("EVENT_ID"));
				tempEvent.setTypeID((rs.getInt("TYPE_ID")));
				tempEvent.setEventName((rs.getString("EVENT_NAME")));
				tempEvent.setEventDate((rs.getString("EVENT_DATE")));
				tempEvent.setEventTime((rs.getString("EVENT_TIME")));
				tempEvent.setLocation((rs.getString("LOCATION")));
				tempEvent.setDescription((rs.getString("DESCRIPTION")));
				tempEvent.setCost((rs.getDouble("COST")));
				
				return tempEvent;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}

	@Override
	public List<Event> getAllEvents() {//Maybe
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event updateEvent(Event event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event deleteEvent(int eventID) {
		// TODO Auto-generated method stub
		return null;
	}

}
