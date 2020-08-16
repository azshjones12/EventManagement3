package com.promineotech.eventManagementAPI4.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.eventManagementAPI4.entity.Attendee;
import com.promineotech.eventManagementAPI4.entity.Event;
import com.promineotech.eventManagementAPI4.repository.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepo;
		
	public Event getEvent(Long id) {
		return eventRepo.findOne(id);
}
	//lists all events
	public Iterable<Event> getEvents(){
		return eventRepo.findAll();
	}
		
	public Set<Attendee> getAttendeesByEventID(Long id) {
		Event foundEvent = eventRepo.findOne(id);
			if(foundEvent !=null) {
			}
			return foundEvent.getAttendees();
	}
	
	public Event createEvent(Event event, Long userId) {
		return eventRepo.save(event); 
	}
	
	public void deleteEvent(Long id) {
		eventRepo.delete(id);
	}
	
	public Event updateEvent(Long id, Event event) {
		Event foundEvent = eventRepo.findOne(id);
		if(foundEvent != null) {
			foundEvent.setEventName(event.getEventName());
			foundEvent.setDescription(event.getDescription());
			foundEvent.setDate(event.getDate());
			foundEvent.setAddress(event.getAddress());
			foundEvent.setCity(event.getCity());
			foundEvent.setState(event.getState());
			foundEvent.setZip(event.getZip());
			foundEvent.setCountry(event.getCountry());
			eventRepo.save(foundEvent);
		}
		return foundEvent;
	}

}
