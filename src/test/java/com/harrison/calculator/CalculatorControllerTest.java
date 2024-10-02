package com.harrison.calculator;

import com.harrison.calculator.controller.CalculatorController;
import com.harrison.calculator.model.Operation;
import com.harrison.calculator.service.CalculatorService;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

public class CalculatorControllerTest {

    @Mock
    private CalculatorService calculatorService;

    @InjectMocks
    private CalculatorController calculatorController;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculate() {
        // Given
        when(calculatorService.calculate(Operation.ADD, 2, 3)).thenReturn(5);

        // When
        Number result = calculatorController.calculate(Operation.ADD, 2, 3);

        // Then
        assertEquals(5, result);
        verify(calculatorService).calculate(Operation.ADD, 2, 3);
    }

    @Test
    public void testChainCalculate() {
        // Given
        List<Pair<Operation, Number>> operations = Arrays.asList(
                Pair.of(Operation.ADD, 3),
                Pair.of(Operation.MULTIPLY, 2)
        );
        when(calculatorService.chainCalculate(5, operations)).thenReturn(16);

        // When
        Number result = calculatorController.chainCalculate(5, operations);

        // Then
        assertEquals(16, result);
        verify(calculatorService).chainCalculate(5, operations);
    }
}
