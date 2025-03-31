package com.ebay.calculator.model;

import org.springframework.stereotype.Component;

/**
 * Represents the multiplication operation in the calculator.
 * This class implements the OperationStrategy interface to define the behavior for the multiplication operation.
 */
@Component
public class MultiplyOperation implements OperationStrategy {

    /**
     * Applies the multiplication operation on two numbers.
     * 
     * @param num1 the first number (multiplicand).
     * @param num2 the second number (multiplier).
     * @return the result of multiplying num1 and num2.
     */
    @Override
    public Number apply(Number num1, Number num2) {
        // Perform the multiplication and return the result
        return num1.doubleValue() * num2.doubleValue();
    }

    /**
     * Returns the operation type for multiplication.
     * This is used to register the operation with the operation registry.
     * 
     * @return the MULTIPLY operation constant.
     */
    @Override
    public Operation getOperation() {
        return Operation.MULTIPLY;
    }
}
