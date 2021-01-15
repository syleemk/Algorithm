package swimlee.boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 숨바꼭질 17071번 문제
 * 수빈이를 먼저 이동시킨후, 동생을 이동시켜서 해당하는 곳에 수빈이가 들린적이 있다면 그때 시간을 리턴
 * 홀수시간대와 짝수시간대로 나눠서 풀이 - 왜냐면 +1 -1 2초동안 방문했던 곳 재방문 가능하니까
 */

public class Prob17071 {
    private static boolean[][] visited = new boolean[2][500001];

    public static void main(String[] args) {
        int answer = -1;

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        if (n == k) {
            System.out.println(0);
            return;
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(n);
        visited[0][n] = true;

        int time = 0;

        while (!q.isEmpty()) {
            int len = q.size();
            time++;
            int layer = time % 2;//홀수 짝수 판단
            //수민이 먼저 이동
            for (int i = 0; i< len; i++) {
                Integer now = q.poll();
                System.out.println("now = " + now);
                if (now + 1 <= 500000 && !visited[layer][now + 1]) {
                    visited[layer][now + 1] = true;
                    q.offer(now + 1);
                }
                if (now - 1 >= 0 && !visited[layer][now - 1]) {
                    visited[layer][now - 1] = true;
                    q.offer(now - 1);
                }
                if (now * 2 <= 500000 && !visited[layer][now * 2]) {
                    visited[layer][now * 2] = true;
                    q.offer(now * 2);
                }
            }

            //동생 이동
            k += time;
            System.out.println("k = " + k);
            if (k > 500000) break;
            if (visited[layer][k]) {
                System.out.println(k);
                answer = time;
                break;
            }
        }
        System.out.println(answer);
    }
}
