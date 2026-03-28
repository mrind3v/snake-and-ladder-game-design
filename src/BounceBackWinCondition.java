// If the roll overshoots the last cell, the player bounces back from the end.
// e.g. board size 100, player at 97, rolls 6 → goes to 100 then bounces back 3 → lands on 97... wait:
// newPos = 97 + 6 = 103 → overshoot by 3 → 100 - 3 = 97
public class BounceBackWinCondition implements WinCondition {

    @Override
    public int computeNewPosition(int currentPos, int roll, int boardSize) {
        int newPos = currentPos + roll;
        if (newPos > boardSize) {
            int overshoot = newPos - boardSize;
            int bounced = boardSize - overshoot;
            System.out.println("  Overshoot! Bouncing back to " + bounced);
            return bounced;
        }
        return newPos;
    }

    @Override
    public boolean isWin(int position, int boardSize) {
        return position == boardSize;
    }
}