package com.healthcare.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.controller.entities.Admin;
import com.healthcare.controller.entities.CartItems;
import com.healthcare.controller.entities.Category;
import com.healthcare.controller.entities.Orders;
import com.healthcare.controller.entities.Product;
import com.healthcare.controller.entities.User;
import com.healthcare.controller.service.AdminDao;
import com.healthcare.controller.service.CartItemsDao;
import com.healthcare.controller.service.CategoryDao;
import com.healthcare.controller.service.OrderDao;
import com.healthcare.controller.service.ProductDao;
import com.healthcare.controller.service.UserDao;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class HealthController {
	@Autowired
	AdminDao admin_dao;
	
	@Autowired
	CategoryDao category_dao;
	
	@Autowired
	UserDao user_dao;

	@Autowired
	ProductDao product_dao;

	@Autowired
	CartItemsDao item_dao;
	
	@Autowired
	OrderDao order_dao;
	
	int user_id;
		
	//Category crud operations
	@GetMapping("/catlist")
	public List<Category> getAllCategory() {
		return category_dao.getAll();
	}
	@PostMapping("/insert")
	public Category insertCategory(@RequestBody Category cat) {
		return category_dao.insert(cat);
	}
	@DeleteMapping("/deleteCategory")
	public String  deleteCategory(int id) {
		return category_dao.deleteById(id);
	}
	@GetMapping("/catname")
	public Category findByIdCategory(int id) {
		return category_dao.getById(id);
	}
	
	// User crud operations	
	@GetMapping("/checkuserlogin")
	public User checkUserLogin(String email,String password) {
		User user = new User();
		user = user_dao.checkLogin(email, password);
		user_id = user.getId();
		System.out.println("user id :"+user_id);
		return user;
	}

	@PostMapping("/registeruser")
	public User insertUser(@RequestBody User user) {
		return user_dao.insert(user);
	}
	@GetMapping("/getalluser")
	public List<User> getAllUser(){
		return user_dao.getAll();
	}
	@PatchMapping("/updateuser")
	public User updateUser(@RequestBody User user) {
		return user_dao.Update(user);
	}
	@DeleteMapping("/deleteuser")
	public String deleteUser(int id) {
		return user_dao.deleteById(id);
	}
	@GetMapping("/finduserbyname")
	public User findUserByName(String name) {
		return user_dao.findByName(name);
	}
	@GetMapping("/finduserbyemail")
	public User findUserByEmail(String email) {
		return user_dao.findByEmail(email);
	}

	//Product crud operations
	@GetMapping("/getproduct")
	public Product getProduct(int id) {
		return product_dao.getById(id);
	}
	@PostMapping("/addproduct")
	public Product insertProduct(@RequestBody Product prod) {
		int cat_id = prod.getCategory().getId();
		Category cat = category_dao.getById(cat_id);
	//	cat.addProduct(prod);
		prod.setCategory(cat);
		return product_dao.insert(prod);
	}
	@GetMapping("/allproducts")
	public List<Product> getAllProduct(){
		return product_dao.getAll();
	}
	@DeleteMapping("/deleteproduct")
	public String deleteProduct(int id) {
		product_dao.deleteById(id);
		return "Deleted";
	}
	@PatchMapping("/updateproduct")
	public Product updateProduct(@RequestBody Product prod) {
		return product_dao.update(prod); 
	}
	@GetMapping("/catproducts")
	public List<Product> getProductByCategory(int id){
		return product_dao.findProductsBycat(id);
	}
	@GetMapping("/mostviewedproduct")
	public List<Product> getViewedProduct(){
		List<Product> prods = product_dao.findViewedProduct();
		List<Product> pds = new ArrayList<Product>();
		Iterator it = prods.iterator();
		int count = 0;
		while(it.hasNext() && (count<5)) {
			pds.add((Product)it.next());
			count++;
		}
		return pds;
		
	}
	//Order crud operations

	@PostMapping("/orderplaced")
	public Orders insertOrder(@RequestBody List<CartItems> cart) {		
		Orders order = new Orders();
	    Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");  
	    String strDate = formatter.format(date);  
		order.setOrderedDate(strDate);
		User user = user_dao.getById(user_id);
		order.setUser(user);
		Orders o = order_dao.insert(order);
		double amt = 0;
		List<CartItems> cartall = new ArrayList<CartItems>();
		Iterator<CartItems> c  = cart.iterator();	
		while(c.hasNext()) {
			CartItems newcart = c.next();
			newcart.setOrders(o);		
			int prod_id = newcart.getProduct().getId();
			Product prod = product_dao.getById(prod_id);
			amt = amt + prod.getPrice()*newcart.getQuantity();
			product_dao.updateQuantity(prod,newcart.getQuantity());
			newcart.setProduct(prod);
			cartall.add(newcart);
		}
		o.setTotalAmount(amt);
		order_dao.updateOrder(o);
		item_dao.insertAll(cartall);
		return o;
	}

	//CartItems crud operations	
	@PostMapping("/insertcart")
	public CartItems insertCart(@RequestBody CartItems cart) {		
		Orders order = new Orders();
	    Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");  
	    String strDate = formatter.format(date);  
		order.setOrderedDate(strDate);
		User user = user_dao.getById(user_id);
		order.setUser(user);
		Orders o = order_dao.insert(order);
		cart.setOrders(o);		
		int prod_id = cart.getProduct().getId();
		Product prod = product_dao.getById(prod_id);
		cart.setProduct(prod);		
		return item_dao.insert(cart);
	}
	
	@PostMapping("/insertallcart")
	public List<CartItems> insertAllCart(@RequestBody List<CartItems> cart) {		
		Orders order = new Orders();
	    Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");  
	    String strDate = formatter.format(date);  
		order.setOrderedDate(strDate);
		User user = user_dao.getById(user_id);
		order.setUser(user);
		Orders o = order_dao.insert(order);
		double amt = 0;
		List<CartItems> cartall = new ArrayList<CartItems>();
		Iterator<CartItems> c  = cart.iterator();	
		while(c.hasNext()) {
			CartItems newcart = c.next();
			newcart.setOrders(o);		
			int prod_id = newcart.getProduct().getId();
			Product prod = product_dao.getById(prod_id);
			amt = amt + prod.getPrice()*newcart.getQuantity();
			newcart.setProduct(prod);
			cartall.add(newcart);
		}
		o.setTotalAmount(amt);
		order_dao.updateOrder(o);
		return item_dao.insertAll(cartall);
	}
	//admin crud operations
	/*
	@GetMapping("/adminlogin/{eamil}/{password}")
	public Admin checkAdminLogin(@PathVariable("email") String email,@PathVariable("password") String password) {
		return admin_dao.checkLogin(email, password);
	}
	*/
	@GetMapping("/adminlogin")
	public Admin checkAdminLogin(String email,String password) {
		return admin_dao.checkLogin(email, password);
	}
	@PostMapping("/updatepassword")
	public Admin updatePassword(String email, String password) {	
		return admin_dao.changePassword(email, password);
	}
}
