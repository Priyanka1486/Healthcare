package com.healthcare.controller.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.healthcare.controller.entities.User;

public interface UserRepo extends JpaRepository<User,Integer>{

	@Query("select user from User user where user.email=?1")
	public User findByEmail(String email);

	@Query("select user from User user where user.firstName=?1")
	public User findByName(String name);

//	@Query("select user from User user where user.order=?1")
//	public User findByOrderId(int id);

}
