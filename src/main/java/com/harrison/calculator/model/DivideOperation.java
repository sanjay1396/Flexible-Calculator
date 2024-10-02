package com.harrison.calculator.model;

import org.springframework.stereotype.Component;

@Component
public class DivideOperation implements OperationStrategy {
    @Override
    public Number apply(Number num1, Number num2) {
        if (num2.doubleValue() == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return num1.doubleValue() / num2.doubleValue();
    }

    @Override
    public Operation getOperation() {
        return Operation.DIVIDE;
    }
}
