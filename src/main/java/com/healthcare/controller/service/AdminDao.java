package com.healthcare.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.controller.entities.Admin;
import com.healthcare.controller.repo.AdminRepo;

@Service
public class AdminDao {
	@Autowired
	AdminRepo repo;
	
	public Admin findByEmail(String email) {
		return repo.findByEmail(email);
	}
	
	public Admin changePassword(String email,String password) {
		Admin existing =  repo.findByEmail(email);
		existing.setPassword(password);
		return repo.save(existing);
	}

	public Admin checkLogin(String email,String password) {
		Admin existing =  repo.findByEmail(email);
		if(existing.getPassword().equals(password))
			return existing;
		else
			return null;
	}
}
