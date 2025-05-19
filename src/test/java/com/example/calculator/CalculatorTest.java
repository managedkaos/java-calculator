package com.example.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Calculator class with additional test categories.
 * Tests are tagged to demonstrate filtering with Jenkins parameters.
 */
public class CalculatorTest {
    
    private Calculator calculator;
    
    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }
    
    @Test
    @Tag("unit")
    public void testAdd() {
        assertEquals(5.0, calculator.add(2.0, 3.0), 0.0001);
        assertEquals(0.0, calculator.add(2.0, -2.0), 0.0001);
        assertEquals(-5.0, calculator.add(-2.0, -3.0), 0.0001);
    }
    
    @Test
    @Tag("unit")
    public void testSubtract() {
        assertEquals(-1.0, calculator.subtract(2.0, 3.0), 0.0001);
        assertEquals(4.0, calculator.subtract(2.0, -2.0), 0.0001);
        assertEquals(1.0, calculator.subtract(-2.0, -3.0), 0.0001);
    }
    
    @Test
    @Tag("unit")
    public void testMultiply() {
        assertEquals(6.0, calculator.multiply(2.0, 3.0), 0.0001);
        assertEquals(-4.0, calculator.multiply(2.0, -2.0), 0.0001);
        assertEquals(6.0, calculator.multiply(-2.0, -3.0), 0.0001);
    }
    
    @Test
    @Tag("unit")
    public void testDivide() {
        assertEquals(2.0, calculator.divide(6.0, 3.0), 0.0001);
        assertEquals(-1.0, calculator.divide(2.0, -2.0), 0.0001);
        assertEquals(2.0/3.0, calculator.divide(2.0, 3.0), 0.0001);
    }
    
    @Test
    @Tag("unit")
    public void testDivideByZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(5.0, 0.0);
        });
        
        String expectedMessage = "Cannot divide by zero";
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    @Tag("performance")
    public void testPerformance() {
        // Simple performance test - would be expanded in real projects
        long startTime = System.nanoTime();
        
        // Run a calculation many times
        for (int i = 0; i < 100000; i++) {
            calculator.add(i, i+1);
        }
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;  // Convert to milliseconds
        
        System.out.println("Performance test completed in " + duration + " ms");
        // This is just a demonstration, not a real assertion
        assertTrue(duration < 500, "Performance test should complete in under 500ms");
    }
}