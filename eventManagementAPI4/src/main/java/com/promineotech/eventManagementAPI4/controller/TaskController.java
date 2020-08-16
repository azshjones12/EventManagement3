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

import com.promineotech.eventManagementAPI4.entity.Task;
import com.promineotech.eventManagementAPI4.service.TaskService;
import com.promineotech.eventManagementAPI4.util.TaskStatus;

@RestController
@RequestMapping("/events/{eventId}/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@RequestMapping(method=RequestMethod.GET)
	public Set<Task> getTasksByEventID(@PathVariable Long eventId) {
		return taskService.getTasksByEventID(eventId);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> addTask(@PathVariable Long eventId, @RequestBody Task task) {
		return new ResponseEntity<Object>(taskService.addTask(eventId, task), HttpStatus.CREATED);
	}

	@RequestMapping(value="/{taskId}",method=RequestMethod.GET)
	public Task getTask(@PathVariable Long eventId, @PathVariable Long taskId) {
		return taskService.getTask(eventId, taskId);
	}
	
	@RequestMapping(value="/{taskId}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateTask(@RequestBody Task task, @PathVariable Long eventId, @PathVariable Long taskId) {
		try {
			if (task.getStatus().equals(TaskStatus.IN_PROGRESS)) {
			return new ResponseEntity<Object>(taskService.updateTask(task, taskId), HttpStatus.OK);
			} else if (task.getStatus().equals(TaskStatus.COMPLETED)) {
				return new ResponseEntity<Object>(taskService.completeTask(taskId), HttpStatus.OK);
			}
			return new ResponseEntity<Object>("Invalid update request.", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/{taskId}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteTask(@PathVariable Long eventId, @PathVariable Long taskId) {
		try {
			taskService.removeTask(taskId);
			return new ResponseEntity<Object>("Successfully deleted task with taskId: " + taskId, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Unable to delete task.", HttpStatus.BAD_REQUEST);
		}
	}
}
