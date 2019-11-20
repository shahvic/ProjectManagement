package com.fse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.dao.DbOperations;
import com.fse.pojo.Project;

@Service
public class ProjectService {

	@Autowired
	private static DbOperations dao = new DbOperations();
	
	public Project createProject(Project project) {
		
		return dao.createProject(project);
	}
	
	public List<Project> getProjects(){
		return dao.getProjects();
	}
	
	public void updateProject(int projectId, Project p) {
		 dao.updateProject(projectId, p);
	}
}
