package solutions;

import java.util.ArrayList;
import java.util.List;

import static solutions.TestHelper.printResult;

/**
 * <h3>Problem: Alphabetical Sum of Palindromic Substrings</h3>
 * * <p><strong>Description:</strong><br>
 * Given a string {@code s} consisting only of lowercase English letters, identify all valid
 * contiguous palindromic substrings within {@code s}.
 * * <p>Calculate the "alphabetical value" for each palindromic substring, where each character
 * is mapped to its position in the alphabet (a = 1, b = 2, c = 3, ..., z = 26).
 * * <p>Return the total cumulative sum of the alphabetical values of all palindromic substrings found.
 * * <p><strong>Notes:</strong>
 * <ul>
 * <li>A <strong>substring</strong> is a contiguous sequence of characters within a string.</li>
 * <li>A <strong>palindrome</strong> is a string that reads the same forward and backward.</li>
 * <li>Single characters are considered valid palindromic substrings of length 1.</li>
 * <li>If a palindromic substring appears multiple times (like "b" in "abba"), each occurrence is counted separately.</li>
 * </ul>
 * * <p><strong>Example 1:</strong>
 * <pre>
 * Input: s = "abba"
 * Output: 16
 * Explanation:
 * The palindromic substrings and their alphabetical values are:
 * - "a" (index 0) -> 1
 * - "b" (index 1) -> 2
 * - "b" (index 2) -> 2
 * - "a" (index 3) -> 1
 * - "bb" (index 1 to 2) -> 2 + 2 = 4
 * - "abba" (index 0 to 3) -> 1 + 2 + 2 + 1 = 6
 * * Total Sum = 1 + 2 + 2 + 1 + 4 + 6 = 16
 * </pre>
 * * <p><strong>Example 2:</strong>
 * <pre>
 * Input: s = "aba"
 * Output: 8
 * Explanation:
 * The palindromic substrings and their alphabetical values are:
 * - "a" -> 1
 * - "b" -> 2
 * - "a" -> 1
 * - "aba" -> 1 + 2 + 1 = 4
 * * Total Sum = 1 + 2 + 1 + 4 = 8
 * </pre>
 * * <p><strong>Constraints:</strong>
 * <ul>
 * <li>{@code 1 <= s.length <= 1000}</li>
 * <li>{@code s} consists of only lowercase English letters.</li>
 * </ul>
 */
public class PalindromeSum {
    public static int findAndSumPalindromes(String s) {
        List<String> palindromes = new ArrayList<>();
        int totalSum = 0;

        for (int i = 0; i < s.length(); i++) {
            // Find odd length palindromes (single character center)
            totalSum += expandAroundCenter(s, i, i, palindromes);
            // Find even length palindromes (two character center)
            totalSum += expandAroundCenter(s, i, i + 1, palindromes);
        }

        return totalSum;
    }
	
	public static int expandAroundCenter(String s, int left, int right, List<String> palindromes) {
		int sum = 0;
		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			String palindrome = s.substring(left, right + 1);
			palindromes.add(palindrome);
            sum += calculateSum(palindrome);
			left--;
			right++;
		}

        return sum;
	}
	
	public static int calculateSum(String s) {
		int sum = 0;
		
		char[] chars = s.toCharArray();
		
		for(char ch : chars) {
            sum += (ch - 'a' + 1);

		}

        return sum;
	}

    public static void main(String[] args) {
        test("abc", "6");
        test("aaa", "10");
        test("aba", "8");
        test("abba", "16");
    }


    private static void test(String input, String expected) {
        int actual = findAndSumPalindromes(input);
        printResult(input, String.valueOf(actual), expected);
    }
}
