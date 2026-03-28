public class ExactWinCondition implements WinCondition{
    @Override
    public int computeNewPosition(int currentPos, int roll, int boardSize) {
        int newPos = currentPos + roll;
        if (newPos > boardSize) {
            System.out.println("  Overshoot! Need exact number to win. Staying at " + currentPos);
            return currentPos; // stay in place
        }
        return newPos;
    }


    @Override
    public boolean isWin(int position, int boardSize) {
        return position==boardSize;
    }
}
