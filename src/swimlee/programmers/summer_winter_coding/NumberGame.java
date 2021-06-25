package swimlee.programmers.summer_winter_coding;

import java.util.Arrays;

/**
 * 투포인터 ㅋㅋㅋㅋ
 */

public class NumberGame {

    public static void main(String[] args) {
        int[] A = {5, 1, 3, 7};
        int[] B = {2, 2, 6, 8};

        NumberGame numberGame = new NumberGame();
        int solution = numberGame.solution(A, B);
        System.out.println("solution = " + solution);
    }

    public int solution(int[] A, int[] B) {
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        for (int i = 0, j = 0; i < A.length && j < B.length; j++) {
            if (A[i] < B[j]) {
                answer++;
                i++;
            }
        }

        return answer;
    }
}
