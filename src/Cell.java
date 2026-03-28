import java.util.Optional;

public class Cell {
    private final int position;
    private Obstacle obstacle;
    Cell(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setObstacle(Obstacle o) {
        this.obstacle = obstacle;
    }

    public Optional<Obstacle> getObstacle() {
        return Optional.ofNullable(obstacle);
    }
}
