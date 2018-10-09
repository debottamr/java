package generics;

import java.util.ArrayList;
import java.util.List;

public class CollectionGenericSuper {

	private void man() {
		// List of apples
		List<Apple1> apples = new ArrayList<Apple1>();
		apples.add(new Apple1());

		// We can assign a list of apples to a basket of apples
		List<? super Apple1> basket = apples;

		basket.add(new Apple1()); // Successful
		basket.add(new AsianApple1()); // Successful
		//basket.add(new Fruit1()); // Compile time error

	}
}

class Fruit1 {
	@Override
	public String toString() {
		return "I am a Fruit !!";
	}
}

class Apple1 extends Fruit1 {
	@Override
	public String toString() {
		return "I am an Apple !!";
	}
}

class AsianApple1 extends Apple1 {
	@Override
	public String toString() {
		return "I am an AsianApple !!";
	}
}

/*
Use the <? extends T> wildcard if you need to retrieve object of type T from a collection.
Use the <? super T> wildcard if you need to put objects of type T in a collection.
If you need to satisfy both things, well, don’t use any wildcard. As simple as it is.
In short, remember the term PECS. Producer extends Consumer super. Really easy to remember.
*/