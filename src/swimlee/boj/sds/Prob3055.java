package swimlee.boj.sds;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Prob3055 {

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static Queue<Point> water = new LinkedList<>();
    static Queue<Point> hedge = new LinkedList<>();

    static int cnt = 0;
    static int R;
    static int C;

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();

        char[][] map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String next = sc.next();
            for (int j = 0; j < C; j++) {
                map[i][j] = next.charAt(j);
                if(map[i][j] == 'S') hedge.add(new Point(i, j));
                if(map[i][j] == '*') water.add(new Point(i, j));
            }
        }

        bfs(map);
    }

    public static void bfs(char[][] map) {
        while (true) {
            cnt++;

            int waterSize = water.size();
            for (int i = 0; i < waterSize; i++) {
                Point now = water.poll();
                for (int j = 0; j < 4; j++) {
                    Point next = new Point(now.x + dx[j], now.y + dy[j]);

                    if (next.x < 0 || next.y < 0 || next.x >= R || next.y >= C) {
                        continue;
                    }

                    if (map[next.x][next.y] == '.') {
                        map[next.x][next.y] = '*';
                        water.add(next);
                    }
                }
            }

            int hedgeSize = hedge.size();

            if(hedgeSize == 0) {
                System.out.println("KAKTUS");
                return;
            }

            for (int i = 0; i < hedgeSize; i++) {
                Point now = hedge.poll();
                for (int j = 0; j < 4; j++) {
                    Point next = new Point(now.x + dx[j], now.y + dy[j]);

                    if (next.x < 0 || next.y < 0 || next.x >= R || next.y >= C) {
                        continue;
                    }

                    if (map[next.x][next.y] == 'D') {
                        System.out.println(cnt);
                        return;
                    }

                    if (map[next.x][next.y] == '.') {
                        map[next.x][next.y] = 'S';
                        hedge.add(next);
                    }
                }
            }

        }
    }
}
