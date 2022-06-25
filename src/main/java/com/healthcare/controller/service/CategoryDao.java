package com.healthcare.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.controller.entities.Category;
import com.healthcare.controller.repo.CategoryRepo;

@Service
public class CategoryDao {

	@Autowired
	CategoryRepo repo;
	
	public Category insert(Category cat) {
		return repo.save(cat);
	}
	
	public List<Category> getAll(){
		return repo.findAll(); 
	}
	
	public Category getById(int id) {
		return repo.findById(id).orElse(null);
	}
	
	public String deleteById(int id) {
		repo.deleteById(id);
		return "Deleted Successfully";
	}
	
	public String update(Category cat) {
		Category getCat = repo.findById(cat.getId()).orElse(null);
		repo.save(cat);
		return "Update Successfully";
	}
	public Category findByName(String name) {
		return repo.findByName(name);
	}
	
}
