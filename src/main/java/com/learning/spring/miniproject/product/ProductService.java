package com.learning.spring.miniproject.product;

import java.time.LocalDateTime;


import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.learning.spring.miniproject.handler.ItemNotFoundException;
import com.learning.spring.miniproject.handler.OutOfStockException;
import com.learning.spring.miniproject.transaction.Order;
import com.learning.spring.miniproject.transaction.TransactionRepository;
import com.learning.spring.miniproject.transaction.TransactionTracker;
@Component
public class ProductService {
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private TransactionRepository transactionRepo;
	
	/*
	 * type->type of order that is initiated i.e, 'purchase order' or 'sales order'
	 * dateAndTime -> to store at which time and date the order is invoked
	 */
	
	private String type;
	private String dateAndTime;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
	
	//This method retrieve all the products from the inventory
	
	public List<Product> retreiveAllProducts() throws ItemNotFoundException{
		List<Product> allProducts = productRepo.findAll();
		if(allProducts.isEmpty()) {
			throw new ItemNotFoundException("Inventory is Empty kindly add a product");
		}else {
		return allProducts;
		}
	}

	//This Method retrieve the details of the product by its name
	
	public Optional<Product> retreiveProductById(int id) throws ItemNotFoundException{
		Optional<Product> searchedProduct = productRepo.findById(id);
		if(searchedProduct == null) {
			throw new ItemNotFoundException("Product by such name doesn't exist in the inventory");
		}else {
			return searchedProduct;
		}
	}
	
	//This Method adds a new product to the database which is associated with the post request for the purchase order
	
	public String addNewProduct(List<Product> products) throws Exception{
		Double totalPrice=0.0;
		Double totalQuantity = 0.0;
		type = "PURCHASE-ORDER";
		dateAndTime = dtf.format(LocalDateTime.now());
		
		//calculating the total price and total quantity of the purchase.
		
		for(Product product: products) {
			totalPrice+=product.getPrice();
			totalQuantity+=product.getQuantity();
		}
		
		TransactionTracker transaction = new TransactionTracker(type,dateAndTime,totalQuantity,totalPrice);
		try {
		productRepo.saveAll(products);
		transactionRepo.save(transaction);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return products.toString();
		
	}
	
	// This method updates the existing product in the database
	
	public String updateExistingProduct(int id, Product productDetails) throws Exception {
	    Product product = productRepo.findById(id)
	    .orElseThrow(() -> new Exception("Product not found for this id :: " + id));

	    product.setId(productDetails.getId());
	    product.setName(productDetails.getName());
	    product.setQuantity(productDetails.getQuantity());
	    product.setPrice(productDetails.getPrice());
	    product.setCategory(productDetails.getCategory());
	    final Product updatedProduct = productRepo.save(product);
	    return updatedProduct.toString();
	}
	
	//This method deletes the product using its id
	
	public void deleteItemById(int id){
		productRepo.deleteById(id);
	}
	
	
	//This method handles the sale of multiple items from the inventory
	
	public void putNewOrder(List<Order> orders)throws Exception{
		Double totalPrice=0.0;
		Double totalQuantity = 0.0;
		List<Product> allProducts = productRepo.findAll();
		if(allProducts.isEmpty()) {
			throw new ItemNotFoundException("The inventory is empty. Purchase Some Item before selling");
		}
		
		for(Product product: allProducts) {
			for(Order order: orders) {
				if(product.getName().equals(order.getProductName()) && product.getId().equals(order.getId())) {
				// adding the total price of the sales order according to the price of it saved in the inventory
				totalPrice+=order.getQuantity()*product.getPrice();
				updateProductQuantityAndPrice(product.getId(), order.getQuantity(), product);
			}
			
			}
		}
		for(Order order:orders) {
			totalQuantity+=order.getQuantity();
		}
		type = "SALE-ORDER";
		dateAndTime = dtf.format(LocalDateTime.now());
		
		TransactionTracker transaction = new TransactionTracker(type,dateAndTime,totalQuantity,totalPrice);
		transactionRepo.save(transaction);
		
		}


	
/*
 * While posting a sales order request 
 * quantity of the the order in the inventory should be reduced from stock
 *  so to to do that I am using this function.
 */
	private void updateProductQuantityAndPrice(int id, Double quantity, Product product) throws Exception{
		if(quantity <= product.getQuantity()) {
				product.setQuantity(product.getQuantity()- quantity);
				productRepo.save(product);
		}
		else {
			throw new OutOfStockException("Stock for such quantity is not in the inventory");
		}
	}
	
	
	
}
