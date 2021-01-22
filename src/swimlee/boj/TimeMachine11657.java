package swimlee.boj;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 벨만포드 시간복잡도가 왜 VE인지 알 수 있는 코드
 */

class Edge {
    int start;
    int end;
    long cost;

    public Edge(int start, int end, long cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }
}

public class TimeMachine11657 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        //그래프 구현 - 간선 리스트
        Edge[] edges = new Edge[M];

        for (int i = 0; i < M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int c = sc.nextInt();

            edges[i] = new Edge(u, v, c);
        }

        if (N == 1) {// 도시가 하나인 경우는 자기자신으로 돌아오는 경로밖에 없음
            return;
        }

        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;

        for (int i = 0; i < N - 1; i++) {
            for (Edge e : edges) {
                if(dist[e.start] == Long.MAX_VALUE) continue;
                if (dist[e.end] > dist[e.start] + e.cost) {
                    dist[e.end] = dist[e.start] + e.cost;
                }
            }
        }

        boolean cycle = false;
        for (Edge e : edges) {
            if(dist[e.start] == Long.MAX_VALUE) continue;
            if (dist[e.end] > dist[e.start] + e.cost) {
                cycle = true;
                break;
            }
        }

        if (cycle) {
            System.out.println(-1);
        } else {
            for (int i = 2; i <= N; i++) {
                if(dist[i] == Long.MAX_VALUE) System.out.println(-1);
                else System.out.println(dist[i]);
            }
        }
    }
}
