package swimlee.realtest.kakaopay;

import java.util.Arrays;

public class Prob2 {

    public static void main(String[] args) {
        int rows = 4;
        int columns = 3;
        int[][] swipes = {{1, 1, 2, 4, 3}, {3, 2, 1, 2, 3}, {4, 1, 1, 4, 3}, {2, 2, 1, 3, 3}};

        Prob2 prob2 = new Prob2();
        int[] solution = prob2.solution(rows, columns, swipes);
        System.out.println("Arrays.toString(solution) = " + Arrays.toString(solution));
    }

    public int[] solution(int rows, int columns, int[][] swipes) {
        int[] answer = new int[swipes.length];

        // 배열 초기화
        int[][] map = new int[rows][columns];
        int num = 1;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                map[r][c] = num++;
            }
        }

        for (int i = 0; i < swipes.length; i++) {
            int result = move(map, swipes[i]);
            answer[i] = result;
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                System.out.print(map[r][c]+ " ");
            }
            System.out.println();
        }
        System.out.println();

        return answer;
    }

    private int move(int[][] map, int[] swipe) {
        int result = 0;

        int d = swipe[0];
        int r1 = swipe[1] - 1;
        int c1 = swipe[2] - 1;
        int r2 = swipe[3] - 1;
        int c2 = swipe[4] - 1;

        if (d == 1) {
            int[] temp = new int[c2 - c1 + 1];
            for (int c = c1; c <= c2; c++) {
                result += map[r2][c];
                temp[c - c1] = map[r2][c];
            }

            for (int c = c1; c <= c2; c++) {
                for (int r = r2; r > r1; r--) {
                    map[r][c] = map[r - 1][c];
                }
            }

            for (int i = 0; i < temp.length; i++) {
                map[r1][c1 + i] = temp[i];
            }

        } else if (d == 2) {
            int[] temp = new int[c2 - c1 + 1];
            for (int c = c1; c <= c2; c++) {
                result += map[r1][c];
                temp[c - c1] = map[r1][c];
            }

            for (int c = c1; c <= c2; c++) {
                for (int r = r1; r < r2; r++) {
                    map[r][c] = map[r + 1][c];
                }
            }

            for (int i = 0; i < temp.length; i++) {
                map[r2][c1 + i] = temp[i];
            }
        } else if (d == 3) {
            int[] temp = new int[r2 - r1 + 1];
            for (int r = r1; r <= r2; r++) {
                result += map[r][c2];
                temp[r - r1] = map[r][c2];
            }

            for (int r = r1; r <= r2; r++) {
                for (int c = c2; c > c1; c--) {
                    map[r][c] = map[r][c - 1];
                }
            }

            for (int i = 0; i < temp.length; i++) {
                map[r1 + i][c1] = temp[i];
            }

        } else if (d == 4) {
            int[] temp = new int[r2 - r1 + 1];
            for (int r = r1; r <= r2; r++) {
                result += map[r][c1];
                temp[r - r1] = map[r][c1];
            }

            for (int r = r1; r <= r2; r++) {
                for (int c = c1; c < c2; c++) {
                    map[r][c] = map[r][c + 1];
                }
            }

            for (int i = 0; i < temp.length; i++) {
                map[r1 + i][c2] = temp[i];
            }

        }

        return result;
    }
}
