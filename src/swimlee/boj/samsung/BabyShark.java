package swimlee.boj.samsung;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * BFS 사용해서 현재 상어위치로부터 모든 물고기까지의 최단거리를 구한다. BFS, DFS는 방문 체크 필요
 */

public class BabyShark {
    static int[][] map;
    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static int sharkSize = 2;
    static int eatCnt = 0;
    static int N;
    static int minX, minY, minDistance;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        map = new int[N][N];

        int fishCnt = 0;
        int sharkX = 0, sharkY = 0;
        int time = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 9) {
                    sharkX = i;
                    sharkY = j;
                    map[i][j] = 0;
                } else if (map[i][j] >= 1 && map[i][j] <= 6) {
                    fishCnt++;
                }
            }
        }

        if(fishCnt == 0) {
            System.out.println(0);
            return;
        }

        while(true) {
            bfs(sharkX, sharkY);

            if(minX == Integer.MAX_VALUE && minY == Integer.MAX_VALUE) break;

            sharkX = minX;
            sharkY = minY;

            map[sharkX][sharkY] = 0;

            if(++eatCnt == sharkSize) {
                sharkSize++;
                eatCnt = 0;
            }

            time += minDistance;

        }

        System.out.println(time);

        sc.close();
    }

    private static void bfs(int x, int y) {

        Queue<Node> q = new LinkedList<>();
        boolean[][] check = new boolean[N][N];

        q.add(new Node(x, y, 0));
        check[x][y] = true;

        minX = Integer.MAX_VALUE;
        minY = Integer.MAX_VALUE;
        minDistance = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                int ndistance = now.distance + 1;

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (check[nx][ny] || map[nx][ny] > sharkSize) continue;

                if (map[nx][ny] != 0 && map[nx][ny] < sharkSize) {
                    if(minDistance > ndistance) {
                        minX = nx;
                        minY = ny;
                        minDistance = ndistance;
                    } else if(minDistance == ndistance) {
                        if(nx < minX) { //가장 위에 있는 물고기인 경우
                            minX = nx;
                            minY = ny;
                        } else if(nx == minX) { //높이가 같은 경우, 가장 왼쪽에 있는 물고기 선택
                            if(ny < minY) {
                                minX = nx;
                                minY = ny;
                            }
                        }
                    }
                }

                check[nx][ny] = true;
                q.add(new Node(nx, ny, ndistance));
            }
        }
    }

}

class Node {
    int x;
    int y;
    int distance;

    Node(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }
}