package com.testing;
import java.util.*;

//5. Знайти лише ті слова, які мають здвоєні приголосні літери.

public class laba1 {
    public static void main(String[] args) {
        String inputString = "Hello, this program finds in this text only those words that have double consonant letters.";
        ArrayList<String> words = checkForConsonants(inputString);
        for (String word : words) {
            System.out.println(word);
        }
    }

    public static ArrayList<String> checkForConsonants(String inputString) {
        ArrayList<String> words = new ArrayList<>();
        String[] lines = inputString.split("\\r?\\n");
        for (String line : lines) {
            if (line.isEmpty()) {
                line = " ";
            }
            List<String> wordsInLine = Arrays.asList(line.split("[\\s(),.!?:;@|-]+"));

            // перевірка слова
            for (String word : wordsInLine) {
                word = word.toLowerCase();
                if (word.length() > 25) {
                    word = word.substring(0, 25);
                }
                boolean twoConsonantal = false;
                if (words.contains(word) || word.isEmpty()) {
                    twoConsonantal = false;
                }
                for (int i = 0; i < (word.length() - 1); i++) {
                    if (isConsonantal(word.charAt(i))) {
                        if (word.charAt(i) == word.charAt(i + 1)) {
                            twoConsonantal = true;
                            break;
                        }
                    }
                }
                if (twoConsonantal) {
                    words.add(word);
                }
            }
        }
        return words;
    }

    // перевірка на приголосну
    public static boolean isConsonantal(char letter) {
        switch (letter) {
            case 'e':
            case 'u':
            case 'i':
            case 'o':
            case 'a':
                return false;
            default:
                return true;
        }
    }

}