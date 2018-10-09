package generics;

import java.util.LinkedList;
import java.util.List;

/**
 * Let's first define several classes with the following hierarchy:
 * 
 *                           Idea
 *                             |
 *                           Form
 *                             |
 *                           Shape
 *                             |
 *                        ------------
 *                        |          |
 *                     Circle    Rectangle
 */

class Idea{}

class Form extends Idea{}

class Shape extends Form{}

class Circle extends Shape{}

class Rectangle extends Shape{}

/*********************************************************/

public class DoStuff{
    
    //A list of an unknown type. But we do know it extends or implements Shape (i.e. Shape is it's supertype).
    private List<? extends Shape> list1;
    
    //A list of an unknown type. But we do know it's a supertyoe if Shape (i.e. Shape extends or implements it).
    private List<? super Shape> list2;
    
    DoStuff(List<? extends Shape> list1, List<? super Shape> list2){
        
        this.list1 = list1;
        this.list2 = list2;
        
        Idea i = new Idea();
        Form f = new Form();
        Shape s = new Shape();
        Circle c = new Circle();
        Rectangle r = new Rectangle();
        
        //*** list1 (with the 'extends' keyword) ***//
        //******************************************//
        
        //list1 can hold any type of object that extends Shape (or Shape itself).
        //Let's say it's a list of Rectangles, but we don't know that, so if we would insert
        //a Circle, we would get an error.
        //Because we don't know the type of objects in the list (we only know the upper bound),
        //we can't risk inserting anything that would not match.
        //The only thing we are allowed to insert, is the 'null' object, since it's common to
        //all types.
        this.list1.add(null);
        
        //We can, however get objects from the list.
        //We know that the objects in the list extend from Shape. It might be Rectangles, Circles
        //or Shapes. Either way, the common ancestor is Shape.
        //So, we can safely do:
        s = this.list1.get(0); //since we are guranteed that all objects inherit from Shape.
        f = this.list1.get(0); //since Shape inherits from Form.
        i = this.list1.get(0); //since Form inherits from Idea.
        //These would be wrong:
        //c = list1.get(0); - since we can't be certain the objects in the list are circles.
        //r = list1.get(0); - same reason.
        //However, if we do somehow know the type of the objects, we could do down casting:
        c = (Circle) this.list1.get(0);
        r = (Rectangle) this.list1.get(0);
        
        //*** list2 (with the 'super' keyword) ***//
        //****************************************//
        
        //list2 can hold any type of object which Shape inherits or implements.
        //We know that whatever type of objects list2 holds, it's a supertype of Shape.
        //Suppose list2 is actually a list of Ideas (which is a supertype of Shape).
        //In that case, we should be able to insert into the list: Ideas, Forms, Shapes,
        //Circles and Rectangles.
        //But now suppose that list2 is actually a list of Forms (which is also a supertype of Shape).
        //In that case we should only be able to insert: Forms, Shapes, Circles and Rectangles...
        // - but NOT ideas.
        //The compilter doesn't know what is actually in the list. All it knows is that it's a list
        //of some type which is a supertype of Shape.
        //We could try to insert an Idea or a Form into this list, but we can't be certain that the type
        //will match. For all we know, it might be a list of Shapes!
        //The only types we can be certain that will match are: Shape, Circle and Rectangel (as subtypes
        //of Shape).
        //So these are legal:
        this.list2.add(s);
        this.list2.add(c);
        this.list2.add(r);
        //But these are NOT legal:
        //list2.add(i); - we try to insert an Idea, but it might not match the type...
        //list2.add(f); - same reason.
        
        //When we try to get objects from the list, all we know is that the type is some kind of
        //supertype of Shape. As far is we are concerned, it can be 'Object' (which is the supertype
        //of all types).
        //For this reason, we always have to downcast when we get an object from the list:
        i = (Idea) this.list2.get(0);
        f = (Form) this.list2.get(0);
        s = (Shape) this.list2.get(0);
        c = (Circle) this.list2.get(0);
        r = (Rectangle) this.list2.get(0);
        
    }
    
    public static void main(String[] args){
        
        //A list of a *concrete* type - Circle:
        List<Circle> list1 = new LinkedList<Circle>();
        
        //A list of a *concrete* type - Idea:
        List<Idea> list2 = new LinkedList<Idea>();
        
        
        DoStuff ds = new DoStuff(list1,list2);
        
    }
    
}