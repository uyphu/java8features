package com.java8.nashorn;

import javax.script.*;
import java.util.*;

/**
 * Comprehensive examples of Nashorn JavaScript Engine in Java 8
 */
public class NashornExamples {
    
    public static void main(String[] args) {
        NashornExamples examples = new NashornExamples();
        
        System.out.println("=== Nashorn JavaScript Engine Examples ===\n");
        
        examples.basicJavaScriptExecution();
        examples.javaJavaScriptInterop();
        examples.scriptEvaluation();
        examples.functionExecution();
        examples.objectManipulation();
        examples.errorHandling();
        examples.practicalExamples();
        examples.performanceConsiderations();
    }
    
    /**
     * Basic JavaScript Execution
     */
    public void basicJavaScriptExecution() {
        System.out.println("1. Basic JavaScript Execution:");
        
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("nashorn");
            
            if (engine == null) {
                System.out.println("Nashorn engine not available. This example requires Java 8-14.");
                return;
            }
            
            // Simple JavaScript expression
            Object result = engine.eval("2 + 3 * 4");
            System.out.println("JavaScript calculation: " + result);
            
            // String operations
            Object stringResult = engine.eval("'Hello ' + 'World' + '!'");
            System.out.println("String concatenation: " + stringResult);
            
            // Array operations
            Object arrayResult = engine.eval("[1, 2, 3, 4, 5].filter(x => x > 2).map(x => x * 2)");
            System.out.println("Array operations: " + arrayResult);
            
            // Object creation
            Object objectResult = engine.eval("({name: 'John', age: 30, city: 'New York'})");
            System.out.println("Object creation: " + objectResult);
            
            // Function definition and execution
            engine.eval("function greet(name) { return 'Hello, ' + name + '!'; }");
            Object functionResult = engine.eval("greet('Alice')");
            System.out.println("Function execution: " + functionResult);
            
        } catch (ScriptException e) {
            System.out.println("Script execution error: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    /**
     * Java-JavaScript Interop
     */
    public void javaJavaScriptInterop() {
        System.out.println("2. Java-JavaScript Interop:");
        
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("nashorn");
            
            if (engine == null) {
                System.out.println("Nashorn engine not available.");
                return;
            }
            
            // Expose Java objects to JavaScript
            engine.put("javaList", Arrays.asList("Apple", "Banana", "Cherry"));
            engine.put("javaMap", new HashMap<String, Object>() {{
                put("name", "John");
                put("age", 30);
            }});
            
            // Access Java objects from JavaScript
            Object listResult = engine.eval("javaList.get(0) + ' is delicious'");
            System.out.println("Java List access: " + listResult);
            
            Object mapResult = engine.eval("javaMap.get('name') + ' is ' + javaMap.get('age') + ' years old'");
            System.out.println("Java Map access: " + mapResult);
            
            // Call Java methods from JavaScript
            engine.put("javaString", "Hello World");
            Object methodResult = engine.eval("javaString.toUpperCase()");
            System.out.println("Java method call: " + methodResult);
            
            // Create Java objects from JavaScript
            Object dateResult = engine.eval("new java.util.Date()");
            System.out.println("Java Date creation: " + dateResult);
            
            // Use Java collections from JavaScript
            Object collectionResult = engine.eval("new java.util.ArrayList()");
            engine.eval("collectionResult.add('Item 1')");
            engine.eval("collectionResult.add('Item 2')");
            Object sizeResult = engine.eval("collectionResult.size()");
            System.out.println("Java Collection size: " + sizeResult);
            
        } catch (ScriptException e) {
            System.out.println("Interop error: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    /**
     * Script Evaluation
     */
    public void scriptEvaluation() {
        System.out.println("3. Script Evaluation:");
        
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("nashorn");
            
            if (engine == null) {
                System.out.println("Nashorn engine not available.");
                return;
            }
            
            // Evaluate complex JavaScript code
            String complexScript = 
                "function calculateArea(radius) {\n" +
                "    return Math.PI * radius * radius;\n" +
                "}\n" +
                "\n" +
                "function calculateCircumference(radius) {\n" +
                "    return 2 * Math.PI * radius;\n" +
                "}\n" +
                "\n" +
                "var radius = 5;\n" +
                "var area = calculateArea(radius);\n" +
                "var circumference = calculateCircumference(radius);\n" +
                "\n" +
                "'Circle with radius ' + radius + ' has area: ' + area.toFixed(2) + \n" +
                "' and circumference: ' + circumference.toFixed(2);";
            
            Object result = engine.eval(complexScript);
            System.out.println("Complex script result: " + result);
            
            // Evaluate with bindings
            Bindings bindings = engine.createBindings();
            bindings.put("x", 10);
            bindings.put("y", 20);
            
            Object bindingResult = engine.eval("x + y * 2", bindings);
            System.out.println("Script with bindings: " + bindingResult);
            
            // Compile and reuse script
            CompiledScript compiled = ((Compilable) engine).compile("a * b + c");
            Bindings compiledBindings = engine.createBindings();
            compiledBindings.put("a", 5);
            compiledBindings.put("b", 3);
            compiledBindings.put("c", 10);
            
            Object compiledResult = compiled.eval(compiledBindings);
            System.out.println("Compiled script result: " + compiledResult);
            
        } catch (ScriptException e) {
            System.out.println("Script evaluation error: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    /**
     * Function Execution
     */
    public void functionExecution() {
        System.out.println("4. Function Execution:");
        
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("nashorn");
            
            if (engine == null) {
                System.out.println("Nashorn engine not available.");
                return;
            }
            
            // Define JavaScript functions
            engine.eval(
                "function factorial(n) {\n" +
                "    if (n <= 1) return 1;\n" +
                "    return n * factorial(n - 1);\n" +
                "}\n" +
                "\n" +
                "function fibonacci(n) {\n" +
                "    if (n <= 1) return n;\n" +
                "    return fibonacci(n - 1) + fibonacci(n - 2);\n" +
                "}\n" +
                "\n" +
                "function isPrime(num) {\n" +
                "    if (num <= 1) return false;\n" +
                "    for (var i = 2; i <= Math.sqrt(num); i++) {\n" +
                "        if (num % i === 0) return false;\n" +
                "    }\n" +
                "    return true;\n" +
                "}"
            );
            
            // Call functions with different parameters
            Object factorialResult = engine.eval("factorial(5)");
            System.out.println("Factorial of 5: " + factorialResult);
            
            Object fibonacciResult = engine.eval("fibonacci(10)");
            System.out.println("10th Fibonacci number: " + fibonacciResult);
            
            Object primeResult = engine.eval("isPrime(17)");
            System.out.println("Is 17 prime? " + primeResult);
            
            // Call functions from Java
            Invocable invocable = (Invocable) engine;
            Object javaFactorial = invocable.invokeFunction("factorial", 6);
            System.out.println("Factorial from Java: " + javaFactorial);
            
            Object javaFibonacci = invocable.invokeFunction("fibonacci", 8);
            System.out.println("Fibonacci from Java: " + javaFibonacci);
            
            // Call functions with multiple parameters
            engine.eval("function multiply(a, b, c) { return a * b * c; }");
            Object multiplyResult = invocable.invokeFunction("multiply", 2, 3, 4);
            System.out.println("Multiply result: " + multiplyResult);
            
        } catch (ScriptException | NoSuchMethodException e) {
            System.out.println("Function execution error: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    /**
     * Object Manipulation
     */
    public void objectManipulation() {
        System.out.println("5. Object Manipulation:");
        
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("nashorn");
            
            if (engine == null) {
                System.out.println("Nashorn engine not available.");
                return;
            }
            
            // Create and manipulate JavaScript objects
            engine.eval(
                "var person = {\n" +
                "    name: 'John Doe',\n" +
                "    age: 30,\n" +
                "    city: 'New York',\n" +
                "    hobbies: ['reading', 'swimming', 'coding'],\n" +
                "    greet: function() {\n" +
                "        return 'Hello, my name is ' + this.name + ' and I am ' + this.age + ' years old.';\n" +
                "    },\n" +
                "    addHobby: function(hobby) {\n" +
                "        this.hobbies.push(hobby);\n" +
                "        return this.hobbies.length;\n" +
                "    }\n" +
                "};"
            );
            
            // Access object properties
            Object nameResult = engine.eval("person.name");
            System.out.println("Person name: " + nameResult);
            
            Object ageResult = engine.eval("person.age");
            System.out.println("Person age: " + ageResult);
            
            Object hobbiesResult = engine.eval("person.hobbies.join(', ')");
            System.out.println("Person hobbies: " + hobbiesResult);
            
            // Call object methods
            Object greetResult = engine.eval("person.greet()");
            System.out.println("Person greeting: " + greetResult);
            
            Object addHobbyResult = engine.eval("person.addHobby('gaming')");
            System.out.println("Hobbies count after adding: " + addHobbyResult);
            
            Object updatedHobbies = engine.eval("person.hobbies.join(', ')");
            System.out.println("Updated hobbies: " + updatedHobbies);
            
            // Create objects from Java
            engine.put("javaPerson", new Person("Jane Doe", 25, "Los Angeles"));
            Object javaPersonResult = engine.eval("javaPerson.getName() + ' lives in ' + javaPerson.getCity()");
            System.out.println("Java person: " + javaPersonResult);
            
        } catch (ScriptException e) {
            System.out.println("Object manipulation error: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    /**
     * Error Handling
     */
    public void errorHandling() {
        System.out.println("6. Error Handling:");
        
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("nashorn");
            
            if (engine == null) {
                System.out.println("Nashorn engine not available.");
                return;
            }
            
            // Handle JavaScript errors
            try {
                engine.eval("undefinedVariable.someMethod()");
            } catch (ScriptException e) {
                System.out.println("Caught JavaScript error: " + e.getMessage());
            }
            
            // Handle syntax errors
            try {
                engine.eval("function test() { return; }");
                engine.eval("test("); // Missing closing parenthesis
            } catch (ScriptException e) {
                System.out.println("Caught syntax error: " + e.getMessage());
            }
            
            // Handle runtime errors
            try {
                engine.eval("var arr = [1, 2, 3]; arr[10].toString();");
            } catch (ScriptException e) {
                System.out.println("Caught runtime error: " + e.getMessage());
            }
            
            // Error handling in JavaScript
            engine.eval(
                "function safeDivide(a, b) {\n" +
                "    try {\n" +
                "        if (b === 0) {\n" +
                "            throw new Error('Division by zero');\n" +
                "        }\n" +
                "        return a / b;\n" +
                "    } catch (error) {\n" +
                "        return 'Error: ' + error.message;\n" +
                "    }\n" +
                "}"
            );
            
            Object safeResult1 = engine.eval("safeDivide(10, 2)");
            System.out.println("Safe division (10/2): " + safeResult1);
            
            Object safeResult2 = engine.eval("safeDivide(10, 0)");
            System.out.println("Safe division (10/0): " + safeResult2);
            
        } catch (ScriptException e) {
            System.out.println("Error handling test failed: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    /**
     * Practical Examples
     */
    public void practicalExamples() {
        System.out.println("7. Practical Examples:");
        
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("nashorn");
            
            if (engine == null) {
                System.out.println("Nashorn engine not available.");
                return;
            }
            
            // JSON processing
            engine.eval(
                "function parseJSON(jsonString) {\n" +
                "    try {\n" +
                "        return JSON.parse(jsonString);\n" +
                "    } catch (error) {\n" +
                "        return 'Invalid JSON: ' + error.message;\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "function stringifyJSON(obj) {\n" +
                "    return JSON.stringify(obj, null, 2);\n" +
                "}"
            );
            
            String jsonString = "{\"name\":\"John\",\"age\":30,\"city\":\"New York\"}";
            engine.put("jsonInput", jsonString);
            Object parsedJson = engine.eval("parseJSON(jsonInput)");
            System.out.println("Parsed JSON: " + parsedJson);
            
            // Data validation
            engine.eval(
                "function validateEmail(email) {\n" +
                "    var emailRegex = /^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$/;\n" +
                "    return emailRegex.test(email);\n" +
                "}\n" +
                "\n" +
                "function validatePhone(phone) {\n" +
                "    var phoneRegex = /^\\+?[1-9]\\d{1,14}$/;\n" +
                "    return phoneRegex.test(phone);\n" +
                "}"
            );
            
            Object emailValid = engine.eval("validateEmail('john@example.com')");
            System.out.println("Email validation (john@example.com): " + emailValid);
            
            Object phoneValid = engine.eval("validatePhone('+1234567890')");
            System.out.println("Phone validation (+1234567890): " + phoneValid);
            
            // String processing
            engine.eval(
                "function formatCurrency(amount, currency) {\n" +
                "    return currency + ' ' + parseFloat(amount).toFixed(2);\n" +
                "}\n" +
                "\n" +
                "function capitalizeWords(str) {\n" +
                "    return str.replace(/\\b\\w/g, function(l) { return l.toUpperCase(); });\n" +
                "}"
            );
            
            Object currencyResult = engine.eval("formatCurrency(1234.56, '$')");
            System.out.println("Currency formatting: " + currencyResult);
            
            Object capitalizeResult = engine.eval("capitalizeWords('hello world example')");
            System.out.println("Capitalized words: " + capitalizeResult);
            
            // Array processing
            engine.put("numbers", Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
            Object arrayProcessing = engine.eval(
                "numbers.stream()\n" +
                "    .filter(function(x) { return x % 2 === 0; })\n" +
                "    .map(function(x) { return x * x; })\n" +
                "    .collect(java.util.stream.Collectors.toList())"
            );
            System.out.println("Even numbers squared: " + arrayProcessing);
            
        } catch (ScriptException e) {
            System.out.println("Practical examples error: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    /**
     * Performance Considerations
     */
    public void performanceConsiderations() {
        System.out.println("8. Performance Considerations:");
        
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("nashorn");
            
            if (engine == null) {
                System.out.println("Nashorn engine not available.");
                return;
            }
            
            // Compile vs Interpret
            long startTime = System.currentTimeMillis();
            
            // Interpreted execution
            for (int i = 0; i < 1000; i++) {
                engine.eval("2 + 3 * 4");
            }
            long interpretedTime = System.currentTimeMillis() - startTime;
            System.out.println("Interpreted execution time: " + interpretedTime + "ms");
            
            // Compiled execution
            startTime = System.currentTimeMillis();
            try {
                CompiledScript compiled = ((Compilable) engine).compile("2 + 3 * 4");
                for (int i = 0; i < 1000; i++) {
                    compiled.eval();
                }
                long compiledTime = System.currentTimeMillis() - startTime;
                System.out.println("Compiled execution time: " + compiledTime + "ms");
                System.out.println("Performance improvement: " + 
                    String.format("%.1f", (double) interpretedTime / compiledTime) + "x");
            } catch (Exception e) {
                System.out.println("Compilation not supported: " + e.getMessage());
            }
            
            // Memory considerations
            System.out.println("\nMemory considerations:");
            System.out.println("- Nashorn engine consumes memory for script compilation");
            System.out.println("- Large scripts should be compiled once and reused");
            System.out.println("- Consider using script pooling for high-frequency operations");
            System.out.println("- Monitor memory usage in production environments");
            
            // Best practices
            System.out.println("\nBest practices:");
            System.out.println("- Compile frequently used scripts");
            System.out.println("- Use bindings for variable passing");
            System.out.println("- Handle exceptions properly");
            System.out.println("- Clean up resources when done");
            System.out.println("- Consider security implications of eval()");
            
        } catch (ScriptException e) {
            System.out.println("Performance test error: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    // Helper class for examples
    public static class Person {
        private String name;
        private int age;
        private String city;
        
        public Person(String name, int age, String city) {
            this.name = name;
            this.age = age;
            this.city = city;
        }
        
        public String getName() { return name; }
        public int getAge() { return age; }
        public String getCity() { return city; }
        
        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + ", city='" + city + "'}";
        }
    }
} 