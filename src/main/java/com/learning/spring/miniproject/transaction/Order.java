package com.learning.spring.miniproject.transaction;


import jakarta.persistence.GeneratedValue;



public class Order {

	@GeneratedValue
	private int id;
	
	private String productName;
	private Double quantity;
	
	public Order(int id, String productName, Double quantity) {
		super();
		this.id = id;
		this.productName = productName;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", productName=" + productName + ", quantity=" + quantity + "]";
	}
	
	
	
	
	
}
