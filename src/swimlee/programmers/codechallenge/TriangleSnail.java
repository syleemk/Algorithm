package swimlee.programmers.codechallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 좌표이동 구현
 */

public class TriangleSnail {

    int[] dx = {1, 0, -1};
    int[] dy = {0, 1, -1};

    public static void main(String[] args) {
        TriangleSnail triangleSnail = new TriangleSnail();
        int[] solution = triangleSnail.solution(6);
        System.out.println("Arrays.toString(solution) = " + Arrays.toString(solution));
    }

    public int[] solution(int n) {
        int[] answer = {};

        int[][] arr = new int[n][n];

        int dir = 0;
        int x = 0;
        int y = 0;

        for (int i = 1; i <= n * (n + 1) / 2; i++) {
            arr[x][y] = i;

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx >= n || ny > nx || arr[nx][ny] != 0) {
                dir = (dir + 1) % 3;
                nx = x + dx[dir];
                ny = y + dy[dir];
            }

            x = nx;
            y = ny;
        }

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                list.add(arr[i][j]);
            }
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
