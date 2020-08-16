package com.promineotech.eventManagementAPI4.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.eventManagementAPI4.entity.Attendee;
import com.promineotech.eventManagementAPI4.repository.AttendeeRepository;

@Service
public class AttendeeService {
	
	private static final Logger logger = LogManager.getLogger(AttendeeService.class);

	@Autowired
	private AttendeeRepository attendeeRepo;
	
	//finds attendee with the given id in the DB
	public Attendee getAttendee(Long id) {
		return attendeeRepo.findOne(id);
}
	//lists all attendees
	public Iterable<Attendee> getAttendees(){
		return attendeeRepo.findAll();
	}
	
	public Attendee addAttendee(Attendee attendee) {
		return attendeeRepo.save(attendee);
	}
	
	public Attendee updateAttendee(Attendee attendee, Long id) throws Exception {
		try {
			Attendee oldAttendee = attendeeRepo.findOne(id);
			oldAttendee.setFirstName(attendee.getFirstName());
			oldAttendee.setLastName(attendee.getLastName());
			oldAttendee.setEvents(attendee.getEvents());
			return attendeeRepo.save(oldAttendee);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to update attendee: " + id, e);
			throw new Exception("Unable to update attendee.");
		}
	}
	
	public void removeAttendee(Long id) throws Exception {
		try {
			attendeeRepo.delete(id);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to delete attendee: " + id, e);
			throw new Exception("Unable to delete attendee.");
		}
	}
	
}