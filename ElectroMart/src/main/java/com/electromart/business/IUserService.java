package com.electromart.business;

import java.util.Optional;

import com.electromart.entities.User;

public interface IUserService {

	User registerUser(User user);

	Optional<User> findByUsername(String username);

}
