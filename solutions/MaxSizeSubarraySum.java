package solutions;

import java.util.*;

/**
 * Maximum Size Subarray Sum Equals k
 * <p>
 * Given an integer array nums and an integer k,
 * return the maximum length of a subarray that sums to k.
 * If there is not one, return 0 instead.
 * <p>
 * Example 1
 * Input: nums = [1,-1,5,-2,3], k = 3
 * Output: 4

 * Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
 * <p>
 * Example 2
 * Input: nums = [-2,-1,2,1], k = 1
 * Output: 2

 * Explanation: The subarray [-1, 2] sums to 1 and is the longest.
 */
public class MaxSizeSubarraySum {
    public static int maxSubArrayLen(int[] nums, int k) {
        List<List<Integer>> maxSubArrays = new ArrayList<>();

        for (int i = 0; i<nums.length; i++) {
            List<Integer> current = new ArrayList<>();
            int total = 0;
            for (int j=i; j<nums.length; j++) {
                total += nums[j];
                current.add(nums[j]);

                if (total == k) {
                    maxSubArrays.add(new ArrayList<>(current));
                }
            }
        }

        return maxSubArrays.stream()
                .mapToInt(List::size).max()
                .orElse(0);
    }

    public static void main(String[] args) {

        // 1. Given example
        int[] nums1 = {1, -1, 5, -2, 3};
        System.out.println("Test 1 (Expected 4): " + maxSubArrayLen(nums1, 3));

        // 2. Given example (subarray does NOT start at 0)
        int[] nums2 = {-2, -1, 2, 1};
        System.out.println("Test 2 (Expected 2): " + maxSubArrayLen(nums2, 1));

        // 3. Subarray in the middle
        int[] nums3 = {3, 1, -1, 2, -2};
        System.out.println("Test 3 (Expected 5): " + maxSubArrayLen(nums3, 3));

        // 4. Whole array sums to k
        int[] nums4 = {1, 2, 3};
        System.out.println("Test 4 (Expected 3): " + maxSubArrayLen(nums4, 6));

        // 5. No valid subarray
        int[] nums5 = {1, 2, 3};
        System.out.println("Test 5 (Expected 0): " + maxSubArrayLen(nums5, 10));

        // 6. Single element equals k
        int[] nums6 = {5};
        System.out.println("Test 6 (Expected 1): " + maxSubArrayLen(nums6, 5));

        // 7. Single element not equal to k
        int[] nums7 = {5};
        System.out.println("Test 7 (Expected 0): " + maxSubArrayLen(nums7, 3));

        // 8. All zeros, k = 0
        int[] nums8 = {0, 0, 0, 0};
        System.out.println("Test 8 (Expected 4): " + maxSubArrayLen(nums8, 0));

        // 9. Multiple valid subarrays, choose longest
        int[] nums9 = {1, -1, 1, -1, 1};
        System.out.println("Test 9 (Expected 4): " + maxSubArrayLen(nums9, 0));

        // 10. Large negative numbers
        int[] nums10 = {-5, -5, 10};
        System.out.println("Test 10 (Expected 3): " + maxSubArrayLen(nums10, 0));

        // 11. Empty array
        int[] nums11 = {};
        System.out.println("Test 11 (Expected 0): " + maxSubArrayLen(nums11, 0));
    }
}
