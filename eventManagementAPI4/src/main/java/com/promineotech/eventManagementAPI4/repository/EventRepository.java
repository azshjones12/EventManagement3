package com.promineotech.eventManagementAPI4.repository;

import org.springframework.data.repository.CrudRepository;

import com.promineotech.eventManagementAPI4.entity.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

}
