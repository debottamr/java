package stream;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;

public class SpliteratorDemo {

	public static void main(String[] args) {
		List<String> nameList = Arrays.asList("Ram", "Sheila", "Mukesh", "Rani", "Nick", "Amy", "Desi", "Margo");
		Iterator<String> itr = nameList.iterator();
		while (itr.hasNext()) {
			System.out.println("name - " + itr.next());
		}

		Spliterator<String> splitStr = nameList.spliterator();
		while (splitStr.tryAdvance((n) -> System.out.println("name - " + n)));
		
		Spliterator<String> splitStr1 = nameList.spliterator();
        splitStr1.forEachRemaining( 
            (n) -> {
                String x = n.toLowerCase();
                System.out.println("" + x);
            }
        );
        
        
        Spliterator<String> splitStr3 = nameList.spliterator();
        Spliterator<String> splitStr4 = splitStr.trySplit();
        // Check if splitting actually happened, then use it
        if(splitStr4 != null){
            System.out.println("Spliterator-2");
            while(splitStr4.tryAdvance((n) -> System.out.println("name - " + n)));
        }
        // Original spliterator
        System.out.println("Original Spliterator");
        while(splitStr3.tryAdvance((n) -> System.out.println("name - " + n)));
	}
}
