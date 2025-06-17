package com.electromart.business;

import java.util.List;

import org.springframework.stereotype.Service;

import com.electromart.dataaccess.ICategoryDal;
import com.electromart.entities.Category;

@Service
public class CategoryManager implements ICategoryService {

	private final ICategoryDal categoryDal;

	public CategoryManager(ICategoryDal categoryDal) {
		this.categoryDal = categoryDal;
	}

	@Override
	public List<Category> getAll() {
		return categoryDal.getAll();
	}

	@Override
	public Category getById(Long id) {
		return categoryDal.getById(id);
	}

	@Override
	public void add(Category category) {
		categoryDal.add(category);
	}

	@Override
	public void update(Category category) {
		categoryDal.update(category);
	}

	@Override
	public void delete(Category category) {
		categoryDal.remove(category);
	}
}
