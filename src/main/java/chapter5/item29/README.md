Item 29: Consider typesafe heterogeneous containers

```
// Typesafe heterogeneous container pattern - API
public class Favorites<T> {
    public void putFavorite(Class<T> type, T instance);
    public T getFavorite(Class<T> type);
}
```

```
// Typesafe heterogeneous container pattern - client
public static void main(String[] args) {
    Favorites f = new Favorites();
    f.putFavorite(String.class, "Java");
    f.putFavorite(Integer.class, 0xcafebabe);
    f.putFavorite(Class.class, Favorites.class);
    String favoriteString = f.getFavorite(String.class);
    int favoriteInteger = f.getFavorite(Integer.class);
    Class<?> favoriteClass = f.getFavorite(Class.class);
    System.out.printf("%s %x %s%n", favoriteString,
        favoriteInteger, favoriteClass.getName());
}
```

```
// Typesafe heterogeneous container pattern - implementation
public class Favorites<T> {
    private Map<Class<?>, Object> favorites =
        new HashMap<Class<?>, Object>();

    public void putFavorite(Class<T> type, T instance) {
        if (type == null)
            throw new NullPointerException("Type is null");
        favorites.put(type, instance);
    }

    public T getFavorite(Class<T> type) {
        return type.cast(favorites.get(type));
    }
}
```

In summary, the normal use of generics, exemplified by the collections APIs, restricts you to a fixed number of type parameters per container. You can get around this restriction by placing the type parameter on the key rather than the container. You can use Class objects as keys for such typesafe heterogeneous containers. A Class object used in this fashion is called a type token. You can also use a custom key type. For example, you could have a DatabaseRow type representing a database row (the container), and a generic type Column<T> as its key.
