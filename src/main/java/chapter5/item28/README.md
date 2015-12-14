# Item 28: Use bounded wildcards to increase API flexibility

Consider following method

```
// pushAll method without wildcard type - deficient!
public void pushAll(Iterable<E> src) {
    for (E e : src)
        push(e);
}
```

Cannot handle subtypes of E. Better solution:

```
// Wildcard type for parameter that serves as an E producer
public void pushAll(Iterable<? extends E> src) {
    for (E e : src)
        push(e);
}
```

Now consider this method

```
// popAll method without wildcard type - deficient!
public void popAll(Collection<E> dst) {
    while (!isEmpty())
        dst.add(pop());
}
```

Cannot handle super types of E. Better solution:

```
// Wildcard type for parameter that serves as an E consumer
public void popAll(Collection<? super E> dst) {
    while (!isEmpty())
        dst.add(pop());
}
```

The lesson is clear. For maximum flexibility, use wildcard types on input parameters that represent producers or consumers. If an input parameter is both a producer and a consumer, then wildcard types will do you no good: you need an exact type match, which is what you get without any wildcards.

Here is a mnemonic to help you remember which wildcard type to use:

PECS stands for producer-extends, consumer-super.

A more advanced example would be:

```
public static <T extends Comparable<? super T>> T max(
        List<? extends T> list)
```

To get the revised declaration from the original one, we apply the PECS transformation twice. The straightforward application is to the parameter list. It produces T instances, so we change the type from List<T> to List<? extends T>. The tricky application is to the type parameter T. This is the first time we’ve seen a wildcard applied to a type parameter. T was originally specified to extend Comparable<T>, but a comparable of T consumes T instances (and produces integers indicating order relations). Therefore the parameterized type Comparable<T> is replaced by the bounded wildcard type Comparable<? super T>. Comparables are always consumers, so you should always use Comparable<? super T> in preference to Comparable<T>. The same is true of comparators, so you should always use Comparator<? super T> in preference to Comparator<T>.
