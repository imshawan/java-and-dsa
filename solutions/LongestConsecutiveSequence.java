package solutions;

import java.util.*;

import static solutions.TestHelper.printResult;

/**
 * Longest Consecutive Sequence
 * Given an unsorted array of integers nums, return the length of the
 * longest consecutive elements sequence.
 * <p>
 * The algorithm must run in O(n) time.
 *
 * @returns {number} The length of the longest consecutive sequence
 *
 * @example
 * Input: nums = [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation:
 * The longest consecutive elements sequence is [1, 2, 3, 4],
 * therefore its length is 4.
 *
 * @example
 * Input: nums = [0, 3, 7, 2, 5, 8, 4, 6, 0, 1]
 * Output: 9
 *
 * @example
 * Input: nums = [1, 0, 1, 2]
 * Output: 3
 */

public class LongestConsecutiveSequence {
    public static int longestConsecutive(int[] nums) {
        if (nums.length < 1) {
            return 0;
        }

        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        if (numSet.size() == 1) {
            return 1;
        }

        int longestStreak = 0;
        for (int num : numSet) {
            // To check if this is the starting point of a sequence
            if (!numSet.contains(num - 1)) {
                int current = num;
                int currentStreak = 1;

                while (numSet.contains(current + 1)) {
                    currentStreak++;
                    current++;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }

    public static void main(String[] args) {
        test(new int[]{100, 4, 200, 1, 3, 2}, 4);
        test(new int[]{0,3,7,2,5,8,4,6,0,1}, 9);
        test(new int[]{1,0,1,2}, 3);
        test(new int[]{0}, 1);
        test(new int[]{0,0}, 1);
        test(new int[]{1, 100}, 1);
        test(new int[]{1,2,3,4,10,11}, 4);
        test(new int[]{23, 24, 5, 1,2,3,4,10,11}, 5);
    }

    private static void test(int[] input, int expected) {
        int actual = longestConsecutive(input);
        printResult(Arrays.toString(input), String.valueOf(actual), String.valueOf(expected));
    }
}
