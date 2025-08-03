package com.java8;

import com.java8.lambda.LambdaExamples;
import com.java8.streams.StreamExamples;
import com.java8.optional.OptionalExamples;
import com.java8.methodreferences.MethodReferenceExamples;
import com.java8.datetime.DateTimeExamples;
import com.java8.defaultmethods.DefaultMethodExamples;

/**
 * Main runner class for Java 8 features examples
 */
public class Java8FeaturesRunner {
    
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("    Java 8 Features Examples Runner");
        System.out.println("==========================================\n");
        
        // Run Lambda Examples
        System.out.println("Running Lambda Expressions Examples...");
        LambdaExamples lambdaExamples = new LambdaExamples();
        lambdaExamples.basicLambdaSyntax();
        lambdaExamples.functionalInterfaces();
        lambdaExamples.lambdaWithCollections();
        lambdaExamples.builtInFunctionalInterfaces();
        lambdaExamples.lambdaWithExceptions();
        lambdaExamples.variableCapture();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Run Streams Examples
        System.out.println("Running Streams API Examples...");
        StreamExamples streamExamples = new StreamExamples();
        streamExamples.basicStreamOperations();
        streamExamples.intermediateOperations();
        streamExamples.terminalOperations();
        streamExamples.streamCollectors();
        streamExamples.parallelStreams();
        streamExamples.streamWithPrimitives();
        streamExamples.streamGroupingAndPartitioning();
        streamExamples.streamReduction();
        streamExamples.streamWithCustomObjects();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Run Optional Examples
        System.out.println("Running Optional Class Examples...");
        OptionalExamples optionalExamples = new OptionalExamples();
        optionalExamples.creatingOptionals();
        optionalExamples.optionalMethods();
        optionalExamples.safeNavigation();
        optionalExamples.optionalWithStreams();
        optionalExamples.optionalChaining();
        optionalExamples.optionalBestPractices();
        optionalExamples.optionalVsNull();
        optionalExamples.optionalWithExceptions();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Run Method References Examples
        System.out.println("Running Method References Examples...");
        MethodReferenceExamples methodRefExamples = new MethodReferenceExamples();
        methodRefExamples.staticMethodReferences();
        methodRefExamples.instanceMethodReferences();
        methodRefExamples.constructorReferences();
        methodRefExamples.arrayConstructorReferences();
        methodRefExamples.methodReferencesWithStreams();
        methodRefExamples.comparisonWithLambdas();
        methodRefExamples.complexMethodReferences();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Run Default Methods Examples
        System.out.println("Running Default Methods Examples...");
        DefaultMethodExamples defaultMethodExamples = new DefaultMethodExamples();
        defaultMethodExamples.basicDefaultMethods();
        defaultMethodExamples.multipleInheritance();
        defaultMethodExamples.defaultMethodsWithCollections();
        defaultMethodExamples.defaultMethodsInFunctionalInterfaces();
        defaultMethodExamples.defaultMethodsWithStreams();
        defaultMethodExamples.defaultMethodsBestPractices();
        defaultMethodExamples.defaultMethodsVsAbstractClasses();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Run Date/Time Examples
        System.out.println("Running Date/Time API Examples...");
        DateTimeExamples dateTimeExamples = new DateTimeExamples();
        dateTimeExamples.localDateExamples();
        dateTimeExamples.localTimeExamples();
        dateTimeExamples.localDateTimeExamples();
        dateTimeExamples.periodAndDuration();
        dateTimeExamples.dateTimeFormatter();
        dateTimeExamples.temporalAdjusters();
        dateTimeExamples.timeZones();
        dateTimeExamples.legacyDateConversion();
        dateTimeExamples.dateTimeCalculations();
        
        System.out.println("\n==========================================");
        System.out.println("    All Java 8 Features Examples Completed!");
        System.out.println("==========================================");
    }
} 