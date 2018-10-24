package com.app.dao;

import java.util.List;

import com.app.model.Employee;

public interface EmployeeCustomRepository {

	List<Employee> findByNameANDDepartId(String name, Long id);
}
