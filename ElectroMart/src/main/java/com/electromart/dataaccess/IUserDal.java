package com.electromart.dataaccess;

import java.util.Optional;

import com.electromart.entities.User;

public interface IUserDal {

	User save(User user);

	Optional<User> findById(Long id);

	Optional<User> findByUsername(String username);

	void delete(User user);
}
