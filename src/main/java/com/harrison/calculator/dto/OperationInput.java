package com.harrison.calculator.dto;

public class OperationInput {
    private String operation;
    private Number value;

    // Getters and Setters

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Number getValue() {
        return value;
    }

    public void setValue(Number value) {
        this.value = value;
    }
}
