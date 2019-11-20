package com.fse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.dao.DbOperations;

import com.fse.pojo.Users;

@Service
public class UserService {

	@Autowired
	private static DbOperations dao = new DbOperations();
	
	public Users createUsers(Users users) {
		
		return dao.createUsers(users);
	}
	public List<Users> viewUsers(){
		return dao.viewUsers();
	}
	
	public void updateUsers(int employeeId, Users users) {
		dao.updateUsers(employeeId, users);
	}
	
	public void deleteUsers(int employeeId) {
		dao.deleteUser(employeeId);
	}
}
