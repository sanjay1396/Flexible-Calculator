package com.ebay.calculator.model;

import org.springframework.stereotype.Component;

/**
 * Represents the power operation (exponentiation) in the calculator.
 * This class implements the OperationStrategy interface to define the behavior for the power operation.
 */
@Component
public class PowerOperation implements OperationStrategy {

    /**
     * Applies the power operation (num1 raised to the power of num2).
     * 
     * @param num1 the base (the number to be raised to a power).
     * @param num2 the exponent (the power to which num1 is raised).
     * @return the result of raising num1 to the power of num2.
     */
    @Override
    public Number apply(Number num1, Number num2) {
        // Use Math.pow to calculate the exponentiation.
        return Math.pow(num1.doubleValue(), num2.doubleValue());
    }

    /**
     * Returns the operation type for power.
     * This is used to register the operation with the operation registry.
     * 
     * @return the POWER operation constant.
     */
    @Override
    public Operation getOperation() {
        return Operation.POWER;
    }
}
