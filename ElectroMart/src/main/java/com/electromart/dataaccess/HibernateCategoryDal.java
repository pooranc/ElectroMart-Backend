package com.electromart.dataaccess;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.electromart.entities.Category;

import jakarta.persistence.EntityManager;

@Repository
public class HibernateCategoryDal implements ICategoryDal {

	private EntityManager entityManager;

	public HibernateCategoryDal(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public List<Category> getAll() {
		return this.entityManager.unwrap(Session.class).createQuery("from Category", Category.class).getResultList();
	}

	@Override
	@Transactional
	public void add(Category category) {
		var session = this.entityManager.unwrap(Session.class);
		if (category.getId() == null) {
			session.persist(category);
		} else {
			session.merge(category);
		}
	}

	@Override
	@Transactional
	public void update(Category category) {
		this.entityManager.unwrap(Session.class).merge(category);
	}

	@Override
	@Transactional
	public void remove(Category category) {
		var session = this.entityManager.unwrap(Session.class);
		var id = category.getId();
		if (id == null) {
			return;
		}
		session.remove(session.get(Category.class, id));
	}

	@Override
	public Category getById(Long id) {
		return this.entityManager.unwrap(Session.class).get(Category.class, id);
	}
}
