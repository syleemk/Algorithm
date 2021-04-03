package swimlee.realtest.devmatching;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 구현 연습 좀더
 */
public class Prob2 {

    ArrayList<Integer> result = new ArrayList<>();

    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) {
        int rows = 6;
        int columns = 6;
        int[][] queries = {{2,2,5,4},{3,3,6,6},{5,1,6,3}};

        Prob2 prob2 = new Prob2();
        int[] solution = prob2.solution(rows, columns, queries);
        System.out.println("Arrays.toString(solution) = " + Arrays.toString(solution));
    }

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = {};

        int[][] arr = new int[rows][columns];
        int cnt = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                arr[i][j] = ++cnt;
            }
        }

        for (int i = 0; i < queries.length; i++) {
            int x1 = queries[i][0];
            int y1 = queries[i][1];
            int x2 = queries[i][2];
            int y2 = queries[i][3];

            int min = rotate(x1 - 1, y1 - 1, x2 - 1, y2 - 1, arr);
            result.add(min);
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private int rotate(int x1, int y1, int x2, int y2, int[][] arr) {

        int cnt = (x2 - x1 + y2 - y1) * 2;
        int nowX = x1;
        int nowY = y1;

        int temp;
        int prev = arr[nowX][nowY];
        int min = prev;
        int dir = 0;
        while (cnt > 0) {
            // 사각형의 모서리에 도달한 경우 방향 전환
            if (nowX == x1 && nowY == y2) {// 오른쪽 위 모서리
                dir = 1;
            } else if (nowX == x2 && nowY == y2) {// 오른쪽 아래 모서리
                dir = 2;
            } else if (nowX == x2 && nowY == y1) {// 왼쪽 아래 모서리
                dir = 3;
            }

            int nextX = nowX + dx[dir];
            int nextY = nowY + dy[dir];

            temp = arr[nextX][nextY];
            arr[nextX][nextY] = prev;
            prev = temp;

            nowX = nextX;
            nowY = nextY;

            min = Math.min(min, prev);

            cnt--;
        }

        return min;
    }
}
