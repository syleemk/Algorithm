package swimlee.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1753 다익스트라
 * 완벽하게 이해못함
 */

class Edge {
    public int vertex;
    public int weight;

    public Edge(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}

public class MinRoute1753 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        List<Edge>[] list = new List[V+1];
        for (int i = 0; i <= V; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[u].add(new Edge(v, w));
        }

        dist[K] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        pq.add(new Edge(K, 0));

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            for (int i = 0; i < list[now.vertex].size(); i++) {
                Edge next = list[now.vertex].get(i);
                if (dist[next.vertex] > dist[now.vertex] + next.weight) {
                    dist[next.vertex] = dist[now.vertex] + next.weight;
                    pq.add(new Edge(next.vertex, dist[next.vertex]));
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            if(dist[i] == Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(dist[i]);
        }
    }

}
