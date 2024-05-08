import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

// Abstract class for sorting algorithm visualization panels
public abstract class SortPanel extends JPanel implements Runnable {
    private static final long serialVersionUID = 1L;
    protected static final int BORDER_WIDTH = 10; // Width of the border around the panel
    private Dimension preferredDimension; // Preferred dimension of the panel
    protected int size; // Size of the array to be sorted
    protected int[] list; // Array to be sorted
    protected int sleepTime; // Sleep time between steps of the sorting algorithm
    private String name; // Name of the sorting algorithm

    // Constructor
    public SortPanel(String name, int sleepTime, int width, int height) {
        preferredDimension = new Dimension(width, height); // Set the preferred dimension
        this.name = name; // Set the name of the sorting algorithm
        this.sleepTime = sleepTime; // Set the sleep time
        setBackground(Color.BLACK); // Set the background color of the panel to black
    }

    // Method to set the array to be sorted
    public void setList(int[] list) {
        reset(); // Reset the panel
        this.size = list.length; // Set the size of the array
        this.list = java.util.Arrays.copyOf(list, size); // Copy the array
        setBackground(Color.BLACK); // Set the background color of the panel to black
    }

    // Method to get the preferred size of the panel
    @Override
    public Dimension getPreferredSize() {
        return preferredDimension; // Return the preferred dimension
    }

    // Method to paint the panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Call the superclass method

        // Draw the border of the panel
        g.setColor(Color.WHITE);
        g.drawRect(BORDER_WIDTH, BORDER_WIDTH, getWidth() - 2 * BORDER_WIDTH, getHeight() - 2 * BORDER_WIDTH);

        // Draw the title of the sorting algorithm
        Font nameFont = new Font(Font.MONOSPACED, Font.BOLD, 18); // Create a font for the title
        FontMetrics nameFontMetrics = getFontMetrics(nameFont); // Get font metrics for the title
        g.setColor(Color.BLACK);
        g.fillRect((getWidth() - nameFontMetrics.stringWidth(name)) / 2, 0, nameFontMetrics.stringWidth(name), BORDER_WIDTH + nameFontMetrics.getAscent() / 3); // Fill rectangle for the title background
        g.setColor(Color.WHITE);
        g.setFont(nameFont); // Set the font for the title
        g.drawString(name, (getWidth() - nameFontMetrics.stringWidth(name)) / 2, BORDER_WIDTH + nameFontMetrics.getAscent() / 3); // Draw the title
    }

    // Abstract method to run the sorting algorithm
    @Override
    public abstract void run();

    // Abstract method to reset the panel
    public abstract void reset();
}