package com.app.util;

import java.io.Serializable;

import org.hibernate.Session;

import com.app.entity.EmployeeEntity;

public class MergeEntityExample
{
   public static void main(String[] args)
   {
      Session sessionOne = HibernateUtil.getSessionFactory().openSession();
      sessionOne.beginTransaction();
       
      //Create new Employee object
      EmployeeEntity emp = new EmployeeEntity();
      emp.setEmployeeId(1);
      emp.setFirstName("Lokesh");
      emp.setLastName("Gupta");
      emp.setEmail("l@l.com");
      //Save employee
      Integer id = (Integer)sessionOne.save(emp);
      sessionOne.refresh(emp);
      sessionOne.getTransaction().commit();
      sessionOne.close();
       
      //Verify employee's firstname
      System.out.println(verifyEmployeeFirstName(id, "Lokesh"));
       
      Session sessionTwo = HibernateUtil.getSessionFactory().openSession();
      sessionTwo.beginTransaction();
       
      //Set new first name
      emp.setFirstName("Vikas");
       
      //Merge the emp object using merge() method
      EmployeeEntity mergedPersistentEmpEntity = (EmployeeEntity) sessionTwo.merge(emp);
       
      sessionTwo.getTransaction().commit();
      sessionTwo.close();
       
      //Verify employee's firstname again in database
      System.out.println(verifyEmployeeFirstName(id, "Vikas"));
       
      HibernateUtil.shutdown();
   } 
    
   private static boolean verifyEmployeeFirstName(Integer employeeId, String firstName){
      Session session = HibernateUtil.getSessionFactory().openSession();
      EmployeeEntity employee = (EmployeeEntity) session.load(EmployeeEntity.class, employeeId);
      //Verify first name
      boolean result = firstName.equals(employee.getFirstName());
      session.close();
      //Return verification result
      return result;
   }
}