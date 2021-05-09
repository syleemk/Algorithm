package swimlee.realtest.lineadintern;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 그리디? 대기시간 빠르게 손해비용 높은 것부터?
 */

public class Prob3 {

    public static void main(String[] args) {
        int[][] ads = {{0, 1}, {0, 2}, {6, 3}, {8, 4}};
        
        Prob3 prob3 = new Prob3();
        int solution = prob3.solution(ads);
        System.out.println("solution = " + solution);
    }

    public int solution(int[][] ads) {
        int answer = 0;
        
        // 우선 수행할 수 있고, 손해비용 큰거부터 먼저 수행
        Arrays.sort(ads, (a, b) -> {
            if(a[0] == b[0]) return b[1] - a[1];
            else return a[0] - b[0];
        });

        Queue<int[]> q = new LinkedList<>(Arrays.asList(ads));
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);



        return answer;
    }
}
