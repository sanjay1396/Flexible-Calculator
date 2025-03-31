package com.ebay.calculator.dto;

public class CalculationInput {
    private String expression;  // This field holds the string expression.

    // Default constructor necessary for frameworks like Spring to easily instantiate and populate the object.
    public CalculationInput() {
    }

    // Constructor with parameter to facilitate easy creation of the object with an expression.
    public CalculationInput(String expression) {
        this.expression = expression;
    }

    // Getter and setter methods for the expression field.
    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
