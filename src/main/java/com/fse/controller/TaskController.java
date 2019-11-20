package com.fse.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fse.pojo.Parent;
import com.fse.pojo.Task;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/api/task"})
public class TaskController {

	final static Logger logger = Logger.getLogger(TaskController.class);
	@Autowired
	private TaskServices taskServices;
	
	@GetMapping("/parents")
	public List<Parent> getParentTasks(){
		return taskServices.getParentTasks();
	}
	
	@PostMapping
	public void createTask(@RequestBody Task task) {
		if(task.isCheckParent()) {
			Parent parent = new Parent();
			parent.setParentTask(task.getTaskName());
			taskServices.createParentTask(parent);
					
		}else {
			 taskServices.createTask(task);
		}
		
	}

	
	@GetMapping
	public List<Task> getTasks(@RequestParam(value="projectId") int projectId){
		return taskServices.getTasks(projectId);
	}
}
