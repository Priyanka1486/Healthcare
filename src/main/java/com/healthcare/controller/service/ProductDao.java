package com.healthcare.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.controller.entities.Product;
import com.healthcare.controller.repo.ProductRepo;

@Service
public class ProductDao {

	@Autowired
	ProductRepo repo;

	public Product getById(int id) {
		return repo.findById(id).orElse(null);
	}
	
	public Product insert(Product u) {
		return repo.save(u);
	}
	
	public List<Product> getAll(){
	//	return repo.findAll();
		return repo.getAllProd();
	}
	public List<Product>findProductsBycat(int id){
		return repo.findByCatId(id);
	}

	public void deleteById(int id) {
		repo.deleteById(id);
	//	return "Deleted successfully";
	}
	
	public Product update(Product p) {
		Product prod = repo.getById(p.getId());
		prod.setAvailableQuantity(p.getAvailableQuantity());
		prod.setPrice(p.getPrice());
		prod.setBrand(p.getBrand());
		prod.setDescp(p.getDescp());
		prod.setExpiry(p.getExpiry());
		prod.setProductName(p.getProductName());
		prod.setImage(p.getImage());
		return repo.save(prod);
	}
	
	public void updateQuantity(Product p,int qnt) {
		int avl_qnt = p.getAvailableQuantity()-qnt;
		if(avl_qnt <= 0)
			avl_qnt = 0;
		p.setAvailableQuantity(avl_qnt);
		repo.save(p);
	}
	
	public List<Product> findViewedProduct(){
	     return repo.getAllProd();
	}
}
