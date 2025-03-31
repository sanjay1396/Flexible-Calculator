package com.ebay.calculator.config;

import com.ebay.calculator.model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CalculatorConfig {

    // Defining a Bean to manage the mapping between Operations and their corresponding Strategies
    @Bean
    public Map<Operation, OperationStrategy> operationStrategies(
            AddOperation add, 
            SubtractOperation subtract, 
            MultiplyOperation multiply, 
            DivideOperation divide, 
            PowerOperation power) {
        
        // Create a new HashMap to hold the operation-strategy associations
        Map<Operation, OperationStrategy> strategies = new HashMap<>();
        
        // Populate the map with each Operation and its corresponding OperationStrategy
        strategies.put(Operation.ADD, add); 
        strategies.put(Operation.SUBTRACT, subtract); 
        strategies.put(Operation.MULTIPLY, multiply); 
        strategies.put(Operation.DIVIDE, divide); 
        strategies.put(Operation.POWER, power);
        
        // Return the populated map
        return strategies;
    }
}

