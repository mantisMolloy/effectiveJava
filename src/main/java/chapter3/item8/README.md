# Obey the general contract when overriding equals

1. Use the == operator to check if the argument is a reference to this object. If so, return true. This is just a performance optimization, but one that is worth doing if the comparison is potentially expensive.

2. Use the instanceof operator to check if the argument has the correct type. If not, return false. Typically, the correct type is the class in which the method occurs. Occasionally, it is some interface implemented by this class. Use an interface if the class implements an interface that refines the equals contract to permit comparisons across classes that implement the interface. Collection interfaces such as Set, List, Map, and Map.Entry have this property.

3. Cast the argument to the correct type. Because this cast was preceded by an instanceof test, it is guaranteed to succeed.

4. For each “significant” field in the class, check if that field of the argument matches the corresponding field of this object. If all these tests succeed, return true; otherwise, return false. If the type in step 2 is an interface, you must access the argument’s fields via interface methods; if the type is a class, you may be able to access the fields directly, depending on their accessibility.

For primitive fields whose type is not float or double, use the == operator for comparisons; for object reference fields, invoke the equals method recursively; for float fields, use the Float.compare method; and for double fields, use Double.compare. The special treatment of float and double fields is made necessary by the existence of Float.NaN, -0.0f and the analogous double constants; see the Float.equals documentation for details. For array fields, apply these guidelines to each element. If every element in an array field is significant, you can use one of the Arrays.equals methods added in release 1.5.

Some object reference fields may legitimately contain null. To avoid the possibility of a NullPointerException, use this idiom to compare such fields:

(field == null ? o.field == null : field.equals(o.field))

This alternative may be faster if field and o.field are often identical:

(field == o.field || (field != null && field.equals(o.field)))

5. When you are finished writing your equals method, ask yourself three questions: Is it symmetric? Is it transitive? Is it consistent? And don’t just ask yourself; write unit tests to check that these properties hold! If they don’t, figure out why not, and modify the equals method accordingly. Of course your equals method also has to satisfy the other two properties (reflexivity and “non-nullity”), but these two usually take care of themselves.

Always override hashCode when you override equals (Item 9).