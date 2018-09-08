
public class MyClass implements MyInterface {

	 @Override
	  public int method1() {
	      return 10;
	  }
	  public static void main(String[] args) {
	      MyInterface myInt = new MyClass();
	      System.out.println("Value " +  myInt.method1());
	      // Calls the default method provided by interface itself
	     System.out.println("Greeting " + myInt.displayGreeting());
	  }
}
