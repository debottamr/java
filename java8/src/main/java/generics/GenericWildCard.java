package generics;

import java.text.RuleBasedCollator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class GenericWildCard {

	
	public static void main(String[] args) {
		
		Collection<?> coll = new ArrayList<String>();
		//OR
		List<? extends Number> list = new ArrayList<Long>();
		//OR
		//Pair<String,?> pair = new Pair<String,Integer>();
		
		//List<? extends Number> list = new ArrayList<String>();  //String is not subclass of Number; so error
		//OR
		//Comparator<? super String> cmp = new RuleBasedCollator(new Integer(100)); //Integer is not superclass of String
		
		
		//A generic type where all type arguments are the unbounded wildcard "?” without any restriction on type variables. e.g.

		ArrayList<?>  list1 = new ArrayList<Long>(); 
		//or
		ArrayList<?>  list2 = new ArrayList<String>(); 
		
		//Bounded wildcard parameterized type

		
		//Upper bounded wildcards
		
		 List<Integer> ints = Arrays.asList(1,2,3,4,5);
	      System.out.println(sum(ints));
	       
	      //List of Doubles
	      List<Double> doubles = Arrays.asList(1.5d,2d,3d);
	      System.out.println(sum(doubles));
	       
	      List<String> strings = Arrays.asList("1","2");
	      //This will give compilation error as :: The method sum(List<? extends Number>) in the
	      //type GenericsExample<T> is not applicable for the arguments (List<String>)
	      //System.out.println(sum(strings));
	      
	      
	      //Lower bounded wildcards

	      
	      //List of grand children
	      List<GrandChildClass> grandChildren = new ArrayList<GrandChildClass>();
	      grandChildren.add(new GrandChildClass());
	      addGrandChildren(grandChildren);
	       
	      //List of grand childs
	      List<ChildClass> childs = new ArrayList<ChildClass>();
	      childs.add(new GrandChildClass());
	      addGrandChildren(childs);
	       
	      //List of grand supers
	      List<SuperClass> supers = new ArrayList<SuperClass>();
	      supers.add(new GrandChildClass());
	      addGrandChildren(supers);
	
	}
	  //Method will accept
	   private static Number sum (List<? extends Number> numbers){
	      double s = 0.0;
	      for (Number n : numbers)
	         s += n.doubleValue();
	      return s;
	   }
	   
	   public static void addGrandChildren(List<? super GrandChildClass> grandChildren)
	   {
	      grandChildren.add(new GrandChildClass());
	      System.out.println(grandChildren);
	   }
}




class SuperClass{
    
}
class ChildClass extends SuperClass{
    
}
class GrandChildClass extends ChildClass{
    
}