# Command-line Calculator Application

A simple yet functional command-line calculator application built with Java and Maven. This project includes:

- Basic arithmetic operations (addition, subtraction, multiplication, division)
- Unit tests with JUnit 5
- Maven configuration for building, testing, and packaging
- An executable JAR file
- Sample Jenkins freestyle job configuration

## Project Structure

```
calculator/
├── pom.xml
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── example/
│   │               └── calculator/
│   │                   ├── Calculator.java
│   │                   └── CalculatorApp.java
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── calculator/
│                       └── CalculatorTest.java
```

## Build Instructions

### Prerequisites

- Java 21 or higher
- Maven 3.6 or higher

### Testing the Project

```bash
mvn test
```

This will run all unit tests.

### Building the Project

```bash
mvn clean package
```

This will:

1. Compile the source code
1. Run the unit tests
1. Package the application into a JAR file with dependencies

The resulting JAR file will be located at:

```
target/calculator-1.0-SNAPSHOT.jar
```

## Running the Application

Run the JAR with the following command format:

```bash
java -jar target/calculator-1.0-SNAPSHOT-jar-with-dependencies.jar [operation] [num1] [num2]
```

Where:

- `[operation]` is one of: add, subtract, multiply, divide
- `[num1]` and `[num2]` are numeric values

### Examples

Addition:

```bash
java -jar target/calculator-1.0-SNAPSHOT-jar-with-dependencies.jar add 10 5
# Output: Result of 10.00 + 5.00 = 15.00
```

Subtraction:

```bash
java -jar target/calculator-1.0-SNAPSHOT-jar-with-dependencies.jar subtract 10 5
# Output: Result of 10.00 - 5.00 = 5.00
```

Multiplication:

```bash
java -jar target/calculator-1.0-SNAPSHOT-jar-with-dependencies.jar multiply 10 5
# Output: Result of 10.00 * 5.00 = 50.00
```

Division:

```bash
java -jar target/calculator-1.0-SNAPSHOT-jar-with-dependencies.jar divide 10 5
# Output: Result of 10.00 / 5.00 = 2.00
```

## Jenkins Integration

This project is designed to work well with Jenkins freestyle jobs. See the [README](./README.md) for more details on how to set up a Jenkins job to build and test this project.

## Extending the Project

Some ideas for extending this project:

- Add logging and error handling
- Add more operations (ie, square root, power, modulus)
- Implement a more complex calculator with memory functions
- Add a simple web interface using Spring Boot
- Implement continuous integration with automated releases

