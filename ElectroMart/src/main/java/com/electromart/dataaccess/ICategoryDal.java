package com.electromart.dataaccess;

import java.util.List;

import com.electromart.entities.Category;

public interface ICategoryDal {
	
	List<Category> getAll();

	void add(Category category);

	void update(Category category);

	void remove(Category category);

	Category getById(Long id);
}