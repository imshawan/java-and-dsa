package solutions;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        int[] found = new int[0];
        for (int i = 0; i<nums.length; i++) {
            for (int j = 0; j<nums.length; j++) {
                if (i != j && (nums[i] + nums[j] == target)) {
                    found = new int[]{nums[i], nums[j]};
                    break;
                }
            }
        }

        return found;
    }

    public static void main(String[] args) {

        // 1. Simple positive numbers
        int[] set1 = {1, 6, 2, 10, 3};
        printResult("Test 1 (Expected [1, 6])", twoSum(set1, 7));

        // 2. Includes negative numbers
        int[] set2 = {1, 3, 5, -7, 6, -3};
        printResult("Test 2 (Expected [3, -3])", twoSum(set2, 0));

        // 3. Pair at the beginning
        int[] set3 = {4, 5, 1, 2};
        printResult("Test 3 (Expected [4, 5])", twoSum(set3, 9));

        // 4. Pair at the end
        int[] set4 = {1, 2, 3, 7, 8};
        printResult("Test 4 (Expected [7, 8])", twoSum(set4, 15));

        // 5. Duplicate numbers
        int[] set5 = {3, 3, 4};
        printResult("Test 5 (Expected [3, 3])", twoSum(set5, 6));

        // 6. Zero handling
        int[] set6 = {0, 4, 3, 0};
        printResult("Test 6 (Expected [0, 0])", twoSum(set6, 0));

        // 7. Single element (no solution)
        int[] set7 = {5};
        printResult("Test 7 (Expected No pair)", twoSum(set7, 5));

        // 8. No valid pair
        int[] set8 = {1, 2, 3};
        printResult("Test 8 (Expected No pair)", twoSum(set8, 10));

        // 9. Negative-only array
        int[] set9 = {-1, -2, -3, -4};
        printResult("Test 9 (Expected [-1, -4])", twoSum(set9, -5));

        // 10. Multiple valid pairs (any one is acceptable)
        int[] set10 = {1, 2, 3, 4, 5};
        printResult("Test 10 (Expected any valid pair)", twoSum(set10, 6));
    }

    static void printResult(String label, int[] result) {
        if (result.length == 0) {
            System.out.println(label + ": No pair found");
        } else {
            System.out.println(label + ": [" + result[0] + ", " + result[1] + "]");
        }
    }
}
