package com.fse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fse.pojo.Project;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/api/project"})
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@GetMapping
	public List<Project> getProjects(){
		return projectService.getProjects();
	}
	
	@PostMapping
	public Project createProject(@RequestBody Project project) {
		
		return projectService.createProject(project);
	}
	
	@PutMapping
	public void updateProject(@RequestParam(value="projectId") int projectId, @RequestBody Project p) {
		projectService.updateProject(projectId, p);
	}
}
