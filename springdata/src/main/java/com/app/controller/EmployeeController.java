package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Employee;
import com.app.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping(value = "/employeeByNameANDId/{name}/{id}/", method = RequestMethod.GET)
	public List<Employee> employeeByNameANDId(@PathVariable String name,@PathVariable Long id) {
		return employeeService.findByNameAndId(name, id); 
	}
	
	@RequestMapping(value = "/employee/page/{page}", method = RequestMethod.GET)
	public @ResponseBody Page<Employee> getPage(@PathVariable Integer page) {
		return employeeService.getEmployees(page);
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public @ResponseBody Employee getAllEmployees(@PathVariable Long id) {
		return employeeService.getById(id);
	}
	
	@RequestMapping(value = "/employee/department/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Employee> getAllEmployeesByDepartmentId(@PathVariable Long id) {
		return employeeService.getByDepartmentId(id);
	}

	@RequestMapping(value = "/employeeByName/{name}", method = RequestMethod.GET)
	public List<Employee> getEmployeeByName(@PathVariable String name) {
		return employeeService.findByName(name);
	}
	
	@RequestMapping(value = "/employeeByNameOrId/{name}/{id}/", method = RequestMethod.GET)
	public List<Employee> employeeByNameOrId(@PathVariable String name,@PathVariable Long id) {
		return employeeService.findByNameOrId(name, id);
	}

	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public List<Employee> getAll() {
		return employeeService.getAllEmployees();
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
	public HttpStatus deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
		return HttpStatus.NO_CONTENT;
	}

	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public HttpStatus insertEmployee(@RequestBody Employee employee) {
		return employeeService.addEmployee(employee) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
	}

	@RequestMapping(value = "/employee", method = RequestMethod.PUT)
	public HttpStatus updateEmployee(@RequestBody Employee employee) {
		return employeeService.updateEmployee(employee) ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST;
	}
}
