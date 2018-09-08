
public interface A {
   int method1();
   // default method, providing default implementation
   default String displayGreeting(){
      return "Hello from interface A";
   }
}
