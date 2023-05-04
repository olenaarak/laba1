package com.testing;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.hamcrest.Matchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

class laba1Test {

    private String inputString;
    private ArrayList<String> expectedWords;

// •	містили setup методи з анотаціями @Before @BeforeClass(@BeforeEach, @BeforeAll);
    @BeforeAll
    static void setUpBeforeAll() {
        System.out.println("Before All tests");
    }

    @BeforeEach
    void setUpBeforeEach() {
        System.out.println("Before each test");
        inputString = "hello";
        expectedWords = new ArrayList<>(Arrays.asList("hello"));
    }

    @Test
    void testCheckForConsonants() {
        ArrayList<String> actualWords = laba1.checkForConsonants(inputString);
        Assertions.assertEquals(expectedWords, actualWords);
    }

    @Test
    void testCheckForConsonants2() {
        String inputString = "Hello, this program finds in this text only those words that have double consonant letters.";
        ArrayList<String> expectedWords = new ArrayList<>(Arrays.asList("hello", "letters"));
        ArrayList<String> actualWords = laba1.checkForConsonants(inputString);
        Assertions.assertEquals(expectedWords, actualWords);
    }

    @Test
    void testCheckForConsonantsWithEmptyString() {
        String inputString = "";
        ArrayList<String> expectedWords = new ArrayList<>();
        ArrayList<String> actualWords = laba1.checkForConsonants(inputString);
        Assertions.assertEquals(expectedWords, actualWords);
    }

    @Test
    void testEmptyString() {
        String inputString = "";
        ArrayList<String> actualWords = laba1.checkForConsonants(inputString);
        assertTrue(actualWords.isEmpty(), "Non-empty list for empty input string");
    }

    @Test
    void testNoDoubleConsonants() {
        String inputString = "This is a test without double consonants";
        ArrayList<String> actualWords = laba1.checkForConsonants(inputString);
        assertTrue(actualWords.isEmpty());
    }

    @Test
    void testWithLongWord() {
        String inputString = "qwertyuiopasdfghjklzxcvbnm";
        ArrayList<String> expectedWords = new ArrayList<>(Arrays.asList("qwertyuiopasdfghjklzxcvbn"));
        ArrayList<String> actualWords = laba1.checkForConsonants(inputString);
    }

// •	містили принаймні 4 різних Assert вирази;
    @Test
    void testWithAsserts() {
        String inputString = "Hello Mississippi";
        ArrayList<String> expectedWords = new ArrayList<>(Arrays.asList("hello","mississippi"));
        ArrayList<String> actualWords = laba1.checkForConsonants(inputString);

        Assertions.assertEquals(expectedWords, actualWords, "The list of words with double consonants does not match the expected result.");
        Assertions.assertTrue(actualWords.contains("mississippi"), "The resulting list of words with double consonants does not contain the word 'Mississippi'");
        Assertions.assertNotEquals(expectedWords.size(), 0, "The list of words with double consonants is empty, but it should not be.");
        Assertions.assertDoesNotThrow(() -> {
            laba1.checkForConsonants(inputString);
        }, "The method should not throw an exception.");
    }

// •	принаймні один тестовий метод, що тестує виключення.
    @Test
    public void testExceptionHandling() {
        assertThrows(NullPointerException.class, () -> {
            laba1.checkForConsonants(null);
        }, "The method should throw a NullPointerException when the input is null.");
    }

// •	використовували принаймні 2 складних метчери Hamcrest або їх аналоги(прикади: метчери для рядків, колекцій, прості порівняння не враховуються);
    @Test
    void testCheckForConsonantsWithHamcrestMatchers() {
        String inputString = "ohHello ohIm ollena";
        ArrayList<String> actualWords = laba1.checkForConsonants(inputString);

        for (String word : actualWords) {
            Assertions.assertTrue(word.startsWith("o"));
        }

        MatcherAssert.assertThat(
                actualWords,
                Matchers.anyOf(
                        Matchers.hasItems("ollena")
                )
        );
    }

//•	містили принаймні один параметризований тестовий метод(Parameters для jUnit 4).
    @ParameterizedTest
    @CsvSource({
            "Hello, 1",
            "world, 0",
            "java programming, 1"
    })
    void testCheckForConsonants(String inputString, int expectedSize) {
        ArrayList<String> actualWords = laba1.checkForConsonants(inputString);
        assertEquals(expectedSize, actualWords.size());
    }

}