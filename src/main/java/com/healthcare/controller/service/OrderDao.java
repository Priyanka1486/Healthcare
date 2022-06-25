package com.healthcare.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.controller.entities.Orders;
import com.healthcare.controller.entities.Product;
import com.healthcare.controller.entities.User;
import com.healthcare.controller.repo.OrderRepo;

@Service
public class OrderDao {

	@Autowired
	OrderRepo repo;

	public Orders getById(int id) {
		return repo.findById(id).orElse(null);
	}
	
	public Orders insert(Orders ord) {
		return repo.save(ord);
	}
	
	public List<Orders> getAll(){
		return repo.findAll();
	}
	
	public Orders updateOrder(Orders ord) {
		Orders order = repo.getById(ord.getId());
		order.setTotalAmount(ord.getTotalAmount());
		return repo.save(order);
	}
	public String deleteById(int id) {
		repo.deleteById(id);
		return "Deleted successfully";
	}
}
