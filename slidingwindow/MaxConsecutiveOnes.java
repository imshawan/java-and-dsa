package slidingwindow;

import java.util.Arrays;

import static solutions.TestHelper.printResult;

public class MaxConsecutiveOnes {
  public static int longestOnesBruteForce(int[] nums, int k) {
    int maxLength = 0;
    int size = nums.length;
    int left = 0;

    while (left < size) {
      int zeros = 0;
      int count = 0;

      for (int i = left; i < size; i++) {
        if (nums[i] == 1) count++;
        else if (nums[i] == 0) {
          zeros++;
          count++;
        }

        if (zeros > k) {
          count--;
          break;
        }
      }

      maxLength = Math.max(maxLength, count);

      left++;
    }

    return maxLength;
  }

  public static void main(String[] args) {
    test(new int[] {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 3, "10");
    test(new int[] {0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1}, 3, "9");
    test(new int[] {0, 0, 0}, 3, "2");
    test(new int[] {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2, "6");
    test(new int[] {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3, "10");
  }

  private static void test(int[] input, int k, String expected) {
    String actual = String.valueOf(longestOnesBruteForce(input, k));
    String inputStr = "{nums=" + Arrays.toString(input) + ", k=" + k + "}";
    printResult(inputStr, actual, expected);
  }
}
