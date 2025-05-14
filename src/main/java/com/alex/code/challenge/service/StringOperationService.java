package com.alex.code.challenge.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for performing various string-based operations.
 */
@Service
public class StringOperationService {

    /**
     * Main dispatcher method that routes the operation to the corresponding logic.
     */
    public String handleOperation(String operation, String input1, String input2) {
        switch (operation.toLowerCase()) { // Normalize operation string to lower case
            case "are same csvs": return String.valueOf(areSameCSVs(input1, input2));
            case "csv union": return csvUnion(input1, input2);
            case "csv intersection": return csvIntersection(input1, input2);
            case "are anagrams": return String.valueOf(areAnagrams(input1, input2));
            case "is palindrome": return String.valueOf(isPalindrome(input1));
            case "spoonerism": return spoonerism(input1, input2);
            case "inflationary": return inflationary(input1);
            case "fizz buzz": return fizzBuzz(input1);
            default: return "Unsupported operation"; // Fallback for unknown operations
        }
    }

    /**
     * Compares two CSV strings to check if they contain the same values (order-insensitive).
     */
    private boolean areSameCSVs(String s1, String s2) {
        // Convert each CSV string into a trimmed Set of strings
        Set<String> set1 = new HashSet<>(Arrays.stream(s1.split(",")).map(String::trim).collect(Collectors.toSet()));
        Set<String> set2 = new HashSet<>(Arrays.stream(s2.split(",")).map(String::trim).collect(Collectors.toSet()));
        return set1.equals(set2); // Compare both sets for equality
    }

    /**
     * Returns the union of two CSV strings as a new CSV string.
     */
    private String csvUnion(String s1, String s2) {
        Set<String> union = new LinkedHashSet<>(); // Preserve insertion order
        // Add all unique trimmed values from both inputs
        union.addAll(Arrays.stream(s1.split(",")).map(String::trim).collect(Collectors.toSet()));
        union.addAll(Arrays.stream(s2.split(",")).map(String::trim).collect(Collectors.toSet()));
        return String.join(",", union); // Join them back into a CSV string
    }

    /**
     * Returns the intersection (common elements) of two CSV strings.
     */
    private String csvIntersection(String s1, String s2) {
        Set<String> set1 = Arrays.stream(s1.split(",")).map(String::trim).collect(Collectors.toSet());
        Set<String> set2 = Arrays.stream(s2.split(",")).map(String::trim).collect(Collectors.toSet());
        set1.retainAll(set2); // Keep only common elements
        return String.join(",", set1); // Convert back to CSV
    }

    /**
     * Checks if two strings are anagrams of each other.
     */
    private boolean areAnagrams(String s1, String s2) {
        // Remove non-letter characters and normalize to lower case
        String clean1 = s1.replaceAll("[^a-zA-Z]", "").toLowerCase();
        String clean2 = s2.replaceAll("[^a-zA-Z]", "").toLowerCase();

        // Convert to char arrays and sort for comparison
        char[] arr1 = clean1.toCharArray();
        char[] arr2 = clean2.toCharArray();
        //sort char arrays
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        return Arrays.equals(arr1, arr2); // Compare sorted arrays
    }

    /**
     * Determines if a string is a palindrome (ignores punctuation and case).
     */
    private boolean isPalindrome(String s) {
        // Remove non-alphanumeric characters and normalize
        String clean = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        // Compare with its reverse
        return new StringBuilder(clean).reverse().toString().equals(clean);
    }

    /**
     * Swaps the first characters of two strings and concatenates the results.
     */
    private String spoonerism(String s1, String s2) {
        if (s1.length() < 1 || s2.length() < 1) return "Invalid input"; // Guard clause
        // Swap first letters and return formatted string
        return s2.charAt(0) + s1.substring(1) + " " + s1.charAt(0) + s2.substring(1);
    }

    /**
     * Replaces number words (e.g., "five") in a sentence with the next number ("six").
     */
    public String inflationary(String input) {
        // Map of number words to their successors
        Map<String, String> numberWords = new LinkedHashMap<>();
        numberWords.put("zero", "one");
        numberWords.put("one", "two");
        numberWords.put("two", "three");
        numberWords.put("three", "four");
        numberWords.put("four", "five");
        numberWords.put("five", "six");
        numberWords.put("six", "seven");
        numberWords.put("seven", "eight");
        numberWords.put("eight", "nine");
        numberWords.put("nine", "ten");
        numberWords.put("ten", "eleven");

        String[] words = input.split(" "); // Split sentence into words
        for (int i = 0; i < words.length; i++) {
            for (Map.Entry<String, String> entry : numberWords.entrySet()) {
                // Replace any occurrence of the number word
                if (words[i].contains(entry.getKey())) {
                    words[i] = words[i].replaceAll(entry.getKey(), entry.getValue());
                    break;
                }
            }
        }
        return String.join(" ", words); // Join words back into a sentence
    }

    /**
     * Returns the FizzBuzz sequence up to a given number.
     */
    private String fizzBuzz(String input) {
        int n;
        try {
            n = Integer.parseInt(input); // Parse input string to integer
        } catch (NumberFormatException e) {
            return "Invalid number"; // Handle invalid input
        }

        List<String> result = new ArrayList<>(); // Store output
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) result.add("fizzbuzz"); // Divisible by 3 and 5
            else if (i % 3 == 0) result.add("fizz");  // Divisible by 3
            else if (i % 5 == 0) result.add("buzz");  // Divisible by 5
            else result.add(String.valueOf(i));      // Plain number
        }
        return String.join(" ", result); // Join results with spaces
    }
}
