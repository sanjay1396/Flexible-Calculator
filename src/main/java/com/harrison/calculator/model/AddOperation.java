package com.harrison.calculator.model;

import org.springframework.stereotype.Component;

@Component
public class AddOperation implements OperationStrategy {
    @Override
    public Number apply(Number num1, Number num2) {
        return num1.doubleValue() + num2.doubleValue();
    }
    @Override
    public Operation getOperation() {
        return Operation.ADD;
    }
}