package com.fse.pojo;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="task_id", updatable=false, nullable=false)
	private int taskId;	
	@Transient
	private Project project;
	@Transient
	private int employeeId;
	@Transient
	private Parent parent;
	@Column(name ="parent_id", updatable=false, nullable=false)
	private int parentId;
	@Column(name ="project_id", updatable=false, nullable=false)
	private int projectId; 
	@Column (name="task")
	private String taskName;
	@Column (name="start_date")
	private Date startDate;
	@Column (name="end_date")
	private Date endDate;
	@Column (name="priority")
	private int priority;
	@Column (name="status")
	private String status;
	@Transient
	private boolean checkParent;
	

	public boolean isCheckParent() {
		return checkParent;
	}
	public void setCheckParent(boolean checkParent) {
		this.checkParent = checkParent;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Parent getParent() {
		return parent;
	}
	public void setParent(Parent parent) {
		this.parent = parent;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Task(int taskId, Project project, int employeeId, Parent parent, int parentId, int projectId,
			String taskName, Date startDate, Date endDate, int priority, String status, boolean checkParent) {
		super();
		this.taskId = taskId;
		this.project = project;
		this.employeeId = employeeId;
		this.parent = parent;
		this.parentId = parentId;
		this.projectId = projectId;
		this.taskName = taskName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority;
		this.status = status;
		this.checkParent = checkParent;
	}
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
