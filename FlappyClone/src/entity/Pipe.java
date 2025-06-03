package entity;

public class Pipe {
    public int leftPos;
    public int rightPos;
    public int height;

    public boolean bottom;

    public Pipe(int leftPos, int rightPos, int height, boolean bottom) {
        this.leftPos = leftPos;
        this.rightPos = rightPos;
        this.height = height;
        this.bottom = bottom;
    }

    public String toString() {
        return String.format("leftPos = %d, rightPos = %d, height = %d, bottom = %b", leftPos, rightPos, height, bottom);
    }
}
