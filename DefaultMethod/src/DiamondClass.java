
public class DiamondClass implements A, B{
	  // provides implementation for the non-default method
	  // of the interface
	  @Override
	  public int method1() {
	     return 10;
	  }
	  //Overriding the default method of MyInterface
	  public String displayGreeting(){
	     return A.super.displayGreeting();
	  }
	 
	  public static void main(String[] args) {
	     A myInt = new DiamondClass();
	     System.out.println("Value " +  myInt.method1());
	     // Calls the default method provided by interface itself
	     System.out.println("Greeting " + myInt.displayGreeting());
	  }
	}