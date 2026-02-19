package dynamicprogramming;

import java.util.Arrays;

import static solutions.TestHelper.printResult;

/**
 * <b>
 * Best Time to Buy and Sell Stock
 * </b>
 * 
 * <p><strong>Problem Description:</strong></p>
 * <p>You are given an array prices where prices[i] is the price of a given stock on the ith day.</p>
 * 
 * <p>You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.</p>
 * 
 * <p>Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.</p>
 * 
 * <p><strong>Examples:</strong></p>
 * <ul>
 *   <li><strong>Example 1:</strong><br>
 *       Input: prices = [7,1,5,3,6,4]<br>
 *       Output: 5<br>
 *       Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 *       Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.</li>
 *   
 *   <li><strong>Example 2:</strong><br>
 *       Input: prices = [7,6,4,3,1]<br>
 *       Output: 0<br>
 *       Explanation: In this case, no transactions are done and the max profit = 0.</li>
 * </ul>
 * 
 * <p><strong>Constraints:</strong></p>
 * <ul>
 *   <li>1 &lt;= prices.length &lt;= 10<sup>5</sup></li>
 *   <li>0 &lt;= prices[i] &lt;= 10<sup>4</sup></li>
 * </ul>
 * 
 * <p><strong>Algorithm Approach:</strong></p>
 * <p>This solution uses a <strong>single-pass greedy approach</strong> that tracks the minimum price seen so far
 * and calculates the maximum profit that can be achieved by selling at each subsequent day.</p>
 * 
 * <p><strong>Key Insights:</strong></p>
 * <ol>
 *   <li><strong>One Pass Solution:</strong> We only need to iterate through the array once</li>
 *   <li><strong>Track Minimum:</strong> Keep track of the minimum price seen so far (best buying opportunity)</li>
 *   <li><strong>Calculate Profit:</strong> At each day, calculate potential profit by selling at current price</li>
 *   <li><strong>Update Maximum:</strong> Keep track of the maximum profit achievable</li>
 * </ol>
 * 
 * <p><strong>Algorithm Steps:</strong></p>
 * <ol>
 *   <li>Initialize minimum price with the first day's price</li>
 *   <li>For each subsequent day, calculate profit if we sell on that day</li>
 *   <li>Update the maximum profit if current profit is better</li>
 *   <li>Update the minimum price if current price is lower</li>
 * </ol>
 * 
 * <p><strong>Time Complexity:</strong> O(n) where n is the number of days</p>
 * <p><strong>Space Complexity:</strong> O(1) - only using constant extra space</p>
 */
public class BestTimeToBuyAndSellStock {
    public static int maxProfit(int[] prices) {
        int profit = 0;
		int min = prices[0];
		
		for (int i = 1; i < prices.length; i++) {
			int cost = prices[i] - min;
			
			profit = Math.max(profit, cost);
			
			min = Math.min(min, prices[i]);
		}

        return profit;
    }

    public static void main(String[] args) {
        test(new int[] {7,1,5,3,6,4}, "5");
        test(new int[] {7,6,4,3,1}, "0");
    }

    private static void test(int[] input, String expected) {
        String actual = String.valueOf(maxProfit(input));
        printResult(Arrays.toString(input), actual, expected);
    }
}
