package swimlee.programmers.summer_winter_coding;

import java.util.*;

/**
 * 다익스트라 
 * 최단 거리 알고리즘 찾아보기
 * 
 * bfs 최단거리는 그래프의 간선의 가중치가 모두 1일 때만 사용가능
 */

public class Delivery {

    public static void main(String[] args) {

        int N = 5;
        int[][] road= {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
        int K = 3;

        Delivery delivery = new Delivery();
        int solution = delivery.solution(N, road, K);
        System.out.println("solution = " + solution);
    }

    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        int[] distance = new int[N];
        boolean[] check = new boolean[N];

        Arrays.fill(distance, Integer.MAX_VALUE);

        // 인접리스트 초기화
        ArrayList<HashMap<Integer, Integer>> arr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            arr.add(new HashMap<>());
        }

        for (int[] r : road) {
            int start = r[0] - 1;
            int end = r[1] - 1;
            int cost = r[2];

            if (arr.get(start).containsKey(end) && arr.get(start).get(end) <= cost) continue;

            arr.get(start).put(end, cost);
            arr.get(end).put(start, cost);
        }

        distance[0] = 0;
        int cnt = N;

        while (--cnt > 0) {
            int now = -1;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                if (!check[i] && min > distance[i]) {
                    now = i;
                    min = distance[i];
                }
            }

            check[now] = true;

            HashMap<Integer, Integer> hm = arr.get(now);
            for (Integer next : hm.keySet()) {
                if (!check[next] && distance[next] > distance[now] + hm.get(next)) {
                    distance[next] = distance[now] + hm.get(next);
                }
            }
        }

        for (int d : distance) {
            if(d <= K) answer++;
        }

        return answer;
    }
}
