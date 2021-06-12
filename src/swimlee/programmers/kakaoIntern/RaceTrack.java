package swimlee.programmers.kakaoIntern;

/**
 * dfs + dp 로 풀게되면 여러 경우에 대한 방향고려가 안된다.
 * 단순히 값이 같거나 작을때 그 경로를 채택하므로
 *
 * 특정포인트에 먼저 도착하고 다른 경로보다 작기만 한다면, 해당 경로로 채택된다.
 * 만약 그 포인트에서 꺾이는 경우, 직진하는 다른 경로가 늦게 도착하더라도 최종결과는 작아질 수 있다.
 * https://programmers.co.kr/questions/17343
 * 
 * 따라서 bfs로 푸는 것이 맞는 문제다 나중에 다시 풀것 지금은 하기 싫음
 * https://codingjuny.tistory.com/41
 */

public class RaceTrack {

    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};
    int[][] board;
    boolean[][] visited;
    int[][] dp;
    int len;

    public static void main(String[] args) {
//        int[][] board = {{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}};
        int[][] board = {
                {0, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 1, 1, 1, 1},
                {1, 1, 1, 1, 0, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 1, 1, 1, 1},
                {0, 1, 0, 1, 1, 1, 1, 1, 1},
                {0, 1, 0, 0, 0, 1, 1, 1, 1},
                {0, 1, 1, 1, 0, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 1, 1, 1, 1},
                {1, 1, 1, 1, 0, 0, 0, 0, 0}
        };
        
        
        RaceTrack raceTrack = new RaceTrack();
        int solution = raceTrack.solution(board);
        System.out.println("solution = " + solution);
    }

    public int solution(int[][] board) {
        this.board = board;
        len = board.length;
        visited = new boolean[len][len];
        dp = new int[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        visited[0][0] = true;
        dp[0][0] = 0;

        for (int dir = 0; dir < 2; dir++) {
            dfs(0, 0, dir, 0);
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if(dp[i][j] == Integer.MAX_VALUE) dp[i][j] = -100;
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        return dp[len-1][len-1];
    }

    private void dfs(int x, int y, int dir, int score) {
        if (x == len - 1 && y == len - 1) return;

        for (int i = 0; i < 4; i++) {
            int ndir = (dir + i) % 4;
            int nx = x + dx[ndir];
            int ny = y + dy[ndir];

            if(nx >= len || ny >= len || nx < 0 || ny < 0) continue;
            if(visited[nx][ny]) continue;
            if(board[nx][ny] == 1) continue;

            visited[nx][ny] = true;

            if (i == 0) {
                if (score + 100 <= dp[nx][ny]) {
                    dp[nx][ny] = score + 100;
                    dfs(nx, ny, ndir, score + 100);
                }
            } else {
                if (score + 600 <= dp[nx][ny]) {
                    dp[nx][ny] = score + 600;
                    dfs(nx, ny, ndir, score + 600);
                }
            }

            visited[nx][ny] = false;
        }
    }
}
