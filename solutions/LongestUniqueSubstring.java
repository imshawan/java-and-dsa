package solutions;

import java.util.HashMap;
import java.util.Map;

import static solutions.TestHelper.printResult;

/**
 * <b>
 * Longest Substring Without Repeating Characters
 * </b>
 * <p>
 * Given a string {@code s}, find the longest substring that contains
 * no repeating characters and return it.
 * </p>
 *
 * <p>
 * A substring is a contiguous sequence of characters within a string.
 * This implementation uses the <b>Sliding Window</b> technique to achieve
 * optimal time complexity.
 * </p>
 *
 * <p>
 * <b>Example 1:</b>
 * </p>
 *
 * <pre>
 * Input:  s = "abcabaabcdef"
 * Output: "abcdef"
 * * Explanation:
 * While "abc" is a unique sequence, "abcdef" is the longest
 * contiguous sequence without repeats.
 * </pre>
 *
 * <p>
 * <b>Example 2:</b>
 * </p>
 *
 * <pre>
 * Input:  s = "bbbbb"
 * Output: "b"
 * * Explanation:
 * The longest substring is "b", with the length of 1.
 * </pre>
 *
 * <p>
 * <b>Example 3:</b>
 * </p>
 *
 * <pre>
 * Input:  s = "pwwkew"
 * Output: "wke"
 * * Explanation:
 * The answer is "wke", with the length of 3.
 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * </pre>
 * * @param s The input string to search.
 * @return The longest contiguous substring with unique characters.
 */
public class LongestUniqueSubstring {
    public static String findLongestUniqueSubstring(String s) {
        String longest = "";
        int start = 0;
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            String ch = String.valueOf(s.charAt(i));
            if (map.containsKey(ch)) {
                start = Math.max(start, map.get(ch) + 1);
            }

            map.put(ch, i);

            if (i - start + 1 > longest.length()) {
                longest = s.substring(start, i + 1);
            }
        }

        return  longest;
    }

    public static void main(String[] args) {
        // --- 1. Basic Scenarios ---
        test("abcabcbb", "abc");         // Standard repeating start
        test("bbbbb", "b");              // All same characters
        test("pwwkew", "wke");           // Answer in the middle/end mixed
        test("abcdef", "abcdef");        // No repeats at all
        test("abacbabcdef", "abcdef");
        test("abcabcbabcdefaa", "abcdef");

        // --- 2. Edge Cases ---
        test("", "");                    // Empty string
        test(" ", " ");                  // Single space is valid
        test("a", "a");                  // Single character
        test("au", "au");                // Short string, no repeats

        // --- 3. Tricky Sliding Window Cases ---
        // "abba": When at second 'a' (index 3), 'start' is at 2.
        // Map has old 'a' at 0. We must ensure start doesn't jump back to 1.
        test("abba", "ab");

        // "dvdf": When at second 'd' (index 2), window resets to 'v'.
        // We need to capture "vdf" eventually.
        test("dvdf", "vdf");

        // --- 4. Longest at Specific Positions ---
        test("aaabcdef", "abcdef");      // Longest at the very end
        test("abcdefaa", "abcdef");      // Longest at the very beginning
        test("anviaj", "nviaj");         // Mixed characters

        // --- 5. Symbols and Numbers ---
        test("1231234", "1234");         // Numbers
        test("a!b!c", "a!b");            // Symbols included
    }

    private static void test(String input, String expected) {
        String actual = findLongestUniqueSubstring(input);
        printResult(input, actual, expected);
    }
}
