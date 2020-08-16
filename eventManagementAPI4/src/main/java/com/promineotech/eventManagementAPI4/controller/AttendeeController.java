package com.promineotech.eventManagementAPI4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.eventManagementAPI4.entity.Attendee;
import com.promineotech.eventManagementAPI4.service.AttendeeService;

@RestController
@RequestMapping("/events/{eventId}/attendees")
public class AttendeeController {
	
	@Autowired
	private AttendeeService attendeeService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> addAttendee(@PathVariable Long eventId, @RequestBody Attendee attendee) {
		return new ResponseEntity<Object>(attendeeService.addAttendee(attendee), HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getAttendees(@PathVariable Long eventId) {
		return new ResponseEntity<Object>(attendeeService.getAttendees(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{attendeeId}",method=RequestMethod.GET)
	public Attendee getAttendee(@PathVariable Long eventId, @PathVariable Long attendeeId) {
		return attendeeService.getAttendee(attendeeId);
	}
	
	@RequestMapping(value="/{attendeeId}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateAttendee(@PathVariable Long eventId, @RequestBody Attendee attendee, @PathVariable Long attendeeId) {
		try {
			return new ResponseEntity<Object>(attendeeService.updateAttendee(attendee, attendeeId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Unable to update attendee.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{attendeeId}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteAttendee(@PathVariable Long eventId, @PathVariable Long attendeeId) {
		try {
			attendeeService.removeAttendee(attendeeId);
			return new ResponseEntity<Object>("Successfully deleted attendee with id: " + attendeeId, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Unable to delete attendee.", HttpStatus.BAD_REQUEST);
		}
	}

}
