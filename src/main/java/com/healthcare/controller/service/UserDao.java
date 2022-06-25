package com.healthcare.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.controller.entities.Admin;
import com.healthcare.controller.entities.User;
import com.healthcare.controller.repo.UserRepo;

@Service
public class UserDao {

	@Autowired
	UserRepo repo;

	public User getById(int id) {
		return repo.findById(id).orElse(null);
	}
	
	public User insert(User u) {
		return repo.save(u);
	}
	
	public List<User> getAll(){
		return repo.findAll();
	}
	
	public String deleteById(int id) {
		repo.deleteById(id);
		return "Deleted successfully";
	}
	
	public User Update(User u) {
		User user = repo.getById(u.getId());
		user.setFirstName(u.getFirstName());
		user.setLastName(u.getLastName());
		user.setEmail(u.getEmail());		
		return user;
	}
	
	public User findByEmail(String email) {
		return repo.findByEmail(email);
	}
	
	public User findByName(String name) {
		return repo.findByName(name);
	}

	public String changePassword(String email,String password) {
		User u  = repo.findByEmail(email);
		u.setPassword(password);
		return "Password changed successfully";
	}
	
	public User checkLogin(String email,String password){
		User existing =  repo.findByEmail(email);
		if(existing.getPassword().equals(password))
			return existing;
		else
			return null;		
	}
}
