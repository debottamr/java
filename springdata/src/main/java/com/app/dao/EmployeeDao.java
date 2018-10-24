package com.app.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Employee;

@Transactional
public interface EmployeeDao extends JpaRepository<Employee, Long> ,EmployeeCustomRepository {

	List<Employee> findByName(String name);
	
	Page<List<Employee>> findByName(String name, Pageable pageable);
	
	List<Employee> findByNameOrId(String name,Long Id);
	
	@Query("select e from Employee e where e.department.id = ?1")
	List<Employee> findEmployeeByDepartemntId(Long Id);
	
	@Query("select e from Employee e where e.name.id like %?1")
	List<Employee> findEmployeeByNameEndWith(String name);
	
}
