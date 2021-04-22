package swimlee.boj.samsung;

import java.util.Scanner;

public class AdultShark {

    // 위, 아래, 왼, 오
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static int[][][] map;
    static Shark[] shark;
    static int N, M, k;
    static int leftShark;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        k = sc.nextInt();

        map = new int[N][N][2];
        shark = new Shark[M + 1];
        leftShark = M;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j][0] = sc.nextInt();
                map[i][j][1] = k;

                // 상어의 경우
                if (map[i][j][0] > 0) {
                    shark[map[i][j][0]] = new Shark(i, j, true);
                }
            }
        }

        // 상어 초기 방향 입력
        for (int i = 1; i <= M; i++) {
            shark[i].dir = sc.nextInt();
        }

        // 상어별 우선순위 입력
        for (int m = 1; m <= M; m++) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    shark[m].priority[i][j] = sc.nextInt();
                }
            }
        }

        int time = 0;
        while (++time <= 1000) {
            moveShark();

            if(leftShark == 1) break;
        }

        if(leftShark > 1) System.out.println(-1);
        else System.out.println(time);

    }

    private static void moveShark() {

        int[][] exist = new int[N][N];

        // 우선순위 높은 것부터 이동 (이후 도착하는 것 다 삭제)
        for (int s = 1; s <= M; s++) {
            // 상어 죽었으면 통과
            if (!shark[s].isAlive)
                continue;

            int nowDir = shark[s].dir;
            int nowX = shark[s].x;
            int nowY = shark[s].y;

            boolean flag = false;

            for (int p = 0; p < 4; p++) {

                int nextDir = shark[s].priority[nowDir - 1][p];
                int nextX = nowX + dx[nextDir-1];
                int nextY = nowY + dy[nextDir-1];

                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N)
                    continue;
                if (map[nextX][nextY][0] != 0)
                    continue;

                // 이미 다른 상어 도착한 경우
                if (exist[nextX][nextY] >= 1 && exist[nextX][nextY] < s) {
                    leftShark--;
                    shark[s].isAlive = false;
                } else { // 빈칸인 경우
                    exist[nextX][nextY] = s;
                    shark[s].x = nextX;
                    shark[s].y = nextY;
                    shark[s].dir = nextDir;
                }

                flag = true;
                break;
            }

            // 빈칸 있는 경우
            if (flag)
                continue;

            // 빈칸 없는 경우, 자기 냄새 있는 곳으로 되돌아가기
            for (int p = 0; p < 4; p++) {
                int nextDir = shark[s].priority[nowDir - 1][p];
                int nextX = nowX + dx[nextDir-1];
                int nextY = nowY + dy[nextDir-1];

                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N)
                    continue;

                if (map[nextX][nextY][0] != s)
                    continue;

                exist[nextX][nextY] = s;
                shark[s].x = nextX;
                shark[s].y = nextY;
                shark[s].dir = nextDir;
                break;
            }

        }

        // 냄새 시간 1씩 감소
        decreaseSmell();

        // 냄새 남겨주기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (exist[i][j] != 0) {
                    map[i][j][0] = exist[i][j];
                    map[i][j][1] = k;
                }
            }
        }
    }

    private static void decreaseSmell() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j][0] == 0)
                    continue;
                if (map[i][j][1] == 1)
                    map[i][j][0] = 0;
                map[i][j][1]--;
            }
        }
    }

}

class Shark {
    int x, y;
    int dir;
    boolean isAlive;

    int[][] priority = new int[4][4];

    Shark(int x, int y, boolean isAlive) {
        this.x = x;
        this.y = y;
        this.isAlive = isAlive;
    }

}