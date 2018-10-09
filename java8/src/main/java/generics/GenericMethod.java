package generics;

public class GenericMethod {

	
	public static <T> int countAllOccurrences(T[] list, T item) {
		   int count = 0;
		   if (item == null) {
		      for ( T listItem : list )
		         if (listItem == null)
		            count++;
		   }
		   else {
		      for ( T listItem : list )
		         if (item.equals(listItem))
		            count++;
		   }
		   return count;
		}  
	
}
