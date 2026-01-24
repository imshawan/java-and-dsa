package solutions;

public class TestHelper {
    private static final String GREEN = "\u001B[32m";
    private static final String RED   = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    public static void printResult(String input, String actual, String expected) {
        boolean pass = expected.equals(actual);

        System.out.println("Input    : " + input);
        System.out.println("Expected : " + expected);
        System.out.println("Actual   : " + actual);

        if (pass) {
            System.out.println("Result   : " + GREEN + "PASS" + RESET);
        } else {
            System.out.println("Result   : " + RED + "FAIL" + RESET);
        }

        System.out.println("-------------------------");
    }
}
