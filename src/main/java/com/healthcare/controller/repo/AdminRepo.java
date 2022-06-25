package com.healthcare.controller.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.healthcare.controller.entities.Admin;

public interface AdminRepo extends JpaRepository<Admin,Integer>{

	@Query("select admin from Admin admin where admin.email=?1")
	public Admin findByEmail(String email);
}
