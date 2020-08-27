package com.app.hitech.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.hitech.dto.DetailsEmployee;
import com.app.hitech.dto.SortDetailsEmployee;
import com.app.hitech.exceptions.BusinessException;
import com.app.hitech.service.IEmployeeService;

/**
 * Controller Employee, contains the CRUD to employee
 * @author Rolando Castillo
 *
 */
@RestController
@RequestMapping("/api/hitech")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	private static final Logger LOG = Logger.getLogger(EmployeeController.class);

	/**
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/employees")
	public ResponseEntity<?> allEmployees() {
		Map<String, Object> response = new HashMap<>();
		List<SortDetailsEmployee> employees = employeeService.getEmployees();
		if (employees.isEmpty()) {
			response.put("message", "no elements");
			LOG.info("No elemenets found");
		} else {
			response.put("employees", employees);
			LOG.info("Elements found: " + employees.size());
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	/**
	 * @param code
	 * @return
	 */
	@PreAuthorize("hasRole('EMPLOYEE')")
	@GetMapping("/employee")
	public ResponseEntity<?> getEmployee(@RequestParam String username) {
		Map<String, Object> response = new HashMap<>();

		LOG.info(username);
		try {
			DetailsEmployee employee = employeeService.getEmployee(username);
			response.put("employee", employee);
		} catch (BusinessException e) {
			LOG.error(e);
			response.put("errors", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	/**
	 * @param employee
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_EMPLOYEE')")
	@PutMapping("/employee")
	public ResponseEntity<?> updateEmployee(@RequestBody DetailsEmployee employee) {
		Map<String, Object> response = new HashMap<>();
		try {
			employeeService.updateEmployee(employee);
		}catch (BusinessException e) {
			LOG.error(e);
			response.put("errors", e.getMessage());
			response.put("data", employee);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("disable")
	public ResponseEntity<?> delete(@RequestBody String code){
		Map<String, Object> response = new HashMap<>();
		employeeService.delete(code);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
