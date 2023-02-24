package com.learning.spring.miniproject.transaction;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name="transactions")
public class TransactionTracker {

	@Id
	@GeneratedValue
	@Column(name="transaction_id")
	@JsonProperty("transaction_id")
	private int transactionId;
	
	@Column(name="order_type")
	@JsonProperty("order_type")
	public String orderType;
	
	@Column(name="date_time")
	@JsonProperty("date_time")
	private String dateTime;
	
	@Column
	@JsonProperty("total_quantity")
	private Double totalQuantity;
	
	@Column
	@JsonProperty("total_price")
	private Double totalPrice;
	
	public TransactionTracker() {
		
	}

	public TransactionTracker(int transactionId, String orderType, String dateTime,Double totalQuantity, Double totalPrice) {
		super();
		this.transactionId = transactionId;
		this.orderType = orderType;
		this.dateTime = dateTime;
		this.totalPrice = totalPrice;
	}
	

	public TransactionTracker(String orderType, String dateTime,Double totalQuantity, Double totalPrice) {
		super();
		this.orderType = orderType;
		this.dateTime = dateTime;
		this.totalQuantity = totalQuantity;
		this.totalPrice = totalPrice;
	}


	public int getTransaction_id() {
		return transactionId;
	}

	public void setTransaction_id(int transaction_id) {
		this.transactionId = transaction_id;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public Double getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Double totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "TransactionTracker [transaction_id=" + transactionId + ", orderType=" + orderType + ", dateTime="
				+ dateTime + "]";
	}
	
	
	
}
