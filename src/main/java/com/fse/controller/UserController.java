package com.fse.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.fse.pojo.Users;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/api/user"})
public class UserController {
	@Autowired
	private UserService userService;
	
	
	@PostMapping
	public Users createUsers(@RequestBody Users users) {
		System.out.println("User - "+users);
		return userService.createUsers(users);
	}
	
	@GetMapping
	public List<Users> viewUsers(){
		
		return userService.viewUsers();
	}
	

	
	@PostMapping("/updateUsers")
	public void updateUsers(@RequestParam(value="employeeId") int employeeId, @RequestBody Users users) {
		userService.updateUsers(employeeId, users);
	}
	
	@DeleteMapping
	public void deleteUsers(@RequestParam(value="employeeId") int employeeId) {
		userService.deleteUsers(employeeId);
	}
}
