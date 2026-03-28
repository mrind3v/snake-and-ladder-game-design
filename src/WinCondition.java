public interface WinCondition {
    // Given a player's current position and the roll, returns the final position they land on.
    // Implementations decide what happens when the roll overshoots the last cell.
    int computeNewPosition(int currentPos, int roll, int boardSize);

    // Returns true if the given position means the player has won
    boolean isWin(int position, int boardSize);
}