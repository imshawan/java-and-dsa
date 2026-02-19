package slidingwindow;

import java.util.*;

/**
 * Sliding Window Maximum
 *
 * <p><strong>Problem Description:</strong></p>
 * <p>You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.</p>
 *
 * <p>Return the max sliding window.</p>
 *
 * <p><strong>Examples:</strong></p>
 * <ul>
 *   <li><strong>Example 1:</strong><br>
 *       Input: nums = [1,3,-1,-3,5,3,6,7], k = 3<br>
 *       Output: [3,3,5,5,6,7]<br>
 *       Explanation:<br>
 *       <pre>
 *       Window position                Max
 *       ---------------               -----
 *       [1  3  -1] -3  5  3  6  7       3
 *        1 [3  -1  -3] 5  3  6  7       3
 *        1  3 [-1  -3  5] 3  6  7       5
 *        1  3  -1 [-3  5  3] 6  7       5
 *        1  3  -1  -3 [5  3  6] 7       6
 *        1  3  -1  -3  5 [3  6  7]      7
 *       </pre></li>
 *
 *   <li><strong>Example 2:</strong><br>
 *       Input: nums = [1], k = 1<br>
 *       Output: [1]</li>
 * </ul>
 *
 * <p><strong>Constraints:</strong></p>
 * <ul>
 *   <li>1 &lt;= nums.length &lt;= 10<sup>5</sup></li>
 *   <li>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></li>
 *   <li>1 &lt;= k &lt;= nums.length</li>
 * </ul>
 *
 * <p><strong>Algorithm Approach:</strong></p>
 * <p>This solution uses a <strong>Monotonic Deque</strong> (decreasing order) to efficiently track potential maximum elements.
 * The deque stores indices of array elements in decreasing order of their values, ensuring that the front always
 * contains the index of the maximum element in the current window.</p>
 *
 * <p><strong>Key Algorithm Steps:</strong></p>
 * <ol>
 *   <li><strong>Remove Outdated Indices:</strong> Remove indices that are outside the current window (i - k)</li>
 *   <li><strong>Maintain Monotonic Property:</strong> Remove smaller elements from the back that can never be maximum</li>
 *   <li><strong>Add Current Index:</strong> Add the current index to maintain the sliding window</li>
 *   <li><strong>Record Maximum:</strong> When window is fully formed, record the maximum (front of deque)</li>
 * </ol>
 *
 * <p><strong>Time Complexity:</strong> O(n) - each element is added and removed at most once</p>
 * <p><strong>Space Complexity:</strong> O(k) - deque stores at most k elements</p>
 *
 */
public class SlidingWindowMaximum {

    public static int[] solve(int[] nums, int k) {

        if (nums == null || nums.length == 0 || k <= 0)
            return new int[0];

        int n = nums.length;
        int[] result = new int[n - k + 1];

        Deque<Integer> dq = new ArrayDeque<>();
        int idx = 0;

        for (int i = 0; i < n; i++) {

            while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }

            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.pollLast();
            }

            dq.offerLast(i);

            if (i >= k - 1 && !dq.isEmpty()) {
                result[idx++] = nums[dq.peekFirst()];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        var result = solve(new int[]{1,3,-1,-3,5,3,6,7}, 3);
        System.out.println(Arrays.toString(result)); // [3, 3, 5, 5, 6, 7]
    }
}
