package com.app.criteriahibernate5;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

//Here is an example of using the CriteriaQuery.select()  method a single object -
public class CriteriaQueryExample2 {

   public static void main(String[] args) {

      Transaction transaction = null;
      try (Session session = HibernateUtil.getSessionFactory().openSession()) {
         transaction = session.beginTransaction();

         CriteriaBuilder builder = session.getCriteriaBuilder();
         CriteriaQuery<Department> query = builder.createQuery(Department.class);
         Root<Department> root = query.from(Department.class);
         query.select(root).where(builder.equal(root.get("id"), 1l));
         Query<Department> q=session.createQuery(query);
         Department department=q.getSingleResult();
         System.out.println(department.getName());
         
         transaction.commit();
      } catch (Exception e) {
         e.printStackTrace();
         if (transaction != null) {
            transaction.rollback();
         }
      }
   }
}
