package com.healthcare.controller.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String firstName;
	
	private String LastName;
	
	@Column(unique=true)	
	private String email;
	
	private String password;
}
