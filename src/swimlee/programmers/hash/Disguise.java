package swimlee.programmers.hash;

import java.util.Arrays;
import java.util.HashMap;

import static java.util.stream.Collectors.*;


/**
 * Stream 이용해서 푸는게 대단함
 */
public class Disguise {

    public static void main(String[] args) {
        String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
        Disguise disguise = new Disguise();
        int solution = disguise.solution(clothes);
        System.out.println("solution = " + solution);
    }

    public int solution(String[][] clothes) {
        int answer = 1;

        HashMap<String, Integer> hm = new HashMap<>();

        for (String[] cloth : clothes) {
            hm.put(cloth[1], hm.getOrDefault(cloth[1], 0) + 1);
        }

        for (String key : hm.keySet()) {
            answer *= (hm.get(key) + 1);
        }
/*
        int i = Arrays.stream(clothes)
                .collect(groupingBy(p -> p[1], mapping(p -> p[0], counting())))
                .values()
                .stream().reduce(1L, (x, y) -> x * (y + 1)).intValue() - 1;
*/

        return answer - 1;
    }
}
