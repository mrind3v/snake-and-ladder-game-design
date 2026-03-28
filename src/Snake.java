public class Snake implements Obstacle{
    private final int head;
    private final int tail;
    Snake(int head, int tail) {
        this.head = head;
        this.tail = tail;
    }
    @Override
    public int applyEffect(int position) {
        System.out.println("Oh no! Snake will take you from " + this.head + " to " + this.tail);
        return tail;
    }
    @Override
    public int getStart() {
        return this.head;
    }
}
