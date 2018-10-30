package com.app.util.saveupdate;

import org.hibernate.Session;

import com.app.entity.EmployeeEntity;
import com.app.util.HibernateUtil;

public class SaveEntityAgainInAnotherSession {
	public static void main(String[] args) {
		Session sessionOne = HibernateUtil.getSessionFactory().openSession();
		try {
			sessionOne.beginTransaction();

			// Create new Employee object
			EmployeeEntity emp = new EmployeeEntity();
			emp.setEmployeeId(1);
			emp.setFirstName("Lokesh");
			emp.setLastName("Gupta");
			emp.setEmail("1@1.com");
			// Save employee
			sessionOne.save(emp);
			sessionOne.getTransaction().commit();

			Session sessionTwo = HibernateUtil.getSessionFactory().openSession();
			sessionTwo.beginTransaction();

			emp.setLastName("temp");

			// Save employee again second time
			sessionTwo.save(emp);

			sessionTwo.getTransaction().commit();
		} finally {
			HibernateUtil.shutdown();
		}

	}
}
