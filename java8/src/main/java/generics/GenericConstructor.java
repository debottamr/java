package generics;

public class GenericConstructor<T> {

	  private T length;
	   private T width;
	   private T height;
	 
	   //Generic constructor
	   public GenericConstructor(T length, T width, T height)
	   {
	      super();
	      this.length = length;
	      this.width = width;
	      this.height = height;
	   }
}
