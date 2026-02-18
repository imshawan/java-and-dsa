package slidingwindow;

import java.util.HashMap;
import java.util.Map;

import static solutions.TestHelper.printResult;

public class LongestSubstring {

    public static String findLongestSubString(String str) {
        String longest = "";
		int size = str.length();
		int left = 0;
		int right = 0;
		int maxLength = 0;
		Map<String, Integer> map = new HashMap<>();
		
		while (right < size) {
			String ch = String.valueOf(str.charAt(right));
			
			if (map.containsKey(ch)) {
				left = Math.max(left, map.get(ch) + 1);
			}
			
			map.put(ch, right);
			
			if (right - left + 1 > maxLength) {
				maxLength = right - left + 1;
				longest = str.substring(left, right + 1);
			}
			
			right++;
		}

        return  longest;
    }

    public static void main(String[] args) {
        test("abcabcaaa", "abc");
        test("bbbbb", "b");
        test("pwwkew", "wke");
        test("abcabcbabcdefaa", "abcdef");
    }

    private static void test(String input, String expected) {
        String actual = findLongestSubString(input);
        printResult(actual, actual, expected);
    }
}
