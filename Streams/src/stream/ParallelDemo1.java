package stream;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public class ParallelDemo1 {

    public static void main(String[] args) {
        // getting list of employee 
        List<Employee> empList = createList();
        OptionalInt maxSalary = empList.parallelStream().mapToInt(e -> e.getSalary()).max();
        if(maxSalary.isPresent()){
            System.out.println("Max Salary " + maxSalary.getAsInt());
        }
    }
    
    // Stub method to create list of employee objects
    private static List createList(){
        List<Employee> empList = new ArrayList<Employee>();
        Employee emp = new Employee();
        emp.setEmpId("E001");
        emp.setAge(40);
        emp.setFirstName("Ram");
        emp.setLastName("Chandra");
        emp.setSalary(5000);
        empList.add(emp);
        emp = new Employee();
        emp.setEmpId("E002");
        emp.setAge(35);
        emp.setFirstName("Sheila");
        emp.setLastName("Baijal");
        emp.setSalary(7000);
        empList.add(emp);
        emp = new Employee();
        emp.setEmpId("E003");
        emp.setAge(24);
        emp.setFirstName("Mukesh");
        emp.setLastName("Rishi");
        emp.setSalary(9000);
        empList.add(emp);
        emp = new Employee();
        emp.setEmpId("E004");
        emp.setAge(37);
        emp.setFirstName("Rani");
        emp.setLastName("Mukherjee");
        emp.setSalary(10000);
        empList.add(emp);
        return empList;
    }
}