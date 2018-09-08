
public class MyClass3 implements MyInterface{
	  // provides implementation for the non-default method
	  // of the interface
	  @Override
	  public int method1() {
	     return 10;
	  }
	  //Overriding the default method of MyInterface
	  public String displayGreeting(){
	     return MyInterface.super.displayGreeting();
	  }
	 
	  public  static int getDefaultAmount(){
	     return 5;
	  }
	 
	  public static void main(String[] args) {
	     MyInterface myInt = new MyClass3();
	  
	     int num = MyInterface.getDefaultAmount();
	     System.out.println("num - Interface " + num);
	     System.out.println("num - Class " + MyClass3.getDefaultAmount());
	  
	  } 
	}
