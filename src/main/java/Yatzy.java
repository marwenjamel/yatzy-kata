import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Yatzy {

    public static int chance(PlayerRoll playerRoll) {
        return playerRoll.streamOfInteger()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static int yatzy(PlayerRoll playerRoll) {
        return playerRoll.streamOfInteger().allMatch(i -> i == playerRoll.getDice()[0]) ? 50 : 0;
    }

    public static int ones(PlayerRoll playerRoll) {
        return playerRoll.sum(1);
    }

    public static int twos(PlayerRoll playerRoll) {
        return playerRoll.sum(2);
    }

    public static int threes(PlayerRoll playerRoll) {
        return playerRoll.sum(3);
    }

    public static int fours(PlayerRoll playerRoll) {
        return playerRoll.sum(4);
    }

    public static int fives(PlayerRoll playerRoll) {
        return playerRoll.sum(5);
    }

    public static int sixes(PlayerRoll playerRoll) {
        return playerRoll.sum(6);
    }

    public static int pair(PlayerRoll playerRoll) {
        return playerRoll.numberOfAKind(2);
    }

    public static int twoPair(PlayerRoll playerRoll) {
        Map<Integer, Long> map = playerRoll.streamOfInteger()
                .collect(Collectors.groupingBy(d -> d, Collectors.counting()));
        List<Integer> integerList = map.entrySet()
                .stream()
                .filter(e -> e.getValue() >= 2)
                .map(e -> e.getKey())
                .collect(Collectors.toList());
        return integerList.size() == 2 ? integerList.stream().mapToInt(Integer::intValue)
                .sum() * 2 : 0;
    }

    public static int threeOfAKind(PlayerRoll playerRoll) {
        return playerRoll.numberOfAKind(3);
    }

    public static int fourOfAKind(PlayerRoll playerRoll) {
        return playerRoll.numberOfAKind(4);
    }

    public static int smallStraight(PlayerRoll playerRoll) {
        return playerRoll.streamOfInteger().filter(d -> d < 6).distinct().count() == 5 ? 15 : 0;
    }

    public static int largeStraight(PlayerRoll playerRoll) {
        return playerRoll.streamOfInteger().filter(d -> d > 1).distinct().count() == 5 ? 20 : 0;
    }

    public static int fullHouse(int d1, int d2, int d3, int d4, int d5) {
        int[] tallies;
        boolean _2 = false;
        int i;
        int _2_at = 0;
        boolean _3 = false;
        int _3_at = 0;


        tallies = new int[6];
        tallies[d1 - 1] += 1;
        tallies[d2 - 1] += 1;
        tallies[d3 - 1] += 1;
        tallies[d4 - 1] += 1;
        tallies[d5 - 1] += 1;

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 2) {
                _2 = true;
                _2_at = i + 1;
            }

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 3) {
                _3 = true;
                _3_at = i + 1;
            }

        if (_2 && _3)
            return _2_at * 2 + _3_at * 3;
        else
            return 0;
    }
}

