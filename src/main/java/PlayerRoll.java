import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PlayerRoll {
    private final int[] dice;

    public PlayerRoll(int d1, int d2, int d3, int d4, int d5) {
        this.dice = new int[]{d1, d2, d3, d4, d5};
    }

    public int[] getDice() {
        return dice;
    }

    public Stream<Integer> streamOfInteger() {
        return IntStream.of(getDice()).boxed();
    }
}
