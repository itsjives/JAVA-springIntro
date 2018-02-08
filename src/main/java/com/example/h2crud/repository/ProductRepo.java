package com.example.h2crud.repository;

import com.example.h2crud.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, String>{

    Product findByType(String type);
    List<Product> findByDescriptionAndCategory(String description, String category);
    List<Product> findByCategoryAndNameIn(String category, List<String> name);

}
