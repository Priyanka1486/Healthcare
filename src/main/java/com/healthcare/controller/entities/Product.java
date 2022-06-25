package com.healthcare.controller.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private double price;
	
	private String productName;
	
	private String image;
	
	private String expiry;
	
	private String manufactuing;
	
	private String brand;
	
	private String descp;
	
	private int availableQuantity;
	
//	@JsonBackReference
	@ManyToOne
	private Category category;

//	@OneToMany(mappedBy="product",fetch=FetchType.LAZY)
//	private List<CartItems> cart;
}
