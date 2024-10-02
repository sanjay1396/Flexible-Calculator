package com.harrison.calculator.model;

public enum Operation {
    ADD("+", 1),
    SUBTRACT("-", 1),
    MULTIPLY("*", 2),
    DIVIDE("/", 2),
    POWER("^" ,3);

    private final String symbol;
    private final int priority;

    Operation(String symbol,int priority) {
        this.symbol = symbol;
        this.priority = priority;
    }

    public String getSymbol(){
        return symbol;
    }
    public int getPriority() {
        return priority;
    }

    public static Operation getBySymbol(String symbol) {
        for (Operation op : Operation.values()) {
            if (op.getSymbol().equals(symbol)) {
                return op;
            }
        }
        throw new IllegalArgumentException("Unknown operation symbol: " + symbol);
    }
}
