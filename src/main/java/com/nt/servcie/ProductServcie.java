package com.nt.servcie;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.Product;
import com.nt.repository.ProductRepo;

@Service
public class ProductServcie {
@Autowired
private ProductRepo productRepo;
public List<Product> getProducts(){
	return productRepo.findAll();
	
}
public String createProducts(Product product) {
	 productRepo.save(product); 
	 return "product save";
}

public String deleteProduct(Integer pid) {
	if(productRepo.existsById(pid)) {
		productRepo.deleteById(pid);
		return "product delete successfully";
	}else {
		return "no product found";
	}
	
}
	     
public Product updateProduct(Integer  id,Product productDetails) {
	Product product=productRepo.findById(id).orElseThrow(()->new RuntimeException("Product not found with id"+id));
product.setId(productDetails.getId());
product.setName(productDetails.getName());
product.setPrice(productDetails.getPrice());
product.setQuantity(productDetails.getQuantity());
return productRepo.save(product);
}
}
