package com.electromart.dataaccess;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.electromart.entities.Product;

import jakarta.persistence.EntityManager;

@Repository
public class HibernateProductDal implements IProductDal {

	private EntityManager entityManager;

	@Autowired
	public HibernateProductDal(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public List<Product> getAll() {
		return entityManager.unwrap(Session.class) //
				.createQuery("from product", Product.class) //
				.getResultList();
	}

	@Override
	@Transactional
	public void add(Product product) {
		final var session = this.entityManager.unwrap(Session.class);//
		if (product.getId() == null) {
			session.persist(product);
		} else {
			session.merge(product);
		}
	}

	@Override
	@Transactional
	public void update(Product product) {
		this.entityManager.unwrap(Session.class).merge(product);//
	}

	@Override
	@Transactional
	public void remove(Product product) {
		var session = this.entityManager.unwrap(Session.class);
		final var id = product.getId();
		if (id == null) {
			return;
		}
		session.remove(session.get(Product.class, id));
	}

	@Override
	public Product getById(int id) {
		return this.entityManager.unwrap(Session.class).get(Product.class, id);
	}

}
