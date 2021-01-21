package swimlee.programmers.greedy;

/**
 * 문제 기준이 좀 이상한 듯
 * 그리디임을 보장하기 힘듦
 */

public class JoyStick {

    public static void main(String[] args) {
        JoyStick joyStick = new JoyStick();
        int solution = joyStick.solution("ABAAAAAAAAABB");
        System.out.println("solution = " + solution);
    }

    public int solution(String name) {
        int answer = 0;

        char[] arr = name.toCharArray();
        int len = name.length();

        int cur = 0;

        while (true) {

            boolean check = true;
            for (char c : arr) {
                if(c != 'A') {
                    check = false;
                    break;
                }
            }
            if(check) break;

            int right = cur;
            int left = cur;
            int rightMv = 0;
            int leftMv = 0;

            while (arr[right] == 'A') {
                if (++right >= len) {
                    right = 0;
                }
                rightMv++;
            }

            while (arr[left] == 'A') {
                if (--left < 0) {
                    left = len - 1;
                }
                leftMv++;
            }

            cur = rightMv < leftMv ? right : left;
            int move = Math.min(rightMv, leftMv);

            answer += move + Math.min(arr[cur] - 'A', 'Z' + 1 - arr[cur]);
            arr[cur] = 'A';
        }

        return answer;
    }
}
