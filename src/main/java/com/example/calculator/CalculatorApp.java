package com.example.calculator;

/**
 * Main application class for the Calculator.
 * Parses command line arguments and performs the requested operation.
 */
public class CalculatorApp {
    
    /**
     * Main method to run the calculator application.
     * Usage: java -jar calculator.jar [operation] [num1] [num2]
     * Operations: add, subtract, multiply, divide
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 3) {
            printUsage();
            System.exit(1);
        }
        
        String operation = args[0].toLowerCase();
        double num1, num2;
        
        try {
            num1 = Double.parseDouble(args[1]);
            num2 = Double.parseDouble(args[2]);
        } catch (NumberFormatException e) {
            System.err.println("Error: Arguments must be valid numbers");
            printUsage();
            System.exit(1);
            return; // Unreachable but needed for compilation
        }
        
        Calculator calculator = new Calculator();
        double result;
        
        try {
            switch (operation) {
                case "add":
                    result = calculator.add(num1, num2);
                    break;
                case "subtract":
                    result = calculator.subtract(num1, num2);
                    break;
                case "multiply":
                    result = calculator.multiply(num1, num2);
                    break;
                case "divide":
                    result = calculator.divide(num1, num2);
                    break;
                default:
                    System.err.println("Error: Invalid operation '" + operation + "'");
                    printUsage();
                    System.exit(1);
                    return; // Unreachable but needed for compilation
            }
            
            System.out.printf("Result of %.2f %s %.2f = %.2f%n", 
                    num1, getOperationSymbol(operation), num2, result);
            
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
    
    /**
     * Get the operation symbol for display purposes.
     * 
     * @param operation the operation name
     * @return the symbol representing the operation
     */
    private static String getOperationSymbol(String operation) {
        switch (operation) {
            case "add": return "+";
            case "subtract": return "-";
            case "multiply": return "*";
            case "divide": return "/";
            default: return operation;
        }
    }
    
    /**
     * Print the usage instructions for the application.
     */
    private static void printUsage() {
        System.out.println("Usage: java -jar calculator.jar [operation] [num1] [num2]");
        System.out.println("Operations: add, subtract, multiply, divide");
        System.out.println("Example: java -jar calculator.jar add 5 3");
    }
}