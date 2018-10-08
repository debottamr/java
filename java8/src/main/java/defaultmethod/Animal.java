package defaultmethod;

import java.util.ArrayList;
import java.util.List;

public class Animal implements Moveable, Walkable{
    public static void main(String[] args){
        Animal tiger = new Animal();
        tiger.move();
        List<Animal> list = new ArrayList();
        list.add(new Animal());
        list.add(new Animal());
        list.add(new Animal());
         
        //Iterator code reduced to one line
        list.forEach(Moveable::move);
        list.forEach((Moveable p) -> p.move());
    }

	@Override
	public void move() {
		Moveable.super.move();
	}
}
 