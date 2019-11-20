package com.fse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.dao.DbOperations;
import com.fse.pojo.Parent;
import com.fse.pojo.Task;
@Service
public class TaskServices {

	@Autowired
	private static DbOperations dao = new DbOperations();
	
	public List<Parent> getParentTasks(){
		return dao.getParentTasks();
	}
	
	public Task createTask(Task task) {
		return dao.createTask(task);
	}
	
	public Parent createParentTask(Parent parent) {
		return dao.createParentTask(parent);
	}
	
	public List<Task> getTasks(int projectId){
		return dao.getTasks(projectId);
	}
}
