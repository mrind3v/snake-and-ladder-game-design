import java.util.Random;

public class Die {
    private final int numFaces;
    private final Random random;
    Die(int numFaces, Random random) {
        this.numFaces = numFaces;
        this.random = random;
    }
    public int roll() {
        // random.nextInt(faces) will generate an integer from 0 to numFaces-1
        // +1 will make it 1 based - from 1 to numFaces
        return random.nextInt(numFaces) + 1;
    }
    public int getNumFaces() {
        return numFaces;
    }
}
