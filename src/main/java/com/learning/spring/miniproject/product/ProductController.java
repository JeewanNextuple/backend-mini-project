package com.learning.spring.miniproject.product;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.spring.miniproject.handler.ItemNotFoundException;
import com.learning.spring.miniproject.transaction.Order;


@RestController
public class ProductController {
	
	@Autowired
	private ProductService service;


//GET REQUEST to retrieve all the products in the inventory
	
	@GetMapping("/products/get-all")
	public List<Product> getAllProducts() throws ItemNotFoundException{
		return service.retreiveAllProducts();
	}
	
//GET REQUEST to retrieve the details for the particular item in the inventory.
	
	@GetMapping("/products/{id}")
		public Optional<Product> getProductByName(@PathVariable int id) throws Exception{
		  return service.retreiveProductById(id);
		}

	
//POST REQUEST to add new product to the inventory.
	
	@PostMapping("/products/purchase-order")
	public String addProduct(@RequestBody List<Product> products) throws Exception{
		
		return service.addNewProduct(products);
	}
	
//PUT REQUEST to update the item in the inventory
	
	@PutMapping("/products/purchase-order/{id}")
	public String updateProduct(@PathVariable(value = "id") int id, @RequestBody Product productDetails) throws Exception {
	  return service.updateExistingProduct(id, productDetails);
	}
	
//DELETE REQUEST to delete particular item by id from the inventory
	
	@DeleteMapping("/products/{id}")
	public HttpStatus deleteItem(@PathVariable int id) {
		service.deleteItemById(id);
		return HttpStatus.OK;
	}

	

	
//POST REQUEST to create a Sales order
	
	@PutMapping("/products/sales-order")
	public HttpStatus salesOrderCreation(@RequestBody List<Order> orders)throws Exception{
		service.putNewOrder(orders);
		return HttpStatus.OK;


	}
}
