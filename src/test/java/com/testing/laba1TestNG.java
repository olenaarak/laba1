package com.testing;
import java.util.ArrayList;
import java.util.Arrays;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

class laba1TestNG {
    private String inputString;
    private ArrayList<String> expectedWords;

// •	містили setup методи з анотаціями @Before @BeforeClass(@BeforeEach, @BeforeAll);
    @BeforeClass
    static void setUpBeforeAll() {
        System.out.println("Before All tests");
    }

    @BeforeMethod
    void setUpBeforeEach() {
        System.out.println("Before each test");
        inputString = "hello";
        expectedWords = new ArrayList<>(Arrays.asList("hello"));
    }

    @Test(groups = "testData")
    void testCheckForConsonants2() {
        String inputString = "Hello, this program finds in this text only those words that have double consonant letters.";
        ArrayList<String> expectedWords = new ArrayList<>(Arrays.asList("hello", "letters"));
        ArrayList<String> actualWords = laba1.checkForConsonants(inputString);
        assertEquals(expectedWords, actualWords);
    }

    @Test(groups = "testData")
    void testCheckForConsonantsWithEmptyString() {
        String inputString = "";
        ArrayList<String> expectedWords = new ArrayList<>();
        ArrayList<String> actualWords = laba1.checkForConsonants(inputString);
        assertEquals(expectedWords, actualWords);
    }

    @Test(groups = "testData")
    void testEmptyString() {
        String inputString = "";
        ArrayList<String> actualWords = laba1.checkForConsonants(inputString);
        assertTrue(actualWords.isEmpty(), "Non-empty list for empty input string");
    }

    @Test(groups = "testData")
    void testNoDoubleConsonants() {
        String inputString = "This is a test without double consonants";
        ArrayList<String> actualWords = laba1.checkForConsonants(inputString);
        assertTrue(actualWords.isEmpty());
    }

    @Test(groups = "testData")
    void testWithLongWord() {
        String inputString = "qwertyuiopasdfghjklzxcvbnm";
        ArrayList<String> expectedWords = new ArrayList<>(Arrays.asList("qwertyuiopasdfghjklzxcvbn"));
        ArrayList<String> actualWords = laba1.checkForConsonants(inputString);
    }

// •	містили Assert вирази;
    @Test(groups = "testDataAsserts")
    public void testWithAsserts() {
        String inputString = "Hello Mississippi";
        ArrayList<String> expectedWords = new ArrayList<>(Arrays.asList("hello", "mississippi"));
        ArrayList<String> actualWords = laba1.checkForConsonants(inputString);

        assertEquals(expectedWords, actualWords);
        assertTrue(actualWords.contains("mississippi"));
        assertNotEquals(expectedWords.size(), 0);
    }

// •	принаймні один тестовий метод, що тестує виключення.
    @Test(groups = "testExceptions",expectedExceptions = NullPointerException.class)
    public void testExceptionHandling() {
        laba1.checkForConsonants(null);
    }

//•	містили принаймні один параметризований тестовий метод(Parameters для jUnit 4).
    @DataProvider(name = "consonantsData")
    public Object[][] createData() {
        return new Object[][] {
                {"Hello", 1},
                {"world", 0},
                {"java programming", 1}
        };
    }
    @Test(groups = "DataProviderTest",dataProvider = "consonantsData")
    void testCheckForConsonants(String inputString, int expectedSize) {
        ArrayList<String> actualWords = laba1.checkForConsonants(inputString);
        assertEquals(expectedSize, actualWords.size());
    }

}