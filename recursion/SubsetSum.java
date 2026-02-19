package recursion;

import java.util.*;

import static solutions.TestHelper.printResult;

/**
 * Subsets

 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * <p>
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 * <p>
 *
 *<pre>
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * </pre>
 *
 * <pre>
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 * </pre>
 *
 * Constraints:
 * <pre>
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * </pre>
 * All the numbers of nums are unique.
 */
public class SubsetSum {
  public static void findSubsets(int idx, int sum, int[] nums, List<List<Integer>> subsets, List<Integer> temp) {
	  if (idx == nums.length) {
		  subsets.add(new ArrayList<>(temp));
          return;
	  }
	  
	  
	  findSubsets(idx + 1, sum, nums, subsets, new ArrayList<>(temp));
	  
	  temp.add(nums[idx]);
	  findSubsets(idx + 1, sum + nums[idx], nums, subsets, temp);
  }

  public static List<List<Integer>> subsets(int[] nums) {
	List<List<Integer>> sets = new ArrayList<>();
	findSubsets(0, 0, nums, sets, new ArrayList<>());
	return sets;
  }

  public static void main(String[] args) {
    // Test case 1: Basic case [1, 2, 3]
    test(
        new int[] {1, 2, 3},
        List.of(
            List.of(),
            List.of(1),
            List.of(2),
            List.of(1, 2),
            List.of(3),
            List.of(1, 3),
            List.of(2, 3),
            List.of(1, 2, 3)
        ));

    // Test case 2: Two elements [1, 2]
    test(
        new int[] {1, 2},
        List.of(
            List.of(),
            List.of(1),
            List.of(2),
            List.of(1, 2)
        ));

    // Test case 3: Single element [4]
    test(
        new int[] {4},
        List.of(
            List.of(),
            List.of(4)
        ));

    // Test case 4: Empty array []
    test(
        new int[] {},
        List.of(
            List.of()
        ));

    // Test case 5: Four elements [1, 2, 3, 4]
    test(
        new int[] {1, 2, 3, 4},
        List.of(
            List.of(),
            List.of(1),
            List.of(2),
            List.of(1, 2),
            List.of(3),
            List.of(1, 3),
            List.of(2, 3),
            List.of(1, 2, 3),
            List.of(4),
            List.of(1, 4),
            List.of(2, 4),
            List.of(1, 2, 4),
            List.of(3, 4),
            List.of(1, 3, 4),
            List.of(2, 3, 4),
            List.of(1, 2, 3, 4)
        ));

    // Test case 6: Duplicate elements [1, 1]
    test(
        new int[] {1, 1},
        List.of(
            List.of(),
            List.of(1),
            List.of(1),
            List.of(1, 1)
        ));

    // Test case 7: Different numbers [5, 6]
    test(
        new int[] {5, 6},
        List.of(
            List.of(),
            List.of(5),
            List.of(6),
            List.of(5, 6)
        ));

    // Test case 8: Larger single element [10]
    test(
        new int[] {10},
        List.of(
            List.of(),
            List.of(10)
        ));
  }

  private static void test(int[] input, List<List<Integer>> expected) {
      List<List<Integer>> actual = subsets(input);
      boolean allContained = new HashSet<>(actual).containsAll(expected);

      // We can ignore the order of subsets, so we check if all expected subsets are present in the actual result.
      printResult(Arrays.toString(input), expected.toString(), (allContained ? expected.toString() : actual.toString()));
  }
}
