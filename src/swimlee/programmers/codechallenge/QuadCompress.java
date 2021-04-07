package swimlee.programmers.codechallenge;

import java.util.Arrays;

/**
 * 별찍기 느낌 - 재귀
 * 다시해보기
 */

public class QuadCompress {

    int[] answer = new int[2];
    double[] dx = {0, 0.5, 0, 0.5};
    double[] dy = {0, 0, 0.5, 0.5};

    public static void main(String[] args) {
        int[][] arr = {{1, 1, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 1}, {1, 1, 1, 1}};

        QuadCompress quadCompress = new QuadCompress();
        int[] solution = quadCompress.solution(arr);
        System.out.println("Arrays.toString(solution) = " + Arrays.toString(solution));
    }

    public int[] solution(int[][] arr) {

        if (!check(arr, 0, 0, arr.length))
            compress(arr, 0, 0, arr.length);
        else
            answer[arr[0][0]]++;

        return answer;
    }

    private void compress(int[][] arr, int x, int y, int len) {
        for (int i = 0; i < 4; i++) {
            int nx = x + (int) (len * dx[i]);
            int ny = y + (int) (len * dy[i]);

            if (!check(arr, nx, ny, len / 2)) {
                compress(arr, nx, ny, len / 2);
                continue;
            }

            answer[arr[nx][ny]]++;
        }
    }

    private boolean check(int[][] arr, int x, int y, int len) {
        int val = arr[x][y];

        for (int i = x; i < x + len; i++) {
            for (int j = y; j < y + len; j++) {
                if(arr[i][j] != val) return false;
            }
        }

        return true;
    }
}
