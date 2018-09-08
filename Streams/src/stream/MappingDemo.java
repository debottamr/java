package stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MappingDemo {

	public static void main(String[] args) {
        List<String> nameList = Stream.of("amy", "nick", "margo", "desi")
              .map(s->s.toUpperCase()).collect(Collectors.toList());
        System.out.println("Names in upper case" + nameList);

    }
	
}
