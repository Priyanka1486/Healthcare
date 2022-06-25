package com.healthcare.controller.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.controller.entities.Orders;

public interface OrderRepo extends JpaRepository<Orders,Integer> {

	
}
