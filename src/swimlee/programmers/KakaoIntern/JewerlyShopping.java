package swimlee.programmers.KakaoIntern;

import java.util.*;

/**
 * hashmap과 hashset 이용!
 */

public class JewerlyShopping {

    public static void main(String[] args) {
        String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};

        JewerlyShopping jewerlyShopping = new JewerlyShopping();
        int[] solution = jewerlyShopping.solution(gems);
        System.out.println("Arrays.toString(solution) = " + Arrays.toString(solution));
    }

    public int[] solution(String[] gems) {
        HashSet<String> hs = new HashSet<>(Arrays.asList(gems));
        HashMap<String, Integer> hm = new HashMap<>();
        Queue<String> q = new LinkedList<>();

        int start = 0;
        int length = Integer.MAX_VALUE;

        int startPoint = 0;

        for (String gem : gems) {
            hm.put(gem, hm.getOrDefault(gem, 0) + 1);
            q.add(gem);

            while (true) {
                String peek = q.peek();
                if (hm.get(peek) > 1) {
                    hm.put(peek, hm.get(peek) - 1);
                    startPoint++;
                    q.remove();
                } else {
                    break;
                }
            }

            if (hm.size() == hs.size() && length > q.size()) {
                length = q.size();
                start = startPoint;
            }
        }

        return new int[]{start + 1, start + length};
    }
}
