package swimlee.programmers.findmaster;

import java.util.LinkedList;
import java.util.Queue;

/**
 * bfs 최단거리 문제
 */

public class GameMap {

    int[][] count;
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) {
        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
//        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,0},{0,0,0,0,1}};
        GameMap gameMap = new GameMap();
        int solution = gameMap.solution(maps);
        System.out.println("solution = " + solution);
    }

    public int solution(int[][] maps) {
        int answer = 0;

        count = new int[maps.length][maps[0].length];
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length; j++) {
                count[i][j] = maps[i][j] == 0 ? 0 : -1;
            }
        }

        int x = 0, y = 0;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        count[x][y] = 1;

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.x == maps.length - 1 && now.y == maps[0].length - 1) break;

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= maps.length || ny >= maps[0].length || nx < 0 || ny < 0) continue;
                if (count[nx][ny] != -1) continue;

                q.add(new Node(nx, ny));
                count[nx][ny] = count[now.x][now.y] + 1;
            }
        }

        return count[maps.length - 1][maps[0].length - 1];
    }
}

class Node {
    int x;
    int y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
