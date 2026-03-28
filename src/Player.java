public class Player {
    private final String id;
    private final String name;
    private int position;
    private int consecutiveSixes;

    public Player(String id, String name) {
        this.id = id;
        this.name = name;
        this.position = 0; // off the board — enters on first roll
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void incrementSixes() {
        consecutiveSixes++;
    }

    public void resetSixes() {
        consecutiveSixes = 0;
    }

    public int getConsecutiveSixes() {
        return consecutiveSixes;
    }

    @Override
    public String toString() {
        return name + "(pos=" + position + ")";
    }
}