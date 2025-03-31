package com.ebay.calculator.service;

import com.ebay.calculator.model.Operation;
import com.ebay.calculator.model.OperationStrategy;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service that handles calculator operations, both basic and chained.
 * It uses a map to look up operation strategies and calculate results accordingly.
 */
@Service
public class CalculatorService {

    // Map to store operations and their corresponding strategies
    private final Map<Operation, OperationStrategy> operationMap;

    // Constructor that injects the operation strategies
    @Autowired
    public CalculatorService(Map<Operation, OperationStrategy> operationMap) {
        this.operationMap = operationMap;
    }

    /**
     * Performs a basic calculation between two numbers using the specified operation.
     * 
     * @param op the operation to be performed (e.g., ADD, SUBTRACT, etc.).
     * @param num1 the first number (operand).
     * @param num2 the second number (operand).
     * @return the result of the operation.
     * @throws UnsupportedOperationException if the operation is not supported.
     */
    public Number calculate(Operation op, Number num1, Number num2) {
        OperationStrategy strategy = operationMap.get(op);
        if (strategy != null) {
            return strategy.apply(num1, num2);
        } else {
            throw new UnsupportedOperationException("Operation not supported.");
        }
    }

    /**
     * Performs a chained calculation, processing a sequence of operations in correct order.
     * Operations are evaluated based on their priority (higher priority operations are applied first).
     * 
     * @param initialValue the starting value for the chain of operations.
     * @param operations the list of operations to be applied in sequence.
     * @return the final result after applying all operations.
     */
    public Number chainCalculate(Number initialValue, List<Pair<Operation, Number>> operations) {
        Stack<Number> numbers = new Stack<>();    // Stack to hold numbers during calculation
        Stack<Operation> operators = new Stack<>(); // Stack to hold operators during calculation

        // Push the initial value onto the number stack
        numbers.push(initialValue);

        // Iterate through the list of operations and apply them in sequence
        for (Pair<Operation, Number> op : operations) {
            Operation currentOperation = op.getLeft();  // Current operation (e.g., ADD, SUBTRACT)
            Number currentValue = op.getRight();        // Current value to be used with the operation

            // Process operations if the current operation has lower or equal priority than the top operator
            while (!operators.isEmpty() && currentOperation.getPriority() <= operators.peek().getPriority()) {
                // Pop two numbers and an operator from the stacks
                Number lastNumber = numbers.pop();
                Number secondLastNumber = numbers.pop();
                Operation lastOperator = operators.pop();

                // Perform the operation and push the result back onto the stack
                Number result = calculate(lastOperator, secondLastNumber, lastNumber);
                numbers.push(result);
            }

            // Push the current operation and the next number onto their respective stacks
            operators.push(currentOperation);
            numbers.push(currentValue);
        }

        // Process the remaining operations in the stack after all operations have been pushed
        while (!operators.isEmpty()) {
            // Pop the operator and two numbers for the final calculation
            Operation operator = operators.pop();
            Number lastNumber = numbers.pop();
            Number secondLastNumber = numbers.pop();

            // Perform the operation and push the final result back onto the stack
            Number result = calculate(operator, secondLastNumber, lastNumber);
            numbers.push(result);
        }

        // At the end, there should be only one result in the number stack, which is the final result
        return numbers.pop();
    }
}

