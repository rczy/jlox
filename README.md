# jlox - Lox interpreter in Java

This repository contains a Lox programming language interpreter implemented in Java. Lox is a small, dynamically-typed language designed for teaching purposes.

The interpreter was built following the awesome book titled [Crafting Interpreters](https://craftinginterpreters.com/) by Robert Nystrom.

## Features
`jlox` is a tree-walk interpreter providing the following features:

- **Scanning:** Splits the raw source code into tokens.
- **Syntax Trees:** Represents code in a traversable form.
- **Parsing:** Converts tokens into Abstract Syntax Tree (AST) nodes, with error reporting.
- **Evaluating expressions:** Computes the values of expressions, with runtime error detection.
- **Executing statements:** Interprets statements one after the other.
- **Variables:** Manages lexically scoped variables.
- **Control flow**: Supports logical expressions, branching, and loops.
- **Functions**: Calls user-defined and native functions with closures.
- **Resolving and Binding**: Analyses the source code statically.
- **Classes**: Handles objects and classes with constructors, fields, and methods.
- **Inheritance**: Allows superclass-subclass relations.

## How to Build
Compiling the source files with `javac` is straightforward:
```
javac -d build src/main/java/com/rczy/lox/*.java
```

## How to Run
`jlox` supports both **interactive (REPL - Read-Eval-Print Loop)**, and **non-interactive** mode.

To run the interpreter, you will use the`com.rczy.lox.Lox` main class.

### Interactive Mode (REPL)
To start the Read-Eval-Print Loop:
```
java -cp ./build com.rczy.lox.Lox
```

### Non-Interactive Mode
To execute a Lox script file, provide its path as an argument:
```
java -cp ./build com.rczy.lox.Lox <path/to/your/script.lox>
```

## Some Thoughts
Building an interpreter holds great educational value. :books:

If you are interested in this field, I strongly recommend you to create your own version of the Lox interpreter 
following the guide of the aforementioned book!