package com.harrison.calculator.model;

public enum Operation {
    ADD(1),
    SUBTRACT(1),
    MULTIPLY(2),
    DIVIDE(2),
    POWER(3);

    private final int priority;

    Operation(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
