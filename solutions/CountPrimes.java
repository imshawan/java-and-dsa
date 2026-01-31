package solutions;

import java.util.Arrays;

import static solutions.TestHelper.printResult;

/**
 * <b>
 * Count Primes
 * </b>
 * <p>
 * Given an integer {@code n}, return the number of prime numbers that are
 * strictly less than {@code n}.
 *
 * <p>
 * A prime number is a natural number greater than 1 that has no positive
 * divisors other than 1 and itself.
 * </p>
 *
 * <p>
 * <b>Example 1:</b>
 * </p>
 *
 * <pre>
 * Input:  n = 10
 * Output: 4
 *
 * Explanation:
 * There are 4 prime numbers less than 10: 2, 3, 5, and 7.
 * </pre>
 *
 * <p>
 * <b>Example 2:</b>
 * </p>
 *
 * <pre>
 * Input:  n = 0
 * Output: 0
 * </pre>
 *
 * <p>
 * <b>Example 3:</b>
 * </p>
 *
 * <pre>
 * Input:  n = 1
 * Output: 0
 * </pre>
 */
public class CountPrimes {
    public static int countPrimes(int n) {
        if (n < 2) {
            return 0;
        }

        boolean[] listOfPrimes = new boolean[n];
        for (int i = 0; i < n; i++) {
            listOfPrimes[i] = true;
        }

        listOfPrimes[0] = false; // 0 is not prime
        listOfPrimes[1] = false; // 1 is not prime

        // Factors come in pairs.
        // If a number n is composite (not prime), it must have two factors, a and b, such that a * b = n.
        // The rule is: One of those factors must be smaller than or equal to the square root of n.
        for (int i = 2; i*i<n; i++) {
            if (listOfPrimes[i]) {
                for (int j = i * i; j<n; j+=i) {
                    listOfPrimes[j] = false;
                }
            }

            if (i == 2 || i == 3) {
                listOfPrimes[i] = true;
            }
        }

        int count = 0;

        for (int i = 0; i < n; i++) {
            if (listOfPrimes[i]) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        test(10, 4);
        test(0, 0);
        test(1, 0);
        test(2, 0);
        test(3, 1);
        test(5, 2);
        test(20, 8);
    }

    private static void test(int input, int expected) {
        int actual = countPrimes(input);
        printResult(String.valueOf(input), String.valueOf(actual), String.valueOf(expected));
    }
}
