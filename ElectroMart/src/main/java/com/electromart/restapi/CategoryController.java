package com.electromart.restapi;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electromart.business.ICategoryService;
import com.electromart.entities.Category;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	private final ICategoryService categoryService;

	public CategoryController(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	public ResponseEntity<List<Category>> getAll() {
		List<Category> categories = categoryService.getAll();
		return ResponseEntity.ok(categories);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> getById(@PathVariable Long id) {
		Category category = categoryService.getById(id);
		if (category == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(category);
	}

	@PostMapping("/add")
	public ResponseEntity<Category> add(@RequestBody Category category) {
	    categoryService.add(category);
	    return ResponseEntity.ok(category);
	}

	@PutMapping("/update/{id}")
	public void update(@PathVariable Long id, @RequestBody Category category) {
		Category existing = categoryService.getById(id);
		if (existing == null) {
			return;
		}
		category.setId(id);
		categoryService.update(category);
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		var existing = categoryService.getById(id);
		if (existing == null) {
			return ResponseEntity.notFound().build();
		}
		categoryService.delete(existing);
		return ResponseEntity.ok().build();
	}
}
