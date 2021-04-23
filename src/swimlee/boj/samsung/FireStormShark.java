package swimlee.boj.samsung;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 1. 먼저 격자로 나누기
 *
 * 2. 시계방향 돌리기
 *
 * 3. 얼음칸 인접검사후 얼음수 하나 줄이기
 *
 * --- 모든 수행 끝난 후
 *
 * - 배열 모든 요소 검사해서 얼음의 합 구하기
 *
 * - dfs로 가장 큰 얼음덩어리가 차지하는 칸 개수 구하기
 *
 * --- 회전은 어떻게?
 *
 * - 부분 격자의 크기와, 회전 시작점을 전달하는 방식! (좌측 상단 좌표)
 */

public class FireStormShark {

    static int N, Q;
    static int len;
    static int[][] map;
    static int[] level;

    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    static int sumOfIce = 0;
    static int bigArea = 0;
    static int cntArea = 0;

    static boolean[][] check;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        Q = sc.nextInt();

        len = 1 << N;

        map = new int[len][len];
        check = new boolean[len][len];
        level = new int[Q];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < Q; i++) {
            level[i] = sc.nextInt();
        }

        // 회전시키기
        for (int i = 0; i < Q; i++) {
            if (level[i] > 0) {
                int subLen = 1 << level[i];

                // 좌측 상단 좌표와 부분 격자의 크기 전달
                for (int r = 0; r < len; r += subLen) {
                    for (int c = 0; c < len; c += subLen) {
                        rotate(r, c, subLen);
                    }
                }
            }

            // 얼음 녹이기
            melt();

        }

//		// 회전한 배열 출력 (확인용)
//		for (int i = 0; i < len; i++) {
//			for (int j = 0; j < len; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println("");
//		}

        // 남아있는 얼음의 합 & 가장 큰 덩어리가 차지하는 칸의 개수 구하기
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                sumOfIce += map[i][j];
            }
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if(check[i][j] || map[i][j] == 0) continue;
                cntArea = 0;
                dfs(i, j);
                bigArea = Math.max(bigArea, cntArea);
            }
        }

        System.out.println(sumOfIce);
        System.out.println(bigArea);

    }

    private static void dfs(int x, int y) {

        cntArea++;
        check[x][y] = true;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || ny < 0 || nx >= len || ny >= len)
                continue;
            if (map[nx][ny] == 0)
                continue;
            if (check[nx][ny])
                continue;

            dfs(nx, ny);
        }
    }

    private static void melt() {

        LinkedList<int[]> remove = new LinkedList<>();

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int cnt = 0;

                if (map[i][j] == 0)
                    continue;

                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if (nx < 0 || ny < 0 || nx >= len || ny >= len)
                        continue;
                    if (map[nx][ny] == 0)
                        continue;

                    cnt++;
                }

                if (cnt < 3)
                    remove.add(new int[] { i, j });
            }
        }

        for (int[] coor : remove) {
            map[coor[0]][coor[1]] -= 1;
        }
    }

    // 부분 격자 회전
    private static void rotate(int x, int y, int level) {

        // 배열 복사를 위한 임시 배열
        int[][] temp = new int[level][level];

        for (int i = 0; i < level; i++) {
            for (int j = 0; j < level; j++) {
                temp[j][level - 1 - i] = map[x + i][y + j];
            }
        }

        // 다시 map으로 복사
        for (int i = 0; i < level; i++) {
            for (int j = 0; j < level; j++) {
                map[x + i][y + j] = temp[i][j];
            }
        }

    }

}
