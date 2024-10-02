package com.harrison.calculator.controller;


import com.harrison.calculator.dto.ExpressionInput;
import com.harrison.calculator.dto.OperationRequest;
import com.harrison.calculator.model.Operation;
import com.harrison.calculator.service.CalculatorService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    // Endpoint to perform a basic calculation
    @PostMapping("/calculate")
    public Number calculate(@RequestParam Operation operation, @RequestParam Number num1, @RequestParam Number num2) {
        return calculatorService.calculate(operation, num1, num2);
    }

    // Endpoint to perform chained operations
    @PostMapping("/chain")
    public Number chainCalculate(@RequestParam Number initialValue, @RequestBody List<Pair<Operation, Number>> operations) {
        return calculatorService.chainCalculate(initialValue, operations);
    }

    @PostMapping("/v1/calculate")
    public Number calculate1(@RequestBody OperationRequest request) {
        List<Pair<Operation, Number>> operations = request.getOperations()
                .stream()
                .map(op -> {
                    Operation operation = Operation.valueOf(op.getOperation());
//                    System.out.println("Operation: " + operation + ", Value: " + op.getValue());
                    return Pair.of(operation, op.getValue());
                })
                .toList();
//        System.out.println(request.getInitialValue());

        return calculatorService.chainCalculate(request.getInitialValue(), operations);
    }


    @PostMapping("/v1/evaluate")
    public Number evaluateExpression(@RequestBody ExpressionInput input) {
        String[] parts = input.getExpression().split("\\s+");
        Number initialResult = parseNumber(parts[0]); // Assume the first part is the initial number.
        List<Pair<Operation, Number>> operations = new ArrayList<>();

        // Process remaining parts, which should be in pairs (operation, number)
        for (int i = 1; i < parts.length; i += 2) {
            if (i + 1 < parts.length) {  // Make sure there's a pair
                Operation operation = Operation.getBySymbol(parts[i]);
                Number value = parseNumber(parts[i + 1]);
                operations.add(Pair.of(operation, value));
            }
        }

        return calculatorService.chainCalculate(initialResult, operations);

    }

    private Number parseNumber(String numberStr) {
        try {
            return Integer.parseInt(numberStr);
        } catch (NumberFormatException e) {
            return Double.parseDouble(numberStr);  // Fall back to double if not an integer
        }
    }
}

