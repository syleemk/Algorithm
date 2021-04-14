package swimlee.programmers.kakaoblind;

/**
 * 진법 변환 메서드 없이 할 수 있을까?
 */

public class NBaseGame {

    public static void main(String[] args) {
        NBaseGame nBaseGame = new NBaseGame();
        String solution = nBaseGame.solution(16,16,2,2);
        System.out.println("solution = " + solution);
    }

    public String solution(int n, int t, int m, int p) {
        String answer = "";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= t * m; i++) {
            sb.append(Integer.toUnsignedString(i, n));
        }

        String str = sb.toString().toUpperCase();

        for (int i = 0; i < t; i++) {
            answer += str.charAt(m * i + p - 1);
        }

        return answer;
    }
}
