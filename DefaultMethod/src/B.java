
public interface B {
	// default method, providing default implementation
	default String displayGreeting() {
		return "Hello from Interface B";
	}
}