package com.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.model.Employee;

public class EmployeeDaoImpl implements EmployeeCustomRepository {

	@Autowired
	EntityManager em;

	
	public List<Employee> findByNameANDDepartId(String name, Long id) {
		Query query = em.createNativeQuery("SELECT em.* FROM employee as "
				+ "em inner join department d on em.d_id = d.department_id" +

		" WHERE em.employee_name LIKE ? and d.department_id =?", Employee.class);

		query.setParameter(1, name + "%");
		query.setParameter(2, id);

		return query.getResultList();
	}

}
