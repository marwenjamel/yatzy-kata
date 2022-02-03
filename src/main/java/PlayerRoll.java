import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PlayerRoll {
    private final int[] dice;

    public PlayerRoll(int d1, int d2, int d3, int d4, int d5) {
        this.dice = new int[]{d1, d2, d3, d4, d5};
    }

    public int sum(int i) {
        return streamOfInteger().filter(d -> d == i)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int numberOfAKind(int i) {
        Map<Integer, Long> map = streamOfInteger()
                .collect(Collectors.groupingBy(d -> d, Collectors.counting()));
        OptionalInt max = map.entrySet()
                .stream()
                .filter(e -> e.getValue() >= i)
                .mapToInt(e -> e.getKey())
                .max();
        return max.orElse(0) * i;
    }

    public int[] getDice() {
        return dice;
    }

    public Stream<Integer> streamOfInteger() {
        return IntStream.of(getDice()).boxed();
    }
}
