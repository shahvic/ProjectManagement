package com.fse.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.fse.pojo.Parent;
import com.fse.pojo.Project;
import com.fse.pojo.Task;
import com.fse.pojo.Users;

public class DbOperations {

	final static Logger logger = Logger.getLogger(DbOperations.class);

	public static SessionFactory getSessionFactory() {
		// Creating Configuration Instance & Passing Hibernate Configuration File
		Configuration configObj = new Configuration();
		configObj.configure("hibernate.cfg.xml");

		// Since Hibernate Version 4.x, Service Registry Is Being Used
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder()
				.applySettings(configObj.getProperties()).build();

		// Creating Hibernate Session Factory Instance
		SessionFactory factoryObj = configObj.buildSessionFactory(serviceRegistryObj);
		return factoryObj;
	}
	
	// Project Methods *************************************************************************************

		public Project createProject(Project project) {
			Session sessionObj = getSessionFactory().openSession();

			// Creating Transaction Object

			Transaction transObj = sessionObj.beginTransaction();
			Integer id = (Integer) sessionObj.save(project);

			// Transaction Is Committed To Database
			transObj.commit();

			// Updating User
			transObj = sessionObj.beginTransaction();
			String queryString = "Update Users set ";
			int count = 0;

			if (project.getEmployeeId() > 0) {
				if (count > 0) {
					queryString += " , ";
				}
				queryString += "projectId = :projectId";
				count++;
			}

			queryString += " where employeeId= :employeeId";
			logger.info(queryString);
			logger.info("Query Parameter: "+project.getEmployeeId());
			Query query = sessionObj.createQuery(queryString);
			logger.info("Project ID - " + project.getProjectId());
			logger.info("Employee ID - " + project.getEmployeeId());

			if (project.getEmployeeId() > 0) {
				query.setParameter("projectId", id);
			}

			query.setParameter("employeeId", project.getEmployeeId());

			int result = query.executeUpdate();
			transObj.commit();

			// Closing The Session Object
			sessionObj.close();

			logger.info("Successfully Created " + project.toString());
			return project;
		}

		

		public List<Project> getProjects() {
			List<Project> projectList = new ArrayList<Project>();
			List<Object[]> resultSet = new ArrayList<Object[]>();
			Session sessionObj = getSessionFactory().openSession();

			Query query = sessionObj.createQuery(
					"select distinct p.projectId,p.projectName, p.startDate, p.endDate, p.priority, u.employeeId FROM com.fse.pojo.Project as p , com.fse.pojo.Users as u where p.projectId = u.projectId");

			resultSet = query.list();
			Project p = null;

			for (Object[] o : resultSet) {
				p = new Project();
				p.setProjectId((Integer) o[0]);
				p.setProjectName((String) o[1]);
				p.setStartDate((Date) o[2]);
				p.setEndDate((Date) o[3]);
				p.setPriority((Integer) o[4]);
				p.setEmployeeId((Integer) o[5]);
				projectList.add(p);

			}

			for (Project pr : projectList) {
				logger.info(pr.toString());
			}

			// Closing The Session Object
			sessionObj.close();
			return projectList;
		}

		public void updateProject(int projectId, Project p) {
			Session sessionObj = getSessionFactory().openSession();
			Transaction transObj = sessionObj.beginTransaction();
			String queryString = "Update Project set ";
			int count = 0;
			if (p.getProjectName() != null && !p.getProjectName().equalsIgnoreCase("")) {
				queryString += "projectName = :projectName";
				count++;
			}
			if (p.getStartDate() != null) {
				if (count > 0) {
					queryString += " , ";
				}
				queryString += "startDate = :startDate";
				count++;
			}
			if (p.getEndDate() != null) {
				if (count > 0) {
					queryString += " , ";
				}
				queryString += "endDate = :endDate";
				count++;
			}
			if (p.getPriority() > 0) {
				if (count > 0) {
					queryString += " , ";
				}
				queryString += "priority = :priority";
				count++;
			}

			queryString += " where projectId= :projectId";
			logger.info(queryString);
			
			Query query = sessionObj.createQuery(queryString);
			if (p.getProjectName() != null && !p.getProjectName().equalsIgnoreCase("")) {
				query.setParameter("projectName", p.getProjectName());
			}
			if (p.getStartDate() != null) {
				query.setParameter("startDate", p.getStartDate());
			}
			if (p.getEndDate() != null) {
				query.setParameter("endDate", p.getEndDate());
			}
			if (p.getPriority() > 0) {
				query.setParameter("priority", p.getPriority());
			}
			query.setParameter("projectId", projectId);

			int result = query.executeUpdate();
			transObj.commit();
			// Closing The Session Object
			sessionObj.close();
			logger.info("Project with id " + projectId + " updated");
			logger.info("Result " + result);
		}

	// Users Methods**********************************************************************************
	public Users createUsers(Users users) {
		Session sessionObj = getSessionFactory().openSession();

		// Creating Transaction Object

		Transaction transObj = sessionObj.beginTransaction();
		Integer id = (Integer) sessionObj.save(users);

		// Transaction Is Committed To Database
		transObj.commit();

		// Closing The Session Object
		sessionObj.close();

		logger.info("Successfully Created " + users.toString());
		return users;
	}

	public List<Users> viewUsers() {
		List<Users> userList = new ArrayList<Users>();
		Session sessionObj = getSessionFactory().openSession();
		Query query = sessionObj.createQuery("FROM Users");

		userList = query.list();

		// Closing The Session Object
		sessionObj.close();
		logger.info("Users fetched successfully ");
		return userList;
	}

	public List<Parent> getParentTasks() {
		List<Parent> parentList = new ArrayList<Parent>();
		Session sessionObj = getSessionFactory().openSession();
		Query query = sessionObj.createQuery("FROM Parent");

		parentList = query.list();

		// Closing The Session Object
		sessionObj.close();
		logger.info("Parent tasks fetched successfully ");
		return parentList;
	}

	public void deleteUser(int employeeId) {
		Session sessionObj = getSessionFactory().openSession();
		Transaction transObj = sessionObj.beginTransaction();

		Query query = sessionObj.createQuery("delete FROM Users where employeeId= :employeeId");
		query.setParameter("employeeId", employeeId);

		int result = query.executeUpdate();
		transObj.commit();
		// Closing The Session Object
		sessionObj.close();
		logger.info("User with employee id " + employeeId + " deleted");
		logger.info("Result " + result);
	}

	public void updateUsers(int employeeId, Users users) {
		Session sessionObj = getSessionFactory().openSession();
		Transaction transObj = sessionObj.beginTransaction();
		String queryString = "Update Users set ";
		int count = 0;
		if (users.getFirstName() != null && !users.getFirstName().equalsIgnoreCase("")) {
			queryString += "firstName = :firstName";
			count++;
		}
		if (users.getLastName() != null && !users.getLastName().equalsIgnoreCase("")) {
			if (count > 0) {
				queryString += " , ";
			}
			queryString += "lastName = :lastName";
			count++;
		}
		if (users.getLastName() != null && !users.getLastName().equalsIgnoreCase("")) {
			if (count > 0) {
				queryString += " , ";
			}
			queryString += "lastName = :lastName";
			count++;
		}
		if (users.getProjectId() > 0) {
			if (count > 0) {
				queryString += " , ";
			}
			queryString += "projectId = :projectId";
			count++;
		}
		if (users.getTaskId() > 0) {
			if (count > 0) {
				queryString += " , ";
			}
			queryString += "taskId = :taskId";
			count++;
		}

		queryString += " where employeeId= :employeeId";
		logger.info(queryString);
		Query query = sessionObj.createQuery(queryString);
		if (users.getFirstName() != null && !users.getFirstName().equalsIgnoreCase("")) {
			query.setParameter("firstName", users.getFirstName());
		}
		if (users.getLastName() != null && !users.getLastName().equalsIgnoreCase("")) {
			query.setParameter("lastName", users.getLastName());
		}
		if (users.getProjectId() > 0) {
			query.setParameter("lastName", users.getProjectId());
		}
		if (users.getTaskId() > 0) {
			query.setParameter("lastName", users.getTaskId());
		}
		query.setParameter("employeeId", employeeId);

		int result = query.executeUpdate();
		transObj.commit();
		// Closing The Session Object
		sessionObj.close();
		logger.info("User with id " + employeeId + " updated");
		logger.info("Result " + result);
	}

	

	// Task Methods *****************************************************************************************

	public void createParent(Parent parent) {
		Session sessionObj = getSessionFactory().openSession();

		// Creating Transaction Object

		Transaction transObj = sessionObj.beginTransaction();
		sessionObj.save(parent);

		// Transaction Is Committed To Database
		transObj.commit();

		// Closing The Session Object
		sessionObj.close();
		logger.info("Successfully Created " + parent.toString());

	}

	public Task createTask(Task task) {
		Session sessionObj = getSessionFactory().openSession();

		// Creating Transaction Object

		Transaction transObj = sessionObj.beginTransaction();
		Integer id = (Integer) sessionObj.save(task);

		// Transaction Is Committed To Database
		transObj.commit();

		// Assigning task to user after creating new task
		transObj = sessionObj.beginTransaction();

		String queryString = "Update Users set ";
		int count = 0;

		if (task.getEmployeeId() > 0) {
			if (count > 0) {
				queryString += " , ";
			}
			queryString += "taskId = :taskId";
			queryString += " , ";
			queryString += "projectId = :projectId";
			count++;
		}

		queryString += " where employeeId= :employeeId";
		logger.info(queryString);
		Query query = sessionObj.createQuery(queryString);

		if (task.getEmployeeId() > 0) {
			query.setParameter("taskId", id);
			query.setParameter("projectId", task.getProjectId());
		}

		query.setParameter("employeeId", task.getEmployeeId());

		int result = query.executeUpdate();
		transObj.commit();
		// Closing The Session Object
		sessionObj.close();

		logger.info("Successfully Created " + task.toString());
		return task;
	}

	public Parent createParentTask(Parent parent) {
		Session sessionObj = getSessionFactory().openSession();

		// Creating Transaction Object

		Transaction transObj = sessionObj.beginTransaction();
		Integer id = (Integer) sessionObj.save(parent);

		// Transaction Is Committed To Database
		transObj.commit();

		// Closing The Session Object
		sessionObj.close();

		logger.info("Successfully Created " + parent.toString());
		return parent;
	}

	public List<Task> getTasks(int projectId) {

		List<Task> taskList = new ArrayList<Task>();
		List<Object[]> resultSet = new ArrayList<Object[]>();
		Session sessionObj = getSessionFactory().openSession();
		Query query = sessionObj.createQuery(
				"select distinct t, p FROM Task t, Parent p where t.parentId=p.parentId and t.projectId=:projectId");
		query.setParameter("projectId", projectId);

		resultSet = query.list();
		Task t = null;

		for (Object[] o : resultSet) {
			t = new Task();
			t = (Task) o[0];
			t.setParent((Parent) o[1]);

			taskList.add(t);

		}
		// Closing The Session Object
		sessionObj.close();
		logger.info("Tasks fetched successfully ");
		return taskList;

	}

}
