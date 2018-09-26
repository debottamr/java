package com.app.db;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.app.vo.EmployeeVO;

public class EmployeeDB {
	
	 private static List<EmployeeVO> list = new ArrayList<>();
	 static {
		 
		 EmployeeVO empOne = new EmployeeVO(1, "Lokesh", "Gupta", "howtodoinjava@gmail.com");
		 EmployeeVO empTwo = new EmployeeVO(2, "Amit", "Singhal", "asinghal@yahoo.com");
		 EmployeeVO empThree = new EmployeeVO(3, "Kirti", "Mishra", "kmishra@gmail.com");
		 list.add(empOne);
	        list.add(empTwo);
	        list.add(empThree);
	 }

	public static void addEmployee(@Valid EmployeeVO employee) {
		list.add(employee);		
	}

	public static EmployeeVO getEmployeeById(final int id) {
		EmployeeVO employeeVO = list.stream().filter(e -> e.getEmployeeId().equals(id)) .findFirst().get();
		return employeeVO;
	}

	 public static List<EmployeeVO> getEmployeeList()
    {
       
 
        
 
        return list;
    }


}
