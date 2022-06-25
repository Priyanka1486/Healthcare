package com.healthcare.controller.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.healthcare.controller.entities.Product;

public interface ProductRepo extends JpaRepository<Product,Integer>{

	@Query("select product from Product product")
	public List<Product>getAllProd();
	
	@Query("select product from Product product where product.category.id=?1")
	public List<Product>findByCatId(int id);

	//@Query("select product from Product product limit 3")
	//public List<Product>findViewedProduct();
}
