package com.java8.completablefuture;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * Comprehensive examples of CompletableFuture in Java 8
 */
public class CompletableFutureExamples {
    
    public static void main(String[] args) {
        CompletableFutureExamples examples = new CompletableFutureExamples();
        
        System.out.println("=== CompletableFuture Examples ===\n");
        
        examples.basicCompletableFuture();
        examples.asynchronousOperations();
        examples.combiningCompletableFutures();
        examples.exceptionHandling();
        examples.completableFutureWithStreams();
        examples.practicalExamples();
        examples.completableFutureVsFuture();
        examples.bestPractices();
    }
    
    /**
     * Basic CompletableFuture Operations
     */
    public void basicCompletableFuture() {
        System.out.println("1. Basic CompletableFuture Operations:");
        
        // Creating a completed CompletableFuture
        CompletableFuture<String> completed = CompletableFuture.completedFuture("Hello World");
        System.out.println("Completed future: " + completed.join());
        
        // Creating and completing a CompletableFuture
        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("Manually completed");
        System.out.println("Manually completed: " + future.join());
        
        // Creating with supplyAsync
        CompletableFuture<String> asyncResult = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100); // Simulate work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Async result";
        });
        System.out.println("Async result: " + asyncResult.join());
        
        // Creating with runAsync
        CompletableFuture<Void> asyncTask = CompletableFuture.runAsync(() -> {
            System.out.println("Running async task...");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        asyncTask.join(); // Wait for completion
        
        // Using custom executor
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CompletableFuture<String> customExecutor = CompletableFuture.supplyAsync(() -> {
            return "Custom executor result";
        }, executor);
        System.out.println("Custom executor: " + customExecutor.join());
        executor.shutdown();
        
        System.out.println();
    }
    
    /**
     * Asynchronous Operations
     */
    public void asynchronousOperations() {
        System.out.println("2. Asynchronous Operations:");
        
        // thenApply - transform result
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(s -> s + " World")
                .thenApply(String::toUpperCase);
        System.out.println("Transformed result: " + future.join());
        
        // thenAccept - consume result
        CompletableFuture<Void> consumer = CompletableFuture.supplyAsync(() -> "Hello World")
                .thenAccept(s -> System.out.println("Consumed: " + s));
        consumer.join();
        
        // thenRun - run action after completion
        CompletableFuture<Void> action = CompletableFuture.supplyAsync(() -> "Hello World")
                .thenRun(() -> System.out.println("Action completed"));
        action.join();
        
        // thenApplyAsync - async transformation
        CompletableFuture<String> asyncTransform = CompletableFuture.supplyAsync(() -> "Hello")
                .thenApplyAsync(s -> {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return s + " Async World";
                });
        System.out.println("Async transform: " + asyncTransform.join());
        
        // Chaining multiple operations
        CompletableFuture<String> chain = CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(s -> s + " World")
                .thenApply(String::toUpperCase)
                .thenApply(s -> s + "!")
                .thenApply(s -> "Result: " + s);
        System.out.println("Chained result: " + chain.join());
        
        System.out.println();
    }
    
    /**
     * Combining CompletableFutures
     */
    public void combiningCompletableFutures() {
        System.out.println("3. Combining CompletableFutures:");
        
        // thenCombine - combine two futures
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "World");
        
        CompletableFuture<String> combined = future1.thenCombine(future2, (s1, s2) -> s1 + " " + s2);
        System.out.println("Combined: " + combined.join());
        
        // thenCompose - chain futures
        CompletableFuture<String> composed = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " Composed World"));
        System.out.println("Composed: " + composed.join());
        
        // allOf - wait for all futures
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "Future 1");
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> "Future 2");
        CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> "Future 3");
        
        CompletableFuture<Void> allOf = CompletableFuture.allOf(f1, f2, f3);
        allOf.join();
        System.out.println("All completed: " + f1.join() + ", " + f2.join() + ", " + f3.join());
        
        // anyOf - wait for any future
        CompletableFuture<String> slow = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Slow result";
        });
        
        CompletableFuture<String> fast = CompletableFuture.supplyAsync(() -> "Fast result");
        
        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(slow, fast);
        System.out.println("Any completed: " + anyOf.join());
        
        // Combining with custom function
        CompletableFuture<Integer> num1 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> num2 = CompletableFuture.supplyAsync(() -> 20);
        
        CompletableFuture<Integer> sum = num1.thenCombine(num2, Integer::sum);
        CompletableFuture<Integer> product = num1.thenCombine(num2, (a, b) -> a * b);
        
        System.out.println("Sum: " + sum.join());
        System.out.println("Product: " + product.join());
        
        System.out.println();
    }
    
    /**
     * Exception Handling
     */
    public void exceptionHandling() {
        System.out.println("4. Exception Handling:");
        
        // Exceptionally - handle exceptions
        CompletableFuture<String> exceptional = CompletableFuture.supplyAsync(() -> {
            if (Math.random() > 0.5) {
                throw new RuntimeException("Random error");
            }
            return "Success";
        }).exceptionally(ex -> "Error handled: " + ex.getMessage());
        
        System.out.println("Exceptionally result: " + exceptional.join());
        
        // handle - handle both success and exception
        CompletableFuture<String> handled = CompletableFuture.supplyAsync(() -> {
            if (Math.random() > 0.5) {
                throw new RuntimeException("Random error");
            }
            return "Success";
        }).handle((result, ex) -> {
            if (ex != null) {
                return "Error: " + ex.getMessage();
            }
            return "Success: " + result;
        });
        
        System.out.println("Handled result: " + handled.join());
        
        // whenComplete - always execute
        CompletableFuture<String> whenComplete = CompletableFuture.supplyAsync(() -> "Hello World")
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        System.out.println("Exception occurred: " + ex.getMessage());
                    } else {
                        System.out.println("Completed successfully: " + result);
                    }
                });
        whenComplete.join();
        
        // Completing exceptionally
        CompletableFuture<String> completedExceptionally = new CompletableFuture<>();
        completedExceptionally.completeExceptionally(new RuntimeException("Manual exception"));
        
        try {
            completedExceptionally.join();
        } catch (CompletionException e) {
            System.out.println("Caught exception: " + e.getCause().getMessage());
        }
        
        System.out.println();
    }
    
    /**
     * CompletableFuture with Streams
     */
    public void completableFutureWithStreams() {
        System.out.println("5. CompletableFuture with Streams:");
        
        // Processing list with CompletableFuture
        List<String> items = Arrays.asList("A", "B", "C", "D", "E");
        
        List<CompletableFuture<String>> futures = items.stream()
                .map(item -> CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(100); // Simulate work
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return "Processed " + item;
                }))
                .collect(Collectors.toList());
        
        // Wait for all to complete
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0])
        );
        
        allFutures.join();
        
        List<String> results = futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        
        System.out.println("Stream results: " + results);
        
        // Parallel processing with custom executor
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        List<CompletableFuture<Integer>> numberFutures = Arrays.asList(1, 2, 3, 4, 5).stream()
                .map(num -> CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return num * num;
                }, executor))
                .collect(Collectors.toList());
        
        CompletableFuture.allOf(numberFutures.toArray(new CompletableFuture[0])).join();
        
        List<Integer> squares = numberFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        
        System.out.println("Squares: " + squares);
        executor.shutdown();
        
        System.out.println();
    }
    
    /**
     * Practical Examples
     */
    public void practicalExamples() {
        System.out.println("6. Practical Examples:");
        
        // User service simulation
        CompletableFuture<User> userFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return new User(1L, "John Doe", "john@example.com");
        });
        
        // Order service simulation
        CompletableFuture<List<Order>> ordersFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return Arrays.asList(
                    new Order(1L, "Book", 29.99),
                    new Order(2L, "Phone", 599.99)
            );
        });
        
        // Combine user and orders
        CompletableFuture<UserProfile> profileFuture = userFuture.thenCombine(ordersFuture, (user, orders) -> {
            double totalSpent = orders.stream()
                    .mapToDouble(Order::getAmount)
                    .sum();
            return new UserProfile(user, orders, totalSpent);
        });
        
        UserProfile profile = profileFuture.join();
        System.out.println("User Profile: " + profile);
        
        // API call simulation with timeout
        CompletableFuture<String> apiCall = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500); // Simulate slow API
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "API Response";
        });
        
        CompletableFuture<String> timeout = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Timeout Response";
        });
        
        CompletableFuture<Object> withTimeout = CompletableFuture.anyOf(apiCall, timeout);
        System.out.println("API result: " + withTimeout.join());
        
        // Data processing pipeline
        CompletableFuture<String> dataProcessing = CompletableFuture.supplyAsync(() -> "Raw Data")
                .thenApplyAsync(data -> data + " -> Processed")
                .thenApplyAsync(data -> data + " -> Validated")
                .thenApplyAsync(data -> data + " -> Stored");
        
        System.out.println("Data processing result: " + dataProcessing.join());
        
        System.out.println();
    }
    
    /**
     * CompletableFuture vs Future
     */
    public void completableFutureVsFuture() {
        System.out.println("7. CompletableFuture vs Future:");
        
        // Traditional Future approach
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        Future<String> traditionalFuture = executor.submit(() -> {
            Thread.sleep(100);
            return "Traditional Future Result";
        });
        
        try {
            String result = traditionalFuture.get(1, TimeUnit.SECONDS);
            System.out.println("Traditional Future: " + result);
        } catch (Exception e) {
            System.out.println("Traditional Future error: " + e.getMessage());
        }
        
        // CompletableFuture approach
        CompletableFuture<String> modernFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Modern CompletableFuture Result";
        }, executor);
        
        String modernResult = modernFuture.join();
        System.out.println("Modern CompletableFuture: " + modernResult);
        
        // CompletableFuture advantages
        CompletableFuture<String> chained = modernFuture
                .thenApply(s -> s + " -> Transformed")
                .thenApply(String::toUpperCase)
                .thenApply(s -> s + " -> Final");
        
        System.out.println("Chained result: " + chained.join());
        
        executor.shutdown();
        
        System.out.println();
    }
    
    /**
     * Best Practices
     */
    public void bestPractices() {
        System.out.println("8. Best Practices:");
        
        // Use custom executor for CPU-intensive tasks
        ExecutorService cpuExecutor = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
        
        CompletableFuture<String> cpuTask = CompletableFuture.supplyAsync(() -> {
            // CPU-intensive work
            return "CPU Task Result";
        }, cpuExecutor);
        
        // Use different executor for I/O tasks
        ExecutorService ioExecutor = Executors.newCachedThreadPool();
        
        CompletableFuture<String> ioTask = CompletableFuture.supplyAsync(() -> {
            // I/O work
            return "I/O Task Result";
        }, ioExecutor);
        
        // Combine results
        CompletableFuture<String> combined = cpuTask.thenCombine(ioTask, (cpu, io) -> 
                cpu + " + " + io);
        
        System.out.println("Combined tasks: " + combined.join());
        
        // Proper exception handling
        CompletableFuture<String> robust = CompletableFuture.supplyAsync(() -> {
            if (Math.random() > 0.7) {
                throw new RuntimeException("Simulated error");
            }
            return "Success";
        }, cpuExecutor)
        .exceptionally(ex -> "Fallback: " + ex.getMessage())
        .whenComplete((result, ex) -> {
            if (ex != null) {
                System.out.println("Error logged: " + ex.getMessage());
            } else {
                System.out.println("Success logged: " + result);
            }
        });
        
        System.out.println("Robust result: " + robust.join());
        
        // Cleanup
        cpuExecutor.shutdown();
        ioExecutor.shutdown();
        
        System.out.println();
    }
    
    // Helper classes for examples
    static class User {
        private Long id;
        private String name;
        private String email;
        
        public User(Long id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }
        
        public Long getId() { return id; }
        public String getName() { return name; }
        public String getEmail() { return email; }
        
        @Override
        public String toString() {
            return "User{id=" + id + ", name='" + name + "', email='" + email + "'}";
        }
    }
    
    static class Order {
        private Long id;
        private String product;
        private double amount;
        
        public Order(Long id, String product, double amount) {
            this.id = id;
            this.product = product;
            this.amount = amount;
        }
        
        public Long getId() { return id; }
        public String getProduct() { return product; }
        public double getAmount() { return amount; }
        
        @Override
        public String toString() {
            return "Order{id=" + id + ", product='" + product + "', amount=" + amount + "}";
        }
    }
    
    static class UserProfile {
        private User user;
        private List<Order> orders;
        private double totalSpent;
        
        public UserProfile(User user, List<Order> orders, double totalSpent) {
            this.user = user;
            this.orders = orders;
            this.totalSpent = totalSpent;
        }
        
        public User getUser() { return user; }
        public List<Order> getOrders() { return orders; }
        public double getTotalSpent() { return totalSpent; }
        
        @Override
        public String toString() {
            return "UserProfile{user=" + user + ", orders=" + orders + ", totalSpent=" + totalSpent + "}";
        }
    }
} 