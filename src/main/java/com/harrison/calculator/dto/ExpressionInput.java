package com.harrison.calculator.dto;


public class ExpressionInput {
    private String expression;  // This field holds the string expression.

    // Default constructor necessary for frameworks like Spring to easily instantiate and populate the object.
    public ExpressionInput() {
    }

    // Constructor with parameter to facilitate easy creation of the object with an expression.
    public ExpressionInput(String expression) {
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

