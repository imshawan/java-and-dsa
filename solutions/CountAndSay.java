package solutions;

import static solutions.TestHelper.*;

public class CountAndSay {
    
    public static String countAndSay(int n) {
        String strNum = "1";

        if (n <= 1) {
            return String.valueOf(n);
        }

        for (int i = 1; i < n; i++) {
            strNum = calculateFrequencies(strNum);
        }

        return strNum;
    }

    public static String calculateFrequencies(String seq) {
        StringBuilder sb = new StringBuilder();

        int count = 1;
        for (int i = 1; i <= seq.length(); i++) {
            char current = seq.charAt(i - 1);
            if (i < seq.length() && seq.charAt(i) == seq.charAt(i - 1)) {
                count++;
            } else {
                sb.append(count).append(current);
                count = 1;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        test(1, "1");
        test(2, "11");
        test(3, "21");
        test(4, "1211");
        test(5, "111221");
        test(6, "312211");
        test(7, "13112221");
        test(8, "1113213211");
        test(9, "31131211131221");
        test(10, "13211311123113112211");
    }

    private static void test(int input, String expected) {
        String actual = countAndSay(input);
        printResult(String.valueOf(input), actual, expected);
    }


}
