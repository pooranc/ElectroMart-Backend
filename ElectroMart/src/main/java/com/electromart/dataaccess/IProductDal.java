package com.electromart.dataaccess;

import java.util.List;

import com.electromart.entities.Product;

public interface IProductDal {

	public List<Product> getAll();

	public void add(Product product);

	public void update(Product product);

	public void remove(Product product);

	public Product getById(int id);
}
