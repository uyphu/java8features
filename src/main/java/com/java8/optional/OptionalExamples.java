package com.java8.optional;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Comprehensive examples of Optional class in Java 8
 */
public class OptionalExamples {
    
    public static void main(String[] args) {
        OptionalExamples examples = new OptionalExamples();
        
        System.out.println("=== Optional Class Examples ===\n");
        
        examples.creatingOptionals();
        examples.optionalMethods();
        examples.safeNavigation();
        examples.optionalWithStreams();
        examples.optionalChaining();
        examples.optionalBestPractices();
        examples.optionalVsNull();
        examples.optionalWithExceptions();
    }
    
    /**
     * Creating Optional Objects
     */
    public void creatingOptionals() {
        System.out.println("1. Creating Optional Objects:");
        
        // Empty Optional
        Optional<String> empty = Optional.empty();
        System.out.println("Empty optional: " + empty);
        
        // Optional with value
        Optional<String> present = Optional.of("Hello World");
        System.out.println("Present optional: " + present);
        
        // Optional.ofNullable - handles null values
        String nullableString = null;
        Optional<String> nullable = Optional.ofNullable(nullableString);
        System.out.println("Nullable optional (null): " + nullable);
        
        String nonNullString = "Not null";
        Optional<String> nonNull = Optional.ofNullable(nonNullString);
        System.out.println("Nullable optional (not null): " + nonNull);
        
        // Creating from collections
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        Optional<String> first = names.stream().findFirst();
        System.out.println("First name: " + first);
        
        Optional<String> longName = names.stream()
                .filter(name -> name.length() > 5)
                .findFirst();
        System.out.println("Long name: " + longName);
        
        System.out.println();
    }
    
    /**
     * Optional Methods
     */
    public void optionalMethods() {
        System.out.println("2. Optional Methods:");
        
        Optional<String> present = Optional.of("Hello");
        Optional<String> empty = Optional.empty();
        
        // isPresent() and isEmpty()
        System.out.println("Present optional is present: " + present.isPresent());
        System.out.println("Present optional is empty: " + present.isEmpty());
        System.out.println("Empty optional is present: " + empty.isPresent());
        System.out.println("Empty optional is empty: " + empty.isEmpty());
        
        // get() - throws exception if empty
        System.out.println("Present optional value: " + present.get());
        try {
            System.out.println("Empty optional value: " + empty.get());
        } catch (NoSuchElementException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
        
        // orElse() - provides default value
        String presentValue = present.orElse("Default");
        String emptyValue = empty.orElse("Default");
        System.out.println("Present orElse: " + presentValue);
        System.out.println("Empty orElse: " + emptyValue);
        
        // orElseGet() - lazy evaluation
        String presentLazy = present.orElseGet(() -> "Computed default");
        String emptyLazy = empty.orElseGet(() -> "Computed default");
        System.out.println("Present orElseGet: " + presentLazy);
        System.out.println("Empty orElseGet: " + emptyLazy);
        
        // orElseThrow() - throws custom exception
        try {
            String presentThrow = present.orElseThrow(() -> new RuntimeException("Not found"));
            System.out.println("Present orElseThrow: " + presentThrow);
            
            String emptyThrow = empty.orElseThrow(() -> new RuntimeException("Not found"));
            System.out.println("Empty orElseThrow: " + emptyThrow);
        } catch (RuntimeException e) {
            System.out.println("Exception thrown: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    /**
     * Safe Navigation with Optional
     */
    public void safeNavigation() {
        System.out.println("3. Safe Navigation:");
        
        // Traditional null checking
        String traditionalResult = getTraditionalValue();
        if (traditionalResult != null) {
            String upperCase = traditionalResult.toUpperCase();
            System.out.println("Traditional approach: " + upperCase);
        } else {
            System.out.println("Traditional approach: null");
        }
        
        // Optional approach
        Optional<String> optionalResult = getOptionalValue();
        String optionalUpperCase = optionalResult
                .map(String::toUpperCase)
                .orElse("No value");
        System.out.println("Optional approach: " + optionalUpperCase);
        
        // Nested object navigation
        Person person = new Person("Alice", new Address("123 Main St", "New York"));
        String city = Optional.ofNullable(person)
                .map(Person::getAddress)
                .map(Address::getCity)
                .orElse("Unknown");
        System.out.println("Person city: " + city);
        
        // Null person
        Person nullPerson = null;
        String nullCity = Optional.ofNullable(nullPerson)
                .map(Person::getAddress)
                .map(Address::getCity)
                .orElse("Unknown");
        System.out.println("Null person city: " + nullCity);
        
        System.out.println();
    }
    
    /**
     * Optional with Streams
     */
    public void optionalWithStreams() {
        System.out.println("4. Optional with Streams:");
        
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");
        
        // Finding elements
        Optional<String> first = names.stream().findFirst();
        Optional<String> any = names.stream().findAny();
        Optional<String> longName = names.stream()
                .filter(name -> name.length() > 5)
                .findFirst();
        
        System.out.println("First: " + first.orElse("None"));
        System.out.println("Any: " + any.orElse("None"));
        System.out.println("Long name: " + longName.orElse("None"));
        
        // Converting Optional to Stream
        Optional<String> optional = Optional.of("Hello");
        List<String> streamed = optional.stream().collect(Collectors.toList());
        System.out.println("Optional to stream: " + streamed);
        
        // Multiple optionals
        List<Optional<String>> optionals = Arrays.asList(
                Optional.of("Alice"),
                Optional.empty(),
                Optional.of("Bob"),
                Optional.empty(),
                Optional.of("Charlie")
        );
        
        List<String> presentValues = optionals.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        System.out.println("Present values: " + presentValues);
        
        // Optional with reduce
        Optional<Integer> max = Stream.of(1, 2, 3, 4, 5)
                .reduce(Integer::max);
        System.out.println("Max value: " + max.orElse(0));
        
        System.out.println();
    }
    
    /**
     * Optional Chaining
     */
    public void optionalChaining() {
        System.out.println("5. Optional Chaining:");
        
        // Method chaining
        Optional<String> result = Optional.of("hello world")
                .map(String::toUpperCase)
                .map(s -> s.replace(" ", "_"))
                .filter(s -> s.length() > 5);
        
        System.out.println("Chained result: " + result.orElse("No result"));
        
        // Complex chaining with custom objects
        Optional<Person> person = Optional.of(new Person("Alice", new Address("123 Main St", "New York")));
        
        String formattedAddress = person
                .map(Person::getAddress)
                .map(Address::getStreet)
                .map(street -> "Street: " + street)
                .orElse("No address");
        
        System.out.println("Formatted address: " + formattedAddress);
        
        // Conditional chaining
        Optional<String> conditional = Optional.of("test")
                .filter(s -> s.length() > 3)
                .map(String::toUpperCase)
                .filter(s -> s.contains("E"));
        
        System.out.println("Conditional result: " + conditional.orElse("No match"));
        
        // FlatMap for nested optionals
        Optional<Optional<String>> nested = Optional.of(Optional.of("nested"));
        Optional<String> flattened = nested.flatMap(opt -> opt);
        System.out.println("Flattened: " + flattened.orElse("None"));
        
        System.out.println();
    }
    
    /**
     * Optional Best Practices
     */
    public void optionalBestPractices() {
        System.out.println("6. Optional Best Practices:");
        
        // Don't use Optional as a field
        // Bad: private Optional<String> name;
        // Good: private String name;
        
        // Don't use Optional to wrap collections
        // Bad: Optional<List<String>> names;
        // Good: List<String> names; (empty list instead of Optional)
        
        // Use Optional for return types
        Optional<String> validReturn = findUserById(1);
        Optional<String> invalidReturn = findUserById(999);
        
        System.out.println("Valid user: " + validReturn.orElse("Not found"));
        System.out.println("Invalid user: " + invalidReturn.orElse("Not found"));
        
        // Use Optional for method parameters sparingly
        processUser(Optional.of("Alice"));
        processUser(Optional.empty());
        
        // Prefer orElseGet over orElse for expensive operations
        String expensive = Optional.of("present")
                .orElseGet(() -> expensiveOperation());
        System.out.println("Expensive operation result: " + expensive);
        
        System.out.println();
    }
    
    /**
     * Optional vs Null
     */
    public void optionalVsNull() {
        System.out.println("7. Optional vs Null:");
        
        // Null approach
        String nullValue = getNullValue();
        if (nullValue != null) {
            System.out.println("Null approach: " + nullValue.toUpperCase());
        } else {
            System.out.println("Null approach: No value");
        }
        
        // Optional approach
        Optional<String> optionalValue = getOptionalValue();
        String result = optionalValue
                .map(String::toUpperCase)
                .orElse("No value");
        System.out.println("Optional approach: " + result);
        
        // Null pointer exceptions
        try {
            String nullString = null;
            String upper = nullString.toUpperCase(); // Throws NullPointerException
        } catch (NullPointerException e) {
            System.out.println("NullPointerException caught: " + e.getMessage());
        }
        
        // Optional prevents null pointer exceptions
        Optional<String> safeOptional = Optional.ofNullable(null);
        String safeResult = safeOptional
                .map(String::toUpperCase)
                .orElse("Safe default");
        System.out.println("Safe optional result: " + safeResult);
        
        System.out.println();
    }
    
    /**
     * Optional with Exceptions
     */
    public void optionalWithExceptions() {
        System.out.println("8. Optional with Exceptions:");
        
        // Wrapping exceptions in Optional
        Optional<Integer> parsed = parseInteger("123");
        Optional<Integer> invalid = parseInteger("abc");
        
        System.out.println("Valid parse: " + parsed.orElse(-1));
        System.out.println("Invalid parse: " + invalid.orElse(-1));
        
        // Optional with custom exception handling
        try {
            String result = Optional.of("test")
                    .orElseThrow(() -> new CustomException("Custom error"));
            System.out.println("Result: " + result);
        } catch (CustomException e) {
            System.out.println("Custom exception: " + e.getMessage());
        }
        
        // Optional with try-catch wrapper
        Optional<String> safeResult = safeOperation(() -> riskyOperation());
        System.out.println("Safe operation result: " + safeResult.orElse("Failed"));
        
        System.out.println();
    }
    
    // Helper methods
    private String getTraditionalValue() {
        return Math.random() > 0.5 ? "Hello" : null;
    }
    
    private Optional<String> getOptionalValue() {
        return Math.random() > 0.5 ? Optional.of("Hello") : Optional.empty();
    }
    
    private Optional<String> findUserById(int id) {
        if (id == 1) {
            return Optional.of("Alice");
        }
        return Optional.empty();
    }
    
    private void processUser(Optional<String> user) {
        String name = user.orElse("Unknown");
        System.out.println("Processing user: " + name);
    }
    
    private String expensiveOperation() {
        System.out.println("Performing expensive operation...");
        return "Expensive result";
    }
    
    private String getNullValue() {
        return null;
    }
    
    private Optional<Integer> parseInteger(String value) {
        try {
            return Optional.of(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
    
    private String riskyOperation() {
        if (Math.random() > 0.5) {
            throw new RuntimeException("Random failure");
        }
        return "Success";
    }
    
    private <T> Optional<T> safeOperation(Supplier<T> supplier) {
        try {
            return Optional.of(supplier.get());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
    
    // Helper classes
    static class Person {
        private String name;
        private Address address;
        
        public Person(String name, Address address) {
            this.name = name;
            this.address = address;
        }
        
        public String getName() { return name; }
        public Address getAddress() { return address; }
    }
    
    static class Address {
        private String street;
        private String city;
        
        public Address(String street, String city) {
            this.street = street;
            this.city = city;
        }
        
        public String getStreet() { return street; }
        public String getCity() { return city; }
    }
    
    static class CustomException extends Exception {
        public CustomException(String message) {
            super(message);
        }
    }
} 