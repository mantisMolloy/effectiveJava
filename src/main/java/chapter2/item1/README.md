# Static Factory Method

In summary, static factory methods and public constructors both have their uses, and it pays to understand their relative merits. Often static factories are preferable, so avoid the reflex to provide public constructors without first considering static factories.

## Advantages
* unlike constructors, they have names
* unlike constructors, they are not required to create a new object each time they’re invoked
* they can return an object of any subtype of their return type


##Disadvantages
* classes without public or protected constructors cannot be subclassed
* they are not readily distinguishable from other static methods
