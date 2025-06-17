package com.electromart.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electromart.dataaccess.IProductDal;
import com.electromart.entities.Product;

@Service
public class ProductManager implements IProductService {

	private IProductDal productDal;

	@Autowired
	public ProductManager(IProductDal productDal) {
		this.productDal = productDal;
	}

	@Override
	public List<Product> getAll() {
		return this.productDal.getAll();
	}

	@Override
	public void add(Product product) {
		this.productDal.add(product);
	}

	@Override
	public void update(Product product) {
		this.productDal.update(product);
	}

	@Override
	public void remove(Product product) {
		this.productDal.remove(product);
	}

	@Override
	public Product getById(int id) {
		return this.productDal.getById(id);
	}

}
