package com.ebay.calculator.model;

import org.springframework.stereotype.Component;

/**
 * Represents the division operation in the calculator.
 * This class implements the OperationStrategy interface to define the behavior for the division operation.
 */
@Component
public class DivideOperation implements OperationStrategy {

    /**
     * Applies the division operation on two numbers.
     * 
     * @param num1 the numerator (dividend).
     * @param num2 the denominator (divisor).
     * @return the result of dividing num1 by num2.
     * @throws IllegalArgumentException if num2 is zero (division by zero is not allowed).
     */
    @Override
    public Number apply(Number num1, Number num2) {
        // Check if the denominator is zero to prevent division by zero
        if (num2.doubleValue() == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        // Perform the division and return the result
        return num1.doubleValue() / num2.doubleValue();
    }

    /**
     * Returns the operation type for division.
     * This is used to register the operation with the operation registry.
     * 
     * @return the DIVIDE operation constant.
     */
    @Override
    public Operation getOperation() {
        return Operation.DIVIDE;
    }
}
