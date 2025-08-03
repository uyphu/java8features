package com.java8.defaultmethods;

import java.util.*;
import java.util.function.Predicate;

/**
 * Comprehensive examples of Default Methods in Java 8
 */
public class DefaultMethodExamples {
    
    public static void main(String[] args) {
        DefaultMethodExamples examples = new DefaultMethodExamples();
        
        System.out.println("=== Default Methods Examples ===\n");
        
        examples.basicDefaultMethods();
        examples.multipleInheritance();
        examples.defaultMethodsWithCollections();
        examples.defaultMethodsInFunctionalInterfaces();
        examples.defaultMethodsWithStreams();
        examples.defaultMethodsBestPractices();
        examples.defaultMethodsVsAbstractClasses();
    }
    
    /**
     * Basic Default Methods
     */
    public void basicDefaultMethods() {
        System.out.println("1. Basic Default Methods:");
        
        // Using default method from interface
        Vehicle car = new Car("Toyota", "Camry");
        System.out.println("Vehicle info: " + car.getInfo());
        System.out.println("Is running: " + car.isRunning());
        
        // Default method with parameters
        car.start();
        System.out.println("After starting: " + car.isRunning());
        
        // Default method that calls other methods
        car.displayStatus();
        
        // Overriding default method
        Vehicle motorcycle = new Motorcycle("Honda", "CBR");
        motorcycle.displayStatus(); // Uses overridden method
        
        System.out.println();
    }
    
    /**
     * Multiple Inheritance with Default Methods
     */
    public void multipleInheritance() {
        System.out.println("2. Multiple Inheritance with Default Methods:");
        
        // Class implementing multiple interfaces with default methods
        SmartPhone smartPhone = new SmartPhone("iPhone", "15");
        
        // Methods from Device interface
        System.out.println("Device info: " + smartPhone.getDeviceInfo());
        System.out.println("Is powered on: " + smartPhone.isPoweredOn());
        
        // Methods from Phone interface
        System.out.println("Phone number: " + smartPhone.getPhoneNumber());
        smartPhone.makeCall("123-456-7890");
        
        // Methods from Computer interface
        System.out.println("OS: " + smartPhone.getOperatingSystem());
        smartPhone.runApplication("Safari");
        
        // Resolving conflicts
        smartPhone.powerOn(); // Uses Device's default method
        smartPhone.displayInfo(); // Uses overridden method in SmartPhone
        
        System.out.println();
    }
    
    /**
     * Default Methods with Collections
     */
    public void defaultMethodsWithCollections() {
        System.out.println("3. Default Methods with Collections:");
        
        // List interface default methods
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        
        // forEach default method
        System.out.println("Names using forEach:");
        names.forEach(name -> System.out.println("  - " + name));
        
        // sort default method
        names.sort(String::compareTo);
        System.out.println("Sorted names: " + names);
        
        // replaceAll default method
        names.replaceAll(String::toUpperCase);
        System.out.println("Uppercase names: " + names);
        
        // removeIf default method
        names.removeIf(name -> name.length() > 4);
        System.out.println("Names after removing long ones: " + names);
        
        // Map interface default methods
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 85);
        scores.put("Bob", 92);
        scores.put("Charlie", 78);
        
        // forEach default method
        System.out.println("\nScores:");
        scores.forEach((name, score) -> System.out.println("  " + name + ": " + score));
        
        // getOrDefault default method
        int aliceScore = scores.getOrDefault("Alice", 0);
        int davidScore = scores.getOrDefault("David", 0);
        System.out.println("Alice's score: " + aliceScore);
        System.out.println("David's score: " + davidScore);
        
        // putIfAbsent default method
        scores.putIfAbsent("Alice", 100); // Won't replace existing value
        scores.putIfAbsent("David", 88);  // Will add new entry
        System.out.println("Scores after putIfAbsent: " + scores);
        
        // replace default method
        scores.replace("Bob", 95);
        System.out.println("Bob's new score: " + scores.get("Bob"));
        
        // replaceAll default method
        scores.replaceAll((name, score) -> score + 5);
        System.out.println("Scores after bonus: " + scores);
        
        System.out.println();
    }
    
    /**
     * Default Methods in Functional Interfaces
     */
    public void defaultMethodsInFunctionalInterfaces() {
        System.out.println("4. Default Methods in Functional Interfaces:");
        
        // Custom functional interface with default methods
        Validator<String> stringValidator = new StringValidator();
        
        // Abstract method
        boolean isValid = stringValidator.validate("test123");
        System.out.println("Is 'test123' valid: " + isValid);
        
        // Default methods
        boolean isNotEmpty = stringValidator.isNotEmpty("hello");
        boolean isNotBlank = stringValidator.isNotBlank("  hello  ");
        System.out.println("Is not empty: " + isNotEmpty);
        System.out.println("Is not blank: " + isNotBlank);
        
        // Chaining default methods
        boolean complexValidation = stringValidator
                .and(s -> stringValidator.isNotBlank(s))
                .and(s -> s.length() > 3)
                .validate("test");
        System.out.println("Complex validation result: " + complexValidation);
        
        // Using with lambda
        Validator<Integer> numberValidator = (Integer n) -> n > 0;
        boolean positiveNumber = numberValidator
                .and(n -> n < 100)
                .and(n -> n % 2 == 0)
                .validate(50);
        System.out.println("Is 50 a valid positive even number < 100: " + positiveNumber);
        
        System.out.println();
    }
    
    /**
     * Default Methods with Streams
     */
    public void defaultMethodsWithStreams() {
        System.out.println("5. Default Methods with Streams:");
        
        // Custom collection with default methods
        CustomList<String> customList = new CustomList<>();
        customList.add("Apple");
        customList.add("Banana");
        customList.add("Cherry");
        customList.add("Date");
        
        // Using default methods that work with streams
        System.out.println("All items: " + customList.getAllItems());
        System.out.println("Filtered items (length > 5): " + customList.filterItems(s -> s.length() > 5));
        System.out.println("Transformed items: " + customList.transformItems(String::toUpperCase));
        
        // Custom stream operations
        List<String> result = customList.stream()
                .filter(s -> s.startsWith("A"))
                .map(String::toUpperCase)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println("Stream result: " + result);
        
        // Using default methods for statistics
        System.out.println("Total items: " + customList.getTotalCount());
        System.out.println("Average length: " + customList.getAverageLength());
        System.out.println("Longest item: " + customList.getLongestItem());
        
        System.out.println();
    }
    
    /**
     * Default Methods Best Practices
     */
    public void defaultMethodsBestPractices() {
        System.out.println("6. Default Methods Best Practices:");
        
        // Good: Default method providing common implementation
        Logger consoleLogger = new ConsoleLogger();
        consoleLogger.log("Info message");
        consoleLogger.logError("Error message");
        consoleLogger.logWarning("Warning message");
        
        // Good: Default method that can be overridden
        Logger fileLogger = new FileLogger();
        fileLogger.log("Info message");
        fileLogger.logError("Error message"); // Overridden
        fileLogger.logWarning("Warning message");
        
        // Good: Default method using other interface methods
        Calculator calculator = new BasicCalculator();
        System.out.println("Calculator result: " + calculator.calculate(10, 5));
        System.out.println("Calculator info: " + calculator.getCalculatorInfo());
        
        // Good: Default method providing utility functionality
        DataProcessor<String> stringProcessor = new StringProcessor();
        List<String> data = Arrays.asList("hello", "world", "java", "8");
        List<String> processed = stringProcessor.processData(data);
        System.out.println("Processed data: " + processed);
        
        System.out.println();
    }
    
    /**
     * Default Methods vs Abstract Classes
     */
    public void defaultMethodsVsAbstractClasses() {
        System.out.println("7. Default Methods vs Abstract Classes:");
        
        // Using interface with default methods
        Shape circle = new Circle(5.0);
        System.out.println("Circle area: " + circle.getArea());
        System.out.println("Circle perimeter: " + circle.getPerimeter());
        System.out.println("Circle description: " + circle.getDescription());
        
        // Using abstract class
        Animal dog = new Dog("Buddy");
        dog.makeSound();
        dog.sleep();
        System.out.println("Dog info: " + dog.getInfo());
        
        // Comparison
        System.out.println("\nInterface with default methods:");
        System.out.println("- Can have multiple inheritance");
        System.out.println("- Can only have public methods");
        System.out.println("- Cannot have instance fields");
        System.out.println("- Good for behavior contracts");
        
        System.out.println("\nAbstract classes:");
        System.out.println("- Single inheritance only");
        System.out.println("- Can have any access modifiers");
        System.out.println("- Can have instance fields");
        System.out.println("- Good for shared state and behavior");
        
        System.out.println();
    }
    
    // Interface definitions and implementations
    
    interface Vehicle {
        String getBrand();
        String getModel();
        
        // Default method
        default String getInfo() {
            return getBrand() + " " + getModel();
        }
        
        // Default method with state
        default boolean isRunning() {
            return false; // Default implementation
        }
        
        // Default method that can be called
        default void start() {
            System.out.println("Starting " + getInfo());
        }
        
        // Default method that calls other methods
        default void displayStatus() {
            System.out.println(getInfo() + " is " + (isRunning() ? "running" : "stopped"));
        }
    }
    
    static class Car implements Vehicle {
        private String brand;
        private String model;
        private boolean running = false;
        
        public Car(String brand, String model) {
            this.brand = brand;
            this.model = model;
        }
        
        @Override
        public String getBrand() { return brand; }
        
        @Override
        public String getModel() { return model; }
        
        @Override
        public boolean isRunning() { return running; }
        
        @Override
        public void start() {
            running = true;
            System.out.println("Car engine started");
        }
    }
    
    static class Motorcycle implements Vehicle {
        private String brand;
        private String model;
        
        public Motorcycle(String brand, String model) {
            this.brand = brand;
            this.model = model;
        }
        
        @Override
        public String getBrand() { return brand; }
        
        @Override
        public String getModel() { return model; }
        
        @Override
        public void displayStatus() {
            System.out.println("Motorcycle " + getInfo() + " is ready to ride!");
        }
    }
    
    // Multiple inheritance example
    interface Device {
        String getDeviceInfo();
        default boolean isPoweredOn() { return false; }
        default void powerOn() { System.out.println("Device powered on"); }
        default void displayInfo() { System.out.println("Device: " + getDeviceInfo()); }
    }
    
    interface Phone {
        String getPhoneNumber();
        default void makeCall(String number) { System.out.println("Calling " + number); }
        default void displayInfo() { System.out.println("Phone: " + getPhoneNumber()); }
    }
    
    interface Computer {
        String getOperatingSystem();
        default void runApplication(String app) { System.out.println("Running " + app); }
    }
    
    static class SmartPhone implements Device, Phone, Computer {
        private String brand;
        private String model;
        private String phoneNumber;
        private String os;
        private boolean poweredOn = false;
        
        public SmartPhone(String brand, String model) {
            this.brand = brand;
            this.model = model;
            this.phoneNumber = "555-0123";
            this.os = "iOS";
        }
        
        @Override
        public String getDeviceInfo() { return brand + " " + model; }
        
        @Override
        public String getPhoneNumber() { return phoneNumber; }
        
        @Override
        public String getOperatingSystem() { return os; }
        
        @Override
        public boolean isPoweredOn() { return poweredOn; }
        
        @Override
        public void powerOn() {
            poweredOn = true;
            Device.super.powerOn(); // Explicitly call Device's default method
        }
        
        @Override
        public void displayInfo() {
            System.out.println("SmartPhone: " + getDeviceInfo() + " | " + getPhoneNumber() + " | " + getOperatingSystem());
        }
    }
    
    // Functional interface with default methods
    @FunctionalInterface
    interface Validator<T> {
        boolean validate(T t);
        
        default Validator<T> and(Validator<T> other) {
            return t -> validate(t) && other.validate(t);
        }
        
        default Validator<T> or(Validator<T> other) {
            return t -> validate(t) || other.validate(t);
        }
        
        default Validator<T> negate() {
            return t -> !validate(t);
        }
        
        default boolean isNotEmpty(String s) {
            return s != null && !s.isEmpty();
        }
        
        default boolean isNotBlank(String s) {
            return s != null && !s.trim().isEmpty();
        }
    }
    
    static class StringValidator implements Validator<String> {
        @Override
        public boolean validate(String s) {
            return s != null && s.length() >= 3;
        }
    }
    
    // Custom collection with default methods
    static class CustomList<T> extends ArrayList<T> {
        public List<T> getAllItems() {
            return new ArrayList<>(this);
        }
        
        public List<T> filterItems(Predicate<T> predicate) {
            return stream().filter(predicate).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        }
        
        public <R> List<R> transformItems(java.util.function.Function<T, R> transformer) {
            return stream().map(transformer).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        }
        
        public int getTotalCount() {
            return size();
        }
        
        public double getAverageLength() {
            if (isEmpty()) return 0.0;
            return stream()
                    .mapToInt(item -> item.toString().length())
                    .average()
                    .orElse(0.0);
        }
        
        public T getLongestItem() {
            return stream()
                    .max(Comparator.comparing(item -> item.toString().length()))
                    .orElse(null);
        }
    }
    
    // Logger interface with default methods
    interface Logger {
        void log(String message);
        
        default void logError(String message) {
            log("ERROR: " + message);
        }
        
        default void logWarning(String message) {
            log("WARNING: " + message);
        }
    }
    
    static class ConsoleLogger implements Logger {
        @Override
        public void log(String message) {
            System.out.println("[CONSOLE] " + message);
        }
    }
    
    static class FileLogger implements Logger {
        @Override
        public void log(String message) {
            System.out.println("[FILE] " + message);
        }
        
        @Override
        public void logError(String message) {
            System.out.println("[FILE ERROR] " + message + " - " + new Date());
        }
    }
    
    // Calculator interface with default methods
    interface Calculator {
        int add(int a, int b);
        int subtract(int a, int b);
        
        default int calculate(int a, int b) {
            return add(a, b);
        }
        
        default String getCalculatorInfo() {
            return "Calculator with add: " + add(1, 2) + ", subtract: " + subtract(5, 3);
        }
    }
    
    static class BasicCalculator implements Calculator {
        @Override
        public int add(int a, int b) { return a + b; }
        
        @Override
        public int subtract(int a, int b) { return a - b; }
    }
    
    // Data processor interface with default methods
    interface DataProcessor<T> {
        List<T> processData(List<T> data);
        
        default List<T> filterData(List<T> data, Predicate<T> predicate) {
            return data.stream().filter(predicate).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        }
        
        default <R> List<R> transformData(List<T> data, java.util.function.Function<T, R> transformer) {
            return data.stream().map(transformer).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        }
    }
    
    static class StringProcessor implements DataProcessor<String> {
        @Override
        public List<String> processData(List<String> data) {
            return transformData(data, String::toUpperCase);
        }
    }
    
    // Shape interface with default methods
    interface Shape {
        double getArea();
        double getPerimeter();
        
        default String getDescription() {
            return "Shape with area: " + getArea() + ", perimeter: " + getPerimeter();
        }
    }
    
    static class Circle implements Shape {
        private double radius;
        
        public Circle(double radius) {
            this.radius = radius;
        }
        
        @Override
        public double getArea() {
            return Math.PI * radius * radius;
        }
        
        @Override
        public double getPerimeter() {
            return 2 * Math.PI * radius;
        }
    }
    
    // Abstract class comparison
    abstract static class Animal {
        protected String name;
        
        public Animal(String name) {
            this.name = name;
        }
        
        public abstract void makeSound();
        
        public void sleep() {
            System.out.println(name + " is sleeping");
        }
        
        public String getInfo() {
            return "Animal: " + name;
        }
    }
    
    static class Dog extends Animal {
        public Dog(String name) {
            super(name);
        }
        
        @Override
        public void makeSound() {
            System.out.println(name + " says: Woof!");
        }
    }
} 