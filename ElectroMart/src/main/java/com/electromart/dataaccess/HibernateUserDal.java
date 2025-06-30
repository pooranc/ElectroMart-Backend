package com.electromart.dataaccess;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.electromart.entities.User;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class HibernateUserDal implements IUserDal {

	private EntityManager entityManager;

	public HibernateUserDal(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Optional<User> findByUsername(String username) {
		final var query = this.entityManager.createQuery(
				"SELECT u FROM User u WHERE u.username = :username", User.class);
	        query.setParameter("username", username);
	        User user = null;
	        try {
	            user = query.getSingleResult();
	        } catch (Exception e) {
	        }
	        return Optional.ofNullable(user);
	}

	@Override
	public User save(User user) {
		if (user.getId() == null) {
			this.entityManager.persist(user);
			return user;
		}
		return entityManager.merge(user);
	}

	@Override
	public Optional<User> findById(Long id) {
		return Optional.ofNullable(this.entityManager.find(User.class, id));
	}

	@Override
	public void delete(User user) {
		if (!this.entityManager.contains(user)) {
			user = this.entityManager.merge(user);
		}
		this.entityManager.remove(user);
	}
}
