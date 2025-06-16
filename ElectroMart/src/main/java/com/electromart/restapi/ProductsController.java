package com.electromart.restapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {

	@GetMapping("/")
	public String get() {
		return "Laptop";
	}

	@GetMapping("/product")
	public String get2() {
		return "Laptop 3";
	}

}
