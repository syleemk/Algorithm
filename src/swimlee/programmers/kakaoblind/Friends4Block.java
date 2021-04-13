package swimlee.programmers.kakaoblind;

import java.util.*;

/**
 * 단순 구현 문제
 * 시간초과 - 재귀사용시 발생 (참고 : https://kpuls.tistory.com/73)
 * 블럭이동 큐사용 아이디어 대박
 */

public class Friends4Block {

    int[] dx = {0, 1, 1};
    int[] dy = {1, 0, 1};

    Set<String> set = new HashSet<>();

    public static void main(String[] args) {
        int m = 6;
        int n = 6;
//        String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
//        String[] board = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
        String[] board = {"IIIIOO", "IIIOOO", "IIIOOI", "IOOIII", "OOOIII", "OOIIII"};

        Friends4Block friends4Block = new Friends4Block();
        int solution = friends4Block.solution(m, n, board);
        System.out.println("solution = " + solution);
    }

    public int solution(int m, int n, String[] board) {
        int answer = 0;

        char[][] arr = new char[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = board[i].charAt(j);
            }
        }

        while (true) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    scan(arr, i, j);
                }
            }

            if(set.isEmpty()) break;

            delete(arr);
            set.clear();
            down(arr, m, n);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(arr[i][j] == ' ') answer++;
            }
        }

        return answer;
    }

    public void scan(char[][] arr, int x, int y) {
        int m = arr.length;
        int n = arr[0].length;
        char ch = arr[x][y];

        if(ch == ' ') return;

        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= m || ny >= n) return;
            if(arr[nx][ny] != ch) return;
        }

        set.add(x + "," + y);

        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            set.add(nx + "," + ny);
        }
    }

    public void delete(char[][] arr) {
        for (String coordinate : set) {
            String[] split = coordinate.split(",");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);

            arr[x][y] = ' ';
        }
    }

    public void down(char[][] arr, int m, int n) {
        Queue<Character> q = new LinkedList<>();

        for (int j = 0; j < n; j++) {
            for (int i = m - 1; i >= 0; i--) {
                if(arr[i][j] != ' ') q.add(arr[i][j]);
                arr[i][j] = ' ';
            }

            int start = m - 1;
            while (!q.isEmpty()) {
                arr[start][j] = q.poll();
                start--;
            }
        }
    }
}
