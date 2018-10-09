package stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class BoxStream {

	public static void main(String[] args) {

		// It works perfect !!
		List<String> strings = Stream.of("how", "to", "do", "in", "java").collect(Collectors.toList());

		// Primitive doesn't work this way
		//// Compilation Error !!
		// IntStream.of(1,2,3,4,5)
		// .collect(Collectors.toList());

		// So we use boxed stream
		List<Integer> ints = IntStream.of(1, 2, 3, 4, 5).boxed().collect(Collectors.toList());

		List<Long> longs = LongStream.of(1l, 2l, 3l, 4l, 5l).boxed().collect(Collectors.toList());

		System.out.println(longs);
		
		List<Double> doubles = DoubleStream.of(1d,2d,3d,4d,5d)
                .boxed()
                .collect(Collectors.toList());
         
System.out.println(doubles);

	}
}
