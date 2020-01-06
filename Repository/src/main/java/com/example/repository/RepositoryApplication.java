package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.repository.model.Product;
import com.example.repository.repository.ProductRepository;

@SpringBootApplication
public class RepositoryApplication implements CommandLineRunner {
	
	private ProductRepository productRepository;
	
	private Logger LOG = LoggerFactory.getLogger(RepositoryApplication.class);
	
	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(RepositoryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Product product1 = new Product();
		product1.setName("Tester product");
		product1.setDescription("This is a terster product");
		product1.setCategory("TEST");
		product1.setType("GENERAL");
		product1.setPrice(0.0);
		
		productRepository.save(product1);
		
		Product product2 = new Product();
		product2.setName("Another Tester product");
		product2.setDescription("This is a terster product");
		product2.setCategory("TEST");
		product2.setType("CUSTOM");
		product2.setPrice(15.0);
		
		productRepository.save(product2);
		
		Product product3 = new Product();
		product3.setName("Tester Product");
		product3.setDescription("Description");
		product3.setCategory("USUAL");
		product3.setType("SPPICIFIC");
		product3.setPrice(20.0);

		productRepository.save(product3);
		
		List<Product> products = productRepository.findAll();
		
		for(Product product : products) {
			LOG.info("Products found: " + product.toString() );
		}
		
		Product resultProduct1 = productRepository.findByType("CUSTOM");
		
		LOG.info("Product custom found: " + resultProduct1.toString() );
		
		Product resultProduct = productRepository.findByType("GENERAL");
		
		LOG.info("Product general found: " + resultProduct.toString() );
		
		List <Product> results = productRepository.findByDescriptionAndCategory("This is a terster product", "TEST");
		
		for (Product product: results) {
			LOG.info("Matching results are: " + product.toString());
		}
		
		List<String> names = new ArrayList<>();
		
		names.add("Tester product");
		
		names.add("Another Tester product");
		
		List <Product> resultProducts = productRepository.findByCategoryAndNameIn( "TEST", names);
		
		for (Product product: resultProducts) {
			LOG.info("NameIn results are: " + product.toString());
		}
		
		
		Product productToUpdate = productRepository.findByType(  "SPPICIFIC"); 
		
		if (productToUpdate != null) {
			productToUpdate.setName("Updated Product");
			productToUpdate.setDescription("Updated description");
			Product updated = productRepository.save(productToUpdate);
			LOG.info("Updated product details: " + updated.toString());
		}
		
		productRepository.delete(product1);
	}

}
