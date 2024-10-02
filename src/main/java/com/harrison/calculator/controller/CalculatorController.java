package com.harrison.calculator.controller;


import com.harrison.calculator.dto.OperationRequest;
import com.harrison.calculator.model.Operation;
import com.harrison.calculator.service.CalculatorService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}

