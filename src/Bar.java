
public class Bar {
    private int value;
    private boolean color;

    public Bar(int value) {
        this.value = value;
        color = false;
    }

    public int val() {
        return value;
    }

    public void setValue(int val) {
        this.value = val;
    }

    public boolean color() {
        return color;
    }

    /**
     * highlights the bar in red
     */
    public void highlight() {
        color = true;
    }

    /**
     * unhighlight the bar and returns it to black color
     */
    public void unHighlight() {
        color = false;
    }
}
