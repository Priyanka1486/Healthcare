package com.healthcare.controller.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.controller.entities.CartItems;

public interface CartItemRepo extends JpaRepository<CartItems,Integer> {

}
