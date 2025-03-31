package com.ebay.calculator.controller;

import com.ebay.calculator.dto.CalculationInput;
import com.ebay.calculator.dto.OperationRequest;
import com.ebay.calculator.model.Operation;
import com.ebay.calculator.service.CalculatorService;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    // Logger for this class to record useful log messages
    private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);

    @Autowired
    private CalculatorService calculatorService;

    // Endpoint to perform a basic calculation (e.g., addition, subtraction, etc.)
    @PostMapping("/calculate")
    public ResponseEntity<Number> calculate(@RequestParam Operation operation, 
                                            @RequestParam Number num1, 
                                            @RequestParam Number num2) {
        logger.info("Received basic calculation request with operation: {}, num1: {}, num2: {}", operation, num1, num2);
        try {
            Number result = calculatorService.calculate(operation, num1, num2);
            logger.info("Calculation result: {}", result);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Error occurred during calculation: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Error processing the calculation");
        }
    }

    // Endpoint to perform chained calculations (multiple operations in a sequence)
    @PostMapping("/chain")
    public ResponseEntity<Number> chainCalculate(@RequestParam Number initialValue, 
                                                 @RequestBody List<Pair<Operation, Number>> operations) {
        logger.info("Received chained calculation request with initial value: {}", initialValue);
        try {
            Number result = calculatorService.chainCalculate(initialValue, operations);
            logger.info("Chained calculation result: {}", result);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Error occurred during chained calculation: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Error processing the chained calculation");
        }
    }

    // Endpoint to perform chained calculation using a structured request
    @PostMapping("/v1/calculate")
    public ResponseEntity<String> calculate1(@RequestBody OperationRequest request) {
        logger.info("Received v1 calculation request with initial value: {}", request.getInitialValue());
        try {
            // Convert the list of operations in the request into pairs of operation and number
            List<Pair<Operation, Number>> operations = request.getOperations()
                    .stream()
                    .map(op -> {
                        Operation operation = Operation.valueOf(op.getOperation());
                        logger.debug("Mapping operation: {} with value: {}", operation, op.getValue());
                        return Pair.of(operation, op.getValue());
                    })
                    .collect(Collectors.toList());

            // Perform chained calculation
            Number result = calculatorService.chainCalculate(request.getInitialValue(), operations);
            logger.info("v1 chained calculation result: {}", result);
            return ResponseEntity.ok(result.toString());
        } catch (Exception e) {
            logger.error("Error in v1 calculation: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Bad input format or value");
        }
    }

    // Endpoint to evaluate a mathematical expression passed as a string
    @PostMapping("/v1/evaluate")
    public ResponseEntity<String> evaluateExpression(@RequestBody CalculationInput input) {
        logger.info("Received expression evaluation request: {}", input.getExpression());
        try {
            String[] parts = input.getExpression().split("\\s+");  // Split the expression into parts (tokens)
            Number initialResult = parseNumber(parts[0]);  // Assume the first part is the initial number
            List<Pair<Operation, Number>> operations = new ArrayList<>();

            // Process the remaining parts in pairs (operation, number)
            for (int i = 1; i < parts.length; i += 2) {
                if (i + 1 < parts.length) {  // Ensure there's a valid pair
                    Operation operation = Operation.getBySymbol(parts[i]);
                    Number value = parseNumber(parts[i + 1]);
                    operations.add(Pair.of(operation, value));
                    logger.debug("Parsed operation: {} with value: {}", operation, value);
                }
            }

            // Perform chained calculation
            Number result = calculatorService.chainCalculate(initialResult, operations);
            logger.info("Expression evaluation result: {}", result);
            return ResponseEntity.ok(result.toString());
        } catch (Exception e) {
            logger.error("Error evaluating expression: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Bad input format or value");
        }
    }

    // Helper method to parse numbers (handles both integers and doubles)
    private Number parseNumber(String numberStr) {
        try {
            // Try parsing as Integer first
            return Integer.parseInt(numberStr);
        } catch (NumberFormatException e) {
            // Fallback to parsing as Double if it's not an integer
            try {
                return Double.parseDouble(numberStr);
            } catch (NumberFormatException ex) {
                logger.error("Failed to parse number: {}", numberStr);
                throw new IllegalArgumentException("Invalid number format: " + numberStr);
            }
        }
    }
}
