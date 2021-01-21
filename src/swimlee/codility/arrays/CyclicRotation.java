package swimlee.codility.arrays;


import java.util.ArrayList;
import java.util.Arrays;

public class CyclicRotation {

    public static void main(String[] args) {
        int[] A = {};
        int K = 3;

        CyclicRotation cyclicRotation = new CyclicRotation();
        int[] solution = cyclicRotation.solution(A, K);
        System.out.println("solution = " + Arrays.toString(solution));
    }

    public int[] solution(int[] A, int K) {
        if(A.length == 0) return A;

        int cnt = K % A.length;

        if (cnt == 0) {
            return A;
        }

        ArrayList<Integer> answer = new ArrayList<>();

        for (int i = A.length - cnt; i < A.length; i++) {
            answer.add(A[i]);
        }

        for (int i = 0; i < A.length - cnt; i++) {
            answer.add(A[i]);
        }

        return answer.stream().mapToInt(i->i).toArray();
    }
}
