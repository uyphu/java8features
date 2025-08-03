package com.java8.lambda;

import java.util.*;
import java.util.function.*;

/**
 * Comprehensive examples of Lambda Expressions in Java 8
 */
public class LambdaExamples {
    
    public static void main(String[] args) {
        LambdaExamples examples = new LambdaExamples();
        
        System.out.println("=== Lambda Expressions Examples ===\n");
        
        examples.basicLambdaSyntax();
        examples.functionalInterfaces();
        examples.lambdaWithCollections();
        examples.builtInFunctionalInterfaces();
        examples.lambdaWithExceptions();
        examples.variableCapture();
    }
    
    /**
     * Basic Lambda Syntax Examples
     */
    public void basicLambdaSyntax() {
        System.out.println("1. Basic Lambda Syntax:");
        
        // No parameters
        Runnable noParams = () -> System.out.println("Hello from lambda!");
        noParams.run();
        
        // Single parameter
        Consumer<String> singleParam = (String s) -> System.out.println("Received: " + s);
        singleParam.accept("Hello World");
        
        // Single parameter with type inference
        Consumer<String> inferredType = s -> System.out.println("Inferred type: " + s);
        inferredType.accept("Type inference works!");
        
        // Multiple parameters
        BinaryOperator<Integer> multipleParams = (a, b) -> a + b;
        System.out.println("Sum: " + multipleParams.apply(5, 3));
        
        // Multiple parameters with explicit types
        BinaryOperator<Integer> explicitTypes = (Integer a, Integer b) -> a * b;
        System.out.println("Product: " + explicitTypes.apply(4, 6));
        
        // Lambda with block body
        BinaryOperator<Integer> blockBody = (a, b) -> {
            int result = a * a + b * b;
            System.out.println("Calculating: " + a + "² + " + b + "² = " + result);
            return result;
        };
        System.out.println("Result: " + blockBody.apply(3, 4));
        
        System.out.println();
    }
    
    /**
     * Custom Functional Interfaces
     */
    public void functionalInterfaces() {
        System.out.println("2. Custom Functional Interfaces:");
        
        // Custom functional interface
        MathOperation add = (a, b) -> a + b;
        MathOperation subtract = (a, b) -> a - b;
        MathOperation multiply = (a, b) -> a * b;
        MathOperation divide = (a, b) -> a / b;
        
        System.out.println("10 + 5 = " + add.operate(10, 5));
        System.out.println("10 - 5 = " + subtract.operate(10, 5));
        System.out.println("10 * 5 = " + multiply.operate(10, 5));
        System.out.println("10 / 5 = " + divide.operate(10, 5));
        
        // Using method reference
        MathOperation power = Math::pow;
        System.out.println("2 ^ 3 = " + power.operate(2, 3));
        
        // Lambda with conditional logic
        MathOperation max = (a, b) -> a > b ? a : b;
        MathOperation min = (a, b) -> a < b ? a : b;
        
        System.out.println("Max(7, 3) = " + max.operate(7, 3));
        System.out.println("Min(7, 3) = " + min.operate(7, 3));
        
        System.out.println();
    }
    
    /**
     * Lambda with Collections
     */
    public void lambdaWithCollections() {
        System.out.println("3. Lambda with Collections:");
        
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");
        
        // Using forEach with lambda
        System.out.println("Names:");
        names.forEach(name -> System.out.println("  - " + name));
        
        // Using forEach with method reference
        System.out.println("\nNames (method reference):");
        names.forEach(System.out::println);
        
        // Filtering with lambda
        List<String> longNames = new ArrayList<>();
        names.forEach(name -> {
            if (name.length() > 4) {
                longNames.add(name);
            }
        });
        System.out.println("\nLong names (>4 chars): " + longNames);
        
        // Sorting with lambda
        List<String> sortedNames = new ArrayList<>(names);
        sortedNames.sort((a, b) -> a.compareTo(b));
        System.out.println("Sorted names: " + sortedNames);
        
        // Reverse sorting
        List<String> reverseSorted = new ArrayList<>(names);
        reverseSorted.sort((a, b) -> b.compareTo(a));
        System.out.println("Reverse sorted: " + reverseSorted);
        
        System.out.println();
    }
    
    /**
     * Built-in Functional Interfaces
     */
    public void builtInFunctionalInterfaces() {
        System.out.println("4. Built-in Functional Interfaces:");
        
        // Predicate
        Predicate<String> isLong = s -> s.length() > 5;
        Predicate<String> startsWithA = s -> s.startsWith("A");
        Predicate<String> isLongAndStartsWithA = isLong.and(startsWithA);
        
        List<String> words = Arrays.asList("Apple", "Banana", "Avocado", "Cherry", "Apricot");
        System.out.println("Words: " + words);
        System.out.println("Long words: " + filterList(words, isLong));
        System.out.println("Words starting with 'A': " + filterList(words, startsWithA));
        System.out.println("Long words starting with 'A': " + filterList(words, isLongAndStartsWithA));
        
        // Function
        Function<String, Integer> lengthFunction = String::length;
        Function<String, String> upperCaseFunction = String::toUpperCase;
        Function<String, String> lengthAndUpper = lengthFunction.andThen(len -> "Length: " + len);
        
        System.out.println("\nWord lengths: " + mapList(words, lengthFunction));
        System.out.println("Uppercase words: " + mapList(words, upperCaseFunction));
        System.out.println("Length descriptions: " + mapList(words, lengthAndUpper));
        
        // Consumer
        Consumer<String> printer = System.out::println;
        Consumer<String> prefixPrinter = s -> System.out.print("Word: ");
        Consumer<String> combinedPrinter = prefixPrinter.andThen(printer);
        
        System.out.println("\nPrinting with consumer:");
        words.forEach(combinedPrinter);
        
        // Supplier
        Supplier<Double> randomSupplier = Math::random;
        Supplier<String> greetingSupplier = () -> "Hello from supplier!";
        
        System.out.println("\nRandom numbers:");
        for (int i = 0; i < 3; i++) {
            System.out.println("  " + randomSupplier.get());
        }
        System.out.println("Greeting: " + greetingSupplier.get());
        
        System.out.println();
    }
    
    /**
     * Lambda with Exception Handling
     */
    public void lambdaWithExceptions() {
        System.out.println("5. Lambda with Exception Handling:");
        
        List<String> numbers = Arrays.asList("1", "2", "abc", "4", "5");
        
        // Traditional approach
        for (String num : numbers) {
            try {
                int value = Integer.parseInt(num);
                System.out.println("Parsed: " + value);
            } catch (NumberFormatException e) {
                System.out.println("Failed to parse: " + num);
            }
        }
        
        // Lambda approach with wrapper
        System.out.println("\nUsing lambda wrapper:");
        numbers.forEach(wrapLambda(num -> {
            int value = Integer.parseInt(num);
            System.out.println("Parsed: " + value);
        }));
        
        System.out.println();
    }
    
    /**
     * Variable Capture in Lambda
     */
    public void variableCapture() {
        System.out.println("6. Variable Capture in Lambda:");
        
        String prefix = "Hello, ";
        int multiplier = 2;
        
        // Capturing local variables
        Function<String, String> greeter = name -> prefix + name;
        Function<Integer, Integer> doubler = num -> num * multiplier;
        
        System.out.println(greeter.apply("World"));
        System.out.println("Doubled 5: " + doubler.apply(5));
        
        // Capturing effectively final variables
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = 0;
        
        // This would cause compilation error if we tried to modify sum
        // numbers.forEach(n -> sum += n); // Error: sum is not effectively final
        
        // Correct approach using AtomicInteger
        java.util.concurrent.atomic.AtomicInteger atomicSum = new java.util.concurrent.atomic.AtomicInteger(0);
        numbers.forEach(atomicSum::addAndGet);
        System.out.println("Sum using AtomicInteger: " + atomicSum.get());
        
        System.out.println();
    }
    
    // Helper methods
    private <T> List<T> filterList(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T item : list) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }
    
    private <T, R> List<R> mapList(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T item : list) {
            result.add(function.apply(item));
        }
        return result;
    }
    
    private Consumer<String> wrapLambda(Consumer<String> consumer) {
        return s -> {
            try {
                consumer.accept(s);
            } catch (Exception e) {
                System.out.println("Exception caught: " + e.getMessage());
            }
        };
    }
    
    // Custom functional interface
    @FunctionalInterface
    interface MathOperation {
        double operate(double a, double b);
    }
} 