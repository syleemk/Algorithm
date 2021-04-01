package swimlee.boj.sds;

import java.util.Scanner;

/**
 * N-Queen
 * 자바 2차원 배열의 깊은 복사!! -> 메모리 시간초과 ㅋㅋㅋㅋ
 * 굳이 2차원 배열 전체를 복사할 필요 없음!!
 */

public class Prob9663 {

    static int cnt = 0;
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        int[] chess = new int[N];

        for (int i = 0; i < N; i++) {
            chess[0] = i;
            dfs(chess, 1);
        }

        System.out.println(cnt);
    }

    public static void dfs(int[] chess, int row) {
        if (row >= N) {
            cnt++;
            return;
        }

        for (int i = 0; i < N; i++) {
            boolean check = true;

            for (int j = 0; j < row; j++) {
                int dif = row - j;
                if (chess[j] + dif == i) {
                    check = false;
                    break;
                }
                if (chess[j] - dif == i) {
                    check = false;
                    break;
                }
                if (chess[j] == i) {
                    check = false;
                    break;
                }
            }

            if(check){
                chess[row] = i;
                dfs(chess, row + 1);
            }
        }
    }
}
