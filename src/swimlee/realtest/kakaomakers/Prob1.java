package swimlee.realtest.kakaomakers;

import java.util.HashMap;
import java.util.Map;

public class Prob1 {

    public static void main(String[] args) {
        int[] gift_cards = {};
        int[] wants = {};

        Prob1 prob1 = new Prob1();
        int solution = prob1.solution(gift_cards, wants);
        System.out.println("solution = " + solution);
    }

    public int solution(int[] gift_cards, int[] wants) {
        int answer = 0;

        Map<Integer, Integer> hm = new HashMap<>();

        for (int gift_card : gift_cards) {
            hm.put(gift_card, hm.getOrDefault(gift_card, 0) + 1);
        }

        for (int want : wants) {
            if (hm.containsKey(want)) {
                if (hm.get(want) > 0) {
                    hm.put(want, hm.get(want) - 1);
                } else {
                    answer++;
                }
            } else {
                answer++;
            }
        }

        return answer;
    }
}
