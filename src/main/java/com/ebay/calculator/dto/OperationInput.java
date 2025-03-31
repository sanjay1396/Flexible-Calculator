package com.ebay.calculator.dto;

public class OperationInput {
    private String operation;  // The type of operation (e.g., ADD, SUBTRACT).
    private Number value;      // The value to apply to the operation.

    // Getter for the operation.
    public String getOperation() {
        return operation;
    }

    // Setter for the operation.
    public void setOperation(String operation) {
        this.operation = operation;
    }

    // Getter for the value.
    public Number getValue() {
        return value;
    }

    // Setter for the value.
    public void setValue(Number value) {
        this.value = value;
    }
}
