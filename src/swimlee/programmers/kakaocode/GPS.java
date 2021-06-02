package swimlee.programmers.kakaocode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 문제에서는 결론만 물어봄. 과정을 반환하지 않고 중간과정의 최소한의 오류 수정 횟수만 물어봄
 * 어느 부분에서의 최소화의 경로나 경유 등을 찾을 때는 주로 DP 사용
 */

public class GPS {

    public static void main(String[] args) {
        int n = 7;
        int m = 10;
        int[][] edge_list = {{1, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 7}};
        int k = 6;
        int[] gps_log = {1, 2, 3, 3, 6, 7};

        GPS gps = new GPS();
        int solution = gps.solution(n, m, edge_list, k, gps_log);
        System.out.println("solution = " + solution);
    }

    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        int answer = 0;

        int[][] dp = new int[k][n+1];

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            graph.get(i).add(i);
        }

        for (int[] edge : edge_list) {
            int start = edge[0];
            int end = edge[1];

            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        for (int i = 0; i < k; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        dp[0][gps_log[0]] = 0;

        for (int i = 1; i < k; i++) {
            for (int j = 1; j <= n; j++) {
                for (int node : graph.get(j)) {
                    dp[i][j] = Math.min(dp[i - 1][node], dp[i][j]);
                }
                if(dp[i][j] != Integer.MAX_VALUE)
                    dp[i][j] += gps_log[i] == j ? 0 : 1;
            }
        }

        if(dp[k-1][gps_log[k-1]] == Integer.MAX_VALUE) {
            answer = -1;
        } else {
            answer = dp[k - 1][gps_log[k - 1]];
        }

        return answer;
    }
}
