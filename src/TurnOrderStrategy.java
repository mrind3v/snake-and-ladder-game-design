import java.util.List;

public interface TurnOrderStrategy {
    // Given the current player index and the full player list,
    // returns the index of the next player who should take a turn.
    int nextPlayerIndex(int currentIndex, List<Player> players);
}