public class SendToStartStrategy implements TripleSixStrategy {
    @Override
    public void apply(Player player) {
        player.setPosition(0);
    }
}
