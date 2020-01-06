package com.example.repository.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.repository.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,String>{
	
	Product findByType(String type);
	
	List<Product> findByDescriptionAndCategory(String description, String category);
	
	List<Product> findByCategoryAndNameIn(String category, List<String> name);
}
