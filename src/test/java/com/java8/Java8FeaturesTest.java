package com.java8;

import org.junit.Test;
import static org.junit.Assert.*;

import com.java8.lambda.LambdaExamples;
import com.java8.streams.StreamExamples;
import com.java8.optional.OptionalExamples;
import com.java8.methodreferences.MethodReferenceExamples;
import com.java8.datetime.DateTimeExamples;

/**
 * Test class for Java 8 features examples
 */
public class Java8FeaturesTest {
    
    @Test
    public void testLambdaExamples() {
        LambdaExamples examples = new LambdaExamples();
        
        // Test that methods can be called without exceptions
        assertNotNull(examples);
        
        // Test basic lambda functionality
        java.util.function.BinaryOperator<Integer> add = (a, b) -> a + b;
        assertEquals(Integer.valueOf(5), add.apply(2, 3));
    }
    
    @Test
    public void testStreamExamples() {
        StreamExamples examples = new StreamExamples();
        
        // Test that methods can be called without exceptions
        assertNotNull(examples);
        
        // Test basic stream functionality
        java.util.List<Integer> numbers = java.util.Arrays.asList(1, 2, 3, 4, 5);
        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        assertEquals(15, sum);
    }
    
    @Test
    public void testOptionalExamples() {
        OptionalExamples examples = new OptionalExamples();
        
        // Test that methods can be called without exceptions
        assertNotNull(examples);
        
        // Test basic optional functionality
        java.util.Optional<String> optional = java.util.Optional.of("test");
        assertTrue(optional.isPresent());
        assertEquals("test", optional.get());
    }
    
    @Test
    public void testMethodReferenceExamples() {
        MethodReferenceExamples examples = new MethodReferenceExamples();
        
        // Test that methods can be called without exceptions
        assertNotNull(examples);
        
        // Test basic method reference functionality
        java.util.function.Function<String, String> upperCase = String::toUpperCase;
        assertEquals("HELLO", upperCase.apply("hello"));
    }
    
    @Test
    public void testDateTimeExamples() {
        DateTimeExamples examples = new DateTimeExamples();
        
        // Test that methods can be called without exceptions
        assertNotNull(examples);
        
        // Test basic date/time functionality
        java.time.LocalDate today = java.time.LocalDate.now();
        assertNotNull(today);
        
        java.time.LocalTime now = java.time.LocalTime.now();
        assertNotNull(now);
    }
    
    @Test
    public void testJava8FeaturesRunner() {
        // Test that the main runner class can be instantiated
        Java8FeaturesRunner runner = new Java8FeaturesRunner();
        assertNotNull(runner);
    }
} 