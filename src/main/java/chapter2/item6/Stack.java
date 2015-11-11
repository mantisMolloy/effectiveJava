package chapter2.item6;

import java.util.EmptyStackException;

/**
 * Created by tmolloy on 11/11/2015.
 */
public class Stack<T> {
    private int size;
    private T[] elements;

    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        Object result = elements[--size];
        elements[size] = null; // Eliminate obsolete reference
        return result;
    }
}
