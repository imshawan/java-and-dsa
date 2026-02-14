package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static solutions.TestHelper.*;

/**
 * Combination Sum
 * <p>
 * Problem Description:
 * Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen numbers
 * sum to target. You may return the combinations in any order.
 * <p>
 * The same number may be chosen from candidates an unlimited number of times.
 * Two combinations are unique if the frequency of at least one of the chosen
 * numbers is different.
 * <p>
 * Examples:
 * <pre>
 * Example 1:
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation: 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 *              7 is a candidate, and 7 = 7. These are the only two combinations.
 * <pre>
 * Example 2:
 * Input: candidates = [2,3,5], target = 8
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 * <pre>
 * Example 3:
 * Input: candidates = [2], target = 1
 * Output: []
 * </pre>
 * <p>
 * Algorithm Approach:
 * Uses backtracking to explore all possible combinations. For each candidate at index i,
 * we have two choices:
 * 1. Include the candidate (if it doesn't exceed the target) and stay at the same index
 *    (allowing reuse of the same number)
 * 2. Skip the candidate and move to the next index
 *
 */
public class CombinationSum {
  public static void findCombinations(
      int idx, int target, int[] candidates, List<List<Integer>> combinations, List<Integer> ds) {
    if (idx == candidates.length) {
      if (target == 0) {
        combinations.add(new ArrayList<>(ds));
      }

      return;
    }

    if (candidates[idx] <= target) {
      ds.add(candidates[idx]);
      findCombinations(idx, target - candidates[idx], candidates, combinations, ds);
      ds.removeLast();
    }
    findCombinations(idx + 1, target, candidates, combinations, ds);
  }

  public static List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> combinations = new ArrayList<>();
    findCombinations(0, target, candidates, combinations, new ArrayList<>());
    return combinations;
  }

  public static void main(String[] args) {
    test(new int[] {2, 3, 6, 7}, 7, List.of(List.of(2, 2, 3), List.of(7)));
    test(new int[] {2, 3, 5}, 8, List.of(List.of(2, 2, 2, 2), List.of(2, 3, 3), List.of(3, 5)));
    test(new int[] {2}, 1, List.of());

    // Test with single element that matches target
    test(new int[] {5}, 5, List.of(List.of(5)));

    // Test with multiple valid combinations
    test(
        new int[] {1, 2, 3},
        4,
        List.of(List.of(1, 1, 1, 1), List.of(1, 1, 2), List.of(1, 3), List.of(2, 2)));

    // Test with larger numbers
    test(
        new int[] {10, 20, 30},
        50,
        List.of(
            List.of(10, 10, 10, 10, 10),
            List.of(10, 10, 10, 20),
            List.of(10, 10, 30),
            List.of(10, 20, 20),
            List.of(20, 30)));

    // Test with no valid combinations (target smaller than the smallest element)
    test(new int[] {5, 10, 15}, 3, List.of());

    // Test with target 1 and candidates containing 1
    test(new int[] {1, 2, 4}, 1, List.of(List.of(1)));

    // Test with larger target and small candidates
    test(
        new int[] {1, 3},
        6,
        List.of(List.of(1, 1, 1, 1, 1, 1), List.of(1, 1, 1, 3), List.of(3, 3)));

    // Test edge case: target 0
    test(new int[] {1, 2, 3}, 0, List.of(List.of()));

    // Test with single large candidate matching target
    test(new int[] {15}, 15, List.of(List.of(15)));

    // Test with mixed small and large numbers
    test(
        new int[] {1, 5, 10},
        11,
        List.of(
            List.of(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
            List.of(1, 1, 1, 1, 1, 1, 5),
            List.of(1, 5, 5),
            List.of(1, 10)));
  }

  private static void test(int[] candidates, int target, List<List<Integer>> expected) {
    List<List<Integer>> output = combinationSum(candidates, target);
    String input = "{candidates=" + Arrays.toString(candidates) + ", target=" + target + "}";
    printResult(input, String.valueOf(output), String.valueOf(expected));
  }
}
