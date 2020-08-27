package com.app.hitech.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.hitech.entities.Employee;

/**
 * <h1>Employee Repositorio</h1>
 * 
 * Definition of the repository to have access data, this interface extends of
 * {@link JpaRepository} to use the methods predefine.
 * 
 * @author Rolando Castillo
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	@Query("select u from Employee u where u.enabled = TRUE")
	List<Employee> findAllEmployees();

	/**
	 * find an employee by code
	 * 
	 * @param code {@link Employee0}
	 * @return {@link Employee0}
	 */
	Optional<Employee> findByCode(String code);
	
	/**
	 * @param username
	 * @return
	 */
	Optional<Employee> findByUsername(String username);
	
	Boolean existsByUsername(String username);
	/**
	 * @param email
	 * @return
	 */
	Boolean existsByEmail(String email);

}
