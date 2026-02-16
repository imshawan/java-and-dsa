package solutions;

import java.util.*;

import static solutions.TestHelper.printResult;

/**
 * Combination Sum II
 * <p>
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sum to target.
 * <p>
 * Each number in candidates may only be used once in the combination.
 * <p>
 * Note: The solution set must not contain duplicate combinations.
 * <pre>
 * Example 1:
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Output:
 * [
 *   [1,1,6],
 *   [1,2,5],
 *   [1,7],
 *   [2,6]
 * ]
 * <pre>
 * Example 2:
 * Input: candidates = [2,5,2,1,2], target = 5
 * Output:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 */
public class CombinationSumII {
  public static void findCombinations(
      int idx,
      int target,
      int[] candidates,
      List<List<Integer>> combinations,
      List<Integer> tempList) {
    if (target == 0) {
      combinations.add(new ArrayList<>(tempList));
      return;
    }

    for (int i = idx; i < candidates.length; i++) {
      if ( i > idx && candidates[i] == candidates[i - 1]) continue;
      if (candidates[i] > target) break;

      tempList.add(candidates[i]);
      findCombinations(i + 1, target - candidates[i], candidates, combinations, tempList);
      tempList.removeLast();
    }
  }

  public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<List<Integer>> combinations = new ArrayList<>();
    Arrays.sort(candidates);
    findCombinations(0, target, candidates, combinations, new ArrayList<>());
    return combinations;
  }

  public static void main(String[] args) {
    test(
        new int[] {10, 1, 2, 7, 6, 1, 5},
        8,
        List.of(List.of(1, 1, 6), List.of(1, 2, 5), List.of(1, 7), List.of(2, 6)));
    test(new int[] {2, 5, 2, 1, 2}, 5, List.of(List.of(1, 2, 2), List.of(5)));
  }

  private static void test(int[] candidates, int target, List<List<Integer>> expected) {
    List<List<Integer>> output = combinationSum2(candidates, target);
    String input = "{candidates=" + Arrays.toString(candidates) + ", target=" + target + "}";
    printResult(input, String.valueOf(output), String.valueOf(expected));
  }
}
