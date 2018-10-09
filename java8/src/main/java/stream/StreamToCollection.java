package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamToCollection {

	public static void main(String[] args) {
		List<Integer> ints = IntStream.of(1, 2, 3, 4, 5).boxed().collect(Collectors.toList());

		System.out.println(ints);
		List<Integer> ints1 = IntStream.of(1, 2, 3, 4, 5).mapToObj(Integer::valueOf).collect(Collectors.toList());

		System.out.println(ints1);
		
		int[] intArray = IntStream.of(1, 2, 3, 4, 5).toArray();
		 
		System.out.println(Arrays.toString(intArray));
	}
}
