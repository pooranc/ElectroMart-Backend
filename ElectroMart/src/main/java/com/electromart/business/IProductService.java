package com.electromart.business;

import java.util.List;

import com.electromart.entities.Product;

public interface IProductService {
	
	public List<Product> getAll();

	public void add(Product product);

	public void update(Product product);

	public void remove(Product product);

	public Product getById(int id);
	
}
