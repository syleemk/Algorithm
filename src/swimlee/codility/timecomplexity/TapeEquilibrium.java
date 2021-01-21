package swimlee.codility.timecomplexity;

import java.util.Arrays;

/**
 * 자바 스트림은 시간복잡도가 안좋다
 * 스트림 사용안하니까 timeout 통과됨
 */

public class TapeEquilibrium {
    public static void main(String[] args) {
        int[] A = {-1000,1000};
        TapeEquilibrium tapeEquilibrium = new TapeEquilibrium();
        int solution = tapeEquilibrium.solution(A);
        System.out.println("solution = " + solution);
    }

    public int solution(int[] A) {
        int sum = 0;
        for (int a : A) {
            sum += a;
        }

        int front = 0;
        int back;
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < A.length - 1; i++) {
            front += A[i];
            back = sum - front;
            int tmp = front - back;
            answer = Math.min(answer, Math.abs(tmp));
        }

        return answer;
    }
}
