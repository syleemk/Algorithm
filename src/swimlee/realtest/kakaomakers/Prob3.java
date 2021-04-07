package swimlee.realtest.kakaomakers;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 약간 dfs 느낌
 * 자바 인접리스트 만드는 방법 찾아보기
 */

public class Prob3 {

    int[] answer;
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

    public static void main(String[] args) {
        int n = 6;
        int[] passenger = {1,1,1,1,1,1};
        int[][] train = {{1,2},{1,3},{1,4},{3,5},{3,6}};
        Prob3 prob3 = new Prob3();
        int[] solution = prob3.solution(n, passenger, train);
        System.out.println("Arrays.toString(solution) = " + Arrays.toString(solution));

    }

    public int[] solution(int n, int[] passenger, int[][] train) {

        boolean[] check = new boolean[n+1];

        // 인접리스트 초기화
        for (int i = 0; i < n + 1; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < train.length; i++) {
            int from = train[i][0];
            int to = train[i][1];

            adj.get(from).add(to);
            adj.get(to).add(from);
        }

        //dfs
        check[1] = true;
        dfs(1, 0, passenger, check);

        return answer;
    }

    private void dfs(int now, int sum, int[] passenger, boolean[] check) {

        sum += passenger[now - 1];

        if (answer != null && answer.length > 0) {
            if(sum > answer[1]) {// 거리합이 더 크면 갱신
                answer[0] = now;
                answer[1] = sum;
            } else if (sum == answer[1] && now > answer[0]) {// 거리합이 같으면 종착역번호 큰거 선택
                answer[0] = now;
                answer[1] = sum;
            }
        } else {
            answer = new int[2];
            answer[0] = now;
            answer[1] = sum;
        }

        for (int i = 0; i < adj.get(now).size(); i++) {
            int next = adj.get(now).get(i);

            if(check[next]) continue;

            check[next] = true;
            dfs(next, sum, passenger, check);
            check[next] = false;
        }

    }


}
