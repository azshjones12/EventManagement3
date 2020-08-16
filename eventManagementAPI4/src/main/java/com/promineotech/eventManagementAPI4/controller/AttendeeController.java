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
@RequestMapping("/attendees")
public class AttendeeController {
	
	@Autowired
	private AttendeeService attendeeService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> addAttendee(@RequestBody Attendee attendee) {
		return new ResponseEntity<Object>(attendeeService.addAttendee(attendee), HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getAttendees() {
		return new ResponseEntity<Object>(attendeeService.getAttendees(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Attendee getAttendee(@PathVariable Long id) {
		return attendeeService.getAttendee(id);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateAttendee(@RequestBody Attendee attendee, @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(attendeeService.updateAttendee(attendee, id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Unable to update attendee.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteAttendee(@PathVariable Long id) {
		try {
			attendeeService.removeAttendee(id);
			return new ResponseEntity<Object>("Successfully deleted attendee with id: " + id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Unable to delete attendee.", HttpStatus.BAD_REQUEST);
		}
	}

}
