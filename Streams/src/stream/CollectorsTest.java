package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class CollectorsTest {

	public static void main(String[] args) {

		CollectorsTest md = new CollectorsTest();
		List<Employee> empList = md.createList();
		List<String> nameList = empList.stream().map(Employee::getName).collect(Collectors.toList());
		
		Set<String> nameSet = empList.stream().map(Employee::getName).collect(Collectors.toSet());
		
		Set<String> nameSet1 = empList.stream().map(Employee::getName).collect(Collectors.toCollection(TreeSet::new));
		
		Map<String, String> nameMap = empList.stream().collect(Collectors.toMap(Employee::getEmpId, Employee::getName));

		String names = empList.stream().map(Employee::getName).collect(Collectors.joining(","));
		
		int totalSalary = empList.stream().collect(Collectors.summingInt(Employee::getSalary));
		
		Map<Character, List<Employee>> empMap = empList.stream().collect(Collectors.groupingBy(Employee::getSex));
		
		Map<Boolean, List<Employee>> empMap1 = empList.stream().collect(Collectors.partitioningBy(e -> e.getSalary() >= 8000 ));
		
		List<String> asList = Stream.of("a", "b", "c").collect(ArrayList::new, 
				ArrayList::add, ArrayList::addAll);
		
		
		asList = Stream.of("a", "b", "c").collect(() -> new ArrayList<>(), (alist, word) -> alist.add(word), (alist1, alist2) -> alist1.addAll(alist2));


	}

	// Stub method to create list of employee objects
	private List<Employee> createList() {
		List<Employee> empList = Arrays.asList(new Employee("E001", 40, "Ram", 'M', 5000),
				new Employee("E002", 35, "Sheila", 'F', 7000), new Employee("E003", 24, "Mukesh", 'M', 9000),
				new Employee("E004", 37, "Rani", 'F', 10000));

		return empList;
	}

	class Employee {
		private String empId;
		private int age;
		private String name;
		private char sex;
		private int salary;

		Employee(String empId, int age, String name, char sex, int salary) {
			this.empId = empId;
			this.age = age;
			this.name = name;
			this.sex = sex;
			this.salary = salary;
		}

		public String getEmpId() {
			return empId;
		}

		public void setEmpId(String empId) {
			this.empId = empId;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public char getSex() {
			return sex;
		}

		public void setSex(char sex) {
			this.sex = sex;
		}

		public int getSalary() {
			return salary;
		}

		public void setSalary(int salary) {
			this.salary = salary;
		}
	}
}
