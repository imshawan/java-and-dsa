package slidingwindow;

import java.util.*;

import static solutions.TestHelper.printResult;

/**
 * Fruit Into Baskets
 *
 * <p><strong>Problem Description:</strong></p>
 * <p>You are visiting a farm that has a single row of fruit trees arranged from left to right.
 * The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.</p>
 *
 * <p>You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:</p>
 * <ul>
 *   <li>You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.</li>
 *   <li>Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.</li>
 *   <li>Once you reach a tree with fruit that cannot fit in your baskets, you must stop.</li>
 * </ul>
 *
 * <p>Given the integer array fruits, return the maximum number of fruits you can pick.</p>
 *
 * <p><strong>Examples:</strong></p>
 * <ul>
 *   <li><strong>Example 1:</strong><br>
 *       Input: fruits = [1,2,1]<br>
 *       Output: 3<br>
 *       Explanation: We can pick from all 3 trees.</li>
 *
 *   <li><strong>Example 2:</strong><br>
 *       Input: fruits = [0,1,2,2]<br>
 *       Output: 3<br>
 *       Explanation: We can pick from trees [1,2,2].
 *       If we had started at the first tree, we would only pick from trees [0,1].</li>
 *
 *   <li><strong>Example 3:</strong><br>
 *       Input: fruits = [1,2,3,2,2]<br>
 *       Output: 4<br>
 *       Explanation: We can pick from trees [2,3,2,2].
 *       If we had started at the first tree, we would only pick from trees [1,2].</li>
 * </ul>
 *
 * <p><strong>Constraints:</strong></p>
 * <ul>
 *   <li>1 &lt;= fruits.length &lt;= 10<sup>5</sup></li>
 *   <li>0 &lt;= fruits[i] &lt; fruits.length</li>
 * </ul>
 *
 * <p><strong>Algorithm Approach:</strong></p>
 * <p>This problem is equivalent to finding the longest subarray with at most 2 distinct elements.
 * The solution uses a sliding window technique with a HashMap to track fruit types and their frequencies.</p>
 *
 * <p><strong>Algorithm Steps:</strong></p>
 * <ol>
 *   <li>Use two pointers (left and right) to maintain a sliding window</li>
 *   <li>Expand the window by moving the right pointer and adding fruits to the map</li>
 *   <li>When we have more than 2 distinct fruit types, shrink the window from the left</li>
 *   <li>Update fruit frequencies and remove fruits with zero frequency from the map</li>
 *   <li>Track the maximum window size throughout the process</li>
 * </ol>
 *
 * <p><strong>Time Complexity:</strong> O(n) where n is the length of the fruits array</p>
 * <p><strong>Space Complexity:</strong> O(1) as we store at most 2 distinct fruit types in the HashMap</p>
 *
 */
public class FruitIntoBaskets {
    public static int totalFruit(int[] fruits) {
        int maxFruits = 0;
        int size = fruits.length;
		int left = 0;
		int right = 0;
		Map<Integer, Integer> map = new HashMap<>();
		
		while (right < size) {
            int fruit = fruits[right];
			
			if (map.containsKey(fruit)) {
				map.put(fruit, map.get(fruit) + 1);
			} else {
				map.put(fruit, 1);
			}
			
			if (map.size() > 2) {
				int leftFruit = fruits[left];
				if (map.containsKey(leftFruit)) {
					int freq = map.get(leftFruit);
					if (freq > 1) {
						map.put(leftFruit, freq - 1);
					} else {
						map.remove(leftFruit);
					}
				}
				left++;
			}
			
			maxFruits = Math.max(maxFruits, right - left + 1);
			
			right++;
        }

        return  maxFruits;
    }

    public static void main(String[] args) {
        test(new int[] {1,2,1}, "3");
        test(new int[] {0,1,2,2}, "3");
        test(new int[] {1,2,3,2,2}, "4");
    }

    private static void test(int[] input, String expected) {
        String actual = String.valueOf(totalFruit(input));
        printResult(Arrays.toString(input), actual, expected);
    }
}
