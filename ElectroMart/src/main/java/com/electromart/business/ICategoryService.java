package com.electromart.business;

import java.util.List;

import com.electromart.entities.Category;

public interface ICategoryService {
	List<Category> getAll();

	Category getById(Long id);

	void add(Category category);

	void update(Category category);

	void delete(Category category);
}