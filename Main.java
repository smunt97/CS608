import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main extends JApplet {

    private SortPanel[] sortPanels = new SortPanel[3]; // Array to hold the sorting algorithm panels
    private static int size = 100; // Size of the array to be sorted
    private int sleepTime = 2; // Sleep time between steps of the sorting algorithms

    // Constructor
    public Main() {
        setLayout(new GridLayout(3, 1, 0, 0)); // Set layout to 3 rows and 1 column

        // Initialize the sorting algorithm panels
        sortPanels[0] = new MergeSortPanel("Merge Sort", sleepTime, size, size);
        sortPanels[1] = new BubbleSortPanel("Bubble Sort", sleepTime, size, size);
        sortPanels[2] = new InsertionSortPanel("Insertion Sort", sleepTime, size, size);

        // Add the sorting algorithm panels to the applet
        for (SortPanel panel : sortPanels) {
            add(panel);
        }
    }

    // Main method
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sorting Algorithm Animations"); // Create a JFrame
        Main main = new Main(); // Create an instance of the Main class
        frame.add(main); // Add the Main instance to the JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        frame.pack(); // Pack the JFrame
        frame.setLocationRelativeTo(null); // Center the JFrame on the screen
        frame.setVisible(true); // Make the JFrame visible

        int[] list = new int[size]; // Create an array to be sorted
        for (int i = 0; i < list.length; i++) {
            list[i] = i + 1; // Initialize the array with values from 1 to size
        }
        shuffleArray(list); // Shuffle the array randomly

        main.beginAnimation("Random", list); // Begin animation with the shuffled array
    }

    // Method to begin animation
    public void beginAnimation(String animationName, int[] list) {
        try {
            // Set the list for each sorting algorithm panel and make them visible
            for (SortPanel panel : sortPanels) {
                panel.setList(list);
                panel.setVisible(true);
            }

            // Create a fixed thread pool for executing sorting algorithms
            ExecutorService executor = Executors.newFixedThreadPool(sortPanels.length);
            // Execute each sorting algorithm in a separate thread
            for (SortPanel panel : sortPanels) {
                executor.execute(panel);
            }
            executor.shutdown(); // Shut down the executor when all tasks are completed
            while (!executor.isTerminated()) {
                Thread.sleep(100); // Wait until all tasks are completed
            }

            // Hide all sorting algorithm panels after animation
            for (SortPanel panel : sortPanels) {
                panel.setVisible(false);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Method to shuffle an array randomly
    private static void shuffleArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int index = (int) (Math.random() * array.length);
            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
    }
}