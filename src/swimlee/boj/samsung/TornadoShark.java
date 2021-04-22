package swimlee.boj.samsung;

import java.util.Scanner;

public class TornadoShark {

    static int N;
    static int[][] map;

    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { -1, 0, 1, 0 };

    static int x, y;
    static int result = 0;

    static int[][] sandX = { { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 }, // 좌
            { -1, -1, 0, 0, 2, 0, 0, 1, 1, 1 }, // 하
            { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 }, // 우
            { 1, 1, 0, 0, -2, 0, 0, -1, -1, -1 } // 상
    };

    static int[][] sandY = { { 1, 1, 0, 0, -2, 0, 0, -1, -1, -1 }, // 좌
            { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 }, // 하
            { -1, -1, 0, 0, 2, 0, 0, 1, 1, 1 }, // 우
            { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 } // 상
    };

    static double[] rate = { 0.01, 0.01, 0.02, 0.02, 0.05, 0.07, 0.07, 0.1, 0.1 };

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N][N];

        x = N / 2;
        y = N / 2;
        int dir = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        for (int i = 1; i < N; i++) {

            int count = (i == N - 1 ? 3 : 2);

            for (int j = 0; j < count; j++) {
                for (int k = 0; k < i; k++) {
                    move(dir);
                }
                dir = (dir + 1) % 4;
            }
        }

        System.out.println(result);

    }

    private static void move(int dir) {

        int nx = x + dx[dir];
        int ny = y + dy[dir];
        int amount = map[nx][ny];
        int out = 0;

        for (int i = 0; i < 9; i++) {

            int windX = nx + sandX[dir][i];
            int windY = ny + sandY[dir][i];
            int windAmount = (int) (amount * rate[i]);

            out += windAmount;

            if (windX < 0 || windY < 0 || windX >= N || windY >= N) {
                result += windAmount;
            } else {
                map[windX][windY] += windAmount;
            }
        }

        int windX = nx + sandX[dir][9];
        int windY = ny + sandY[dir][9];
        int windAmount = amount - out;

        if (windX < 0 || windY < 0 || windX >= N || windY >= N) {
            result += windAmount;
        } else {
            map[windX][windY] += windAmount;
        }

        x = nx;
        y = ny;

    }
}
