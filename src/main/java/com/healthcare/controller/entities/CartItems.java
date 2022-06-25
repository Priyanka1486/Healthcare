package com.healthcare.controller.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
public class CartItems {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private int quantity;
		
	@OneToOne
	private Product product;
	
	@ManyToOne
	private Orders orders;
}
