import java.util.List;
import java.util.Optional;

public class Game {

    private static final int TRIPLE_SIX_THRESHOLD = 3;
    private static final int SPECIAL_ROLL_VALUE    = 6;

    private final Board              board;
    private final List<Player>       players;
    private final DiceSet            diceSet;
    private final WinCondition       winCondition;
    private final OneSixStrategy            sixRule;
    private final TripleSixStrategy      tripleSixRule;
    private final TurnOrderStrategy  turnOrder;

    private int currentIndex = 0;

    public Game(Board board,
                List<Player> players,
                DiceSet diceSet,
                WinCondition winCondition,
                OneSixStrategy sixRule,
                TripleSixStrategy tripleSixRule,
                TurnOrderStrategy turnOrder) {

        if (players == null || players.size() < 2) {
            throw new IllegalArgumentException("At least 2 players are required");
        }
        this.board         = board;
        this.players       = players;
        this.diceSet       = diceSet;
        this.winCondition  = winCondition;
        this.sixRule       = sixRule;
        this.tripleSixRule = tripleSixRule;
        this.turnOrder     = turnOrder;
    }

    public void start() {
        System.out.println("=== Snake and Ladder Game Start ===");
        System.out.println("Players: " + players);
        System.out.println("Board size: " + board.getSize());
        System.out.println("===================================\n");

        while (true) {
            Player current = players.get(currentIndex);
            boolean gameOver = playTurn(current);
            if (gameOver) break;
        }
    }

    // ---------------------------------------------------------------------------
    // Core turn logic
    // ---------------------------------------------------------------------------

    private boolean playTurn(Player player) {
        System.out.println("--- " + player.getName() + "'s turn (position: " + player.getPosition() + ") ---");

        int roll = diceSet.rollAll();
        System.out.println("  Rolled: " + diceSet.lastRolls() + " (total: " + roll + ")");

        // ── Triple-six check ────────────────────────────────────────────────────
        // We track consecutive sixes based on whether ALL dice show the special
        // value (for a single die that simply means it showed 6).
        if (diceSet.allDiceShowValue(SPECIAL_ROLL_VALUE)) {
            player.incrementSixes();
        } else {
            player.resetSixes();
        }

        if (player.getConsecutiveSixes() >= TRIPLE_SIX_THRESHOLD) {
            tripleSixRule.apply(player);
            player.resetSixes();
            // Triple-six always ends the current player's turn — no bonus
            advanceTurn(false);
            return false;
        }

        // ── Normal move ─────────────────────────────────────────────────────────
        int newPos = winCondition.computeNewPosition(player.getPosition(), roll, board.getSize());
        newPos     = resolveObstacle(newPos);
        player.setPosition(newPos);

        System.out.println("  " + player.getName() + " moves to " + newPos);

        // ── Win check ───────────────────────────────────────────────────────────
        if (winCondition.isWin(newPos, board.getSize())) {
            System.out.println("\n🎉 " + player.getName() + " wins! Congratulations!");
            return true;
        }

        // ── Bonus turn check (single six) ───────────────────────────────────────
        boolean bonusTurn = false;
        if (diceSet.allDiceShowValue(SPECIAL_ROLL_VALUE)) {
            bonusTurn = sixRule.grantBonusChance(player);
            if (bonusTurn) {
                System.out.println("  🎲 Six rolled! " + player.getName() + " gets another turn.");
            }
        }

        advanceTurn(bonusTurn);
        return false;
    }

    // ---------------------------------------------------------------------------
    // Helpers
    // ---------------------------------------------------------------------------

    // Checks if the cell at the given position has an obstacle and applies it.
    private int resolveObstacle(int position) {
        if (position < 1 || position > board.getSize()) return position;

        Cell cell = board.getCell(position);
        Optional<Obstacle> obstacle = cell.getObstacle();

        if (obstacle.isPresent()) {
            return obstacle.get().applyEffect(position);
        }
        return position;
    }

    // Advances to the next player unless bonusTurn is true, in which case
    // the same player goes again.
    private void advanceTurn(boolean bonusTurn) {
        if (!bonusTurn) {
            currentIndex = turnOrder.nextPlayerIndex(currentIndex, players);
        }
    }
}