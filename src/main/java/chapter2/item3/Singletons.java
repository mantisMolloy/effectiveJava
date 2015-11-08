package chapter2.item3;

/**
 * Created by tmolloy on 08/11/2015.
 */
public class Singletons {

    /**
     * Singleton pattern example using Java Enum
     */
    public enum EasySingleton {
        INSTANCE;

        private void doSomething(){
            System.out.println("hello");
        }
    }

    public static void main (String... args){
        EasySingleton.INSTANCE.doSomething();
    }
}
