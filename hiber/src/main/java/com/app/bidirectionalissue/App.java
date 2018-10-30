package com.app.bidirectionalissue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

public class App {

	public static void main(String[] args) {
		Session sessionOne = HibernateUtil.getSessionFactory().openSession();
		sessionOne.beginTransaction();

		// Create new Employee object
		EmployeeEntity emp = new EmployeeEntity();
		emp.setFirstName("Lokesh");
		emp.setLastName("Gupta");

		// Create new Employee object
		AccountEntity acc = new AccountEntity();
		acc.setAccountNumber("DUMMY_ACCOUNT");
		emp.setAccount(acc);
		// acc.setEmployee(emp);

		sessionOne.save(acc);
		sessionOne.save(emp);
		sessionOne.getTransaction().commit();

		Integer genEmpId = emp.getEmployeeId();
		Integer genAccId = acc.getAccountId();

		Session sessionTwo = HibernateUtil.getSessionFactory().openSession();
		sessionTwo.beginTransaction();
		EmployeeEntity employee = (EmployeeEntity) sessionTwo.get(EmployeeEntity.class, genEmpId);
		AccountEntity account = (AccountEntity) sessionTwo.get(AccountEntity.class, genAccId);

		System.out.println(employee.getEmployeeId());
		System.out.println(employee.getAccount().getAccountNumber());
		System.out.println(account.getAccountId());
		System.out.println(account.getEmployee().getEmployeeId());

		HibernateUtil.shutdown();
	}

}