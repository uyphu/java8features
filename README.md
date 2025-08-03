# Java 8 Features Examples

This repository contains comprehensive examples of Java 8 features and their usage.

## Features Covered

### 1. Lambda Expressions
- Basic lambda syntax
- Functional interfaces
- Lambda with collections

### 2. Streams API
- Stream operations (map, filter, reduce, collect)
- Parallel streams
- Stream collectors

### 3. Optional Class
- Creating Optional objects
- Safe navigation and default values
- Optional with streams

### 4. Default Methods
- Interface default methods
- Multiple inheritance resolution

### 5. Method References
- Static method references
- Instance method references
- Constructor references

### 6. Date/Time API
- LocalDate, LocalTime, LocalDateTime
- Period and Duration
- DateTimeFormatter

### 7. CompletableFuture
- Asynchronous programming
- Promise-like operations
- Exception handling

### 8. Nashorn JavaScript Engine
- JavaScript execution in Java
- Interop between Java and JavaScript

## Project Structure

```
src/
├── main/
│   └── java/
│       └── com/
│           └── java8/
│               ├── lambda/
│               ├── streams/
│               ├── optional/
│               ├── defaultmethods/
│               ├── methodreferences/
│               ├── datetime/
│               ├── completablefuture/
│               └── nashorn/
└── test/
    └── java/
        └── com/
            └── java8/
```

## Requirements

- Java 8 or higher
- Maven (for build management)

## Getting Started

1. Clone this repository
2. Navigate to the project directory
3. Run examples using your IDE or command line

## Command Line Instructions

### Building the Project

```bash
# Clean and compile the project
mvn clean compile

# Clean, compile, and run tests
mvn clean test

# Package the project (creates JAR file)
mvn clean package

# Install to local Maven repository
mvn clean install
```

### Running Examples

#### Run All Java 8 Features Examples
```bash
# Run all features sequentially
mvn exec:java -Dexec.mainClass="com.java8.Java8FeaturesRunner"
```

#### Run Individual Feature Examples

```bash
# Lambda Expressions
mvn exec:java -Dexec.mainClass="com.java8.lambda.LambdaExamples"

# Streams API
mvn exec:java -Dexec.mainClass="com.java8.streams.StreamExamples"

# Optional Class
mvn exec:java -Dexec.mainClass="com.java8.optional.OptionalExamples"

# Method References
mvn exec:java -Dexec.mainClass="com.java8.methodreferences.MethodReferenceExamples"

# Default Methods
mvn exec:java -Dexec.mainClass="com.java8.defaultmethods.DefaultMethodExamples"

# Date/Time API
mvn exec:java -Dexec.mainClass="com.java8.datetime.DateTimeExamples"

# CompletableFuture
mvn exec:java -Dexec.mainClass="com.java8.completablefuture.CompletableFutureExamples"

# Nashorn JavaScript Engine (requires Java 8-14)
mvn exec:java -Dexec.mainClass="com.java8.nashorn.NashornExamples"
```

### Testing

```bash
# Run all tests
mvn test

# Run tests with detailed output
mvn test -Dtest=Java8FeaturesTest

# Run specific test
mvn test -Dtest=Java8FeaturesTest#testLambdaExamples

# Run tests with coverage report
mvn clean test jacoco:report
```

### Development Commands

```bash
# Check for compilation warnings
mvn compile -Xlint:all

# Run with debug output
mvn exec:java -Dexec.mainClass="com.java8.Java8FeaturesRunner" -X

# Clean target directory
mvn clean

# Validate project structure
mvn validate

# Show dependency tree
mvn dependency:tree

# Show project information
mvn help:describe -Dcmd=compile
```

### Quick Start Commands

```bash
# Quick build and test
mvn clean test

# Quick run of all examples
mvn clean compile exec:java -Dexec.mainClass="com.java8.Java8FeaturesRunner"

# Quick run of specific feature
mvn clean compile exec:java -Dexec.mainClass="com.java8.lambda.LambdaExamples"
```

### IDE Integration

#### IntelliJ IDEA
1. Open the project as a Maven project
2. Run `mvn clean compile` to ensure all dependencies are downloaded
3. Use the IDE's run configurations or Maven tool window

#### Eclipse
1. Import as Maven project
2. Run `mvn eclipse:eclipse` if needed
3. Use Maven plugin or run configurations

#### VS Code
1. Install Java Extension Pack
2. Open the project folder
3. Use the integrated terminal for Maven commands

## Troubleshooting

### Common Issues

1. **Nashorn not available**: Nashorn examples require Java 8-14. On Java 15+, you'll see "Nashorn engine not available" messages.

2. **Maven not found**: Ensure Maven is installed and in your PATH:
   ```bash
   mvn --version
   ```

3. **Java version issues**: Ensure you're using Java 8 or higher:
   ```bash
   java -version
   ```

4. **Permission denied**: On Unix-like systems, you might need to make scripts executable:
   ```bash
   chmod +x mvnw
   ```

### Performance Tips

- Use `mvn clean compile` before running examples for faster execution
- For repeated runs, skip the clean step: `mvn compile exec:java -Dexec.mainClass="..."`
- Use compiled scripts in Nashorn examples for better performance

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Run `mvn clean test` to ensure all tests pass
6. Submit a pull request

## License

This project is open source and available under the [MIT License](LICENSE).
