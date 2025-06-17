package com.electromart.restapi;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electromart.business.IProductService;
import com.electromart.entities.Product;

@RestController
@RequestMapping("/api")
public class ProductsController {

	private IProductService productService;

	public ProductsController(IProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/products")
	public List<Product> get() {
		return this.productService.getAll();
	}

	@PostMapping("/add")
	public void add(@RequestBody Product product) {
		this.productService.add(product);
	}

	@PostMapping("/update")
	public void update(@RequestBody Product product) {
		this.productService.update(product);
	}

	@PostMapping("/remove")
	public void remove(@RequestBody Product product) {
		this.productService.remove(product);
	}

	@GetMapping("/products/{id}")
	public Product getById(@PathVariable int id) {
		return this.productService.getById(id);
	}

}
