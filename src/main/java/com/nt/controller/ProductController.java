package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.Product;
import com.nt.servcie.ProductServcie;

@RestController

public class ProductController 
{
@Autowired
private ProductServcie productService;
@PostMapping("/create")
public  ResponseEntity<String> createProducts(@RequestBody Product product) 
{
	String string = productService.createProducts(product);
	return new ResponseEntity<String>(string, HttpStatus.CREATED);

}
@GetMapping("/get")
public ResponseEntity<List<Product>> getProducts()
{
	List<Product> list = productService.getProducts();
	return new ResponseEntity<>(list, HttpStatus.OK);		
}

@PutMapping("/update/{id}")
public ResponseEntity<Product> updateProdct(@PathVariable Integer id, @RequestBody Product product ){
	Product updateProduct = productService.updateProduct(id, product);
	return new ResponseEntity<Product>(updateProduct, HttpStatus.OK);
	
}

@DeleteMapping("/delete/{id}")
public ResponseEntity<String> deleteProduct(@PathVariable Integer id) 
{
	String deleteProduct = productService.deleteProduct(id);
	return new ResponseEntity<String>(deleteProduct, HttpStatus.OK);
}
}
