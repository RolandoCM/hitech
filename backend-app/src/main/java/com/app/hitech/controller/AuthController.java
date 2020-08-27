package com.app.hitech.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.hitech.configuration.JwtUtils;
import com.app.hitech.dto.JwtResponse;
import com.app.hitech.dto.LoginRequest;
import com.app.hitech.dto.MessageResponse;
import com.app.hitech.dto.SignUpRequest;
import com.app.hitech.exceptions.BusinessException;
import com.app.hitech.service.IEmployeeService;
import com.app.hitech.service.impl.UserDetailsImpl;

/**
 * Rest controller for request to auth
 * 
 * @author casti
 *
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	IEmployeeService employeeService;

	@Autowired
	JwtUtils jwtUtils;

	/**
	 * @param loginRequest
	 * @return
	 */
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		System.out.println(loginRequest.toString());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	/**
	 * @param signUpRequest
	 * @return
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
		if (employeeService.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (employeeService.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		try {
			employeeService.newEmployee(signUpRequest);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

}