# Item 22: Favor static member classes over nonstatic

If you declare a member class that does not require access to an enclosing instance, always put the static modifier in its declaration, making it a static rather than a nonstatic member class. If you omit this modifier, each instance will have an extraneous reference to its enclosing instance. Storing this reference costs time and space, and can result in the enclosing instance being retained when it would otherwise be eligible for garbage collection (Item 6). And should you ever need to allocate an instance without an enclosing instance, you’ll be unable to do so, as nonstatic member class instances are required to have an enclosing instance.

