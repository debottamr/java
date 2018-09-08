package stream;
import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntStreamList {

	public static void main(String[] args) {
		System.out.println("--- IntStream ---");
		IntStream is = IntStream.of(3, 4, 5, 6);
		is.forEach(System.out::println);
		
		System.out.println("--- DoubleStream ---");
		double[] num = {3.0, 4.7, 5.2, 6.8};
		DoubleStream numStream = Arrays.stream(num);
		numStream.forEach(System.out::println);
		
		System.out.println("--- With Specified range ---");
		numStream = Arrays.stream(num, 0, 2);
		numStream.forEach(System.out::println);
		
		System.out.println("--- Inclusive ---");
		IntStream thousandInt = IntStream.rangeClosed(1, 10);
		thousandInt.forEach(System.out::println);
		
		System.out.println("--- Exclusive ---");
		IntStream thousandIntExclusive = IntStream.range(1, 10);
		thousandIntExclusive.forEach(System.out::println);
		
		System.out.println("--- boxed ---");
		Stream<Integer> iStream = IntStream.of(3, 4, 5, 6).boxed();
		iStream.forEach(System.out::println);

	}
}
