import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JPanel;

public class SortArray extends JPanel {
    static final int ARRAYMAX = (Visualizer.HEIGHT - Visualizer.MENUHEIGHT) / 2;
    private static int BARWIDTH = 10;
    public static int NUMBERS = Visualizer.WIDTH / (BARWIDTH + 1);
    private int sleepTime = 10;
    private Bar[] data;
    private boolean aux;


    /**
     * Default constructor, not including the Aux Array
     */
    public SortArray() {
        this.aux = false;
        data = new Bar[NUMBERS];
        for (int i = 0; i < data.length; i++)
            data[i] = new Bar(i + 1);
    }

    /**
     * SortArray constructor, including the tracking of the auxiliary (/aux/ array as a parameter)
     * @param aux Whether to include the Auxiliary Array sorting.
     */
    public SortArray(boolean aux) {
        this.aux = aux;
        data = new Bar[NUMBERS];
        for (int i = 0; i < data.length; i++)
            data[i] = new Bar(0);
    }


    /**
     * Paints the components (blocks)
     * @param g gives graphics to the method, in order for it to draw the blocks
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        for (int i = 0; i < data.length; i++) {
            if (data[i].color())
                g2.setColor(Color.RED);
            else
                g2.setColor(Color.BLACK);
            if (!aux)
                g2.fillRect(i + BARWIDTH * i, 0, BARWIDTH, data[i].val());
            else if (aux)
                g2.fillRect(i + BARWIDTH * i, 0, BARWIDTH, data[i].val());

        }
        if (aux) {
            g2.setFont(new Font("Verdana", Font.BOLD, 20));
            g2.drawString("Auxiliary Array", Visualizer.WIDTH / 2 - 60, SortArray.ARRAYMAX / 2);
        }

    }

    /**
     * Shuffles the bars of the array randomly so that they can be sorted.
     */
    public void shuffle() {
        Random r = new Random();
        for (int i = 0; i < data.length - 1; i++)
            swap(i, r.nextInt(NUMBERS - 1));
        Visualizer.running = false;
    }

    /**
     * Swaps two numbers
     */
    public void swap(int x, int y) {
        int temp = data[x].val();
        data[x].setValue(data[y].val());
        data[y].setValue(temp);

        update();
    }

    private long milliToNano(long m) {
        return 1000000 * m;
    }

    /**
     * @return the data (value, color) of a single bar
     */
    public Bar[] data() {
        return data;
    }

    public void update() {
        repaint();
        Visualizer.sleepFor(milliToNano(sleepTime));
    }

    /**
     * Increases the speed of sorting execution
     */
    public void incSleepTime() {
        if (sleepTime < 20)
            sleepTime++;
    }

    /**
     * Decreases the speed of sorting execution
     */
    public void decSleepTime() {
        if (sleepTime > 0)
            sleepTime--;
    }

    /**
     * Gets the current speed of execution
     */
    public int getSpeed() {
        if (sleepTime < 10)
            return 20 - sleepTime;
        else if (sleepTime > 10)
            return 10 - (sleepTime - 10);
        else
            return sleepTime;
    }

    /**
     * Gets called when the user wants to increase the bar size
     */
    public void increaseBarWidth() {
        BARWIDTH++;
    }

    /**
     * Gets called when the user wants to decrease the bar size
     */
    public void decreaseBarWidth() {
        if (BARWIDTH > 1) BARWIDTH--;
    }

    /**
     * Reconstructs the bars when the user wants to add or remove bars from the current amount.
     */
    public void reconstruct() {
        NUMBERS = Visualizer.WIDTH / (BARWIDTH + 1);
        data = new Bar[NUMBERS];
        for (int i = 0; i < data.length; i++)
            data[i] = new Bar(i + 1);
    }

    /**
     * Removes all highlighted (with red) bars
     */
    public void unHighlightAll() {
        for (int i = 0; i < data.length; i++)
            data[i].unHighlight();
    }


}
