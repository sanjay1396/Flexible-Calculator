package com.harrison.calculator;


import com.harrison.calculator.model.*;
import com.harrison.calculator.service.CalculatorService;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorServiceTest {

    private CalculatorService calculatorService;

    @Before
    public void setUp() {
        Map<Operation, OperationStrategy> strategies = new HashMap<>();
        strategies.put(Operation.ADD, new AddOperation());
        strategies.put(Operation.SUBTRACT, new SubtractOperation());
        strategies.put(Operation.MULTIPLY, new MultiplyOperation());
        strategies.put(Operation.DIVIDE, new DivideOperation());

        calculatorService = new CalculatorService(strategies);
    }

    @Test
    public void testAddOperation() {
        assertEquals(5.0, calculatorService.calculate(Operation.ADD, 2, 3));
    }

    @Test
    public void testChainOperation1() {
        List<Pair<Operation, Number>> operations = Arrays.asList(

                Pair.of(Operation.MULTIPLY, 5),
                Pair.of(Operation.ADD, 3),
                Pair.of(Operation.MULTIPLY, 2),
                Pair.of(Operation.ADD, 5),
                Pair.of(Operation.MULTIPLY, 2)

        );
        assertEquals(26.0, calculatorService.chainCalculate(2, operations));
    }
}

