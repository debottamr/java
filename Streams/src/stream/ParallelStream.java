package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParallelStream {

	public static void main(String[] args) {
		
		Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8 };
		List<Integer> listOfIntegers = new ArrayList<>(Arrays.asList(intArray));
		System.out.println("listOfIntegers:");
		listOfIntegers.stream().forEach(e -> System.out.print(e + " "));
		
		System.out.println("With forEachOrdered:");
		listOfIntegers.parallelStream().forEachOrdered(e -> System.out.print(e + " "));
	}
}
