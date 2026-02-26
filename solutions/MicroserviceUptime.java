package solutions;

import static solutions.TestHelper.printResult;

/**
 *
 *
 * <h3>Problem: Maximum Consecutive Microservice Uptime</h3>
 *
 * <p><strong>Description:</strong><br>
 * You are given {@code m} microservices and {@code n} days of log data. The log data is represented
 * as an array of strings {@code data} of length {@code n}, where each string is of length {@code
 * m}.
 *
 * <p>Each character in the string represents the status of a microservice on that day: 'Y' means
 * the microservice was up, and 'N' means it was down.
 *
 * <p>Find the maximum number of consecutive days when <strong>all</strong> {@code m} microservices
 * were up simultaneously. *
 *
 * <p><strong>Example:</strong>
 *
 * <pre>
 * Input: m = 3, n = 5, data = ["YYY", "YYY", "YYN", "NNN", "YNY"]
 * Output: 2
 * Explanation:
 * Day 1: "YYY" (All UP) -> streak = 1
 * Day 2: "YYY" (All UP) -> streak = 2
 * Day 3: "YYN" (Not all UP) -> streak breaks
 * Day 4: "NNN" (Not all UP) -> streak = 0
 * Day 5: "YNY" (Not all UP) -> streak = 0
 * Maximum consecutive days = 2.
 * </pre>
 *
 * *
 *
 * <p><strong>Constraints:</strong>
 *
 * <ul>
 *   <li>{@code 1 <= n <= 10^5}
 *   <li>{@code 1 <= m <= 100}
 *   <li>{@code data[i]} consists only of 'Y' and 'N'.
 * </ul>
 */
public class MicroserviceUptime {

  public static int getMaxConsecutiveUptimeDays(int m, int n, String[] data) {
    int maxStreak = 0;
    int currentStreak = 0;

    for (int i = 0; i < n; i++) {
      String day = data[i];
      if (areAllServicesUp(day, m)) {
        currentStreak++;
        maxStreak = Math.max(maxStreak, currentStreak);
      } else {
        currentStreak = 0; // Reset streak if any service goes down
      }
    }

    return maxStreak;
  }

  private static boolean areAllServicesUp(String dayStatus, int m) {
    for (int i = 0; i < m; i++) {
      if (dayStatus.charAt(i) == 'N') {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    test(new String[] {"YYY", "YYY", "YYN", "NNN", "YNY"}, 3, 5, "2");
    test(new String[] {"YYY", "YYY", "YYN", "NNN", "YYY", "YYY", "YYY", "YNY"}, 3, 7, "3");
    test(new String[] {"NNN", "NNN", "NNN"}, 3, 3, "0");
    test(new String[] {"YYY", "YYY", "YYY"}, 3, 3, "3");
    test(new String[] {"YNY", "YNY", "YNY"}, 3, 3, "0");
  }

  private static void test(String[] input, int k, int n, String expected) {
    int actual = getMaxConsecutiveUptimeDays(k, n, input);
    String inputStr =
        String.format("{m = %d, n = %d, data = %s}", k, n, java.util.Arrays.toString(input));
    printResult(inputStr, String.valueOf(actual), expected);
  }
}
