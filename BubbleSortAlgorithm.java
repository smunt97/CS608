import java.util.*;

/**
 * A class implementing the Bubble Sort algorithm for sorting integers.
 */
public class BubbleSortAlgorithm {
    /**
     * Main method to start the program and handle user input.
     * Allows the user to enter numbers to be sorted or to exit the program.
     * 
     * @param args Command-line arguments (not used).
     */

    public static void main(String args[]) {
        // Initializing a scanner to read input from the console
        Scanner scanner = new Scanner(System.in);
        
        // Creating an ArrayList to store the input numbers
        ArrayList<Integer> inputArray = new ArrayList<Integer>();
        
        // Prompting the user to enter numbers or type 'exit' to quit
        System.out.println("Please keep on entering a number or type 'exit' to quit:");
        
        // Reading the input until the user types 'exit'
        while (true) {
            String input = scanner.nextLine(); // Reading the next line of input from the console
            
            // Checking if the user wants to exit the program
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting program...");
                break;
            }
            
            try {
                // Trying to parse the input as an integer and add it to the ArrayList 'inputArray'
                int number = Integer.parseInt(input);
                inputArray.add(number);
            } 
            
             // Display an error message if the input is not an integer
            catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter intger only or type 'exit' to quit.");
            }
        }
        
        // Displaying the input ArrayList before sorting
        System.out.println("\nThe Numbers Before Sorting is:"); 
        PrintArray(inputArray);
        
        // Sorting the input ArrayList using the Merge Sort Algorithm
        BubbleSort(inputArray);
        
        // Displaying the input ArrayList after sorting
        System.out.println("\nThe Numbers After Sorting is:");
        PrintArray(inputArray);
    }

    /**
     * Prints the elements of an ArrayList of integers.
     * 
     * @param arr The ArrayList to be printed.
     */

    // Function to print the elements of the ArrayList
    public static void PrintArray(ArrayList<Integer> arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    /**
     * Sorts an ArrayList of integers using the Bubble Sort algorithm.
     * 
     * @param arr The ArrayList of integers to be sorted.
     */

    // Function to perform the BubbleSort on ArraList of Integers
    public static void BubbleSort(ArrayList<Integer> arr) {
        int n = arr.size(); // Getting the size of the ArrayList

        // Traversing through the array elements
        for(int i = 0; i < n; i++) {
            // Traversing the array from 0 to n-i-1 as (the last i elements are already sorted after i iterations)
            for(int j=0; j < n-i-1; j++) {
                // Comparing adjacent elements and swapping them if they are in the wrong order
                if (arr.get(j) > arr.get(j+1)) {
                    // Swapping arr[j] and arr[j+1]
                    int temp = arr.get(j);
                    arr.set(j, arr.get(j+1));
                    arr.set(j+1, temp);
                }
            }
        }
    }
}