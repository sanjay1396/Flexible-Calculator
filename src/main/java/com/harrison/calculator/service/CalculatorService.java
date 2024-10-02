package com.harrison.calculator.service;


import com.harrison.calculator.model.Operation;
import com.harrison.calculator.model.OperationStrategy;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CalculatorService {

    private final Map<Operation, OperationStrategy> operationMap = new HashMap<>();

    @Autowired
    public CalculatorService(Map<Operation, OperationStrategy> strategies) {
        this.operationMap.putAll(strategies);
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

        // Start with the initial value
        numbers.push(initialValue);

        for (Pair<Operation, Number> op : operations) {
            Operation currentOperation = op.getLeft();
            Number currentValue = op.getRight();

            if (currentOperation == Operation.MULTIPLY || currentOperation == Operation.DIVIDE) {
                // If the operator is * or /, pop the last number and apply the operation
                Number lastNumber = numbers.pop();
                Number result = calculate(currentOperation, lastNumber, currentValue);
                numbers.push(result);
            } else {
                // If the operator is + or -, just push the operator
                operators.push(currentOperation);
                numbers.push(currentValue);
            }
        }

        // Now process the remaining numbers with + and - operators
        Number result = numbers.pop();  // Start with the last number

        while (!operators.isEmpty()) {
            Operation operator = operators.pop();
            Number nextValue = numbers.pop();

            result = calculate(operator, result, nextValue);
        }

        return result;
//        List<Pair<Operation, Number>> sortedOperations = new ArrayList<>(operations);
//        sortedOperations.sort(Comparator.comparingInt(pair -> pair.getLeft().getPriority()));
//
//        // Step 2: Calculate based on sorted operations
//        Number result = initialValue;
//
//        for (Pair<Operation, Number> op : sortedOperations) {
//            result = calculate(op.getLeft(), result, op.getRight());
//        }
//
//        return result;
    }
}
