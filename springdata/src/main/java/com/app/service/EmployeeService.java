package com.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.app.dao.EmployeeDao;
import com.app.model.Employee;

@Service
public class EmployeeService {

	@Autowired
	EmployeeDao employeeDao;
	
	@Transactional
	public List<Employee> findByNameAndId(String name,Long id) {
		return employeeDao.findByNameANDDepartId(name, id);
	}

	public Page<Employee> getEmployees(Integer page) {
		Order order1 = new Order(Direction.DESC, "department.name");
		Order order2 = new Order(Direction.DESC, "id");
		List<Order> orders = new ArrayList<Sort.Order>();
		orders.add(order1);
		orders.add(order2);
		Sort sort = new Sort(orders);
		Page<Employee> findAll = employeeDao.findAll(new PageRequest(page, 2, sort));

		return findAll;
	}

	@Transactional
	public List<Employee> getAllEmployees() {
		return (List<Employee>) employeeDao.findAll();
	}

	@Transactional
	public List<Employee> findByName(String name) {
		return employeeDao.findByName(name);
	}
	
	@Transactional
	public List<Employee> findByNameOrId(String name,Long id) {
		return employeeDao.findByNameOrId(name,id);
	}

	@Transactional
	public Employee getById(Long id) {
		return employeeDao.getOne(id);
	}

	@Transactional
	public List<Employee> getByDepartmentId(Long id) {
		return employeeDao.findEmployeeByDepartemntId(id);
	}

	@Transactional
	public void deleteEmployee(Long personId) {
		employeeDao.deleteById(personId);
	}

	@Transactional
	public boolean addEmployee(Employee person) {
		return employeeDao.save(person) != null;
	}

	@Transactional
	public boolean updateEmployee(Employee person) {
		return employeeDao.save(person) != null;
	}
}
