# Item 26: Favor generic types

```
// Initial attempt to generify Stack - won't compile!
public class Stack<E> {
    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new E[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop() {
        if (size==0)
            throw new EmptyStackException();
        E result = elements[--size];
        elements[size] = null; // Eliminate obsolete reference
        return result;
    }
    ... // no changes in isEmpty or ensureCapacity
}
```

You can’t create an array of a non-reifiable type, such as E. This problem arises every time you write a generic type that is backed by an array. There are two ways to solve it.

```
Stack.java:8: generic array creation
        elements = new E[DEFAULT_INITIAL_CAPACITY];
```

The first solution directly circumvents the prohibition on generic array creation: create an array of Object and cast it to the generic array type. Now in place of an error, the compiler will emit a warning. This usage is legal, but it’s not (in general) typesafe:

```
Stack.java:8: warning: [unchecked] unchecked cast
found   : Object[], required: E[]
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
```

The compiler may not be able to prove that your program is typesafe, but you can. You must convince yourself that the unchecked cast will not compromise the type safety of the program. The array in question (elements) is stored in a private field and never returned to the client or passed to any other method. The only elements stored in the array are those passed to the push method, which are of type E, so the unchecked cast can do no harm.

Once you’ve proved that an unchecked cast is safe, suppress the warning in as narrow a scope as possible (Item 24). In this case, the constructor contains only the unchecked array creation, so it’s appropriate to suppress the warning in the entire constructor. With the addition of an annotation to do this, Stack compiles cleanly and you can use it without explicit casts or fear of a ClassCastException:

```
// The elements array will contain only E instances from push(E).
// This is sufficient to ensure type safety, but the runtime
// type of the array won't be E[]; it will always be Object[]!
@SuppressWarnings("unchecked")
public Stack() {
    elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
}
```

The second way to eliminate the generic array creation error in Stack is to change the type of the field elements from E[] to Object[]. If you do this, you’ll get a different error:

```
Stack.java:19: incompatible types
found   : Object, required: E
        E result = elements[--size];
```

You can change this error into a warning by casting the element retrieved from the array from Object to E:

```
Stack.java:19: warning: [unchecked] unchecked cast
found   : Object, required: E
        E result = (E) elements[--size];
```

In summary, generic types are safer and easier to use than types that require casts in client code. When you design new types, make sure that they can be used without such casts. This will often mean making the types generic. Generify your existing types as time permits. This will make life easier for new users of these types without breaking existing clients (Item 23).

