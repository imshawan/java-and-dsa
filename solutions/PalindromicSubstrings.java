package solutions;

import static solutions.TestHelper.printResult;

/**
 * Palindromic Substrings
 *
 * <p>Given a string s, return the number of palindromic substrings in it.</p>
 *
 * <p>A string is a palindrome when it reads the same backward as forward.
 * A substring is a contiguous sequence of characters within the string.</p>
 *
 * <h3>Examples:</h3>
 * <ul>
 *   <li><b>Example 1:</b>
 *     <ul>
 *       <li>Input: s = "abc"</li>
 *       <li>Output: 3</li>
 *       <li>Explanation: Three palindromic strings: "a", "b", "c".</li>
 *     </ul>
 *   </li>
 *   <li><b>Example 2:</b>
 *     <ul>
 *       <li>Input: s = "aaa"</li>
 *       <li>Output: 6</li>
 *       <li>Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * <h3>Constraints:</h3>
 * <ul>
 *   <li>1 &lt;= s.length &lt;= 1000</li>
 *   <li>s consists of lowercase English letters.</li>
 * </ul>
 */
public class PalindromicSubstrings {

    public static int countSubstrings(String s) {
		int size = s.length();
		int count = 0;
		
		for (int i = 0; i < size; i++) {

			count += expand(s, i, i);
			count += expand(s, i, i+1);
		}
		
		return count;
	}
	
	public static int expand(String str, int left, int right) {
		int palindrome = 0;
		
		while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
			palindrome++;
			left--;
			right++;
		}
		
		return palindrome;
	}

    public static void main(String[] args) {
        test("abc", "3");
        test("aaa", "6");
        test("aba", "4");
        test("abba", "6");
    }

    private static void test(String input, String expected) {
        int actual = countSubstrings(input);
        printResult(input, String.valueOf(actual), expected);
    }

}
