package com.harrison.calculator.dto;

import java.util.List;


import java.util.List;

public class OperationRequest {
    private Number initialValue;
    private List<OperationInput> operations;

    // Getters and Setters

    public Number getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(Number initialValue) {
        this.initialValue = initialValue;
    }

    public List<OperationInput> getOperations() {
        return operations;
    }

    public void setOperations(List<OperationInput> operations) {
        this.operations = operations;
    }
}


