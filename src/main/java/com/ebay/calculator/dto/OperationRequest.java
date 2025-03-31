package com.ebay.calculator.dto;

import java.util.List;

public class OperationRequest {
    private Number initialValue;  // The initial value to perform operations on.
    private List<OperationInput> operations;  // List of operations to perform on the initial value.

    // Getter for the initial value.
    public Number getInitialValue() {
        return initialValue;
    }

    // Setter for the initial value.
    public void setInitialValue(Number initialValue) {
        this.initialValue = initialValue;
    }

    // Getter for the list of operations.
    public List<OperationInput> getOperations() {
        return operations;
    }

    // Setter for the list of operations.
    public void setOperations(List<OperationInput> operations) {
        this.operations = operations;
    }
}
