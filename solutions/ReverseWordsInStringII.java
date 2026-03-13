package solutions;

import static solutions.TestHelper.printResult;

/**
 * Reverse Words in a String II
 * <p>
 * Given an input string s, reverse the order of the words.
 *
 * <p>A word is defined as a sequence of non-space characters. The words in s will be separated by
 * at least one space.</p>
 *
 * <p>Return a string of the words in reverse order concatenated by a single space.</p>
 *
 * <p>Note that s may contain leading or trailing spaces or multiple spaces between two words.
 * The returned string should only have a single space separating the words. Do not include
 * any extra spaces.</p>
 *
 * <p><b>Example 1:</b></p>
 * <pre>
 * Input: s = "the sky is blue"
 * Output: "blue is sky the"
 * </pre>
 *
 * <p><b>Example 2:</b></p>
 * <pre>
 * Input: s = "  hello world  "
 * Output: "world hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 * </pre>
 *
 * <p><b>Example 3:</b></p>
 * <pre>
 * Input: s = "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space
 * in the reversed string.
 * </pre>
 *
 * <p><b>Constraints:</b></p>
 * <ul>
 *   <li>1 &lt;= s.length &lt;= 10^4</li>
 *   <li>s contains English letters (upper-case and lower-case), digits, and spaces ' '.</li>
 *   <li>There is at least one word in s.</li>
 * </ul>
 */
public class ReverseWordsInStringII {
    public static String reverseOrderWords(String str) {
        int right;
        StringBuilder sb = new StringBuilder();

        for (int i = str.length() - 1; i >= 0; i--) {

            // skip spaces
            while (i >= 0 && str.charAt(i) == ' ') i--;
            if (i < 0) break;

            right = i;

            // move to start of word
            while (i >= 0 && str.charAt(i) != ' ') i--;

            int left = i + 1;

            // append word
            sb.append(str, left, right + 1);

            // append space if not last
            if (i > 0) sb.append(" ");
        }

        return sb.toString().trim();
    }

    public static void main(String[] args) {
        test("when all else fails reboot", "reboot fails else all when");
        test("  hello world  ",  "world hello");
        test("a good   example", "example good a");
    }

    private static void test(String input, String expected) {
        String actual = reverseOrderWords(input);
        printResult(input, actual, expected);
    }
}
