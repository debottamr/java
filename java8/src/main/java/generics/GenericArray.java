package generics;

public class GenericArray<T> {
    // this one is fine
    public T[] notYetInstantiatedArray;
  
    // causes compiler error; Cannot create a generic array of T
    //public T[] array = new T[5];
    
    
    public static void main(String[] args) {
    	Object[] array = new String[10];
    	array[0] = "lokesh";
    	array[1] = 10; 
	}
}