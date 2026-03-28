public class Ladder implements Obstacle{
    private final int bottom;
    private final int top;
    Ladder(int bottom, int top) {
        this.bottom = bottom;
        this.top = top;
    }
    @Override
    public int applyEffect(int position) {
        System.out.println("Lucky! Ladder will take you from " + this.bottom + " to " + this.top);
        return top;
    }
    @Override
    public int getStart() {
        return this.bottom;
    }
}
