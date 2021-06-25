package swimlee.programmers.codechallenge;

import java.util.Arrays;

/**
 * 그냥 String += concat연산하는 경우 매번 새로 객체 생성하니까 
 * StringBuilder보다 속도 느리다
 *
 * 하지만 이 방법 사용해도 여전히 시간초과
 * -> Stack을 이용해야 O(n)안에 해결할 수 있다고 한다.
 *
 * https://programmers.co.kr/questions/17683
 */

public class Move110 {

    public static void main(String[] args) {
        String[] s = {"1110","100111100","0111111010"};

        Move110 move110 = new Move110();
        String[] solution = move110.solution(s);
        System.out.println("Arrays.toString(solution) = " + Arrays.toString(solution));
    }

    static final String OOZ = "110";

    public String[] solution(String[] s) {
        String[] answer = new String[s.length];

        for (int i = 0; i < s.length; i++) {
            StringBuilder now = new StringBuilder(s[i]);
            StringBuilder repeat = new StringBuilder();

            int idx;

            while ((idx = now.indexOf(OOZ)) >= 0) {
                idx = now.indexOf(OOZ);
                // 이 부분에서 중간의 110 삭제하면 뒤에 있는 문자열 옮겨오는데, O(n)의 시간복잡도 발생
                // 시간초과 요인이라고 한다.
                now.delete(idx, idx + 3);
                repeat.append(OOZ);
            }

            if (now.equals("")) {
                now = repeat;
            } else if (now.equals("1")) {
                now = repeat.append(now);
            } else if ((idx = now.indexOf("11")) >= 0) {
                now.insert(idx, repeat);
            } else {
                idx = now.lastIndexOf("0");
                now.insert(idx + 1, repeat);
            }

            answer[i] = now.toString();
        }

        return answer;
    }
}
