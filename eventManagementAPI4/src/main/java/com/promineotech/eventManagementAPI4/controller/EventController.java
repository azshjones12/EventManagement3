package com.promineotech.eventManagementAPI4.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.eventManagementAPI4.entity.Attendee;
import com.promineotech.eventManagementAPI4.entity.Event;
import com.promineotech.eventManagementAPI4.service.EventService;

@RestController
@RequestMapping("/users/{userId}/events")
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createEvent(@PathVariable Long userId, @RequestBody Event event) {
		try {
			return new ResponseEntity<Object>(eventService.createEvent(event, userId), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getEvents() {
		return new ResponseEntity<Object>(eventService.getEvents(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{eventId}", method=RequestMethod.GET)
	public Event getEvent(@PathVariable Long eventId) {
		return eventService.getEvent(eventId);
	}
	
	@RequestMapping(value="{eventId}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateEvent(@PathVariable Long eventId, @RequestBody Event event) {
		try {
			return new ResponseEntity<Object>(eventService.updateEvent(eventId, event), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Unable to update event.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{eventId}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteEvent(@PathVariable Long eventId) {
		try {
			eventService.deleteEvent(eventId);
			return new ResponseEntity<Object>("Successfully deleted event with ID: " + eventId, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Unable to delete event.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{eventId}/attendees", method=RequestMethod.GET)
	public Set<Attendee> getAttendeesByEventID(@PathVariable Long eventId) {
		return eventService.getAttendeesByEventID(eventId);
	}
	

}
