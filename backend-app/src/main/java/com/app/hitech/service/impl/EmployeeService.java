package com.app.hitech.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.hitech.dto.DetailsEmployee;
import com.app.hitech.dto.SignUpRequest;
import com.app.hitech.dto.SortDetailsEmployee;
import com.app.hitech.entities.Employee;
import com.app.hitech.entities.EnumRole;
import com.app.hitech.entities.Role;
import com.app.hitech.exceptions.BusinessException;
import com.app.hitech.exceptions.MessageException;
import com.app.hitech.repository.EmployeeRepository;
import com.app.hitech.repository.RoleRepository;
import com.app.hitech.service.IEmployeeService;
import com.app.hitech.utils.ConverterEmployee;

/**\
 * 
 * @author Rolando C
 *
 */
@Service
public class EmployeeService implements IEmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public List<SortDetailsEmployee> getEmployees() {
		List<SortDetailsEmployee> sortDetailsList = new ArrayList<>();
		employeeRepository.findAllEmployees().forEach( e  -> {
			SortDetailsEmployee sortDetails = new SortDetailsEmployee()
					.withName(e.getName())
					.withCode(e.getCode())
					.roles(e.getRoles())
					.enabled(e.isEnabled())
					.build();
			sortDetailsList.add(sortDetails);
			});
		return sortDetailsList;
	}

	@Override
	@Transactional
	public DetailsEmployee getEmployee(String username) throws BusinessException {
		try {
			
			Employee e = employeeRepository.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
			return ConverterEmployee.convertEmployeeToDetailsEmployee(e);
		} catch (Exception e) {
			throw new BusinessException(MessageException.ERROR_NO_FOUND.toString(), e);
		}

	}

	@Override
	public void newEmployee(SignUpRequest request) throws BusinessException {
		try {
			// Create new user's account
			Employee employee = ConverterEmployee.convertRequestToEmployee(request);
			Set<String> strRoles = request.getRole();
			Set<Role> roles = new HashSet<>();
			employee.setPassword(encoder.encode(request.getPassword()));
			System.out.println(employee.getPassword());
			if (strRoles == null) {
				Role userRole = roleRepository.findByName(EnumRole.ROLE_EMPLOYEE)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(userRole);
			}

			employee.setRoles(roles);
			employee.setEnabled(true);
			employeeRepository.save(employee);

		} catch (Exception e) {
			throw new BusinessException(MessageException.ERROR_BAD_UPDATE.toString().concat(request.getCode()), e);
		}
	}

	@Override
	public void updateEmployee(DetailsEmployee de ) throws BusinessException {
		try {
			Employee employee = employeeRepository.findByCode(de.getCode())
					.orElseThrow(() -> new RuntimeException("Error: employee is not found."));
			employee.setName(de.getName());
			employee.setBranch(de.getBranch());
			employee.setCity(de.getCity());
			employee.setEmail(de.getEmail());
			employee.setProfession(de.getProfession());
			employeeRepository.saveAndFlush(employee);
		} catch (Exception e) {
			throw new BusinessException(MessageException.ERROR_BAD_INSERT.toString(), e);
		}
	}

	@Override
	public Boolean existsByUsername(String username) {
		return employeeRepository.existsByUsername(username);
	}

	@Override
	public Boolean existsByEmail(String email) {
		return employeeRepository.existsByEmail(email);
	}

	@Override
	public void delete(String code) {
		Employee employee = employeeRepository.findByCode(code)
				.orElseThrow(() -> new RuntimeException("Employee is not found"));
		employee.setEnabled(false);
		employeeRepository.saveAndFlush(employee);
	}

}
