package com.promineotech.eventManagementAPI4.repository;

import org.springframework.data.repository.CrudRepository;

import com.promineotech.eventManagementAPI4.entity.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {

}
