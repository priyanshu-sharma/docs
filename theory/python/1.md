# Python

1.	What is init keyword ?

```
Used to define the constructor for a class.
Allow us to initialize the attributes of the objects.
Allow us to perform any initial setup tasks befor using any member functions.

Example - 

class Person:
    def __init__(self, name, age):
        self.name = name
        self.age = age

    def display(self):
        print(f"Name: {self.name}, Age: {self.age}")

# Creating an instance of the Person class
person1 = Person("Alice", 30)

# Accessing attributes
print(person1.name)  # Output: Alice
print(person1.age)   # Output: 30

# Calling a method
person1.display()    # Output: Name: Alice, Age: 30

```

2.	What is self keyword ?

```
Used within the class methods to refer to the instance of the class on which method is called.
Allow access to the attributes and other methods of class in OOP.
It is the first parameter of the method in the class.
This parameter does not need to be explicitly passed when calling the method on an object.
It is a reference to the current instance of the class and is used to access variables that belong to the class.

class Dog:
    def __init__(self, name, breed):
        self.name = name
        self.breed = breed

    def bark(self):
        print(f"{self.name} says woof!")

# Creating an instance of the Dog class
my_dog = Dog("Buddy", "Golden Retriever")

# Accessing attributes
print(my_dog.name)  # Output: Buddy
print(my_dog.breed) # Output: Golden Retriever

# Calling a method
my_dog.bark()       # Output: Buddy says woof!
```

3.	What is lambda functon?

```
It is a small anonymous function defined using the lambda keyword. 
Can have any number of arguments, but it can only have one expression. 
Expression is evaluated and returned. 
They are often used for short, throwaway functions that are not intended to be reused elsewhere in the code.

lambda arguments: expression

# Regular function
def add(x, y):
    return x + y

# Lambda function
add_lambda = lambda x, y: x + y

# Both functions give the same result
print(add(2, 3))        # Output: 5
print(add_lambda(2, 3)) # Output: 5
```

4.	Difference between lambda and normal function?

```
Both serves similar purpose but in different ways.

Lambda
1. Anonymous, Not bound to any name and are explicity assigned to a variable.
2. Single line expression.
3. Consice and short.
4. Limited functionality (No docstring, annotation like normal functions).
5. uses lambda keyword to define it.

Normal
1. Have name and uses def keywords for function defination.
2. Multiple statement.
3. Can have docstring, annotations, decorators, and other features.
4. Can be used again and again.
5. Carries more complex logics.
```

5.	What are generators? When to use ? share one example

```
Type of iterable like list, tuples, etc.
But doesn't store all values in memory instread generate on fly.
Memory efficient for larger dataset and infinite sequences.
Performs lazy evaluation.
uses Yield to produce a series of values. next to get the next value in the sequence.

Usage -

Large Dataset which cannot fit in memory.
Infinite sequence.
To improve the performance of program by yielding one value at a time.

def fibonacci():
    a, b = 0, 1
    while True:
        yield a
        a, b = b, a + b

# Create a generator
fib_gen = fibonacci()

# Generate the first 10 Fibonacci numbers
for _ in range(10):
    print(next(fib_gen))
```

6.	Python is compiled or interpreted language ? what does it mean?

```
interpreted language

The code is executed line-by-line by an interpreter, which reads the source code, translates it to intermediate code or directly to machine instructions, and executes it on the fly.

It first compiles the source code (.py file) into an intermediate form called bytecode (.pyc file). This bytecode is a low-level, platform-independent representation of your source code.

It is then executed by the Python Virtual Machine (PVM). The PVM is an interpreter that reads the bytecode, translates it into machine instructions, and executes it.
```

7.	What is the difference between list and tuples in Python?

```
Both are sequence data types.

List -
1. Mutable.
2. [] are used during creation.
3. Slower as compared to Tuple.
4. Used to store collection which can change during the lifetime of the program.
5. Methods like append, pop, reverse.
6. Hashable (can be used as key in dict)

Tuple - 
1. Immutable.
2. () are used during creation.
3. Faster.
4. Used to store collection which cann't change during the lifetime of the program.
5. Methods like count, index
6. NonHashable (can't be used as key in dict)

# List methods
my_list = [1, 2, 3]
my_list.append(4)    # [1, 2, 3, 4]
my_list.remove(2)    # [1, 3, 4]

# Tuple methods
my_tuple = (1, 2, 3)
print(my_tuple.count(2))  # 1
print(my_tuple.index(3))  # 2
```

8.	What is the difference between list and set in Python?

```
List
1. Order collection can be access by index.
2. can have duplicates.
3. slower for membership test (in operation)
4. Operation like append, reverse, etc.

Set
1. Unordered collection, can't be access by index.
2. No duplicates.
3. Faster for membership text (due to underlying hash table implementation).
4. Operations like union, intersection, etc.

# List methods
my_list = [1, 2, 3]
my_list.append(4)    # [1, 2, 3, 4]
my_list.remove(2)    # [1, 3, 4]

# Set methods
my_set = {1, 2, 3}
another_set = {3, 4, 5}
union_set = my_set.union(another_set)               # {1, 2, 3, 4, 5}
intersection_set = my_set.intersection(another_set) # {3}
```

9.	When to use dictionary?

```
It is used when you need to associate unique keys with values. 

Usage -
Fast Lookups
Associative Arrays - When you need a data structure to model relationships between items.
Counting occurence.
Grouping Data based on key.
Configuration and Settings.
Avoid multiple if-else conditions.
```

10.	What are decorators? When to use ? share one example

```
1. Allow us to modify or extend the behavior of functions or methods without changing their actual code. 
2. Wrap other functions or methods, enabling you to add functionality before or after the wrapped function executes.
3. Function that takes another function as an argument and returns a new function that typically extends or modifies the behavior of the original function. 
4. Allows us to apply the same functionality to multiple functions in a clean and reusable manner.

@decorator_name
def some_function():
    # Function body

Usage -
Code reuseability.
Separation of concerns.
enhances readability.
add functionality like caching, memoization, or validation without modifying the core logic of the functions.

import time

def log_decorator(func):
    def wrapper(*args, **kwargs):
        print(f"Executing {func.__name__} with arguments {args} and keyword arguments {kwargs}")
        start_time = time.time()
        result = func(*args, **kwargs)
        end_time = time.time()
        print(f"{func.__name__} executed in {end_time - start_time:.4f} seconds")
        return result
    return wrapper

@log_decorator
def example_function(a, b):
    time.sleep(1)  # Simulating a time-consuming operation
    return a + b

# Calling the decorated function
result = example_function(5, 10)
print(f"Result: {result}")
```

11.	What are Iterators?

```
Objects that allow you to iterate over a sequence of values. 
Provide a way to access the elements of a collection (such as lists, tuples, or dictionaries) one at a time without exposing the underlying details of the collection.

Iterator: An object that implements two methods defined by the iterator protocol:
__iter__(): This method returns the iterator object itself. It is required to implement the iterator protocol.
__next__(): This method returns the next item in the sequence. When there are no more items, it raises the StopIteration exception to signal the end of the iteration.

my_list = [1, 2, 3]
my_iter = iter(my_list)  # Create an iterator from the list

print(next(my_iter))  # Output: 1
print(next(my_iter))  # Output: 2
print(next(my_iter))  # Output: 3

Memory Efficient, 
Lazy Evaluation
Abstraction.
Generators are a convenient way to create iterators using functions with the yield keyword. They simplify iterator creation and manage the state internally.

def my_generator(start, end):
    while start <= end:
        yield start
        start += 1

# Example usage
for value in my_generator(1, 3):
    print(value)
# Output:
# 1
# 2
# 3
```

12.	What is slicing?
```
It is a technique used to extract a portion of a sequence, such as a list, tuple, or string, by specifying a start, stop, and step index. 
It allows you to access a subsequence or sublist without modifying the original sequence.

my_list = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
print(my_list[2:5])   # Output: [2, 3, 4]

Omitting parameters can be used to get slices from the start or to the end of the sequence.
Negative indices and steps can be used for more complex slicing operations, such as reversing the sequenc
```

13.	What is mutable and immutable?

```
Mutable - Mutable objects can be changed after they are created. This means you can modify their content, such as adding, removing, or changing elements.
Example - List, Dict, Set

Immutable - Immutable objects cannot be changed after they are created. Any operation that tries to modify the object will instead create a new object.
Example - Tuple, String, Number.
```

14.	Python is single thread or multithread?

```
Default behaviour is single thread.
But it does support Multithread influenced by Global Interpreter Lock (GIL).
But there are modules to support multithreading, threading, multiprocessing, asyncio.
```

15.	What is GIL

```
It is a mutex that protects access to Python objects, preventing multiple native threads from executing Python bytecodes at once in the CPython interpreter. The GIL is a feature of the standard implementation of Python, CPython, and it affects how multi-threading is handled in Python programs.

Ensures that only one thread executes Python bytecode at a time.
More impact on CPU bound tasks as compared to I/O bound tasks.
Simplify the implementation of the Python interpreter and ensure thread safety for Python objects. It allows CPython to avoid complex locking mechanisms and simplifies memory management.
```

16.	What you don’t like about python?

```
GIL
Interpretation overhead.
Dynamic typing
Memory Usage.
```

17.	What is list Comprehension?

```
1. It is a concise and expressive way to create lists in Python. 
2. It allows you to generate a new list by applying an expression to each item in an existing iterable (like a list, tuple, or range) and optionally filtering the items based on a condition.

Syntax -
[expression for item in iterable if condition]

numbers = [1, 2, 3, 4, 5]
even_squares = [x**2 for x in numbers if x % 2 == 0]
print(even_squares)  # Output: [4, 16]
```

18.	What are Dunder methods? Give examples

```
These are also known as "magic methods" or "special methods," are a set of predefined methods in Python that begin and end with double underscores (__). 
They allow you to define how objects of your classes behave with built-in operations, such as arithmetic operations, string representations, and object comparisons.

"__str__(self):"

Purpose: Defines the string representation of an object for human-readable output.
class Person:
    def __init__(self, name, age):
        self.name = name
        self.age = age

    def __str__(self):
        return f"{self.name} is {self.age} years old."

person = Person("Alice", 30)
print(person)  # Output: Alice is 30 years old.


Purpose: Define how objects of a class behave with built-in operations and functions.
Common Methods: __init__, __str__, __repr__, __add__, __eq__, __len__, __getitem__, __setitem__, etc.
These methods allow you to customize how your objects interact with Python's built-in operations, making your classes more powerful and integrated with the language's syntax.
```

19.	What does _init_ method do?

```
It is a special method known as the constructor. It is automatically called when a new instance of a class is created. 

Setup the initial state of the objects.
Called when creating a new instance of class.
Can take any number of parameter including self.
It doesn't have any return value.

class Person:
    def __init__(self, name, age):
        self.name = name  # Initialize the name attribute
        self.age = age    # Initialize the age attribute

    def __str__(self):
        return f"{self.name} is {self.age} years old."

# Create an instance of the Person class
person = Person("Alice", 30)

# Access the attributes
print(person.name)  # Output: Alice
print(person.age)   # Output: 30

# Print the string representation of the person
print(person)       # Output: Alice is 30 years old.
```

20.	Difference between array and numpy library.

```
Array -
1. Homogeneous Elements.
2. Support basic operation but lacks advance operation of vectorization, etc.
3. Basic functionality but lacks advance functionality of mathematical/scientific computing.
4. Slower for larger computation.

import array

# Create an array of integers
arr = array.array('i', [1, 2, 3, 4, 5])

# Access elements
print(arr[2])  # Output: 3

# Append an element
arr.append(6)

# Print array
print(arr)  # Output: array('i', [1, 2, 3, 4, 5, 6])

Numpy -
1. Homogeneous Elements but supports wide range of data types.
2. Advanced Operation like statistical function, linear algebra, broadcasting, etc.
3. Advanced Function.
4. Highly optimized for vectorized operations.

import numpy as np

# Create a NumPy array
arr = np.array([1, 2, 3, 4, 5])

# Access elements
print(arr[2])  # Output: 3

# Vectorized operation
arr_squared = arr ** 2
print(arr_squared)  # Output: [ 1  4  9 16 25]

# Broadcasting example
arr2 = np.array([[1, 2], [3, 4]])
arr3 = arr2 + 10
print(arr3)  # Output: [[11 12] [13 14]]
```
