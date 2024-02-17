package Language;
import java.util.Scanner;

class DatatypesAndVariables {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner scn = new Scanner(System.in);

        System.out.print("\nEnter first number: ");
        int x = scn.nextInt();
        
        System.out.print("Enter second number: ");
        int y = scn.nextInt();

        System.out.print("\nAfter addition: ");
        System.out.println(x + y);
    }
}