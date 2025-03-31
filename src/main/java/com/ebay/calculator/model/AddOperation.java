package com.ebay.calculator.model;

import org.springframework.stereotype.Component;

/**
 * Represents the addition operation in the calculator.
 * This class implements the OperationStrategy interface to define the behavior for the addition operation.
 */
@Component
public class AddOperation implements OperationStrategy {

    /**
     * Applies the addition operation on two numbers.
     * 
     * @param num1 the first number.
     * @param num2 the second number.
     * @return the result of adding num1 and num2.
     */
    @Override
    public Number apply(Number num1, Number num2) {
        // Convert both numbers to double to ensure floating-point precision
        return num1.doubleValue() + num2.doubleValue();
    }

    /**
     * Returns the operation type for addition.
     * This is used to register the operation with the operation registry.
     * 
     * @return the ADD operation constant.
     */
    @Override
    public Operation getOperation() {
        return Operation.ADD;
    }
}
