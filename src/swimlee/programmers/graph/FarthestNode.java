package swimlee.programmers.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FarthestNode {

    public static void main(String[] args) {
        int n = 6;
        int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};

        FarthestNode farthestNode = new FarthestNode();
        int solution = farthestNode.solution(n, edge);
        System.out.println("solution = " + solution);
    }

    public int solution(int n, int[][] edge) {
        int answer = 0;

        ArrayList<Integer>[] graph = new ArrayList[n+1];
        boolean[] visited = new boolean[n + 1];
        int[] distance = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 그래프 초기화
        for (int[] e : edge) {
            int start = e[0];
            int end = e[1];

            graph[start].add(end);
            graph[end].add(start);
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{1, 0});
        visited[1] = true;

        int maxDis = -1;

        while (!q.isEmpty()) {
            int[] now = q.remove();
            int node = now[0];
            int dis = now[1];

            maxDis = Math.max(maxDis, dis);

            for (int next : graph[node]) {
                if(visited[next]) continue;

                q.add(new int[]{next, dis + 1});
                visited[next] = true;
                distance[next] = dis + 1;
            }
        }

        for (int i = 1; i <= n; i++) {
            if(distance[i] == maxDis) answer++;
        }

        return answer;
    }
}
