package stream;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamFilter {

	public static void main(String[] args) {
		List<Integer> myList = Arrays.asList(7, 18, 10, 24, 17, 5);  
		long count = myList.stream().count();
		System.out.println("Total elements in the list " + count);
		
		List<Integer> myList1 = Arrays.asList(7, 18, 10, 24, 17, 5); 
		long count1 = myList.stream().filter(i -> i > 10).count();
		System.out.println("Total elements in the list with value greater than 10 " + count1);
		
		
		List<String> myList2 = Arrays.asList("1", "2", "3", "4", "5");
		  
		String[] arr1 = { "a", "b", "c", "d" };
		// concatenating two streams
		Stream<String> stream = Stream.concat(myList2.stream(), Arrays.stream(arr1));
		stream.forEach(System.out::print);
		
		
		List<String> nameList1 = Arrays.asList("Ram", "Amit", "Ashok", "Manish", "Rajat");
		  
		List<String> nameList2 = Arrays.asList("Anthony", "Samir", "Akash", "Uttam");
		  
		// concatenating two streams
		Stream<String> stream2 = Stream.concat(nameList1.stream().filter(n -> n.startsWith("A")), nameList2.stream().filter(n -> n.startsWith("A")));

		stream2.forEach(System.out::println);
		
		
		
		//collector
		List<Integer> myList3 = Arrays.asList(7, 18, 10, 7, 10, 24, 17, 5);
		  
		System.out.println("Original list: " + myList3);
		List<Integer> newList = myList3.stream().distinct().collect(Collectors.toList());

		System.out.println("new List : " + newList);
		
		
		//Limit
		Random random = new Random();
		random.ints().limit(10).forEach(System.out::println);
		
		//transform
		List<Integer> myList4 = Arrays.asList(7000, 5000, 4000, 24000, 17000, 6000);
		  
		myList4.stream().map(i -> (i+ i * 10/100)).forEach(System.out::println);
		
		
		
		List<String> nameList = Arrays.asList("amy", "nick", "margo", "desi");
		Optional<String> name = nameList.stream().findFirst();
		System.out.println("First Name " + name);

		name = nameList.parallelStream().findAny();
		System.out.println("First Name " + name);
		
		
		//compare
		
		List<Integer> myList5 = Arrays.asList(7000, 5000, 4000, 24000, 17000, 6000);
		// Obtain a Stream to the array list.
		Stream<Integer> myStream = myList5.stream();
		Optional<Integer> val = myStream.min(Integer::compare);
		if(val.isPresent()){
		 System.out.println("minimum value in the list " + val.get());
		}  
		Optional<Integer> val1 = myList.stream().max(Integer::compare);
		if(val1.isPresent()){
		 System.out.println("maximum value in the list " + val1.get());
		}
		
		
		List<Integer> myList6 = Arrays.asList(7000, 5000, 4000, 24000, 17000, 6000);
		// Obtain a Stream to the array list.
		Stream<Integer> myStream6 = myList6.stream();
		Optional<Integer> val6 = myStream6.min(Integer::compare);
		if(val6.isPresent()){
		 System.out.println("minimum value in the list " + val6.get());
		}  
		Optional<Integer> val61 = myList6.stream().max(Integer::compare);
		if(val61.isPresent()){
		 System.out.println("maximum value in the list " + val61.get());
		}

		List<Integer> myList7 = Arrays.asList(7000, 5000, 4000, 24000, 17000, 6000);
		myList7.stream().sorted().forEach(System.out::println);
		
		List<Integer> myList8 = Arrays.asList(7, 5, 4, 24, 17, 6);
		IntSummaryStatistics stats = myList8.stream().collect(Collectors.summarizingInt(i-> i));

		System.out.println("Sum - " + stats.getSum());
		System.out.println("Count " + stats.getCount());
		System.out.println("Average " + stats.getAverage());
		System.out.println("Max " + stats.getMax());
		System.out.println("Min " + stats.getMin());
	}
}
