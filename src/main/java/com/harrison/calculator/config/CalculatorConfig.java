package com.harrison.calculator.config;


import com.harrison.calculator.model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CalculatorConfig {

    @Bean
    public Map<Operation, OperationStrategy> operationStrategies(AddOperation add, SubtractOperation subtract, MultiplyOperation multiply, DivideOperation divide, PowerOperation power) {
        Map<Operation, OperationStrategy> strategies = new HashMap<>();
        strategies.put(Operation.ADD, add);
        strategies.put(Operation.SUBTRACT, subtract);
        strategies.put(Operation.MULTIPLY, multiply);
        strategies.put(Operation.DIVIDE, divide);
        strategies.put(Operation.POWER, power);
        return strategies;
    }

}
