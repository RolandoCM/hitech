package com.app.hitech.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.hitech.entities.Employee;
import com.app.hitech.repository.EmployeeRepository;

/**
 * @author casti
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee user = employeeRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}

}
