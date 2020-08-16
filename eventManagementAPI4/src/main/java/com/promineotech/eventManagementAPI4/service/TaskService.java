package com.promineotech.eventManagementAPI4.service;

import java.time.LocalDate;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.eventManagementAPI4.entity.Event;
import com.promineotech.eventManagementAPI4.entity.Task;
import com.promineotech.eventManagementAPI4.repository.EventRepository;
import com.promineotech.eventManagementAPI4.repository.TaskRepository;
import com.promineotech.eventManagementAPI4.util.TaskStatus;

@Service
public class TaskService {

	private static final Logger logger = LogManager.getLogger(TaskService.class);

	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private EventRepository eventRepo;
	
//finds task with the given taskid in the DB with given event id
	public Task getTask(Long id, Long taskId) {
		Event foundEvent = eventRepo.findOne(id);
		Task foundTask = taskRepo.findOne(taskId);
		if(foundEvent !=null && foundTask !=null && foundTask.getEvent().getEventId().equals(id)) {
		}
		return taskRepo.findOne(taskId);
}

//lists all tasks
	public Iterable<Task> getTasks(){
		return taskRepo.findAll();
	}
	
	public Set<Task> getTasksByEventID(Long id) {
		Event foundEvent = eventRepo.findOne(id);
			if(foundEvent !=null) {
			}
			return foundEvent.getTasks();
	}
	
	public Task addTask(Long id, Task task) {
		Event foundEvent = eventRepo.findOne(id);
			if(foundEvent !=null) {
		}
		task.setStatus(TaskStatus.IN_PROGRESS);
		foundEvent.getTasks().add(task);
		return taskRepo.save(task);
	}
	
	public Task updateTask(Task task, Long taskId) throws Exception {
		try {
			Task oldTask = taskRepo.findOne(taskId);
			oldTask.setName(task.getName());
			oldTask.setDescription(task.getDescription());
			oldTask.setNote(task.getNote());
			oldTask.setStatus(TaskStatus.IN_PROGRESS);
			return taskRepo.save(oldTask);
	} catch (Exception e) {
			logger.error("Exception occurred while trying to update task: " + taskId, e);
			throw new Exception("Unable to update task.");
		}
	}
	
	public Task completeTask(Long taskId) throws Exception {
		try {
			Task task = taskRepo.findOne(taskId);
			task.setStatus(TaskStatus.COMPLETED);
			task.setCompletionDate(LocalDate.now());
			return taskRepo.save(task);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to complete task: " + taskId, e);
			throw new Exception("Unable to complete task.");
		}
	}
	
	public void removeTask(Long taskId) throws Exception {
		try {
			taskRepo.delete(taskId);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to delete task: " + taskId, e);
			throw new Exception("Unable to delete task.");
		}
	}

}
