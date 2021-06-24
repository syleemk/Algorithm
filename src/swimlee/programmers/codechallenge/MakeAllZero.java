package swimlee.programmers.codechallenge;

import java.util.ArrayList;

/**
 * 트리 탐색? 그리디
 * 리프노드부터 제거해나가기 (왜냐하면 합을 0으로 만들려면 결국 리프노드는 무조건 언제가 되었든 결국 제거해야함)
 * 리프노드까지 이동하고, 리프노드부터 다시 탐색할 수 있는가!
 *
 * 해설 : https://prgms.tistory.com/47?category=882795
 */

public class MakeAllZero {
    public static void main(String[] args) {
        int[] a = {-5, 0, 2, 1, 2};
        int[][] edges = {{0, 1}, {3, 4}, {2, 3}, {0, 3}};

        MakeAllZero makeAllZero = new MakeAllZero();
        long solution = makeAllZero.solution(a, edges);
        System.out.println("solution = " + solution);
    }

    /**
     * static으로 선언 안하면 서버에 따라 통과 안되는게 있음 OOM 일 수 있다고함 (Out of Memory)
     * 서버 상태에 따라 복불복이라함 ;;
     *
     * https://programmers.co.kr/questions/17024
     */
    static ArrayList<Integer>[] tree;
    static long[] ln_a;
    static boolean[] visited;
    static long answer = 0;

    public long solution(int[] a, int[][] edges) {

        ln_a = new long[a.length];
        tree = new ArrayList[a.length];
        visited = new boolean[a.length];

        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            ln_a[i] = a[i];
            tree[i] = new ArrayList<>();
        }
        if(sum != 0) return -1;

        for (int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];

            tree[start].add(end);
            tree[end].add(start);

        }

        dfs(0);

        return answer;
    }

    private long dfs(int idx) {
        visited[idx] = true;
        for (int i = 0; i < tree[idx].size(); i++) {
            int next = tree[idx].get(i);
            if(visited[next]) continue;
            ln_a[idx] += dfs(next);
        }

        answer += Math.abs(ln_a[idx]);
        return ln_a[idx];
    }
}
