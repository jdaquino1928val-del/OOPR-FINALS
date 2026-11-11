import java.util.Scanner;

public class EvenNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
 
        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();

      
        int[] numbers = new int[size];

     
        System.out.println("Enter " + size + " numbers:");
        for (int i = 0; i < size; i++) {
            numbers[i] = scanner.nextInt();
        }

        System.out.println("\nEven Numbers:");
        boolean foundEven = false;

       
        for (int i = 0; i < size; i++) {
            if (numbers[i] % 2 == 0) {
                System.out.print(numbers[i] + " ");
                foundEven = true;
            }
        }

        if (!foundEven) {
            System.out.print("No even numbers were entered.");
        }
        System.out.println();

        scanner.close();
    }
}