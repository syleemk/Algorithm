package swimlee.programmers.devmatching;

import java.util.Arrays;
import java.util.HashMap;

public class ToothBrush {
    public static void main(String[] args) {
        // 판매원
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        // 각 판매원을 조직에 참여시킨 사람
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};

        // 판매량 집계 데이터 순서
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};

        ToothBrush toothBrush = new ToothBrush();
        int[] solution = toothBrush.solution(enroll, referral, seller, amount);
        System.out.println("Arrays.toString(solution) = " + Arrays.toString(solution));
    }

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];

        HashMap<String, String> tree = new HashMap<>();
        HashMap<String, Integer> cost = new HashMap<>();

        // 부모 정보 초기화
        for (int i = 0; i < enroll.length; i++) {
            tree.put(enroll[i], referral[i]);
        }

        for (int i = 0; i < seller.length; i++) {
            String key = seller[i];
            int money = amount[i] * 100;

            while (!key.equals("-")) {
                if (money == 0) break;
                int benefit = money / 10;
                cost.put(key, cost.getOrDefault(key, 0) + money - benefit);
                money = benefit;
                key = tree.get(key);
            }
        }

        for (int i = 0; i < enroll.length; i++) {
            answer[i] = cost.getOrDefault(enroll[i], 0);
        }

        return answer;
    }
}
