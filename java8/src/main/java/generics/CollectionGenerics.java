package generics;

import java.util.ArrayList;
import java.util.List;

public class CollectionGenerics {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		 
		list.add(1000);     //works fine
		
		List list1 = new ArrayList();
		 
		list1.add(1000); 
		
		GenericClass<String> instance = new GenericClass<String>();
		instance.set("1");   //Correct usage
	}
}
