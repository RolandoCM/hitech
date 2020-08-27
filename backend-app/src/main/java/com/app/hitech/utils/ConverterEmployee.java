package com.app.hitech.utils;

import com.app.hitech.dto.DetailsEmployee;
import com.app.hitech.dto.SignUpRequest;
import com.app.hitech.entities.Employee;

/**
 * @author casti
 *
 */
public class ConverterEmployee {

	public static DetailsEmployee convertEmployeeToDetailsEmployee(Employee employee) {
		DetailsEmployee detailsEmployee = new DetailsEmployee(employee.getId()).username(employee.getUsername())
				.code(employee.getCode()).email(employee.getEmail()).theName(employee.getName())
				.atCity(employee.getCity()).withProfession(employee.getProfession()).atBranch(employee.getBranch())
				.build();
		return detailsEmployee;
	}

	public static Employee convertRequestToEmployee(SignUpRequest req) {
		Employee employee = new Employee();
		employee.setUsername(req.getUsername());
		employee.setCode(req.getCode());
		employee.setName(req.getName());
		employee.setEmail(req.getEmail());
		employee.setBranch(req.getBranch());
		employee.setCity(req.getCity());
		employee.setProfession(req.getProfession());

		return employee;
	}

}
