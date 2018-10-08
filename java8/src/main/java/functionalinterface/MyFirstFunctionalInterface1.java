package functionalinterface;

@FunctionalInterface
public interface MyFirstFunctionalInterface1 {
	public void firstWork();

	default void doSomeMoreWork1() {
		//Method body
	}

	default void doSomeMoreWork2() {
		//Method body
	}
}