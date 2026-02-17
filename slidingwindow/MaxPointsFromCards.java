package slidingwindow;

import java.util.Arrays;

import static solutions.TestHelper.printResult;

/**
 * Maximum Points You Can Obtain from Cards
 *
 * <p><strong>Problem Description:</strong></p>
 * <p>There are several cards arranged in a row, and each card has an associated number of points.
 * The points are given in the integer array cardPoints.</p>
 *
 * <p>In one step, you can take one card from the beginning or from the end of the row.
 * You have to take exactly k cards.</p>
 *
 * <p>Your score is the sum of the points of the cards you have taken.</p>
 *
 * <p>Given the integer array cardPoints and the integer k, return the maximum score you can obtain.</p>
 *
 * <p><strong>Examples:</strong></p>
 * <ul>
 *   <li><strong>Example 1:</strong><br>
 *       Input: cardPoints = [1,2,3,4,5,6,1], k = 3<br>
 *       Output: 12<br>
 *       Explanation: After the first step, your score will always be 1. However, choosing the
 *       rightmost card first will maximize your total score. The optimal strategy is to take
 *       the three cards on the right, giving a final score of 1 + 6 + 5 = 12.</li>
 *
 *   <li><strong>Example 2:</strong><br>
 *       Input: cardPoints = [2,2,2], k = 2<br>
 *       Output: 4<br>
 *       Explanation: Regardless of which two cards you take, your score will always be 4.</li>
 *
 *   <li><strong>Example 3:</strong><br>
 *       Input: cardPoints = [9,7,7,9,7,7,9], k = 7<br>
 *       Output: 55<br>
 *       Explanation: You have to take all the cards. Your score is the sum of points of all cards.</li>
 * </ul>
 *
 * <p><strong>Constraints:</strong></p>
 * <ul>
 *   <li>1 &lt;= cardPoints.length &lt;= 10<sup>5</sup></li>
 *   <li>1 &lt;= cardPoints[i] &lt;= 10<sup>4</sup></li>
 *   <li>1 &lt;= k &lt;= cardPoints.length</li>
 * </ul>
 *
 * <p><strong>Algorithm Approach:</strong></p>
 * <p>This solution uses a sliding window technique. The key insight is that we can take cards
 * from either end, so we need to consider all combinations of taking i cards from the left
 * and (k-i) cards from the right, where i ranges from 0 to k.</p>
 *
 * <p><strong>Time Complexity:</strong> O(k) - We iterate through k elements twice</p>
 * <p><strong>Space Complexity:</strong> O(1) - Only using constant extra space</p>
 *
 */
public class MaxPointsFromCards {
    public static int maxScore(int[] cardPoints, int k) {
        int left = 0;
        int right = 0;
        int maxScore;

        for (int i = 0; i < k; i++) {
            left += cardPoints[i];
        }
        maxScore = left;
		
		int rightIdx = cardPoints.length - 1;
		for (int i = k-1; i >= 0; i--) {
			left = left - cardPoints[i];
			right = right + cardPoints[rightIdx];
			
			maxScore = Math.max(maxScore, left + right);

            rightIdx--;
		} 

        return maxScore;
    }

    public static void main(String[] args) {

        test(new int[]{1,2,3,4,5,6,1}, 3, "12");
        test(new int[]{2,2,2}, 2, "4");
        test(new int[]{9,7,7,9,7,7,9}, 7, "55");
        test(new int[]{1,79,80,1,1,1,200,1}, 3, "202");
    }

    private static void test(int[] input, int target, String expected) {
        int actual = maxScore(input, target);
        printResult(Arrays.toString(input), String.valueOf(actual), expected);
    }
}
