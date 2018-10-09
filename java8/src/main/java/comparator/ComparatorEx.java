package comparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ComparatorEx {

	public static void main(String[] args) {
		List<Employee> employees = getEmployees();

		// Sort all employees by first name
		employees.sort(Comparator.comparing(e -> e.getFirstName()));

		// OR you can use below
		employees.sort(Comparator.comparing(Employee::getFirstName));

		// Let's print the sorted list
		System.out.println(employees);
		
		
		//Reverse
	    Comparator<Employee> comparator = Comparator.comparing(e -> e.getFirstName());
		employees.sort(comparator.reversed());
		
		//Group sort
		//Sorting on multiple fields; Group by.
		Comparator<Employee> groupByComparator = Comparator.comparing(Employee::getFirstName)
		                                                    .thenComparing(Employee::getLastName);
		employees.sort(groupByComparator);
		 
		System.out.println(employees);
		
		//Parallel sort (with multiple threads)
		
		//Parallel Sorting
		Employee[] employeesArray = employees.toArray(new Employee[employees.size()]);
		 
		//Parallel sorting
		Arrays.parallelSort(employeesArray, groupByComparator);
		 
		System.out.println(employeesArray);
		
		
	}

	private static List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee(6, "Yash", "Chopra", 25));
		employees.add(new Employee(2, "Aman", "Sharma", 28));
		employees.add(new Employee(3, "Aakash", "Yaadav", 52));
		employees.add(new Employee(5, "David", "Kameron", 19));
		employees.add(new Employee(4, "James", "Hedge", 72));
		employees.add(new Employee(8, "Balaji", "Subbu", 88));
		employees.add(new Employee(7, "Karan", "Johar", 59));
		employees.add(new Employee(1, "Lokesh", "Gupta", 32));
		employees.add(new Employee(9, "Vishu", "Bissi", 33));
		employees.add(new Employee(10, "Lokesh", "Ramachandran", 60));
		return employees;
	}
}

class Employee {
	private Integer id;
	private String firstName;
	private String lastName;
	private Integer age;

	public Employee(Integer id, String firstName, String lastName, Integer age) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	// Other getter and setter methods

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "\n[" + this.id + "," + this.firstName + "," + this.lastName + "," + this.age + "]";
	}
}