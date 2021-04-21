package swimlee.boj.samsung;

import java.util.Scanner;

/**
 * 백트랙킹 자바는 참조변수타입을 전달할 때 인스턴스의 주소를 전달하므로 원본 객체가 수정된다. 따라서 객체를 깊은 복사해서 전달하고 이를
 * 원상복구 시키는 작업이 필요하다.
 */

public class TeenageShark {

    static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };
    static int[][] map = new int[4][4];
    static Fish[] fish = new Fish[17];
    static int maxCnt = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int fishNum = sc.nextInt();
                int fishDir = sc.nextInt();

                map[i][j] = fishNum;
                fish[fishNum] = new Fish(fishDir - 1, i, j, true);
            }
        }

        int first = map[0][0];
        map[0][0] = -1;
        fish[first].alive = false;

        dfs(0, 0, fish[first].dir, first);

        System.out.println(maxCnt);

    }

    // 1번 물고기부터 이동
    private static void moveFish() {
        for (int i = 1; i <= 16; i++) {
            if (!fish[i].alive)
                continue;

            int x = fish[i].x;
            int y = fish[i].y;

            for (int j = 0; j < 8; j++) {
                int ndir = (fish[i].dir + j) % 8;
                int nx = x + dx[ndir];
                int ny = y + dy[ndir];

                if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4 || map[nx][ny] == -1)
                    continue;

                if (map[nx][ny] == 0) {

                    map[x][y] = 0;
                    map[nx][ny] = i;

                    fish[i].x = nx;
                    fish[i].y = ny;
                    fish[i].dir = ndir;

                } else {

                    map[x][y] = map[nx][ny];
                    map[nx][ny] = i;

                    fish[map[x][y]].x = fish[i].x;
                    fish[map[x][y]].y = fish[i].y;

                    fish[i].x = nx;
                    fish[i].y = ny;
                    fish[i].dir = ndir;

                }

                break;
            }
        }
    }

    private static void dfs(int x, int y, int dir, int eatCnt) {

        if (maxCnt < eatCnt)
            maxCnt = eatCnt;

        int[][] tempMap = new int[4][4];
        for (int i = 0; i < 4; i++) {
            System.arraycopy(map[i], 0, tempMap[i], 0, 4);
        }

        Fish[] tempFish = new Fish[17];
        for (int i = 1; i <= 16; i++) {
            tempFish[i] = new Fish(fish[i].dir, fish[i].x, fish[i].y, fish[i].alive);
        }

        moveFish();

        for (int i = 1; i < 4; i++) {
            int nx = x + dx[dir] * i;
            int ny = y + dy[dir] * i;

            if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4)
                continue;
            if (map[nx][ny] == 0)
                continue;

            int eatenFish = map[nx][ny];

            fish[eatenFish].alive = false;
            map[nx][ny] = -1;
            map[x][y] = 0;

            dfs(nx, ny, fish[eatenFish].dir, eatCnt + eatenFish);

            fish[eatenFish].alive = true;
            map[nx][ny] = eatenFish;
            map[x][y] = -1;
        }

        for (int i = 0; i < 4; i++) {
            System.arraycopy(tempMap[i], 0, map[i], 0, 4);
        }

        for (int i = 1; i <= 16; i++) {
            fish[i] = new Fish(tempFish[i].dir, tempFish[i].x, tempFish[i].y, tempFish[i].alive);
        }

    }

}

class Fish {
    int dir;
    int x, y;
    boolean alive;

    public Fish(int dir, int x, int y, boolean alive) {
        this.dir = dir;
        this.x = x;
        this.y = y;
        this.alive = alive;
    }

}
