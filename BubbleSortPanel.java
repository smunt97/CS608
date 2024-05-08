import java.awt.Color;
import java.awt.Graphics;

// Class for visualizing the Bubble Sort algorithm
public class BubbleSortPanel extends SortPanel {
    private static final long serialVersionUID = 1L;
    private int redColumn = -1; // Index of the column currently being compared in red
    private int greenColumn = -1; // Index marking the sorted part of the array

    // Constructor
    public BubbleSortPanel(String name, int sleepTime, int width, int height) {
        super(name, sleepTime, width, height); // Call superclass constructor
    }

    // Method to reset the visualization
    @Override
    public void reset() {
        redColumn = -1;
        greenColumn = -1;
    }

    // Method to execute the Bubble Sort algorithm
    @Override
    public void run() {
        try {
            boolean needNextPass = true;
            for (int k = 1; k < list.length && needNextPass; k++) {
                needNextPass = false;
                for (int i = 0; i < list.length - k; i++) {
                    redColumn = i; // Highlight the current column being compared in red
                    repaint(); // Redraw the panel
                    Thread.sleep(4 * sleepTime); // Sleep to control visualization speed
                    if (list[i] > list[i + 1]) {
                        redColumn = i + 1; // Highlight the next column being compared in red
                        int temp = list[i];
                        list[i] = list[i + 1];
                        list[i + 1] = temp;
                        repaint(); // Redraw the panel
                        Thread.sleep(4 * sleepTime); // Sleep to control visualization speed
                        needNextPass = true;
                    }
                }
                greenColumn = size - k; // Mark the sorted part of the array in green
            }
            greenColumn = 0;
            redColumn = -1;
        } catch (InterruptedException e) {
        }
        repaint(); // Redraw the panel
    }

    // Method to paint the panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Call superclass method to paint the panel background

        int columnWidth = (getWidth() - 4 * BORDER_WIDTH) / size; // Calculate the width of each column
        int columnHeight = (getHeight() - 4 * BORDER_WIDTH) / size; // Calculate the height of each column

        // Draw rectangles to represent elements of the array
        for (int i = 0; i < (greenColumn == -1 ? list.length : greenColumn); i++) {
            g.setColor(Color.WHITE); // Set color to white
            g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight); // Fill rectangle
            g.setColor(Color.BLACK); // Set color to black
            g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight); // Draw rectangle border
        }

        // Highlight the sorted part of the array in green
        if (greenColumn != -1) {
            for (int i = greenColumn; i < list.length; i++) {
                g.setColor(Color.GREEN); // Set color to green
                g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight); // Fill rectangle
                g.setColor(Color.BLACK); // Set color to black
                g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight); // Draw rectangle border
            }
        }

        // Highlight the current column being compared in red
        if (redColumn != -1) {
            g.setColor(Color.RED); // Set color to red
            g.fillRect(2 * BORDER_WIDTH + columnWidth * redColumn, getHeight() - list[redColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[redColumn] * columnHeight); // Fill rectangle
            g.setColor(Color.BLACK); // Set color to black
            g.drawRect(2 * BORDER_WIDTH + columnWidth * redColumn, getHeight() - list[redColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[redColumn] * columnHeight); // Draw rectangle border
        }
    }
}