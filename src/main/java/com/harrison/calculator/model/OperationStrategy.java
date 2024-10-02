package com.harrison.calculator.model;

public interface OperationStrategy {
    Number apply(Number num1, Number num2);
    Operation getOperation();
}
