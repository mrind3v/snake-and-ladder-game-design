import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DiceSet {
    private final List<Die> dice;
    private List<Integer> lastRolls;

    public DiceSet() {
        this.dice = new ArrayList<>();
        this.lastRolls = new ArrayList<>();
    }

    public void addDie(Die die) {
        dice.add(die);
    }

    // Rolls all dice and returns their sum
    public int rollAll() {
        lastRolls = new ArrayList<>();
        int total = 0;
        for (Die die : dice) {
            int roll = die.roll();
            lastRolls.add(roll);
            total += roll;
        }
        return total;
    }

    // Returns the individual values from the most recent roll
    public List<Integer> lastRolls() {
        return Collections.unmodifiableList(lastRolls);
    }

    // Checks if all dice showed the same value in the last roll
    // Used to determine if a "six" (or any special value) was rolled across all dice
    public boolean allDiceShowValue(int value) {
        if (lastRolls.isEmpty()) return false;
        return lastRolls.stream().allMatch(r -> r == value);
    }
}