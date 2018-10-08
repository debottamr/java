package stream;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamBuilders {

	  public static void main(String[] args){
	         Stream<Integer> stream = Stream.of(1,2,3,4,5,6,7,8,9);
	         stream.forEach(p -> System.out.println(p));
	         
	         Stream<Integer> stream1 = Stream.of( new Integer[]{1,2,3,4,5,6,7,8,9} );
	         stream1.forEach(p -> System.out.println(p));
	         
	         List<Integer> list = new ArrayList<Integer>();
	         for(int i = 1; i< 10; i++){
	             list.add(i);
	         }
	         Stream<Integer> stream2 = list.stream();
	         stream2.forEach(p -> System.out.println(p));
	         
	         Stream<Date> stream3 = Stream.generate(() -> { return new Date();});
	         stream3.forEach(p -> System.out.println(p));
	         
	         
	         IntStream stream4 = "12345_abcdefg".chars();
	         stream4.forEach(p -> System.out.println(p));
	          
	         //OR
	          
	         Stream<String> stream5 = Stream.of("A$B$C".split("\\$"));
	         stream5.forEach(p -> System.out.println(p));
	         
	     }
}
