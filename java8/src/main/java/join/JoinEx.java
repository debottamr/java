package join;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class JoinEx {

	public static void main(String[] args) {
		List<String> strList = Arrays.asList("How", "To", "Do", "In", "Java");

		String joinedString = String.join(", ", strList);

		System.out.println(joinedString);

		StringJoiner joiner = new StringJoiner(", ", "[", "]");

		joiner.add("How").add("To").add("Do").add("In").add("Java");
		System.out.println(joiner.toString());

		List<String> numbers = Arrays.asList("How", "To", "Do", "In", "Java");

		String joinedString1 = numbers.stream().collect(Collectors.joining(", ", "[", "]"));
		System.out.println(joinedString1);

		String joined = String.join("/", "usr", "local", "bin");
		System.out.println(joined);

		String ids = String.join(", ", ZoneId.getAvailableZoneIds());
		System.out.println(ids);
	}
}
