package com.app.hitech.service;

import java.util.List;

import com.app.hitech.dto.DetailsEmployee;
import com.app.hitech.dto.SignUpRequest;
import com.app.hitech.dto.SortDetailsEmployee;
import com.app.hitech.exceptions.BusinessException;

/**
 * @author Rolando Castillo
 *
 */
public interface IEmployeeService {

	/**
	 * Method to get all the employees form database. Return an employees
	 * {@link List} if not exist any employees the {@link List} return empty.
	 * 
	 * @return {@link List} < {@link Employee0} >
	 */
	List<SortDetailsEmployee> getEmployees();

	/**
	 * Method to get an employee from database by code
	 * 
	 * @param code
	 * @return {@link DetailsEmployee}
	 * @throws BusinessException
	 */
	DetailsEmployee getEmployee(String username) throws BusinessException;

	/**
	 * Update of the employee, this method receive {@link Employee0} as parameter and
	 * return the update {@link Employee0}. If exist any exception, this is throw.
	 * 
	 * @param employee {@link DetailsEmployee}
	 * @return {@link DetailsEmployee}
	 * @throws BusinessException
	 */
	void updateEmployee(DetailsEmployee employee) throws BusinessException;

	/**
	 * Method to save a new employee
	 * 
	 * @param request {@link SignUpRequest }
	 * @return {@link SignUpRequest}
	 * @throws BusinessException
	 */
	void newEmployee(SignUpRequest request) throws BusinessException;
	
	/**
	 * @param username
	 * @return
	 */
	Boolean existsByUsername(String username);
	
	/**
	 * @param email
	 * @return
	 */
	Boolean existsByEmail( String email);
	
	/**
	 * @param code
	 */
	void delete(String code);
	
	
	

}
