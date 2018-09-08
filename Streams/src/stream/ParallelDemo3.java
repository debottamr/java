package stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelDemo3 {

	public static void main(String[] args) {
        Salary sal = new Salary();
        List<Employee> empList = createList();
        //empList.parallelStream().forEach(sal::doProcess);
        int totalSalary = empList.parallelStream().mapToInt(e -> e.getSalary()).sum();
        sal.addSalary(totalSalary);
        System.out.println("Total - " + sal.getTotalSalary());

    }
    // Stub method to create list of employee objects
    private static List createList(){
        List<Employee> empList = IntStream.rangeClosed(1, 500).mapToObj(Employee::new)
         .collect(Collectors.toList());
        return empList;
    }
}
