package solutions;

import java.util.*;

/**
 * Valid Parenthesis Checker
 *
 * <p>
 * Given a string s consisting only of the characters
 * '(', ')', and '*', determine whether the string is valid.
 * </p>
 *
 * <p>
 * A string is considered valid if all the following conditions are met:
 * </p>
 *
 * <ul>
 *   <li>Every left parenthesis '(' has a corresponding right parenthesis ')'</li>
 *   <li>Every right parenthesis ')' has a corresponding left parenthesis '('</li>
 *   <li>A left parenthesis '(' must appear before its corresponding right parenthesis ')'</li>
 *   <li>The character '*' can be treated as:
 *     <ul>
 *       <li>a left parenthesis '('</li>
 *       <li>a right parenthesis ')'</li>
 *       <li>an empty string ""</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * <p>
 * <b>Example 1:</b>
 * </p>
 *
 * <pre>
 * Input:  s = "(*))"
 * Output: true
 *
 * Explanation:
 * The '*' can be replaced by an opening parenthesis '('.
 * The resulting string "(())" is valid.
 * </pre>
 *
 * <p>
 * <b>Example 2:</b>
 * </p>
 *
 * <pre>
 * Input:  s = "*(()"
 * Output: false
 *
 * Explanation:
 * Replacing '*' with any possible value does not result in a valid string.
 * </pre>
 */
public class ValidParenthesisChecker {
    public static boolean isValid(String s) {
        Map<String, String> mapOfBrackets = Map.of(
                "}", "{",
                ")", "(",
                "]", "["
        );

        Stack<String> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            String bracket = String.valueOf(s.charAt(i));

            if (mapOfBrackets.containsValue(bracket)) { // Opening brackets are in Map values
                stack.push(bracket);
            } else if (mapOfBrackets.containsKey(bracket)) {
                boolean isEmpty = stack.isEmpty();
                String poppedBracket = stack.pop();
                String openBracket = mapOfBrackets.get(bracket);

                if (isEmpty || !poppedBracket.equals(openBracket)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static boolean checkValidBrackets(String s) {
        List<String> starReplacements = List.of("", "(", ")");
        boolean valid = false;
        for (String starReplacement : starReplacements) {
            try {
                valid = isValid(s.replaceAll("\\*", starReplacement));
                if (valid) {
                    return valid;
                }
            } catch (Exception ignored) {}
        }

        return valid;
    }

    public static void main(String[] args) {
        test("(*))", true);
        test("*(()", false);
        test("()", true);
        test("(*()", true);
        test("(*)(", false);
        test("*", true);
        test("**", true);
        test(")*(", false);
        test("(((*)))", true);
        test("", true);
    }

    private static void test(String input, boolean expected) {
        boolean actual = checkValidBrackets(input);

        System.out.println("Input    : " + input);
        System.out.println("Expected : " + expected);
        System.out.println("Actual   : " + actual);
        System.out.println("Result   : " + (actual == expected ? "PASS" : "FAIL"));
        System.out.println("-------------------------");
    }

}
