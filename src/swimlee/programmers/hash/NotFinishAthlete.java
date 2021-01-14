package swimlee.programmers.hash;

import java.util.HashMap;

/**
 * 자바 해시맵의 다양한 매서드 활용
 */

public class NotFinishAthlete {

    public static void main(String[] args) {
        String[] participant ={"leo", "kiki", "eden"};
        String[] completion ={"eden", "kiki"};

        NotFinishAthlete notFinishAthlete = new NotFinishAthlete();
        String solution = notFinishAthlete.solution(participant, completion);
        System.out.println("solution = " + solution);
    }

    public String solution(String[] participant, String[] completion) {
        String answer = "";

        HashMap<String, Integer> hm = new HashMap<>();
        for(String player : participant) hm.put(player, hm.getOrDefault(player, 0) + 1);
        for(String player : completion) hm.put(player, hm.get(player) - 1);

        for (String key : hm.keySet()) {
            if (hm.get(key) != 0) {
                answer = key;
            }
        }
        
        return answer;
    }
}
