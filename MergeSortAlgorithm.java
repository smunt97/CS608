import java.util.*;

/**
 * A class implementing the Merge Sort algorithm for sorting integers.
 */
public class MergeSortAlgorithm {
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
        MergeSort(inputArray);
        
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
     * Sorts an ArrayList of integers using the Merge Sort algorithm.
     * 
     * @param arr The ArrayList of integers to be sorted.
     */

    // Function to perform the MergeSort on ArraList of Integers
    public static void MergeSort(ArrayList<Integer> arr) {
        int n = arr.size();

        // Base Condition to exit from Recursion
        if (n < 2) {
            return;
        }

        // Dividing the ArrayList into two halves
        int mid = n / 2;
        ArrayList<Integer> left = new ArrayList<Integer>(arr.subList(0, mid));
        ArrayList<Integer> right = new ArrayList<Integer>(arr.subList(mid, n));
        
        // Recursively sorting the left and right halves
        MergeSort(left);
        MergeSort(right);

        // Merge the sorted halves
        Merge(left, right, arr);
    }

    /**
     * Merges two sorted ArrayLists into one sorted ArrayList.
     * 
     * @param l The left ArrayList to be merged.
     * @param r The right ArrayList to be merged.
     * @param arr The ArrayList to store the merged result.
     */

    // Function to merge the two sorted arrays
    public static void Merge(ArrayList<Integer> l, ArrayList<Integer> r, ArrayList<Integer> arr) {
        int lengthLeft = l.size();
        int lengthRight = r.size();
        int x = 0, y = 0, z = 0;
        
        // Creating a temporary ArrayList to store the merged result
        ArrayList<Integer> temp = new ArrayList<>();
        
        // Comparing elements from the left and right ArrayLists and merge them in sorted order
        while (x < lengthLeft && y < lengthRight) {
            if (l.get(x) <= r.get(y)) {
                temp.add(l.get(x));
                x++;
            } else {
                temp.add(r.get(y));
                y++;
            }
        }
        
        // Adding any remaining elements from the left ArrayList
        while (x < lengthLeft) {
            temp.add(l.get(x));
            x++;
        }
        
        // Adding any remaining elements from the right ArrayList
        while (y < lengthRight) {
            temp.add(r.get(y));
            y++;
        }
        
        // Copying elements from the temporary ArrayList back to the original ArrayList
        for (int i = 0; i < temp.size(); i++) {
            arr.set(z + i, temp.get(i));
        }
    }    
}