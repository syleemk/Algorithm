package swimlee.programmers.dfsbfs;

import java.util.LinkedList;
import java.util.Queue;

public class Network {



    public static void main(String[] args) {
        Network network = new Network();

        int[][] computer = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
        int solution = network.solution(3, computer);
        System.out.println("solution = " + solution);
    }

    public int solution(int n, int[][] computers) {
        int answer = 0;

        boolean[] check = new boolean[n];

        for (int i = 0; i < n; i++) {
            if(check[i]) continue;
            check[i]= true;
            answer++;
            dfs(i, n, check, computers);
        }

        return answer;
    }

    public void dfs(int idx, int n, boolean[] check, int[][] computers) {

        for (int i = 0; i < n; i++) {
            if (!check[i] && computers[idx][i] == 1) {
                check[i] = true;
                dfs(i, n, check, computers);
            }
        }
    }
}
