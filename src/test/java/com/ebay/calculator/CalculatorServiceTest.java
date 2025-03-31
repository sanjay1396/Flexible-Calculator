package com.ebay.calculator;

import com.ebay.calculator.model.*;
import com.ebay.calculator.service.CalculatorService;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorServiceTest {

    private CalculatorService calculatorService;

    @Before
    public void setUp() {
        // Setting up the strategy map for the operations
        Map<Operation, OperationStrategy> strategies = new HashMap<>();
        strategies.put(Operation.ADD, new AddOperation());
        strategies.put(Operation.SUBTRACT, new SubtractOperation());
        strategies.put(Operation.MULTIPLY, new MultiplyOperation());
        strategies.put(Operation.DIVIDE, new DivideOperation());
        strategies.put(Operation.POWER, new PowerOperation());  // Assuming the Power operation is also part of the service.

        calculatorService = new CalculatorService(strategies);
    }

    @Test
    public void testAddOperation() {
        // Testing addition operation with new inputs
        assertEquals(15.0, calculatorService.calculate(Operation.ADD, 7, 8));
    }

    @Test
    public void testSubtractOperation() {
        // Testing subtraction operation with new inputs
        assertEquals(12.0, calculatorService.calculate(Operation.SUBTRACT, 20, 8));
    }

    @Test
    public void testMultiplyOperation() {
        // Testing multiplication operation with new inputs
        assertEquals(30.0, calculatorService.calculate(Operation.MULTIPLY, 6, 5));
    }

    @Test
    public void testDivideOperation() {
        // Testing division operation with new inputs
        assertEquals(5.0, calculatorService.calculate(Operation.DIVIDE, 25, 5));
    }

    @Test
    public void testDivideByZero() {
        // Testing division by zero (should throw exception)
        assertThrows(IllegalArgumentException.class, () -> {
            calculatorService.calculate(Operation.DIVIDE, 10, 0);
        });
    }

    @Test
    public void testChainCalculate_SimpleOperations() {
        // Testing a simple chain of operations: 3 * 4 + 2 * 3 + 5 * 2 = 35
        List<Pair<Operation, Number>> operations = Arrays.asList(
                Pair.of(Operation.MULTIPLY, 4),
                Pair.of(Operation.ADD, 2),
                Pair.of(Operation.MULTIPLY, 3),
                Pair.of(Operation.ADD, 5),
                Pair.of(Operation.MULTIPLY, 2)
        );
        assertEquals(35.0, calculatorService.chainCalculate(3, operations));
    }

    @Test
    public void testChainCalculate_EmptyOperations() {
        // Testing chain calculation with no operations (should return initial value)
        List<Pair<Operation, Number>> operations = Arrays.asList();
        assertEquals(50.0, calculatorService.chainCalculate(50, operations));
    }

    @Test
    public void testChainCalculate_WithSubtractionAndMultiplication() {
        // Testing a chain of operations: 30 - 10 + 6 * 4 = 56
        List<Pair<Operation, Number>> operations = Arrays.asList(
                Pair.of(Operation.SUBTRACT, 10),
                Pair.of(Operation.ADD, 6),
                Pair.of(Operation.MULTIPLY, 4)
        );
        assertEquals(56.0, calculatorService.chainCalculate(30, operations));
    }

    @Test
    public void testChainCalculate_UsingPowerOperation() {
        // Testing the chain calculation with power operation: 3 ^ 2 * 4 = 36
        List<Pair<Operation, Number>> operations = Arrays.asList(
                Pair.of(Operation.POWER, 2),
                Pair.of(Operation.MULTIPLY, 4)
        );
        assertEquals(36.0, calculatorService.chainCalculate(3, operations));
    }

    @Test
    public void testChainCalculate_WithNegativeResult() {
        // Testing a chain calculation that could result in a negative value: 15 - 30 + 5
        List<Pair<Operation, Number>> operations = Arrays.asList(
                Pair.of(Operation.SUBTRACT, 30),
                Pair.of(Operation.ADD, 5)
        );
        assertEquals(-10.0, calculatorService.chainCalculate(15, operations));
    }

    @Test
    public void testChainCalculate_WithDivision() {
        // Testing division within a chain: 40 / 5 + 2 * 3 = 14
        List<Pair<Operation, Number>> operations = Arrays.asList(
                Pair.of(Operation.DIVIDE, 5),
                Pair.of(Operation.ADD, 2),
                Pair.of(Operation.MULTIPLY, 3)
        );
        assertEquals(14.0, calculatorService.chainCalculate(40, operations));
    }

    @Test
    public void testChainCalculate_ComplexExpression() {
        // Testing a more complex chain of operations: 25 + 6 * 2 - 8 / 2 ^ 2
        List<Pair<Operation, Number>> operations = Arrays.asList(
                Pair.of(Operation.ADD, 6),
                Pair.of(Operation.MULTIPLY, 2),
                Pair.of(Operation.SUBTRACT, 8),
                Pair.of(Operation.DIVIDE, 2),
                Pair.of(Operation.POWER, 2)
        );
        assertEquals(31.0, calculatorService.chainCalculate(25, operations));
    }
}

