# Item 24: Eliminate unchecked warnings

Some warnings will be much more difficult to eliminate. This chapter is filled with examples of such warnings. When you get warnings that require some thought, persevere! Eliminate every unchecked warning that you can. If you eliminate all warnings, you are assured that your code is typesafe, which is a very good thing. It means that you won’t get a ClassCastException at runtime, and it increases your confidence that your program is behaving as you intended.

```
// Adding local variable to reduce scope of @SuppressWarnings
public <T> T[] toArray(T[] a) {
    if (a.length < size) {
        // This cast is correct because the array we're creating
        // is of the same type as the one passed in, which is T[].
        @SuppressWarnings("unchecked") T[] result =
            (T[]) Arrays.copyOf(elements, size, a.getClass());
        return result;
    }
    System.arraycopy(elements, 0, a, 0, size);
    if (a.length > size)
        a[size] = null;
    return a;
}
```

Every time you use an @SuppressWarnings("unchecked") annotation, add a comment saying why it’s safe to do so. This will help others understand the code, and more importantly, it will decrease the odds that someone will modify the code so as to make the computation unsafe. If you find it hard to write such a comment, keep thinking. You may end up figuring out that the unchecked operation isn’t safe after all.

