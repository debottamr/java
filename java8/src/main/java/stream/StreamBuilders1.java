package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamBuilders1 {

	public static void main(String[] args) {

		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i < 10; i++) {
			list.add(i);
		}
		Stream<Integer> stream = list.stream();
		List<Integer> evenNumbersList = stream.filter(i -> i % 2 == 0).collect(Collectors.toList());
		System.out.print(evenNumbersList);

		// Core Stream Operation filter/map/flatmap/sorted
		List<Integer> list1 = new ArrayList<Integer>();
		for (int i = 1; i < 10; i++) {
			list1.add(i);
		}
		Stream<Integer> stream1 = list.stream();
		Integer[] evenNumbersArr = stream1.filter(i -> i % 2 == 0).toArray(Integer[]::new);
		System.out.print(Arrays.toString(evenNumbersArr));

		List<String> memberNames = new ArrayList<>();
		memberNames.add("Amitabh");
		memberNames.add("Shekhar");
		memberNames.add("Aman");
		memberNames.add("Rahul");
		memberNames.add("Shahrukh");
		memberNames.add("Salman");
		memberNames.add("Yana");
		memberNames.add("Lokesh");

		memberNames.stream().filter((s) -> s.startsWith("A")).forEach(System.out::println);

		memberNames.stream().filter((s) -> s.startsWith("A")).map(String::toUpperCase).forEach(System.out::println);

		memberNames.stream().sorted().map(String::toUpperCase).forEach(System.out::println);

		// Terminal Operation forEach, collect, match, count
		memberNames.forEach(System.out::println);

		List<String> memNamesInUppercase = memberNames.stream().sorted().map(String::toUpperCase)
				.collect(Collectors.toList());
		System.out.print(memNamesInUppercase);

		boolean matchedResult = memberNames.stream().anyMatch((s) -> s.startsWith("A"));

		System.out.println(matchedResult);

		matchedResult = memberNames.stream().allMatch((s) -> s.startsWith("A"));

		System.out.println(matchedResult);

		matchedResult = memberNames.stream().noneMatch((s) -> s.startsWith("A"));

		System.out.println(matchedResult);

		long totalMatched = memberNames.stream().filter((s) -> s.startsWith("A")).count();

		System.out.println(totalMatched);

		Optional<String> reduced = memberNames.stream().reduce((s1, s2) -> s1 + "#" + s2);

		reduced.ifPresent(System.out::println);

		// Short-circuit operations
		// Only first Match
		boolean matched = memberNames.stream().anyMatch((s) -> s.startsWith("A"));

		System.out.println(matched);

		String firstMatchedName = memberNames.stream().filter((s) -> s.startsWith("L")).findFirst().get();

		System.out.println(firstMatchedName);

		//Parallelism
		
	}
}
