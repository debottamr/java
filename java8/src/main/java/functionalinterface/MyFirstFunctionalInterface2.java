package functionalinterface;

@FunctionalInterface
public interface MyFirstFunctionalInterface2 {
	public void firstWork();

	@Override
	public String toString(); // Overridden from Object class

	@Override
	public boolean equals(Object obj); // Overridden from Object class
	
	@Override
	public int hashCode(); // Overridden from Object class
}