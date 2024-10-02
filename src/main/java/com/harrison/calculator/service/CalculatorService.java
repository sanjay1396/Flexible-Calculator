package com.harrison.calculator.service;


import com.harrison.calculator.model.Operation;
import com.harrison.calculator.model.OperationStrategy;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CalculatorService {

    private final Map<Operation, OperationStrategy> operationMap;

    @Autowired
    public CalculatorService(Map<Operation, OperationStrategy> operationMap) {
        this.operationMap = operationMap;
    }

    public Number calculate(Operation op, Number num1, Number num2) {
        OperationStrategy strategy = operationMap.get(op);
        if (strategy != null) {
            return strategy.apply(num1, num2);
        } else {
            throw new UnsupportedOperationException("Operation not supported.");
        }
    }

    public Number chainCalculate(Number initialValue, List<Pair<Operation, Number>> operations) {
        Stack<Number> numbers = new Stack<>();
        Stack<Operation> operators = new Stack<>();

        numbers.push(initialValue);

        for (Pair<Operation, Number> op : operations) {
            Operation currentOperation = op.getLeft();
            Number currentValue = op.getRight();

            while (!operators.isEmpty() && currentOperation.getPriority() <= operators.peek().getPriority()) {
                // Process the stack if the current operation has a lower or equal priority
                Number lastNumber = numbers.pop();
                Number secondLastNumber = numbers.pop();
                Operation lastOperator = operators.pop();

                Number result = calculate(lastOperator, secondLastNumber, lastNumber);
                numbers.push(result);
            }

            // Push the current operation and value onto their respective stacks
            operators.push(currentOperation);
            numbers.push(currentValue);
        }

        // Now process the remaining operations in the stack
        while (!operators.isEmpty()) {
            Operation operator = operators.pop();
            Number lastNumber = numbers.pop();
            Number secondLastNumber = numbers.pop();

            Number result = calculate(operator, secondLastNumber, lastNumber);
            numbers.push(result);
        }

        return numbers.pop(); // The final result
    }

}
