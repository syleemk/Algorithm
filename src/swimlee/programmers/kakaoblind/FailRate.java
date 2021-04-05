package swimlee.programmers.kakaoblind;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 자바 클래스 정의해서 문제풀기
 * Array Sorting 문제
 * Comparator or Comparable을 사용한 정렬
 * Integer, Double 값 비교시에는 static method인 Compare 사용!!
 */

public class FailRate {

    public static void main(String[] args) {
        int N = 5;
        int[] stages = {2,1,2,4,2,4,3,3};

        FailRate failRate = new FailRate();
        int[] solution = failRate.solution(N, stages);
        System.out.println("Arrays.toString(solution) = " + Arrays.toString(solution));
    }

    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];

        int totalCnt = stages.length;

        Arrays.sort(stages);
        Map<Integer, Integer> hm = new HashMap<>();

        for (int num : stages) {
            hm.put(num, hm.getOrDefault(num, 0) + 1);
        }

        Node[] result = new Node[N];

        for (int i = 1; i <= N; i++) {
            result[i-1] = new Node();
            result[i-1].stage = i;
            if(totalCnt != 0)
                result[i - 1].failRate = (double) hm.getOrDefault(i,0) / totalCnt;
            System.out.println("result = " + result[i-1].failRate);
            totalCnt -= hm.getOrDefault(i,0);
        }

        Arrays.sort(result,(o1, o2)-> {
            if(o1.failRate == o2.failRate) return o1.stage - o2.stage;
            return Double.compare(o2.failRate, o1.failRate);
        });

        for (int i = 0; i < N; i++) {
            answer[i] = result[i].stage;
        }

        return answer;
    }
}

class Node {
    int stage;
    double failRate;

    Node() {
        failRate = 0;
    }
}
