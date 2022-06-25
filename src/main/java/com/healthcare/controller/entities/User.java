package com.healthcare.controller.entities;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
		
	@Embedded
	private Address address;

//	@OneToMany(mappedBy="user")
//	private List<Orders> order;
	
//	public void addOrder(Orders r) {
//		this.order.add(r);
//	}
}
