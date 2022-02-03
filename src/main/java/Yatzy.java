import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
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
        return sum(playerRoll, 1);
    }

    public static int twos(PlayerRoll playerRoll) {
        return sum(playerRoll, 2);
    }

    public static int threes(PlayerRoll playerRoll) {
        return sum(playerRoll, 3);
    }

    public static int fours(PlayerRoll playerRoll) {
        return sum(playerRoll, 4);
    }

    public static int fives(PlayerRoll playerRoll) {
        return sum(playerRoll, 5);
    }

    public static int sixes(PlayerRoll playerRoll) {
        return sum(playerRoll, 6);
    }

    private static int sum(PlayerRoll playerRoll, int i) {
        return playerRoll.streamOfInteger().filter(d -> d == i)
                .mapToInt(Integer::intValue)
                .sum();
    }

    protected int[] dice;

    public Yatzy(int d1, int d2, int d3, int d4, int _5) {
        dice = new int[5];
        dice[0] = d1;
        dice[1] = d2;
        dice[2] = d3;
        dice[3] = d4;
        dice[4] = _5;
    }

    public static int pair(PlayerRoll playerRoll) {
        Map<Integer, Long> map = playerRoll.streamOfInteger()
                .collect(Collectors.groupingBy(d -> d, Collectors.counting()));
        OptionalInt max = map.entrySet()
                .stream()
                .filter(e -> e.getValue() >= 2)
                .mapToInt(e -> e.getKey())
                .max();
        return max.orElse(0) * 2;
    }

    public static int twoPair(PlayerRoll playerRoll) {
        Map<Integer, Long> map = playerRoll.streamOfInteger()
                .collect(Collectors.groupingBy(d -> d, Collectors.counting()));
        List<Integer> integerList = map.entrySet()
                .stream()
                .filter(e -> e.getValue() >= 2)
                .map(e -> e.getKey())
                .collect(Collectors.toList());
        return integerList.size()==2 ?integerList .stream().mapToInt(Integer::intValue)
                .sum()*2:0;
    }

    public static int three_of_a_kind(int d1, int d2, int d3, int d4, int d5) {
        int[] t;
        t = new int[6];
        t[d1 - 1]++;
        t[d2 - 1]++;
        t[d3 - 1]++;
        t[d4 - 1]++;
        t[d5 - 1]++;
        for (int i = 0; i < 6; i++)
            if (t[i] >= 3)
                return (i + 1) * 3;
        return 0;
    }

    public static int four_of_a_kind(int _1, int _2, int d3, int d4, int d5) {
        int[] tallies;
        tallies = new int[6];
        tallies[_1 - 1]++;
        tallies[_2 - 1]++;
        tallies[d3 - 1]++;
        tallies[d4 - 1]++;
        tallies[d5 - 1]++;
        for (int i = 0; i < 6; i++)
            if (tallies[i] >= 4)
                return (i + 1) * 4;
        return 0;
    }


    public static int smallStraight(int d1, int d2, int d3, int d4, int d5) {
        int[] tallies;
        tallies = new int[6];
        tallies[d1 - 1] += 1;
        tallies[d2 - 1] += 1;
        tallies[d3 - 1] += 1;
        tallies[d4 - 1] += 1;
        tallies[d5 - 1] += 1;
        if (tallies[0] == 1 &&
                tallies[1] == 1 &&
                tallies[2] == 1 &&
                tallies[3] == 1 &&
                tallies[4] == 1)
            return 15;
        return 0;
    }

    public static int largeStraight(int d1, int d2, int d3, int d4, int d5) {
        int[] tallies;
        tallies = new int[6];
        tallies[d1 - 1] += 1;
        tallies[d2 - 1] += 1;
        tallies[d3 - 1] += 1;
        tallies[d4 - 1] += 1;
        tallies[d5 - 1] += 1;
        if (tallies[1] == 1 &&
                tallies[2] == 1 &&
                tallies[3] == 1 &&
                tallies[4] == 1
                && tallies[5] == 1)
            return 20;
        return 0;
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

