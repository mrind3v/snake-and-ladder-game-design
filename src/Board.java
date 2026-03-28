import java.util.List;

public class Board {
    private final int size;
    private final Cell[] cells;
    Board(int size, List<Obstacle> obstacles) {
        // initialize board size
        this.size = size;
        // initialize board cells based on size (1-based)
        this.cells = new Cell[size+1];
        for (int i=1; i<=size; i++) {
            cells[i] = new Cell(i);
        }
        for (Obstacle o : obstacles) {
            cells[o.getStart()].setObstacle(o);
        }
    }

    public int getSize() {
        return size;
    }
    public Cell getCell(int position) {
        return cells[position];
    }
}
