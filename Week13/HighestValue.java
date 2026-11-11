import java.util.Scanner;

public class HighestValue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();
        
        int[] numbers = new int[size];

        
        System.out.print("enter numbers: ");
        for (int i = 0; i < size; i++) {
            numbers[i] = scanner.nextInt();
        }

        
        int max = numbers[0];
        for (int i = 1; i < size; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }

        
        System.out.println("the highest is: " + max);
        System.out.println("the difference from the highest are:");
        for (int i = 0; i < size; i++) {
            System.out.println(max + " - " + numbers[i] + " = " + (max - numbers[i]));
        }

        scanner.close();
    }
}