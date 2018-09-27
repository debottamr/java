package stream;

import java.util.Arrays;
import java.util.List;

public class Perf {

	public static void main(String[] args) {
		
	}
	public void iteratorMaxInteger() {
		List<Integer> list = Arrays.asList(1, 2, 3);
		 
		// Old school
		for (Integer i : list)
		    System.out.println(i);
		 
		// "Modern"
		list.forEach(System.out::println);
	}
}
