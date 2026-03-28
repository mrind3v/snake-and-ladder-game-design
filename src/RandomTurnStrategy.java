import java.util.List;
import java.util.Random;

// The next player is chosen randomly from all players except the current one.
public class RandomTurnStrategy implements TurnOrderStrategy {

    private final Random random = new Random();

    @Override
    public int nextPlayerIndex(int currentIndex, List<Player> players) {
        if (players.size() == 1) return 0;

        int next;
        do {
            next = random.nextInt(players.size());
        } while (next == currentIndex);

        return next;
    }
}