package com.ebay.calculator;

import com.ebay.calculator.service.CalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CalculatorServiceApplicationTests {

    @Autowired
    private CalculatorService calculatorService;

    @Test
    void contextLoads() {
        // Verifying that the CalculatorService bean is loaded into the application context
        assertNotNull(calculatorService, "CalculatorService bean should be loaded");
    }
}
