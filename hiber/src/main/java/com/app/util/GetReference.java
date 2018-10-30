package com.app.util;

import org.hibernate.Session;

import com.app.entity.EmployeeEntity;

public class GetReference {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		// Add new Employee object
		EmployeeEntity emp = new EmployeeEntity();
		emp.setEmail("demo-user@mail.com");
		emp.setFirstName("demo");
		emp.setLastName("user");
		// Save entity
		Integer id = (Integer)session.save(emp);

		session.getTransaction().commit();
		session.close();

		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		EmployeeEntity empGet = (EmployeeEntity) session.byId(EmployeeEntity.class).getReference(id);

		System.out.println("No data initialized till now; Lets fetch some data..");

		System.out.println(empGet.getFirstName());
		System.out.println(empGet.getLastName());

		session.getTransaction().commit();
		HibernateUtil.shutdown();
	}
}
