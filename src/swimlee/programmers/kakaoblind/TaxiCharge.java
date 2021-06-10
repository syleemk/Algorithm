package swimlee.programmers.kakaoblind;

/**
 * 그래프와 최단거리?
 * -> 다익스트라, 벨만포드, 플로이드 와샬 떠올려라!
 *
 * 다익스트라 시간복잡도 O(ElogE)
 * 플로이드와샬 시간복잡도 O(V^3)
 *
 * 각각 간선의 개수와 정점의 개수에 비례한다는 것을 알자!
 */

public class TaxiCharge {

    static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int n = 6;
        int s = 4;
        int a = 6;
        int b = 2;
        int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};

        TaxiCharge taxiCharge = new TaxiCharge();
        int solution = taxiCharge.solution(n, s, a, b, fares);
        System.out.println("solution = " + solution);
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;

        int[][] distance = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) distance[i][j] = 0;
                else distance[i][j] = MAX;
            }
        }

        for (int[] fare : fares) {
            int start = fare[0] - 1;
            int end = fare[1] - 1;
            int cost = fare[2];

            distance[start][end] = cost;
            distance[end][start] = cost;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(distance[i][k] == MAX || distance[k][j] == MAX) continue;
                    if (distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }

        answer = distance[s - 1][a - 1] + distance[s - 1][b - 1];

        for (int i = 0; i < n; i++) {
            if(distance[s-1][i] == MAX || distance[i][a-1] == MAX || distance[i][b-1] == MAX) continue;
            if (answer > distance[s - 1][i] + distance[i][a - 1] + distance[i][b - 1]) {
                answer = distance[s - 1][i] + distance[i][a - 1] + distance[i][b - 1];
            }
        }

        return answer;
    }
}
