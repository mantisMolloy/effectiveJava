package chapter2.item7;

/**
 * Created by tmolloy on 11/11/2015.
 */
public class Foo {

    public void bar() {
        // try-finally block guarantees execution of termination method
        Foo foo = new Foo();
        try {
            // Do what must be done with foo

        } finally {
            foo.terminate();  // Explicit termination method
        }
    }
}
