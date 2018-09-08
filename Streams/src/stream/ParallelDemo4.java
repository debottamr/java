package stream;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class ParallelDemo4 {

	public static void main(String[] args) {
        ParallelDemo4 pd = new ParallelDemo4();
        // getting list of employee 
        List<Employee1> empList = pd.createList();
        
        ConcurrentMap<Character, List<Employee1>> bySalary = empList.parallelStream()
         .collect(Collectors.groupingByConcurrent(e -> e.sex));
        bySalary.forEach((K, V)->{
            System.out.println("Key- " + K + " Value ");
            V.forEach(v->System.out.println(v.name));
        });      
    }
       
    // Stub method to create list of employee objects
    private List<Employee1> createList(){
        List<Employee1> empList = Arrays.asList(new Employee1("E001", 40, "Ram", 'M', 5000), 
        new Employee1("E002", 35, "Sheila", 'F', 7000), 
        new Employee1("E003", 24, "Mukesh", 'M', 9000), 
        new Employee1("E004", 37, "Rani", 'F', 10000));
        
        return empList;
    }
    
    class Employee1 {
        private String empId;
        private int age;
        private String name;
        private char sex;
        private int salary;
        Employee1(String empId, int age, String name, char sex, int salary){
            this.empId = empId;
            this.age = age;
            this.name = name;
            this.sex = sex;
            this.salary = salary;
        }        
    }
    
}
