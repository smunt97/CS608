import java.awt.Color;
import java.awt.Graphics;

// Class for visualizing the Merge Sort algorithm
public class MergeSortPanel extends SortPanel {
    private static final long serialVersionUID = 1L;
    private int redColumn = -1; // Index of the column currently being compared in red
    private int blueColumn = -1; // Index of the column currently being compared in blue
    private int greenColumnStart = -1; // Start index of the region being merged, marked in green
    private int greenColumnFinish = -1; // End index of the region being merged, marked in green

    // Constructor
    public MergeSortPanel(String name, int sleepTime, int width, int height) {
        super(name, sleepTime, width, height); // Call superclass constructor
    }

    // Method to reset the visualization
    @Override
    public void reset() {
        redColumn = -1;
        blueColumn = -1;
        greenColumnStart = -1;
        greenColumnFinish = -1;
    }

    // Method to execute the Merge Sort algorithm
    @Override
    public void run() {
        try {
            mergeSort(0, list.length - 1); // Call the merge sort algorithm
            greenColumnStart = 0; // Set the start index of the merged region
            greenColumnFinish = size - 1; // Set the end index of the merged region
        } catch (InterruptedException e) {
        }
        repaint(); // Redraw the panel
    }

    // Recursive method to perform merge sort
    public void mergeSort(int start, int fin) throws InterruptedException {
        if ((fin - start) > 0) {
            mergeSort(start, start + (fin - start) / 2); // Recursively sort the first half
            mergeSort(start + (fin - start) / 2 + 1, fin); // Recursively sort the second half
            merge(start, start + (fin - start) / 2, start + (fin - start) / 2 + 1, fin); // Merge the sorted halves
        }
    }

    // Method to merge two sorted subarrays
    public void merge(int start1, int fin1, int start2, int fin2) throws InterruptedException {
        int[] list1 = new int[fin1 - start1 + 1]; // Create a temporary array for the first subarray
        int[] list2 = new int[fin2 - start2 + 1]; // Create a temporary array for the second subarray
        int[] tmp = new int[list1.length + list2.length]; // Create a temporary array for merging
        System.arraycopy(list, start1, list1, 0, list1.length); // Copy the first subarray
        System.arraycopy(list, start2, list2, 0, list2.length); // Copy the second subarray
        Thread.sleep(2 * sleepTime); // Sleep to control visualization speed
        repaint(); // Redraw the panel
        int current1 = 0;
        redColumn = start1 + current1;
        int current2 = 0;
        blueColumn = start2 + current2;
        int current3 = 0;

        // Merge the two subarrays
        while (current1 < list1.length && current2 < list2.length) {
            Thread.sleep(2 * sleepTime); // Sleep to control visualization speed
            repaint(); // Redraw the panel
            if (list1[current1] < list2[current2]) {
                tmp[current3++] = list1[current1++];
                redColumn = start1 + current1;
            } else {
                tmp[current3++] = list2[current2++];
                blueColumn = start2 + current2 - 1;
            }
            Thread.sleep(2 * sleepTime); // Sleep to control visualization speed
            repaint(); // Redraw the panel
        }

        // Copy remaining elements of list1, if any
        while (current1 < list1.length) {
            tmp[current3++] = list1[current1++];
            redColumn = start1 + current1;
            Thread.sleep(2 * sleepTime); // Sleep to control visualization speed
            repaint(); // Redraw the panel
        }

        // Copy remaining elements of list2, if any
        while (current2 < list2.length) {
            tmp[current3++] = list2[current2++];
            blueColumn = start2 + current2 - 1;
            Thread.sleep(2 * sleepTime); // Sleep to control visualization speed
            repaint(); // Redraw the panel
        }

        // Copy merged elements back to the original list and visualize the merge
        redColumn = -1;
        blueColumn = -1;
        greenColumnStart = start1;
        for (int i = 0; i < tmp.length; i++) {
            greenColumnFinish = start1 + i;
            list[start1 + i] = tmp[i];
            Thread.sleep(2 * sleepTime); // Sleep to control visualization speed
            repaint(); // Redraw the panel
        }
        greenColumnStart = -1;
        greenColumnFinish = -1;
    }

    // Method to paint the panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Call superclass method to paint the panel background

        int columnWidth = (getWidth() - 4 * BORDER_WIDTH) / size; // Calculate the width of each column
        int columnHeight = (getHeight() - 4 * BORDER_WIDTH) / size; // Calculate the height of each column

        // Draw rectangles to represent elements of the array
        for (int i = 0; i < list.length; i++) {
            g.setColor(Color.WHITE); // Set color to white
            g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight); // Fill rectangle
            g.setColor(Color.BLACK); // Set color to black
            g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight); // Draw rectangle border
        }

        // Highlight the merged region in green
        if ((greenColumnStart != -1) && (greenColumnFinish != -1)) {
            for (int i = greenColumnStart; i <= greenColumnFinish; i++) {
                g.setColor(Color.GREEN); // Set color to green
                g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight); // Fill rectangle
                g.setColor(Color.BLACK); // Set color to black
                g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight); // Draw rectangle border
            }
        }

        // Highlight the column currently being compared in red
        if (redColumn != -1) {
            g.setColor(Color.RED); // Set color to red
            g.fillRect(2 * BORDER_WIDTH + columnWidth * redColumn, getHeight() - list[redColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[redColumn] * columnHeight); // Fill rectangle
            g.setColor(Color.BLACK); // Set color to black
            g.drawRect(2 * BORDER_WIDTH + columnWidth * redColumn, getHeight() - list[redColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[redColumn] * columnHeight); // Draw rectangle border
        }

        // Highlight the column currently being compared in blue
        if (blueColumn != -1) {
            g.setColor(Color.BLUE); // Set color to blue
            g.fillRect(2 * BORDER_WIDTH + columnWidth * blueColumn, getHeight() - list[blueColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[blueColumn] * columnHeight); // Fill rectangle
            g.setColor(Color.BLACK); // Set color to black
            g.drawRect(2 * BORDER_WIDTH + columnWidth * blueColumn, getHeight() - list[blueColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[blueColumn] * columnHeight); // Draw rectangle border
        }

    }

}