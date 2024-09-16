# Scala Assignment

1.	What is a trait ? when to use ?  example

```
Similar to interface in Java.
Used to share interface and fields between classes.
It cannot be instantiated and have no parameters.
But can contains methods implementation and fields.

Usage : -

Multiple Level of inheritance (not possible with class). (Diamond Problem).
Code Reuseability.
Modularity.
Abstract Methods. (Traits defines abstract methods which can be implemented by inheriting class).

Example - 

// Define a trait
trait Logger {
  def log(message: String): Unit = {
    println(s"Log message: $message")
  }
}

// Another trait with an abstract method
trait TimestampLogger extends Logger {
  abstract override def log(message: String): Unit = {
    super.log(s"${java.time.Instant.now}: $message")
  }
}

// A class using the trait
class ConsoleLogger extends Logger

// A class using multiple traits
class DetailedLogger extends Logger with TimestampLogger

object Main extends App {
  val logger1 = new ConsoleLogger
  logger1.log("This is a simple log message.")

  val logger2 = new DetailedLogger
  logger2.log("This is a detailed log message.")
}
```

2.	Difference between trait and sealed trait?

```
Similarity - Both are used to define the reusable components.
Differences - Related to inheritance and the visibility of the subclasses.

Traits - 
1. No restriction on extension from any files.
2. No guarantee that all subclasses are covered in pattern matching.
3. Usage it when we need flexibility and the ability to extend the trait across different files.

Example - 

// Define a regular trait
trait Animal {
  def sound: String
}

// Extend the trait in another file or anywhere in the project
class Dog extends Animal {
  def sound: String = "Bark"
}

class Cat extends Animal {
  def sound: String = "Meow"
}


Sealed Traits - 
1. It can only be extended in the same file where it is defined. This help us in knowing all the defined
subclasses at the compile time.
2. Compiler can check that all possible cases are covered in pattern matching, which makes code more robust and less prone to runtime errors.
3. Use when we want to have a closed hierarchy of subclasses, ensuring completeness in handling all possible cases.

Example - 

// Define a sealed trait
sealed trait Vehicle {
  def maxSpeed: Int
}

// Extend the sealed trait in the same file
class Car extends Vehicle {
  def maxSpeed: Int = 200
}

class Bike extends Vehicle {
  def maxSpeed: Int = 100
}

// Pattern matching on sealed trait ensures all cases are covered
def vehicleInfo(vehicle: Vehicle): String = vehicle match {
  case car: Car => s"Car with max speed: ${car.maxSpeed}"
  case bike: Bike => s"Bike with max speed: ${bike.maxSpeed}"
}

Conclusion - Use sealed trait when you want to limit the extensibility to a specific file and ensure exhaustive pattern matching, and use regular trait for more flexible, widely-extendable traits.
```

3.	What is an abstract class? 

```
1. Cannot be instantiated on its own.
2. Is meant to be extended by other classes. 
3. Contain both concrete (implemented) methods and abstract (unimplemented) methods. 
4. Serves as a blueprint for other classes, allowing you to define methods and fields that must be implemented by subclasses.

Usage - 

1. Inheritance: Provide a common base class with some default behavior that can be shared by multiple subclasses.
2. Partially Implemented Classes: When some methods are implemented but you want to leave others abstract for subclasses to implement.
3. Rich Interfaces: When you need to define a richer interface that includes fields, constructors, and implemented methods, which traits do not support.

Example - 

// Define an abstract class
abstract class Animal {
  // Concrete method
  def eat(): Unit = {
    println("This animal is eating.")
  }

  // Abstract method
  def sound(): String
}

// Concrete subclass of Animal
class Dog extends Animal {
  def sound(): String = "Bark"
}

// Another concrete subclass of Animal
class Cat extends Animal {
  def sound(): String = "Meow"
}

object Main extends App {
  val dog = new Dog
  val cat = new Cat

  dog.eat()         // Output: This animal is eating.
  println(dog.sound()) // Output: Bark

  cat.eat()         // Output: This animal is eating.
  println(cat.sound()) // Output: Meow
}
```

4.	What is the difference between  java interface and a scala trait?

```
Similarity - Both used for similar purpose.
Differences - 

Interface - 
1. Of Reference Type contains constants, method signatures, default and static methods and nested types.
2. Can have abstract and default methods (after Java 8).
3. Fields are implicit (public, static and final).
4. No constructors.
5. Class can have multiple interfaces which achieves multiple inheritance.

Example -

// Java Interface
interface Animal {
    void sound(); // Abstract method
    default void eat() { // Default method
        System.out.println("This animal is eating.");
    }
}

class Dog implements Animal {
    public void sound() {
        System.out.println("Bark");
    }
}

class Cat implements Animal {
    public void sound() {
        System.out.println("Meow");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat(); // Output: This animal is eating.
        dog.sound(); // Output: Bark

        Cat cat = new Cat();
        cat.eat(); // Output: This animal is eating.
        cat.sound(); // Output: Meow
    }
}


Traits - 
1. More powerful than interface and contains abstract and concrete methods and fields.
2. Contains abstract and concrete methods.
3. Can have abstract as well as concrete methods.
4. Cannot have constructor but can have initialization code which can be executed when traits are mixed with class.
5. Allows class to mix in multiple traits with "with" keywords.
6. Supports Linearization - a well-defined order in which traits are initialized when a class inherits multiple traits.

Example -

// Scala Trait
trait Animal {
  def sound(): String // Abstract method
  def eat(): Unit = { // Concrete method
    println("This animal is eating.")
  }
}

class Dog extends Animal {
  def sound(): String = "Bark"
}

class Cat extends Animal {
  def sound(): String = "Meow"
}

object Main extends App {
  val dog = new Dog
  dog.eat() // Output: This animal is eating.
  println(dog.sound()) // Output: Bark

  val cat = new Cat
  cat.eat() // Output: This animal is eating.
  println(cat.sound()) // Output: Meow
}


1. Method Implementations:
Java Interface: Only default methods (since Java 8) can have implementations.
Scala Trait: Can have both abstract and concrete methods.

2. Fields:
Java Interface: Fields are implicitly public, static, and final.
Scala Trait: Can have abstract or concrete fields.

3. Constructors:
Java Interface: Cannot have constructors.
Scala Trait: Cannot have constructor parameters but can have initialization code.

4. Multiple Inheritance:
Java Interface: Supports multiple inheritance through interfaces.
Scala Trait: Supports multiple inheritance directly and follows a linearization order for initialization.

5. Usage:
Java Interface: Typically used to define a contract for classes.
Scala Trait: Used for both defining a contract and providing reusable code with concrete implementations.
```

5.	What is a singleton

```
It can be instantiated only once. Cannot be instantiated multiple times.
Hold utility methods or as a replacement for static methods and fields.
When shares same name as a class it is "companion object" and access private members of the class.
No constructor and cannot take parameters.

Example - 

// Singleton object
object MathUtils {
  def add(x: Int, y: Int): Int = x + y
  def multiply(x: Int, y: Int): Int = x * y
}

// Companion class and object
class Circle(val radius: Double)

object Circle {
  val Pi = 3.14159

  def calculateArea(circle: Circle): Double = {
    Pi * circle.radius * circle.radius
  }
}

object Main extends App {
  // Using the singleton object
  println(MathUtils.add(3, 4))       // Output: 7
  println(MathUtils.multiply(3, 4))  // Output: 12

  // Using the companion object
  val circle = new Circle(5.0)
  println(Circle.calculateArea(circle)) // Output: 78.53975
}
```

6.	What is a higher order function?

```
Take one or more functions as input, return a function as output or both

Example -

def transformAndApply(x: Int, y: Int, transformer: Int => Int, operation: (Int, Int) => Int): Int = {
  operation(transformer(x), transformer(y))
}

def square(a: Int): Int = a * a
def add(a: Int, b: Int): Int = a + b

object Main extends App {
  println(transformAndApply(3, 4, square, add))  // Output: 25 (9 + 16)
}

Enables composition and creation of even more complex functions from the simpler one.
Provide better abstraction and one of important feature for functional programming.
```

7.	What is a closure function

```
1. Lexical Scope: Closures capture the variables from the lexical scope in which they are defined.
2. Persistent State: They maintain access to these variables even when the context in which they were created has exited.
3. Mutable State: If the captured variables are mutable, changes to them are reflected in the closure's behavior.

Example -

def makeAdder(base: Int): Int => Int = {
  (x: Int) => x + base
}

val addFive = makeAdder(5)
val addTen = makeAdder(10)

println(addFive(3))  // Output: 8
println(addTen(3))   // Output: 13
```

8.	What is a companion object? What are the advantages ? example

```
Shares same name as class and defined in the same sources file.
Access the private member of the companion class and generally used to provide factory methods for creating instances of the companion class.
Provides a way to define methods and values that are similar to static members.

Advantages -

1. Better Encapsulation
2. Factory Methods
3. Utility Function
4. Access Control
5. Organized Code (Abstraction)

Example - 

// Companion class
class Circle(val radius: Double) {
  // Instance method
  def area: Double = Circle.Pi * radius * radius
}

// Companion object
object Circle {
  val Pi = 3.14159

  // Factory method
  def apply(radius: Double): Circle = new Circle(radius)
}

object Main extends App {
  // Using the factory method in the companion object
  val circle1 = Circle(5.0)
  println(s"Area of circle1: ${circle1.area}") // Output: Area of circle1: 78.53975

  // Directly accessing a value in the companion object
  println(s"Value of Pi: ${Circle.Pi}") // Output: Value of Pi: 3.14159
}

```

9.	Nil vs Null vs null vs Nothing vs None vs Unit 

```
1. Nil - Case object and used to represent the empty list.


val emptyList = Nil
println(emptyList) // Output: List()

2. Null - Trait, and is used to represent null value. It is subtype of all reference types.
It denotes a reference that doesn't point to any object.

val nullValue: String = null
println(nullValue) // Output: null

3. null - literal and represents null references. Denote the absence of a value for a reference type.

val nullValue: String = null
println(nullValue) // Output: null

4. Nothing - bottom type, and is subtype of every other type and is used to signal abnormal termination (such as throwing an exception) or as the type of an empty collection.
It is used when defining a method that never returns normally.

def fail(msg: String): Nothing = throw new RuntimeException(msg)

5. None - Case object, Represents the absence of a value in an Option type.
It is used when an Option type does not contain a value.

val noValue: Option[String] = None
println(noValue) // Output: None

6. Unit - Value Type, It correspond to no value. It is used as the return type of methods that perform side effects but do not return a value.

def printMessage(msg: String): Unit = {
  println(msg)
}
println(printMessage("Hello, Scala!")) // Output: Hello, Scala!
                                      //         ()
```

10.	What is pure function?

```
Function which has two properties - Deterministic and No side effects.
1. Deterministic: Given the same inputs, a pure function will always return the same output.
2. No Side Effects: It does not cause any observable side effects outside of the function. This means it doesn't modify any external state (such as global variables, files, or databases) and does not rely on any external state that may change.

Example -

def add(x: Int, y: Int): Int = x + y

Benefits - Predictability, Testability, Concurrency and Composability.
```

11.	What is SBT and how have you used it? 

```
SBT (Simple Build Tool) is a popular build tool for Scala and Java projects. 
It is designed to handle the build, compilation, testing, and packaging of applications efficiently.

Features : -

Incremental Compilation, Interactive Shell, Dependency Management, Configuration and Plugin System.
```

12.	What is currying?

```
Currying is a technique used to transform a function that takes multiple arguments into a sequence of functions, each taking a single argument. 
This allows for partial application of functions, where you can fix some arguments and defer the remaining ones for later.
It is a function that is decomposed into a series of functions, each of which takes a single argument.

Example -

// Curried function
def add(x: Int)(y: Int): Int = x + y

// Using the curried function
val add5 = add(5)  // Partially applied function
val result = add5(3)  // Complete the application

println(result)  // Output: 8


In contrast to curried functions, non-curried functions take all arguments in one call.
It enables the creation of functions that can be applied partially, improving code modularity and reusability.
```

13.	Difference between currying and higher-order functions

```
1. Purpose:
Currying: Focuses on transforming a function with multiple arguments into a series of functions with single arguments, enabling partial application.
Higher-Order Functions: Focus on manipulating functions as first-class citizens, either by accepting functions as arguments, returning functions, or both.

2. Application:
Currying: Is about function transformation and partial application.
Higher-Order Functions: Are about abstracting and generalizing operations on functions.

3. Usage:
Currying: Often used to create specialized functions and simplify function calls with multiple arguments.
Higher-Order Functions: Used to apply different operations, compose functions, and enhance code reusability and flexibility.

Currying is a technique for transforming a function that takes multiple arguments into a series of functions that each take a single argument, facilitating partial application.
Higher-Order Functions are functions that operate on other functions, either by taking them as arguments or returning them as results, allowing for more abstract and reusable code.
```

14.	Difference between var and val?

```
val - Immutable reference (It cannot be changed or reassigned). and must be declared.
Used for defining constants.

val x: Int = 10
// x = 20 // This will cause a compilation error because x is immutable

var - Mutable references, which can be change. values of var can be change during the lifetime of the program.
Used for defining a variable.

var y: Int = 10
y = 20 // This is allowed because y is mutable

```

15.	What is case class?

```
Special type of class optimized for pattern matching and immutable data structure.
It automatically provides useful methods and supports pattern matching, making it ideal for defining data structures that are used in functional programming and scenarios where immutability and ease of use are important.

// Define a case class
case class Person(name: String, age: Int)

object Main extends App {
  // Create instances of the case class
  val person1 = Person("Alice", 30)
  val person2 = Person("Bob", 25)

  // Print instances
  println(person1) // Output: Person(Alice,30)
  println(person2) // Output: Person(Bob,25)

  // Use pattern matching
  person1 match {
    case Person(name, age) => println(s"Name: $name, Age: $age")
  }

  // Copy an instance with modified fields
  val person3 = person1.copy(age = 31)
  println(person3) // Output: Person(Alice,31)

  // Equality check
  println(person1 == person2) // Output: false
  println(person1 == Person("Alice", 30)) // Output: true
}
```

16.	Why/when to use case class? Example

```
Why - Immutable, Pattern Matching, built in functions like toString, Equals, hashCode, etc
When - Data Transfer objects, Pattern Matching, Functional Programming, Configurations and Settings.

// Define a case class for a Person
case class Person(name: String, age: Int)

// Define a case class for an Address
case class Address(street: String, city: String, zip: String)

// Define a case class for a UserProfile
case class UserProfile(person: Person, address: Address)

object Main extends App {
  // Create instances of the case classes
  val person = Person("Alice", 30)
  val address = Address("123 Main St", "Springfield", "12345")
  val profile = UserProfile(person, address)

  // Print the profile
  println(profile) // Output: UserProfile(Person(Alice,30),Address(123 Main St,Springfield,12345))

  // Pattern matching
  profile match {
    case UserProfile(Person(name, age), Address(street, city, zip)) =>
      println(s"Name: $name, Age: $age, Street: $street, City: $city, Zip: $zip")
  }

  // Copying an instance with modification
  val updatedProfile = profile.copy(person = person.copy(age = 31))
  println(updatedProfile) // Output: UserProfile(Person(Alice,31),Address(123 Main St,Springfield,12345))

  // Equality check
  println(profile == updatedProfile) // Output: false
}

```

17.	Difference between case class and normal class?

```
Case Class -
1. Immutable
2. Built in methods 
3. Support pattern Matching
4. apply to create new instance.

Normal Class -
1. Mutable as well as immutable.
2. No built in methods.
3. No Support for pattern matching.
4. new is used to create new instance.
```

18.	Scala type hierarchy?

```
Scala’s type hierarchy provides a structured and flexible framework for categorizing types. 

Any
	AnyVal
		Boolean, Byte, Char, Double, Float, Int, Long, Short, Unit.
	AnyRef 
		String, List, Map, Custom class or traits.
Nothing
Null
Unit

Any: The top-most type in Scala, encompassing all types.
AnyVal: Represents value types, including primitive types and Unit.
AnyRef: Represents reference types and is equivalent to java.lang.Object in Java.
Nothing: A type that represents no values and is a subtype of all types.
Null: A type that represents null references and is a subtype of all reference types.
Unit: Represents a method that does not return a meaningful value.
```

19.	What are partially applied functions?

```
functions that are created by providing some but not all of the arguments to a function.
When you provide a subset of the arguments to a function and omit the remaining arguments, Scala creates a new function with the fixed arguments. This new function is called a partially applied function.

// Define a general function
def add(x: Int, y: Int): Int = x + y

// Create a partially applied function
val addFive = add(5, _)

// Use the partially applied function
println(addFive(10))  // Output: 15

Allow you to create new functions by fixing some of the arguments of an existing function.
Useful for creating specialized versions of functions and reducing code duplication.
Use the function's name with some arguments provided, and underscore (_) to indicate omitted arguments.
```

20.	What is tail recursion.

```
Specific type of recursion where the recursive call is the last operation in the function. This property allows the compiler to optimize the recursive calls, transforming them into a loop-like structure to avoid stack overflow errors and improve performance. This optimization is known as tail call optimization (TCO).

// Tail-recursive factorial function
def factorial(n: Int): Int = {
  // Helper function with an accumulator
  @annotation.tailrec
  def factorialHelper(x: Int, accumulator: Int): Int = {
    if (x <= 1) accumulator
    else factorialHelper(x - 1, x * accumulator)
  }

  factorialHelper(n, 1)
}
```