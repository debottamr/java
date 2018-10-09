package generics;

public class GenericNonAllowedFunctionality {

}

//You can’t have static field of type
class GenericsExample<T>
{
   //private static T member; //This is not allowed
}

//b) You can not create an instance of T
class GenericsExample1<T>
{
   public GenericsExample1(){
     // new T();
   }
}

//c) Generics are not compatible with primitives in declarations
class T{
	
	public static void main(String[] args) {
	//	final List<int> ids = new ArrayList<>();    //Not allowed
		 
	}
	
}

//d) You can’t create Generic exception class
//class GenericException<T> extends Exception {}



