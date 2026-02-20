package recursion;

import java.util.*;

import static solutions.TestHelper.printResult;

/**
 * Palindrome Partitioning
 * <p>
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * <pre>
 * Example 1:
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 * <pre>
 * Example 2:
 * Input: s = "a"
 * Output: [["a"]]
 * <pre>
 * Constraints:
 * 1 <= s.length <= 16
 * s contains only lowercase English letters.
 */
public class PalindromePartitioning {
    public static void findValidPartitions(int idx, String str, List<List<String>> partitions, List<String> current) {
		if (idx == str.length()) {
			partitions.add(new ArrayList<>(current));
			return;
		}
		
		for (int i = idx; i < str.length(); i++) {
			String partition = str.substring(idx, i + 1);
			
			if (isPalindrome(str, idx, i)) {
				current.add(partition);
				findValidPartitions(i + 1, str, partitions, current);
				current.removeLast();
			}
		}
	}

    public static List<List<String>> partition(String s) {
        List<List<String>> partitions = new ArrayList<>();
		findValidPartitions(0, s, partitions, new ArrayList<>());
		return partitions;
    }
	
	public static boolean isPalindrome(String str, int start, int end) {
		while (start <= end) {
			if (str.charAt(start++) != str.charAt(end--)) {
				return false;
			}
		}
		return true;
	}

    public static void main(String[] args) {
        test("aab", List.of(List.of("a", "a", "b"), List.of("aa", "b")));
        test("a", List.of(List.of("a")));
    }

    private static void test(String s, List<List<String>> expected) {
        List<List<String>> output = partition(s);
        printResult(s, String.valueOf(output), String.valueOf(expected));
    }
}
