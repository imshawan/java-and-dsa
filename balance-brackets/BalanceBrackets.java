import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

public class BalanceBrackets {

    public static boolean isBalanced(String str) {
        List<String> openingBracList = Arrays.asList("{", "[", "(");
        List<String> closingBracList = Arrays.asList("]", "}", ")");
        Map<String, String> bracketPairs = Map.of(
            "}", "{",
            ")", "(",
            "]", "["
            );
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            String bracket = String.valueOf(str.charAt(i));
            if (openingBracList.contains(bracket)) {
                stack.push(bracket);
            } else if (closingBracList.contains(bracket)) {
                if (stack.isEmpty()) return false;

                String popped = stack.pop();
                String pair = bracketPairs.get(bracket);

                if (!Objects.equals(popped, pair)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
    public static void main(String[] args) {
        List<String> testCases = Arrays.asList(
            "(){}[]",
            "({[]})",
            "([])",
            "{[({})]}",
            "({[)}]",
            "((())",
                "(*))"
        );

        for (String test : testCases) {
            System.out.println("isBalanced(\"" + test + "\") = " + isBalanced(test));
        }
    }
}