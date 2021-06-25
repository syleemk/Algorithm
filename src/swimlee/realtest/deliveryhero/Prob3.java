package swimlee.realtest.deliveryhero;

import java.util.HashMap;

/**
 * 투포인터 문제!
 * 완탐으로는 시간초과나는 경우 사용!
 * - 투포인터
 * - 이분탐색
 * - dp
 * - 그리디
 *
 * 이 문제는 투포인터의 한 유형인 슬라이딩 윈도우와도 비슷
 * 슬라이딩 윈도우는 그 크기가 변하지 않는다는 특징! (즉 두 포인터 간격이 유지된다)
 */

public class Prob3 {

    public static void main(String[] args) {
//        int[] A = {0, 5, 4, 4, 5, 12};
//        int[] A = {4, 2, 2, 4, 2};
//        int[] A = {1,2,3,2};
//        int[] A = {4, 4};
        int[] A = {1, 2, 3, 4, 5, 6};

        Prob3 prob3 = new Prob3();
        int solution = prob3.solution(A);
        System.out.println("solution = " + solution);
    }

    public int solution(int[] A) {
        int answer = 0;

        if(A.length <= 2) return A.length;

        int i = 0, j = 1;
        HashMap<Integer, Integer> hm = new HashMap<>();
        hm.put(A[0], 1);

        while (j < A.length) {
            hm.put(A[j], hm.getOrDefault(A[j], 0) + 1);

            if (hm.size() <= 2) {
                j++;
            } else {
                if (hm.get(A[i]) == 1) {
                    hm.remove(A[i]);
                } else {
                    hm.put(A[i], hm.get(A[i]) - 1);
                }
                i++;
                j++;
            }
        }

        answer = j - i;

        return answer;
    }
}
