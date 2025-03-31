package com.ebay.calculator.model;

/**
 * Interface for defining the strategy of operations (addition, subtraction, multiplication, etc.).
 * Each operation (Add, Subtract, Multiply, etc.) must implement this interface to define 
 * how the operation is applied and to return the associated operation type.
 */
public interface OperationStrategy {

    /**
     * Applies the operation on two numbers.
     * 
     * @param num1 the first number (operand 1).
     * @param num2 the second number (operand 2).
     * @return the result of applying the operation to num1 and num2.
     */
    Number apply(Number num1, Number num2);

    /**
     * Returns the specific operation that this strategy implements.
     * 
     * @return the operation enum constant that this strategy corresponds to (e.g., ADD, MULTIPLY).
     */
    Operation getOperation();
}

