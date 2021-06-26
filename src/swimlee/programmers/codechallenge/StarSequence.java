package swimlee.programmers.codechallenge;

import java.util.HashMap;

public class StarSequence {

    public static void main(String[] args) {
        int[] a = {0, 3, 3, 0, 7, 2, 0, 2, 2, 0};

        StarSequence starSequence = new StarSequence();
        int solution = starSequence.solution(a);
        System.out.println("solution = " + solution);
    }

    public int solution(int[] a) {
        int answer = 0;

        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i : a) {
            hm.put(i, hm.getOrDefault(i, 0) + 1);
        }

        for (Integer key : hm.keySet()) {
            if (hm.get(key) * 2 <= answer) continue;

            int cnt = 0;

            for (int i = 0; i < a.length-1; i++) {
                if ((a[i] == key || a[i + 1] == key) && (a[i] != a[i + 1])) {
                    cnt += 2;
                    i++;
                }
            }

            answer = Math.max(answer, cnt);
        }

        return answer;
    }
}
