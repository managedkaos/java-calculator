package com.example.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Calculator class.
 */
public class CalculatorTest {
    
    private Calculator calculator;
    
    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }
    
    @Test
    public void testAdd() {
        assertEquals(5.0, calculator.add(2.0, 3.0), 0.0001);
        assertEquals(0.0, calculator.add(2.0, -2.0), 0.0001);
        assertEquals(-5.0, calculator.add(-2.0, -3.0), 0.0001);
    }
    
    @Test
    public void testSubtract() {
        assertEquals(-1.0, calculator.subtract(2.0, 3.0), 0.0001);
        assertEquals(4.0, calculator.subtract(2.0, -2.0), 0.0001);
        assertEquals(1.0, calculator.subtract(-2.0, -3.0), 0.0001);
    }
    
    @Test
    public void testMultiply() {
        assertEquals(6.0, calculator.multiply(2.0, 3.0), 0.0001);
        assertEquals(-4.0, calculator.multiply(2.0, -2.0), 0.0001);
        assertEquals(6.0, calculator.multiply(-2.0, -3.0), 0.0001);
    }
    
    @Test
    public void testDivide() {
        assertEquals(2.0, calculator.divide(6.0, 3.0), 0.0001);
        assertEquals(-1.0, calculator.divide(2.0, -2.0), 0.0001);
        assertEquals(2.0/3.0, calculator.divide(2.0, 3.0), 0.0001);
    }
    
    @Test
    public void testDivideByZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(5.0, 0.0);
        });
        
        String expectedMessage = "Cannot divide by zero";
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains(expectedMessage));
    }
}