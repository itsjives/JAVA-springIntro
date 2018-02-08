package com.example.h2crud;

import com.example.h2crud.model.Product;
import com.example.h2crud.repository.ProductRepo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@SpringBootApplication
public class H2crudApplication implements CommandLineRunner {

	private ProductRepo productRepo;
	private org.slf4j.Logger LOG = LoggerFactory.getLogger(H2crudApplication.class);

	@Autowired
	public void productRepo(ProductRepo productRepo) {
		this.productRepo = productRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(H2crudApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		Product product1 = new Product();
		product1.setName("Tester Product");
		product1.setDescription("Testing product persist");
		product1.setCategory("TEST");
		product1.setType("GENERAL");
		product1.setPrice(0.0);

		productRepo.save(product1);

		Product product2 = new Product();
		product2.setName("Second Tester");
		product2.setDescription("Keep on Testin");
		product2.setCategory("TEST");
		product2.setType("CUSTOM");
		product2.setPrice(900.0);

		productRepo.save(product2);

		Product product3 = new Product();
		product3.setName("Tester Product");
		product3.setDescription("Keep on Testin");
		product3.setCategory("TEST");
		product3.setType("SPECIFIC");
		product3.setPrice(78.0);

		productRepo.save(product3);

//		List<Product> products = productRepo.findAll();
//
//		for(Product product : products){
//			LOG.info("Products found:" + product.toString());
//		}

		Product resultProduct = productRepo.findByType("CUSTOM");

		LOG.info("GENERAL TYPE OF PRODUCT FOUND " + resultProduct.toString());

		List<Product> results = productRepo.findByDescriptionAndCategory("Testing product persist", "TEST");
		for (Product product : results){
			LOG.info("Matching results are: " + product.toString());
		}

		List<String> names = new ArrayList<>();
		names.add("Tester Product");
//		names.add("Second Tester");

		List<Product> resultProducts = productRepo.findByCategoryAndNameIn("TEST", names);
		for (Product product : resultProducts){
			LOG.info("MATCHING results findByCategoryANDNAME: " + product.toString() );
		}
	}
}
