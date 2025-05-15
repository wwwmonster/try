package com.alex.code.challenge.model;

public enum OperationType {
    ARE_SAME_CSVS("Are Same CSVs"),
    CSV_UNION("CSV Union"),
    CSV_INTERSECTION("CSV Intersection"),
    ARE_ANAGRAMS("Are Anagrams"),
    IS_PALINDROME("Is Palindrome"),
    SPOONERISM("Spoonerism"),
    INFLATIONARY("Inflationary"),
    FIZZ_BUZZ("Fizz Buzz");

    private final String label;

    OperationType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
