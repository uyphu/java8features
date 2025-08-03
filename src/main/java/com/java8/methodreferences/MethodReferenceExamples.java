package com.java8.methodreferences;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * Comprehensive examples of Method References in Java 8
 */
public class MethodReferenceExamples {
    
    public static void main(String[] args) {
        MethodReferenceExamples examples = new MethodReferenceExamples();
        
        System.out.println("=== Method References Examples ===\n");
        
        examples.staticMethodReferences();
        examples.instanceMethodReferences();
        examples.constructorReferences();
        examples.arrayConstructorReferences();
        examples.methodReferencesWithStreams();
        examples.comparisonWithLambdas();
        examples.complexMethodReferences();
    }
    
    /**
     * Static Method References
     */
    public void staticMethodReferences() {
        System.out.println("1. Static Method References:");
        
        // Static method reference
        Function<String, Integer> parseInt = Integer::parseInt;
        System.out.println("Parsed: " + parseInt.apply("123"));
        
        // Static method reference with multiple parameters
        BiFunction<String, Integer, Integer> parseIntWithRadix = Integer::parseInt;
        System.out.println("Parsed with radix: " + parseIntWithRadix.apply("FF", 16));
        
        // Static method reference for utility methods
        Function<String, String> upperCase = String::valueOf;
        System.out.println("String value: " + upperCase.apply("hello"));
        
        // Static method reference for math operations
        Function<Double, Double> sqrt = Math::sqrt;
        Function<Double, Double> abs = Math::abs;
        System.out.println("Square root of 16: " + sqrt.apply(16.0));
        System.out.println("Absolute value of -5: " + abs.apply(-5.0));
        
        // Static method reference for custom static methods
        Function<String, String> reverse = StringUtils::reverse;
        System.out.println("Reversed: " + reverse.apply("hello"));
        
        // Static method reference with Predicate
        Predicate<String> isEmpty = StringUtils::isEmpty;
        System.out.println("Is empty '': " + isEmpty.test(""));
        System.out.println("Is empty 'hello': " + isEmpty.test("hello"));
        
        System.out.println();
    }
    
    /**
     * Instance Method References
     */
    public void instanceMethodReferences() {
        System.out.println("2. Instance Method References:");
        
        // Instance method reference on specific object
        String prefix = "Hello, ";
        Function<String, String> greeter = prefix::concat;
        System.out.println("Greeting: " + greeter.apply("World"));
        
        // Instance method reference on arbitrary object
        Function<String, String> toUpperCase = String::toUpperCase;
        Function<String, String> toLowerCase = String::toLowerCase;
        System.out.println("Uppercase: " + toUpperCase.apply("hello"));
        System.out.println("Lowercase: " + toLowerCase.apply("WORLD"));
        
        // Instance method reference for length
        Function<String, Integer> length = String::length;
        System.out.println("Length of 'hello': " + length.apply("hello"));
        
        // Instance method reference for contains
        BiPredicate<String, String> contains = String::contains;
        System.out.println("Contains 'lo' in 'hello': " + contains.test("hello", "lo"));
        
        // Instance method reference for startsWith
        BiPredicate<String, String> startsWith = String::startsWith;
        System.out.println("Starts with 'he': " + startsWith.test("hello", "he"));
        
        // Instance method reference for equals
        BiPredicate<String, String> equals = String::equals;
        System.out.println("Equals 'hello': " + equals.test("hello", "hello"));
        
        // Instance method reference for custom objects
        List<Person> people = Arrays.asList(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 35)
        );
        
        Function<Person, String> getName = Person::getName;
        Function<Person, Integer> getAge = Person::getAge;
        
        System.out.println("Names: " + people.stream().map(getName).collect(Collectors.toList()));
        System.out.println("Ages: " + people.stream().map(getAge).collect(Collectors.toList()));
        
        System.out.println();
    }
    
    /**
     * Constructor References
     */
    public void constructorReferences() {
        System.out.println("3. Constructor References:");
        
        // Constructor reference for String
        Supplier<String> newString = String::new;
        System.out.println("New string: '" + newString.get() + "'");
        
        // Constructor reference with parameters
        Function<String, String> stringConstructor = String::new;
        System.out.println("String from char array: " + stringConstructor.apply("hello"));
        
        // Constructor reference for custom class
        Supplier<Person> personSupplier = Person::new;
        Person newPerson = personSupplier.get();
        System.out.println("New person: " + newPerson);
        
        // Constructor reference with parameters
        BiFunction<String, Integer, Person> personConstructor = Person::new;
        Person alice = personConstructor.apply("Alice", 25);
        System.out.println("Constructed person: " + alice);
        
        // Constructor reference for wrapper classes
        Function<String, Integer> integerConstructor = Integer::new;
        System.out.println("Integer from string: " + integerConstructor.apply("123"));
        
        // Constructor reference for collections
        Supplier<ArrayList<String>> listSupplier = ArrayList::new;
        ArrayList<String> newList = listSupplier.get();
        newList.add("Hello");
        System.out.println("New list: " + newList);
        
        // Constructor reference for HashMap
        Supplier<HashMap<String, Integer>> mapSupplier = HashMap::new;
        HashMap<String, Integer> newMap = mapSupplier.get();
        newMap.put("key", 1);
        System.out.println("New map: " + newMap);
        
        System.out.println();
    }
    
    /**
     * Array Constructor References
     */
    public void arrayConstructorReferences() {
        System.out.println("4. Array Constructor References:");
        
        // Array constructor reference
        IntFunction<String[]> stringArrayConstructor = String[]::new;
        String[] stringArray = stringArrayConstructor.apply(3);
        stringArray[0] = "Hello";
        stringArray[1] = "World";
        stringArray[2] = "Java";
        System.out.println("String array: " + Arrays.toString(stringArray));
        
        // Integer array constructor
        IntFunction<Integer[]> intArrayConstructor = Integer[]::new;
        Integer[] intArray = intArrayConstructor.apply(5);
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = i * 2;
        }
        System.out.println("Integer array: " + Arrays.toString(intArray));
        
        // Person array constructor
        IntFunction<Person[]> personArrayConstructor = Person[]::new;
        Person[] personArray = personArrayConstructor.apply(2);
        personArray[0] = new Person("Alice", 25);
        personArray[1] = new Person("Bob", 30);
        System.out.println("Person array: " + Arrays.toString(personArray));
        
        // 2D array constructor
        IntFunction<int[][]> matrixConstructor = int[][]::new;
        int[][] matrix = matrixConstructor.apply(3);
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new int[3];
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = i + j;
            }
        }
        System.out.println("Matrix:");
        for (int[] row : matrix) {
            System.out.println("  " + Arrays.toString(row));
        }
        
        System.out.println();
    }
    
    /**
     * Method References with Streams
     */
    public void methodReferencesWithStreams() {
        System.out.println("5. Method References with Streams:");
        
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");
        
        // Method reference in forEach
        System.out.println("Names:");
        names.forEach(System.out::println);
        
        // Method reference in map
        List<String> upperCaseNames = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Uppercase names: " + upperCaseNames);
        
        // Method reference in filter
        List<String> longNames = names.stream()
                .filter(name -> name.length() > 4)
                .collect(Collectors.toList());
        System.out.println("Long names: " + longNames);
        
        // Method reference in sorted
        List<String> sortedNames = names.stream()
                .sorted(String::compareTo)
                .collect(Collectors.toList());
        System.out.println("Sorted names: " + sortedNames);
        
        // Method reference in reduce
        Optional<String> concatenated = names.stream()
                .reduce(String::concat);
        System.out.println("Concatenated: " + concatenated.orElse(""));
        
        // Method reference with custom objects
        List<Person> people = Arrays.asList(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 35)
        );
        
        // Extract names using method reference
        List<String> personNames = people.stream()
                .map(Person::getName)
                .collect(Collectors.toList());
        System.out.println("Person names: " + personNames);
        
        // Extract ages using method reference
        List<Integer> personAges = people.stream()
                .map(Person::getAge)
                .collect(Collectors.toList());
        System.out.println("Person ages: " + personAges);
        
        // Sort by age using method reference
        List<Person> sortedByAge = people.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .collect(Collectors.toList());
        System.out.println("Sorted by age: " + sortedByAge);
        
        System.out.println();
    }
    
    /**
     * Comparison with Lambdas
     */
    public void comparisonWithLambdas() {
        System.out.println("6. Comparison with Lambdas:");
        
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        
        // Lambda vs Method Reference
        System.out.println("Using lambda:");
        names.forEach(name -> System.out.println(name));
        
        System.out.println("Using method reference:");
        names.forEach(System.out::println);
        
        // Lambda vs Method Reference for mapping
        List<String> upperCaseLambda = names.stream()
                .map(name -> name.toUpperCase())
                .collect(Collectors.toList());
        System.out.println("Lambda uppercase: " + upperCaseLambda);
        
        List<String> upperCaseMethodRef = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Method ref uppercase: " + upperCaseMethodRef);
        
        // Lambda vs Method Reference for filtering
        List<String> longNamesLambda = names.stream()
                .filter(name -> name.length() > 4)
                .collect(Collectors.toList());
        System.out.println("Lambda long names: " + longNamesLambda);
        
        // Method reference with custom predicate
        Predicate<String> isLong = name -> name.length() > 4;
        List<String> longNamesMethodRef = names.stream()
                .filter(isLong)
                .collect(Collectors.toList());
        System.out.println("Method ref long names: " + longNamesMethodRef);
        
        // Lambda vs Method Reference for sorting
        List<String> sortedLambda = names.stream()
                .sorted((a, b) -> a.compareTo(b))
                .collect(Collectors.toList());
        System.out.println("Lambda sorted: " + sortedLambda);
        
        List<String> sortedMethodRef = names.stream()
                .sorted(String::compareTo)
                .collect(Collectors.toList());
        System.out.println("Method ref sorted: " + sortedMethodRef);
        
        System.out.println();
    }
    
    /**
     * Complex Method References
     */
    public void complexMethodReferences() {
        System.out.println("7. Complex Method References:");
        
        // Method reference with bound parameters
        String prefix = "Hello, ";
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        
        List<String> greetings = names.stream()
                .map(prefix::concat)
                .collect(Collectors.toList());
        System.out.println("Greetings: " + greetings);
        
        // Method reference with unbound parameters
        BiFunction<String, String, String> concat = String::concat;
        System.out.println("Concatenated: " + concat.apply("Hello, ", "World"));
        
        // Method reference for static utility methods
        List<String> words = Arrays.asList("hello", "world", "java", "8");
        List<String> capitalized = words.stream()
                .map(StringUtils::capitalize)
                .collect(Collectors.toList());
        System.out.println("Capitalized: " + capitalized);
        
        // Method reference for instance methods on different types
        List<Person> people = Arrays.asList(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 35)
        );
        
        // Extract and format names
        List<String> formattedNames = people.stream()
                .map(Person::getName)
                .map(String::toUpperCase)
                .map(name -> "Name: " + name)
                .collect(Collectors.toList());
        System.out.println("Formatted names: " + formattedNames);
        
        // Method reference with Optional
        Optional<String> optional = Optional.of("hello");
        String result = optional
                .map(String::toUpperCase)
                .orElse("DEFAULT");
        System.out.println("Optional result: " + result);
        
        // Method reference with custom functional interfaces
        StringProcessor processor = String::toUpperCase;
        System.out.println("Processed: " + processor.process("hello world"));
        
        // Method reference for constructor with factory pattern
        PersonFactory factory = Person::new;
        Person person = factory.create("David", 28);
        System.out.println("Factory created: " + person);
        
        System.out.println();
    }
    
    // Helper classes and interfaces
    static class StringUtils {
        public static String reverse(String str) {
            return new StringBuilder(str).reverse().toString();
        }
        
        public static boolean isEmpty(String str) {
            return str == null || str.isEmpty();
        }
        
        public static String capitalize(String str) {
            if (str == null || str.isEmpty()) {
                return str;
            }
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
    }
    
    static class Person {
        private String name;
        private int age;
        
        public Person() {
            this.name = "Unknown";
            this.age = 0;
        }
        
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        public String getName() { return name; }
        public int getAge() { return age; }
        
        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }
    
    @FunctionalInterface
    interface StringProcessor {
        String process(String input);
    }
    
    @FunctionalInterface
    interface PersonFactory {
        Person create(String name, int age);
    }
} 