package template;

//AbstractRecipe.java
abstract class AbstractRecipe {
	public void cookFood() {
		putStoveOn();
		cutSpecificVegetable();
		cookSpecificVegetable();
		putStoveOff();
	}

	public void putStoveOn() {
		// code for putting stove on
	}

	public void putStoveOff() {
		// code for putting stove off
	}

	public abstract void cutSpecificVegetable();

	public abstract void cookSpecificVegetable();
}

//ConcreteRecipe1.java
class ConcreteRecipe1 extends AbstractRecipe {
	public void cutSpecificVegetable() {
		// specific implementation for cutting as per recipe 1
	}

	public void cookSpecificVegetable() {
		// specific implementation for cooking as per recipe 1
	}
}

//ConcreteRecipe2.java
public class ConcreteRecipe2 extends AbstractRecipe {
	public void cutSpecificVegetable() {
		// specific implementation for cutting as per recipe 2
	}

	public void cookSpecificVegetable() {
		// specific implementation for cooking as per recipe 2
	}
}
