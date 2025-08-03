# Java 8 Features - Quick Start Guide

## Prerequisites

- Java 8 or higher
- Maven 3.6 or higher

## Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/uyphu/java8features.git
cd java8features
```

### 2. Build the Project
```bash
mvn clean compile
```

### 3. Run Tests
```bash
mvn test
```

### 4. Run Individual Examples

#### Lambda Expressions
```bash
mvn exec:java -Dexec.mainClass="com.java8.lambda.LambdaExamples"
```

#### Streams API
```bash
mvn exec:java -Dexec.mainClass="com.java8.streams.StreamExamples"
```

#### Optional Class
```bash
mvn exec:java -Dexec.mainClass="com.java8.optional.OptionalExamples"
```

#### Method References
```bash
mvn exec:java -Dexec.mainClass="com.java8.methodreferences.MethodReferenceExamples"
```

#### Date/Time API
```bash
mvn exec:java -Dexec.mainClass="com.java8.datetime.DateTimeExamples"
```

### 5. Run All Examples
```bash
mvn exec:java -Dexec.mainClass="com.java8.Java8FeaturesRunner"
```

## Features Covered

### 1. Lambda Expressions
- Basic lambda syntax
- Functional interfaces
- Lambda with collections
- Built-in functional interfaces
- Exception handling
- Variable capture

### 2. Streams API
- Basic stream operations
- Intermediate operations (filter, map, flatMap, etc.)
- Terminal operations (collect, reduce, forEach, etc.)
- Stream collectors
- Parallel streams
- Streams with primitives
- Grouping and partitioning

### 3. Optional Class
- Creating Optional objects
- Optional methods (isPresent, orElse, orElseGet, etc.)
- Safe navigation
- Optional with streams
- Optional chaining
- Best practices

### 4. Method References
- Static method references
- Instance method references
- Constructor references
- Array constructor references
- Method references with streams
- Comparison with lambdas

### 5. Date/Time API
- LocalDate, LocalTime, LocalDateTime
- Period and Duration
- DateTimeFormatter
- Temporal adjusters
- Time zones
- Legacy date conversion

## Project Structure

```
src/
├── main/java/com/java8/
│   ├── lambda/LambdaExamples.java
│   ├── streams/StreamExamples.java
│   ├── optional/OptionalExamples.java
│   ├── methodreferences/MethodReferenceExamples.java
│   ├── datetime/DateTimeExamples.java
│   └── Java8FeaturesRunner.java
└── test/java/com/java8/
    └── Java8FeaturesTest.java
```

## IDE Setup

### IntelliJ IDEA
1. Open the project
2. Import as Maven project
3. Run any of the example classes

### Eclipse
1. Import as Maven project
2. Run any of the example classes

### VS Code
1. Open the project folder
2. Install Java extension pack
3. Run any of the example classes

## Learning Path

1. Start with **Lambda Expressions** - fundamental concept
2. Move to **Method References** - syntactic sugar for lambdas
3. Learn **Streams API** - powerful data processing
4. Understand **Optional** - null safety
5. Explore **Date/Time API** - modern date handling

## Tips for Learning

- Run examples and experiment with the code
- Try modifying parameters and see the results
- Use the debugger to step through complex examples
- Read the comments in the code for explanations
- Practice by creating your own examples

## Contributing

Feel free to contribute by:
- Adding more examples
- Improving existing examples
- Adding new Java 8 features
- Fixing bugs or improving documentation

## License

This project is open source and available under the MIT License. 