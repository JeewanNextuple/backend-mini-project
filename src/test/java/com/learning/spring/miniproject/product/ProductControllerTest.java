package com.learning.spring.miniproject.product;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductControllerTest {
	
	@Autowired
	ProductRepository productRepo;

	@Test
	public void testPostRequestToAddItemsInInventory(){
		Product product = new Product();
		product.setId(1001);
		productRepo.saveAll(List.of(product));
		
		assertNotNull(productRepo.findById(1001).get());
	}
	
	


}
