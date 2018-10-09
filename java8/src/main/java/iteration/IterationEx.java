package iteration;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IterationEx {

	public static void main(String[] args) {
		   List<String> alphabets = Arrays.asList(new String[]{"a","b","b","d"});
	         
	        alphabets.forEach(l -> l.toUpperCase());
	        
	        
	         
	        Iterator<String> iterator = alphabets.listIterator();
	        while(iterator.hasNext()){
	            System.out.println(iterator.next().toUpperCase());
	        }
	        
	        for(String letter: alphabets){
	            System.out.println(letter.toUpperCase());
	        }
	        
	}
}
