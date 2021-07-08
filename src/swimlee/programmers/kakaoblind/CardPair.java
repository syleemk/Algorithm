package swimlee.programmers.kakaoblind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 순열 문제
 * 완전탐색
 * <p>
 * permutation visited 배열 대신 비트마스킹으로 방문표시 ㅋㅋ
 * 관련 permutation 함수 구현 링크 :
 * https://skmouse.tistory.com/entry/2021%EC%B9%B4%EC%B9%B4%EC%98%A4-%EC%B9%B4%EB%93%9C-%EC%A7%9D-%EB%A7%9E%EC%B6%94%EA%B8%B0-Java
 * <p>
 * 배열복사
 * -> 얕은 복사, 깊은 복사!
 * -> 배열또한 객체이므로 (Heap 영역에 저장되는)
 * -> 깊은복사를 해주어야한다!
 * <p>
 * https://coding-factory.tistory.com/548
 */

public class CardPair {
    public static void main(String[] args) {
        int[][] board = {{1, 0, 0, 3}, {2, 0, 0, 0}, {0, 0, 0, 2}, {3, 0, 1, 0}};
        int r = 1;
        int c = 0;

        CardPair cardPair = new CardPair();
        int solution = cardPair.solution(board, r, c);
        System.out.println("solution = " + solution);
    }

    ArrayList<int[]> perms = new ArrayList<>();

    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};

    public int solution(int[][] board, int r, int c) {
        int answer = Integer.MAX_VALUE;

        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0) cnt++;
            }
        }
        cnt /= 2;

        int[] cards = new int[cnt];
        for (int i = 0; i < cnt; i++) {
            cards[i] = i + 1;
        }

        permutation(cards, 0, 0, new int[cnt]);

        for (int[] order : perms) {
            int[] position = new int[]{r, c};
            int[][] cBoard = new int[4][4];
            int dis = 0;

            for (int i = 0; i < 4; i++) {
                System.arraycopy(board[i], 0, cBoard[i], 0, 4);
            }

            for (int target : order) {
                dis += bfs(cBoard, position, target);
                dis += bfs(cBoard, position, target);
            }

            answer = Math.min(answer, dis);
        }

        return answer;
    }

    private int bfs(int[][] board, int[] position, int target) {

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{position[0], position[1], 0});

        boolean[][] visited = new boolean[4][4];
        visited[position[0]][position[1]] = true;

        while (!q.isEmpty()) {
            int[] now = q.remove();
            int x = now[0];
            int y = now[1];
            int dis = now[2];

            if (board[x][y] == target) {
                position[0] = x;
                position[1] = y;
                board[x][y] = 0;
                return dis + 1;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4) continue;
                if(visited[nx][ny]) continue;

                visited[nx][ny] = true;
                q.add(new int[]{nx, ny, dis + 1});
            }

            for (int i = 0; i < 4; i++) {
                int[] next = ctrlMove(board, new int[]{x, y}, i);
                int nx = next[0];
                int ny = next[1];

                if(nx == x && ny == y) continue;
                if(visited[nx][ny]) continue;

                visited[nx][ny] = true;
                q.add(new int[]{nx, ny, dis + 1});
            }
        }

        // 해당하는 target 값이 없으면 move 안하니까 0을 리턴시키는 것도 좋은 방법인듯
        return 0;
    }

    private int[] ctrlMove(int[][] board, int[] position, int dir) {
        int nx = position[0] + dx[dir];
        int ny = position[1] + dy[dir];

        while (nx >= 0 && ny >= 0 && nx < 4 && ny < 4) {
            if (board[nx][ny] != 0) {
                return new int[]{nx, ny};
            }

            nx += dx[dir];
            ny += dy[dir];
        }

        return new int[]{nx - dx[dir], ny - dy[dir]};
    }


    private void permutation(int[] cards, int depth, int visited, int[] result) {
        if (depth == cards.length) {
            perms.add(Arrays.copyOf(result, result.length));
            return;
        }

        for (int i = 0; i < cards.length; i++) {
            if ((visited & (1 << i)) == 0) {
                result[depth] = cards[i];
                permutation(cards, depth + 1, (visited | (1 << i)), result);
            }
        }
    }
}
