import java.util.*;

/**
 * A class implementing the Insertion Sort algorithm for sorting integers.
 */
public class InsertionSortAlgorithm {
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
        InsertionSort(inputArray);
        
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
     * Sorts an ArrayList of integers using the Insertion Sort algorithm.
     * 
     * @param arr The ArrayList of integers to be sorted.
     */

    // Function to perform the InsertionSort on ArraList of Integers
    public static void InsertionSort(ArrayList<Integer> arr) {
        int n = arr.size(); // Getting the size of the ArrayList
        // Traversing through the array elements starting from the second element (index 1)
        for (int i = 1; i < n; ++i) {
            int key = arr.get(i);   // Storing the current element to be inserted
            int j = i - 1;  // Initializing the index of the previous element
            
            // Moving elements of arr[0..i-1], that are greater than key, to one position ahead of their current position
            while (j >= 0 && arr.get(j) > key) {
                arr.set(j + 1, arr.get(j)); // Moving the element to the next position
                j = j - 1;  // Moving to the previous element
            }
            // Inserting the key into its correct position in the sorted subarray
            arr.set(j + 1, key);
        }
    }
}