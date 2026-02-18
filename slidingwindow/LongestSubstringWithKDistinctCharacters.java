package slidingwindow;

import java.util.HashMap;
import java.util.Map;

import static solutions.TestHelper.printResult;

/**
 * Solution for "Longest Substring With At Most K Distinct Characters" problem
 *
 * <p><strong>Problem Description:</strong></p>
 * <p>Given a string s and an integer k. Find the length of the longest substring with at most k distinct characters.</p>
 *
 * <p><strong>Examples:</strong></p>
 * <ul>
 *   <li><strong>Example 1:</strong><br>
 *       Input: s = "aababbcaacc", k = 2<br>
 *       Output: 6<br>
 *       Explanation: The longest substring with at most two distinct characters is "aababb".
 *       The length of the string 6.</li>
 *
 *   <li><strong>Example 2:</strong><br>
 *       Input: s = "abcddefg", k = 3<br>
 *       Output: 4<br>
 *       Explanation: The longest substring with at most three distinct characters is "bcdd".
 *       The length of the string 4.</li>
 * </ul>
 *
 * <p><strong>Algorithm Approach:</strong></p>
 * <p>This class provides two solutions using the sliding window technique:</p>
 * <ol>
 *   <li><strong>First Solution:</strong> Basic approach that resets the window when k is exceeded</li>
 *   <li><strong>Better Solution:</strong> Optimized sliding window that shrinks the window efficiently</li>
 * </ol>
 *
 * <p><strong>Time Complexity:</strong> O(n) for the better solution, where n is the length of the string</p>
 * <p><strong>Space Complexity:</strong> O(k) for storing at most k distinct characters in the HashMap</p>
 */
public class LongestSubstringWithKDistinctCharacters {

    /**
     * First solution approach - Basic sliding window with reset strategy.
     *
     * <p>This method uses a sliding window approach but resets the entire window when the number
     * of distinct characters exceeds k. While functional, this approach is less efficient as it
     * may miss optimal solutions by completely resetting the window.</p>
     *
     * <p><strong>Algorithm Steps:</strong></p>
     * <ol>
     *   <li>Use two pointers (left and right) to maintain a sliding window</li>
     *   <li>Expand the window by moving the right pointer and adding characters to a map</li>
     *   <li>When distinct characters exceed k, clear the map and reset the window</li>
     *   <li>Track the maximum window size encountered</li>
     * </ol>
     *
     * @param str the input string to analyze
     * @param k the maximum number of distinct characters allowed in the substring
     * @return the length of the longest substring with at most k distinct characters
     */
    public static int kDistinctCharsSolutionFirst(String str, int k) {
        int maxLen = 0;
		int left = 0;
		int right = 0;
		int size = str.length();
		Map<String, Integer> map = new HashMap<>();
		
		while (right < size) {
			String ch = String.valueOf(str.charAt(right));
			
			if (map.containsKey(ch)) {
				map.put(ch, map.get(ch) + 1);
			} else {
				map.put(ch, 1);
			}
			
			if (map.size() > k) {
				map.clear();
				left++;
				right = (left - 1);
			}
			
			maxLen = Math.max(maxLen, right - left + 1);
			
			right++;
		}

        return maxLen;
    }
	
    /**
     * Better solution approach - Optimized sliding window with efficient shrinking.
     *
     * <p>This method uses an optimized sliding window approach that efficiently shrinks the window
     * from the left when the number of distinct characters exceeds k, rather than resetting
     * the entire window. This ensures we don't miss any potential optimal solutions.</p>
     *
     * <p><strong>Algorithm Steps:</strong></p>
     * <ol>
     *   <li>Use two pointers (left and right) to maintain a sliding window</li>
     *   <li>Expand the window by moving the right pointer and adding characters to a map</li>
     *   <li>When distinct characters exceed k, shrink from left by removing characters</li>
     *   <li>Update character frequencies and remove characters with zero frequency</li>
     *   <li>Track the maximum window size throughout the process</li>
     * </ol>
     *
     * <p><strong>Key Optimization:</strong> Instead of clearing the entire map and resetting,
     * this solution incrementally removes characters from the left side of the window,
     * maintaining the sliding window property efficiently.</p>
     *
     * @param str the input string to analyze
     * @param k the maximum number of distinct characters allowed in the substring
     * @return the length of the longest substring with at most k distinct characters
     */
	public static int kDistinctCharsSolutionBetter(String str, int k) {
		int maxLen = 0;
		int left = 0;
		int right = 0;
		int size = str.length();
		Map<String, Integer> map = new HashMap<>();
		
		while (right < size) {
			String ch = String.valueOf(str.charAt(right));
			
			if (map.containsKey(ch)) {
				map.put(ch, map.get(ch) + 1);
			} else {
				map.put(ch, 1);
			}
			
			while (map.size() > k) {
				String c = String.valueOf(str.charAt(left));
				
				if (map.containsKey(c)) {
					int frequency = map.get(c);
					if (frequency > 1) {
						map.put(c, frequency - 1);
					} else {
						map.remove(c);
					}
				}
				left++;
			}

			maxLen = Math.max(maxLen, right - left + 1);

			
			right++;
		}
		
		return maxLen;
	}

    public static void main(String[] args) {
        test("abbbbbbc", 2, "7");
		test("abcddefg", 3, "4");

    }

    private static void test(String input, int k, String expected) {
        int actual = kDistinctCharsSolutionBetter(input, k);
        printResult(String.valueOf(actual), String.valueOf(actual), expected);
    }
}
