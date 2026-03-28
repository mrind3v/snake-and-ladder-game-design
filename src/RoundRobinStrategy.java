import java.util.List;

// Each player takes turns in order, cycling back to the first after the last.
public class RoundRobinStrategy implements TurnOrderStrategy {

    @Override
    public int nextPlayerIndex(int currentIndex, List<Player> players) {
        return (currentIndex + 1) % players.size();
    }
}