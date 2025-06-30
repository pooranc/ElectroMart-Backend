package com.electromart.business;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.electromart.dataaccess.IUserDal;
import com.electromart.entities.User;

@Service
public class UserManager implements IUserService {

	public static final String USER = "ROLE_USER";
	public static final String ADMIN = "ROLE_ADMIN";

	private final PasswordEncoder passwordEncoder;

	private IUserDal userDal;

	public UserManager(IUserDal userDal, PasswordEncoder passwordEncoder) {
		this.userDal = userDal;
		this.passwordEncoder = passwordEncoder;
	}

	public User registerUser(User user) {
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		user.setRole(USER);
		return this.userDal.save(user);
	}

	public Optional<User> findByUsername(String username) {
		return this.userDal.findByUsername(username);
	}

}
