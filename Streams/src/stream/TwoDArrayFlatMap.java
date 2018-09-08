package stream;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TwoDArrayFlatMap {
	public static void main(String[] args) {
		int[][] numArray = {{1, 2}, {3, 4}, {5, 6}};
		Stream<int[]> numStream = Stream.of(numArray);
		IntStream iStream = Stream.of(numArray).flatMapToInt(n -> Arrays.stream(n));
		iStream.forEach(System.out::println);
	}
}
