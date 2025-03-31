# Flexible Calculator

This project implements a flexible, extensible calculator in Java that adheres to the Open-Closed Principle, ensuring that new operations can be added without modifying the core `Calculator` class. The design follows Object-Oriented Programming (OOP) principles and is compatible with an Inversion of Control (IoC) environment.

## Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Architecture & Design](#architecture--design)
- [Usage](#usage)
- [Assumptions & Considerations](#assumptions--considerations)
- [Testing](#testing)

## Features

- Supports basic operations: Addition, Subtraction, Multiplication, and Division.
- Allows chaining of operations on an initial value.
- Extensible design for adding new operations without modifying existing code.
- Handles unsupported operations gracefully.
- API-based implementation for easy integration and testing.

## Technologies

- Java 17
- Spring Boot
- Maven
- JUnit and Mockito for testing

## Architecture & Design

### Class Diagram

![Class Diagram]()

### Design Patterns Used

- **Strategy Pattern**: Allows dynamic selection of operations without modifying the core calculator.
- **Factory Pattern**: Used to register and retrieve different operations.
- **Fluent Interface**: Enables chaining operations for enhanced readability.
- **Dependency Injection**: Ensures testability and modular design.

### Component Breakdown

- **Calculator Class**: Core calculator logic performing operations.
- **Operation Enum**: Defines available operations.
- **OperationStrategy Interface**: Encapsulates operation logic, making it extendable.
- **OperationRegistry**: Centralized management of operations.
- **ChainedCalculator**: Implements chaining for sequential calculations.

## Usage

### 1. Basic Calculation

The `calculate` method performs a basic operation between two numbers:

```java
Calculator calculator = new Calculator();
Number result = calculator.calculate(Operation.ADD, 5, 3);
System.out.println(result); // Output: 8
```

### 2. Chaining Operations

The calculator supports operation chaining:

```java
ChainedCalculator calc = new ChainedCalculator(5);
Number result = calc.add(3).multiply(2).getResult();
System.out.println(result); // Output: 16
```

### 3. Adding New Operations

New operations can be added without modifying the `Calculator` class:

```java
public class ModulusOperation implements OperationStrategy {
    @Override
    public Number apply(Number num1, Number num2) {
        return num1.intValue() % num2.intValue();
    }
}
```

Register the new operation:

```java
OperationRegistry.register(Operation.MODULUS, new ModulusOperation());
```

## Assumptions & Considerations

- Operations apply to numeric types only (`Integer`, `Double`, etc.).
- Floating-point precision is handled internally to minimize rounding errors.
- Division by zero is gracefully managed by throwing an exception.
- Operations are registered dynamically, making it easy to expand functionality.
- Thread safety is ensured for concurrent operations.

## Testing

Unit tests are included to validate:

- Basic operations
- Chaining functionality
- Error handling for invalid operations

Run tests using:

```sh
mvn

