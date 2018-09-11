package trywithresource;

class Foo implements AutoCloseable {
    public void close() throws Exception {
        throw new Exception("1");
    }
    public void close1() throws Exception {
        throw new Exception("1");
    }
}

public class Try {
    public static void main(final String[] args) {
        try(Foo f = new Foo();
        		Foo f1 = new Foo();
        		) {
            System.out.println("No op!");
            throw new Exception("2");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}