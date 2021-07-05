package swimlee.realtest.naverfinancial;

import java.util.Arrays;

/**
 * A값의 포인터가 고정되어있어야함
 */

public class Prob2 {

    public static void main(String[] args) {
//        int[] B = {1, 3, 2, 1, 1};
//        int[] A = {4, 2, 5, 3, 2};
//        int[] B = {3, 3};
//        int[] A = {1, 2};
        int[] A = {3};
        int[] B = {1, 1, 2, 3};

        Prob2 prob2 = new Prob2();
        int solution = prob2.solution(A, B);
        System.out.println("solution = " + solution);
    }

    int solution(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;

        Arrays.sort(A);
        Arrays.sort(B);
        int i = 0;
        for (int k = 0; k < n; k++) {
            if (i < m - 1 && B[i] < A[k]) {
                i += 1; k--; continue;
            }
            if (A[k] == B[i])
                return A[k];
        }
        return -1;
    }
}
