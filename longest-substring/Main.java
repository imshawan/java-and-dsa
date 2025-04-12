import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static String findLongestSubString(String str) {
        String longestSubstring = "";
        int size = str.length();
        int maxLength = 0;
        int start = 0;
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < size; i++) {
            String charStr = java.lang.String.valueOf(str.charAt(i));
            if (map.containsKey(charStr) && map.get(charStr) >= start) {
                start = map.get(charStr) + 1;
            }

            map.put(charStr, i);

            if (i - start + 1 > maxLength) {
                maxLength = i - start + 1;
                longestSubstring = str.substring(start, i+1);
            }
        }

        return longestSubstring;
    }
    public static void main(String[] args) {
        List<String> testCases = Arrays.asList("abcabcaaa", "bbbbb", "pwwkew");
        for (String cases: testCases) {
            System.out.println("Longest substring for '" + cases + "' is " + findLongestSubString(cases));
        }
    }
}