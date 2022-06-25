package com.healthcare.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.controller.entities.CartItems;
import com.healthcare.controller.repo.CartItemRepo;

@Service
public class CartItemsDao {

	@Autowired
	CartItemRepo repo;
	
	public CartItems insert(CartItems items) {
		return repo.save(items);
	}
	
	public List<CartItems> getAll() {
		return repo.findAll();
	}
	
	public List<CartItems> insertAll(List<CartItems> cartitems){
		return repo.saveAll(cartitems);
	}

	public String deleteById(int id) {
		repo.deleteById(id);
		return "deleted successfully";
	}
}
