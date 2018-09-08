
public class MyClass1 implements MyInterface{
	  // provides implementation for the non-default method
	  // of the interface
	  @Override
	  public int method1() {
	      return 10;
	  }
	  //Overriding the default method of MyInterface
	  @Override
	  public String displayGreeting(){
	      return "Hello from MyClass";
	  }
	 
	  public static void main(String[] args) {
	      MyInterface myInt = new MyClass1();
	      System.out.println("Value " +  myInt.method1());
	      // Calls the default method provided by interface itself
	      System.out.println("Greeting " + myInt.displayGreeting());
	  }
	}