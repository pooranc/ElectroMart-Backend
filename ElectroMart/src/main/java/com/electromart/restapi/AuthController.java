package com.electromart.restapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electromart.business.UserManager;
import com.electromart.entities.User;
import com.electromart.restapi.dto.JwtResponse;
import com.electromart.restapi.dto.JwtUtil;
import com.electromart.restapi.dto.LoginRequest;
import com.electromart.restapi.dto.RegisterRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final UserManager userManager;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	public AuthController(UserManager userManager, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
		this.userManager = userManager;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
		if (request == null) {

		}
		var user = new User();
		user.setUsername(request.getUsername());
		user.setPassword(request.getPassword());

		var registeredUser = userManager.registerUser(user);
		return ResponseEntity.ok("User registered with id: " + registeredUser.getId());
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		var userOpt = userManager.findByUsername(request.getUsername());
		if (userOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}

		var user = userOpt.get();
		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}

		var token = jwtUtil.generateToken(user.getUsername());
		return ResponseEntity.ok(new JwtResponse(token));
	}

}
