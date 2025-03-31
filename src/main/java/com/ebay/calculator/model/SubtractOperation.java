package com.ebay.calculator.model;

import org.springframework.stereotype.Component;

/**
 * Represents the subtraction operation in the calculator.
 * This class implements the OperationStrategy interface to define the behavior for the subtraction operation.
 */
@Component
public class SubtractOperation implements OperationStrategy {

    /**
     * Applies the subtraction operation (num1 - num2).
     * 
     * @param num1 the first number (minuend).
     * @param num2 the second number (subtrahend).
     * @return the result of subtracting num2 from num1.
     */
    @Override
    public Number apply(Number num1, Number num2) {
        // Perform subtraction and return the result
        return num1.doubleValue() - num2.doubleValue();
    }

    /**
     * Returns the operation type for subtraction.
     * This is used to register the operation with the operation registry.
     * 
     * @return the SUBTRACT operation constant.
     */
    @Override
    public Operation getOperation() {
        return Operation.SUBTRACT;
    }
}
