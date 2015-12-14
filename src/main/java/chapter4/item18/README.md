# Item 18: Prefer interfaces to abstract classes

**NOTE:** Java 8 now allows default methods in interfaces

As for interfaces vs abstract classes, the two are still different in Java 8; you still can't have a constructor in an interface, for example. Hence, the two approaches are not "conceptually equivalent" per se. Abstract classes are more structured and can have a state associated with them, whereas interfaces can not. You should use whichever makes more sense in the context of your program, just like you would do in Java 7 and below.

---

* Existing classes can be easily retrofitted to implement a new interface.
* Interfaces are ideal for defining mixins.
* Interfaces allow the construction of nonhierarchical type frameworks.

While interfaces are not permitted to contain method implementations, using interfaces to define types does not prevent you from providing implementation assistance to programmers. You can combine the virtues of interfaces and abstract classes by providing an abstract skeletal implementation class to go with each nontrivial interface that you export. The interface still defines the type, but the skeletal implementation takes all of the work out of implementing it.

Public interfaces, therefore, must be designed carefully. Once an interface is released and widely implemented, it is almost impossible to change. You really must get it right the first time. If an interface contains a minor flaw, it will irritate you and its users forever. If an interface is severely deficient, it can doom an API. The best thing to do when releasing a new interface is to have as many programmers as possible implement the interface in as many ways as possible before the interface is frozen. This will allow you to discover flaws while you can still correct them.

To summarize, an interface is generally the best way to define a type that permits multiple implementations. An exception to this rule is the case where ease of evolution is deemed more important than flexibility and power. Under these circumstances, you should use an abstract class to define the type, but only if you understand and can accept the limitations. If you export a nontrivial interface, you should strongly consider providing a skeletal implementation to go with it. Finally, you should design all of your public interfaces with the utmost care and test them thoroughly by writing multiple implementations.
