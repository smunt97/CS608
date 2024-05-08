import java.awt.Color;
import java.awt.Graphics;

// Class for visualizing the Insertion Sort algorithm
public class InsertionSortPanel extends SortPanel {
    private static final long serialVersionUID = 1L;
    private int redColumn = -1; // Index of the column being compared in red
    private int greenColumn = -1; // Index marking the sorted part of the array

    // Constructor
    public InsertionSortPanel(String name, int sleepTime, int width, int height) {
        super(name, sleepTime, width, height); // Call superclass constructor
    }

    // Method to reset the visualization
    @Override
    public void reset() {
        redColumn = -1;
        greenColumn = -1;
    }

    // Method to execute the Insertion Sort algorithm
    @Override
    public void run() {
        try {
            for (int i = 1; i < list.length; i++) {
                greenColumn = i; // Mark the current column as part of the sorted array
                redColumn = greenColumn; // Highlight the current column being compared in red
                int k;
                for (k = i - 1; k >= 0 && list[k] > list[k + 1]; k--) {
                    Thread.sleep(3 * sleepTime); // Sleep to control visualization speed
                    repaint(); // Redraw the panel
                    redColumn = k + 1; // Highlight the next column being compared in red
                    repaint(); // Redraw the panel
                    Thread.sleep(4 * sleepTime); // Sleep to control visualization speed
                    int tmp = list[k + 1]; // Swap elements if necessary
                    list[k + 1] = list[k];
                    list[k] = tmp;
                }
                redColumn = k + 1; // Highlight the correct position for the current element in red
                repaint(); // Redraw the panel
            }
            redColumn = -1; // Reset the red column indicator
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
        for (int i = (greenColumn == -1 ? 0 : greenColumn); i < list.length; i++) {
            g.setColor(Color.WHITE); // Set color to white
            g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight); // Fill rectangle
            g.setColor(Color.BLACK); // Set color to black
            g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight); // Draw rectangle border
        }

        // Highlight the sorted part of the array in green
        for (int i = 0; i <= greenColumn; i++) {
            g.setColor(Color.GREEN); // Set color to green
            g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight); // Fill rectangle
            g.setColor(Color.BLACK); // Set color to black
            g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight); // Draw rectangle border
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