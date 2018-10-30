package com.app.criteriahibernate5;

import java.util.List;
import javax.persistence.criteria.*;
import org.hibernate.*;
import org.hibernate.query.Query;

//Here is an example of using the CriteriaQuery.select()  method for a list of objects -
public class CriteriaQueryExample {

	public static void main(String[] args) {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
			Root<Employee> root = query.from(Employee.class);
			query.select(root);
			Query<Employee> q = session.createQuery(query);
			List<Employee> employees = q.getResultList();
			for (Employee employee : employees) {
				System.out.println(employee.getName());
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}

	}
}