package com.java8.streams;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.*;

/**
 * Comprehensive examples of Streams API in Java 8
 */
public class StreamExamples {
    
    public static void main(String[] args) {
        StreamExamples examples = new StreamExamples();
        
        System.out.println("=== Streams API Examples ===\n");
        
        examples.basicStreamOperations();
        examples.intermediateOperations();
        examples.terminalOperations();
        examples.streamCollectors();
        examples.parallelStreams();
        examples.streamWithPrimitives();
        examples.streamGroupingAndPartitioning();
        examples.streamReduction();
        examples.streamWithCustomObjects();
    }
    
    /**
     * Basic Stream Operations
     */
    public void basicStreamOperations() {
        System.out.println("1. Basic Stream Operations:");
        
        // Creating streams from collections
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");
        Stream<String> nameStream = names.stream();
        
        // Creating streams from arrays
        String[] words = {"Hello", "World", "Java", "8", "Streams"};
        Stream<String> wordStream = Arrays.stream(words);
        
        // Creating streams using Stream.of()
        Stream<String> directStream = Stream.of("One", "Two", "Three");
        
        // Creating empty stream
        Stream<String> emptyStream = Stream.empty();
        
        // Creating infinite streams
        Stream<Integer> infiniteStream = Stream.iterate(0, n -> n + 2);
        Stream<Double> randomStream = Stream.generate(Math::random);
        
        System.out.println("Names: " + names);
        System.out.println("Words: " + Arrays.toString(words));
        
        System.out.println();
    }
    
    /**
     * Intermediate Operations
     */
    public void intermediateOperations() {
        System.out.println("2. Intermediate Operations:");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Filter operation
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Even numbers: " + evenNumbers);
        
        // Map operation
        List<String> numberStrings = numbers.stream()
                .map(Object::toString)
                .collect(Collectors.toList());
        System.out.println("Number strings: " + numberStrings);
        
        // FlatMap operation
        List<List<Integer>> nestedLists = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9)
        );
        
        List<Integer> flattened = nestedLists.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println("Flattened list: " + flattened);
        
        // Distinct operation
        List<Integer> duplicates = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 4, 4, 4);
        List<Integer> distinct = duplicates.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Distinct numbers: " + distinct);
        
        // Sorted operation
        List<String> unsorted = Arrays.asList("Charlie", "Alice", "Bob", "David");
        List<String> sorted = unsorted.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Sorted names: " + sorted);
        
        // Limit and Skip operations
        List<Integer> limited = numbers.stream()
                .limit(5)
                .collect(Collectors.toList());
        System.out.println("Limited to 5: " + limited);
        
        List<Integer> skipped = numbers.stream()
                .skip(5)
                .collect(Collectors.toList());
        System.out.println("Skipped first 5: " + skipped);
        
        // Peek operation (for debugging)
        List<Integer> doubled = numbers.stream()
                .peek(n -> System.out.print("Processing: " + n + " "))
                .map(n -> n * 2)
                .peek(n -> System.out.println("-> " + n))
                .collect(Collectors.toList());
        
        System.out.println();
    }
    
    /**
     * Terminal Operations
     */
    public void terminalOperations() {
        System.out.println("3. Terminal Operations:");
        
        List<String> fruits = Arrays.asList("Apple", "Banana", "Cherry", "Date", "Elderberry");
        
        // forEach
        System.out.println("Fruits:");
        fruits.stream().forEach(fruit -> System.out.println("  - " + fruit));
        
        // collect
        List<String> upperCaseFruits = fruits.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Uppercase fruits: " + upperCaseFruits);
        
        // reduce
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println("Sum: " + sum);
        
        Optional<Integer> product = numbers.stream().reduce((a, b) -> a * b);
        System.out.println("Product: " + product.orElse(0));
        
        // count
        long count = fruits.stream().filter(fruit -> fruit.length() > 5).count();
        System.out.println("Fruits with length > 5: " + count);
        
        // anyMatch, allMatch, noneMatch
        boolean anyStartsWithA = fruits.stream().anyMatch(fruit -> fruit.startsWith("A"));
        boolean allHaveLength5 = fruits.stream().allMatch(fruit -> fruit.length() == 5);
        boolean noneStartsWithZ = fruits.stream().noneMatch(fruit -> fruit.startsWith("Z"));
        
        System.out.println("Any starts with 'A': " + anyStartsWithA);
        System.out.println("All have length 5: " + allHaveLength5);
        System.out.println("None starts with 'Z': " + noneStartsWithZ);
        
        // findFirst, findAny
        Optional<String> first = fruits.stream().findFirst();
        Optional<String> any = fruits.stream().findAny();
        
        System.out.println("First fruit: " + first.orElse("None"));
        System.out.println("Any fruit: " + any.orElse("None"));
        
        // min, max
        Optional<String> shortest = fruits.stream().min(Comparator.comparing(String::length));
        Optional<String> longest = fruits.stream().max(Comparator.comparing(String::length));
        
        System.out.println("Shortest fruit: " + shortest.orElse("None"));
        System.out.println("Longest fruit: " + longest.orElse("None"));
        
        System.out.println();
    }
    
    /**
     * Stream Collectors
     */
    public void streamCollectors() {
        System.out.println("4. Stream Collectors:");
        
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve", "Frank");
        
        // toList, toSet
        List<String> nameList = names.stream().collect(Collectors.toList());
        Set<String> nameSet = names.stream().collect(Collectors.toSet());
        
        // toMap
        Map<String, Integer> nameLengthMap = names.stream()
                .collect(Collectors.toMap(
                        name -> name,
                        String::length
                ));
        System.out.println("Name to length map: " + nameLengthMap);
        
        // joining
        String joined = names.stream().collect(Collectors.joining(", "));
        System.out.println("Joined names: " + joined);
        
        // counting
        long count = names.stream().collect(Collectors.counting());
        System.out.println("Count: " + count);
        
        // averaging
        double avgLength = names.stream()
                .collect(Collectors.averagingInt(String::length));
        System.out.println("Average name length: " + avgLength);
        
        // summarizing
        IntSummaryStatistics stats = names.stream()
                .collect(Collectors.summarizingInt(String::length));
        System.out.println("Name length statistics: " + stats);
        
        // partitioning
        Map<Boolean, List<String>> partitioned = names.stream()
                .collect(Collectors.partitioningBy(name -> name.length() > 4));
        System.out.println("Partitioned by length > 4: " + partitioned);
        
        // grouping
        Map<Integer, List<String>> groupedByLength = names.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println("Grouped by length: " + groupedByLength);
        
        System.out.println();
    }
    
    /**
     * Parallel Streams
     */
    public void parallelStreams() {
        System.out.println("5. Parallel Streams:");
        
        List<Integer> numbers = IntStream.rangeClosed(1, 1000000)
                .boxed()
                .collect(Collectors.toList());
        
        // Sequential processing
        long startTime = System.currentTimeMillis();
        long sequentialSum = numbers.stream()
                .mapToLong(Integer::longValue)
                .sum();
        long sequentialTime = System.currentTimeMillis() - startTime;
        
        // Parallel processing
        startTime = System.currentTimeMillis();
        long parallelSum = numbers.parallelStream()
                .mapToLong(Integer::longValue)
                .sum();
        long parallelTime = System.currentTimeMillis() - startTime;
        
        System.out.println("Sequential sum: " + sequentialSum + " (Time: " + sequentialTime + "ms)");
        System.out.println("Parallel sum: " + parallelSum + " (Time: " + parallelTime + "ms)");
        
        // Parallel stream with custom thread pool
        ForkJoinPool customThreadPool = new ForkJoinPool(4);
        long customParallelSum = customThreadPool.submit(() ->
                numbers.parallelStream().mapToLong(Integer::longValue).sum()
        ).join();
        
        System.out.println("Custom thread pool sum: " + customParallelSum);
        
        System.out.println();
    }
    
    /**
     * Streams with Primitives
     */
    public void streamWithPrimitives() {
        System.out.println("6. Streams with Primitives:");
        
        // IntStream
        IntStream intStream = IntStream.range(1, 10);
        System.out.println("IntStream range: " + intStream.boxed().collect(Collectors.toList()));
        
        IntStream closedRange = IntStream.rangeClosed(1, 10);
        System.out.println("Closed range: " + closedRange.boxed().collect(Collectors.toList()));
        
        // LongStream
        LongStream longStream = LongStream.range(1, 10);
        System.out.println("LongStream: " + longStream.boxed().collect(Collectors.toList()));
        
        // DoubleStream
        DoubleStream doubleStream = DoubleStream.of(1.1, 2.2, 3.3, 4.4, 5.5);
        System.out.println("DoubleStream: " + doubleStream.boxed().collect(Collectors.toList()));
        
        // Converting between stream types
        List<String> words = Arrays.asList("Hello", "World", "Java", "8");
        IntStream lengths = words.stream().mapToInt(String::length);
        System.out.println("Word lengths: " + lengths.boxed().collect(Collectors.toList()));
        
        System.out.println();
    }
    
    /**
     * Grouping and Partitioning
     */
    public void streamGroupingAndPartitioning() {
        System.out.println("7. Grouping and Partitioning:");
        
        List<Person> people = Arrays.asList(
                new Person("Alice", 25, "Engineering"),
                new Person("Bob", 30, "Sales"),
                new Person("Charlie", 35, "Engineering"),
                new Person("David", 28, "Marketing"),
                new Person("Eve", 32, "Engineering"),
                new Person("Frank", 29, "Sales")
        );
        
        // Grouping by department
        Map<String, List<Person>> byDepartment = people.stream()
                .collect(Collectors.groupingBy(Person::getDepartment));
        System.out.println("Grouped by department: " + byDepartment);
        
        // Grouping by department with counting
        Map<String, Long> departmentCounts = people.stream()
                .collect(Collectors.groupingBy(Person::getDepartment, Collectors.counting()));
        System.out.println("Department counts: " + departmentCounts);
        
        // Grouping by age range
        Map<String, List<Person>> byAgeRange = people.stream()
                .collect(Collectors.groupingBy(person -> {
                    if (person.getAge() < 30) return "Young";
                    else if (person.getAge() < 35) return "Middle";
                    else return "Senior";
                }));
        System.out.println("Grouped by age range: " + byAgeRange);
        
        // Partitioning by age
        Map<Boolean, List<Person>> partitionedByAge = people.stream()
                .collect(Collectors.partitioningBy(person -> person.getAge() >= 30));
        System.out.println("Partitioned by age >= 30: " + partitionedByAge);
        
        System.out.println();
    }
    
    /**
     * Stream Reduction
     */
    public void streamReduction() {
        System.out.println("8. Stream Reduction:");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Simple reduction
        int sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println("Sum: " + sum);
        
        // Reduction with identity
        int product = numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println("Product: " + product);
        
        // Reduction without identity
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        System.out.println("Max: " + max.orElse(0));
        
        // Complex reduction
        String concatenated = numbers.stream()
                .map(Object::toString)
                .reduce("", (a, b) -> a + b);
        System.out.println("Concatenated: " + concatenated);
        
        // Reduction with combiner (for parallel streams)
        int parallelSum = numbers.parallelStream()
                .reduce(0, Integer::sum, Integer::sum);
        System.out.println("Parallel sum: " + parallelSum);
        
        System.out.println();
    }
    
    /**
     * Streams with Custom Objects
     */
    public void streamWithCustomObjects() {
        System.out.println("9. Streams with Custom Objects:");
        
        List<Person> people = Arrays.asList(
                new Person("Alice", 25, "Engineering"),
                new Person("Bob", 30, "Sales"),
                new Person("Charlie", 35, "Engineering"),
                new Person("David", 28, "Marketing")
        );
        
        // Filtering and mapping
        List<String> engineerNames = people.stream()
                .filter(person -> "Engineering".equals(person.getDepartment()))
                .map(Person::getName)
                .collect(Collectors.toList());
        System.out.println("Engineer names: " + engineerNames);
        
        // Finding oldest person
        Optional<Person> oldest = people.stream()
                .max(Comparator.comparing(Person::getAge));
        System.out.println("Oldest person: " + oldest.map(Person::getName).orElse("None"));
        
        // Average age by department
        Map<String, Double> avgAgeByDept = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getDepartment,
                        Collectors.averagingInt(Person::getAge)
                ));
        System.out.println("Average age by department: " + avgAgeByDept);
        
        // Creating a summary
        String summary = people.stream()
                .map(person -> person.getName() + " (" + person.getAge() + ")")
                .collect(Collectors.joining(", ", "People: [", "]"));
        System.out.println(summary);
        
        System.out.println();
    }
    
    // Helper class for examples
    static class Person {
        private String name;
        private int age;
        private String department;
        
        public Person(String name, int age, String department) {
            this.name = name;
            this.age = age;
            this.department = department;
        }
        
        public String getName() { return name; }
        public int getAge() { return age; }
        public String getDepartment() { return department; }
        
        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }
} 