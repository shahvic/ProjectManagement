package com.fse.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="parent")
public class Parent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="parent_id", updatable=false, nullable=false)
	private int parentId;
	@Column (name="parent_task")
	private String parentTask;
	@OneToMany(mappedBy="parentId", cascade = CascadeType.ALL)
	@JsonIgnore
	Set<Task> tasks = new HashSet<Task>();
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getParentTask() {
		return parentTask;
	}
	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}
	@Override
	public String toString() {
		return "Parent [parentId=" + parentId + ", parentTask=" + parentTask + "]";
	}
	public Set<Task> getTasks() {
		return tasks;
	}
	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
	
	
	
}
