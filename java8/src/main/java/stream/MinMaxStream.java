package stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class MinMaxStream {

	public static void main(String[] args) {
		// Get Min or Max String/Char
		String maxChar = Stream.of("H", "T", "D", "I", "J").max(Comparator.comparing(String::valueOf)).get();

		String minChar = Stream.of("H", "T", "D", "I", "J").min(Comparator.comparing(String::valueOf)).get();

		System.out.println("maxChar = " + maxChar);
		System.out.println("minChar = " + minChar);
		
		
		List<Employee> employees = new ArrayList<Employee>();
		 
		employees.add(new Employee(1, "Lokesh", 36));
		employees.add(new Employee(2, "Alex", 46));
		employees.add(new Employee(3, "Brian", 52));
		 
		Comparator<Employee> comparator = Comparator.comparing( Employee::getAge );
		 
		// Get Min or Max Object
		Employee minObject = employees.stream().min(comparator).get();
		Employee maxObject = employees.stream().max(comparator).get();
		 
		System.out.println("minObject = " + minObject);
		System.out.println("maxObject = " + maxObject);
		
		
		
	}
}
