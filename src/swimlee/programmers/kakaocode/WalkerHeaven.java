package swimlee.programmers.kakaocode;

/**
 * 카카오 경로 건설? 문제랑 비슷한 문제인듯
 */

public class WalkerHeaven {

    public static void main(String[] args) {
        int m = 3;
        int n = 6;
        int[][] cityMap = {
                {0, 2, 0, 0, 0, 2},
                {0, 0, 2, 0, 1, 0},
                {1, 0, 0, 2, 2, 0}
        };

        WalkerHeaven walkerHeaven = new WalkerHeaven();
        int solution = walkerHeaven.solution(m, n, cityMap);
        System.out.println("solution = " + solution);
    }

    int MOD = 20170805;
    int[][][] dp;

    public int solution(int m, int n, int[][] cityMap) {

        dp = new int[m + 1][n + 1][2];
        dp[1][1][0] = dp[1][1][1] = 1;

        for (int r = 1; r <= m; r++) {
            for (int c = 1; c <= n; c++) {
                if (cityMap[r - 1][c - 1] == 0) {
                    dp[r][c][0] += (dp[r - 1][c][0] + dp[r][c - 1][1]) % MOD;
                    dp[r][c][1] += (dp[r - 1][c][0] + dp[r][c - 1][1]) % MOD;
                } else if (cityMap[r - 1][c - 1] == 2) {
                    dp[r][c][0] = dp[r - 1][c][0];
                    dp[r][c][1] = dp[r][c - 1][1];
                } else {
                    dp[r][c][0] = 0;
                    dp[r][c][1] = 0;
                }
            }
        }

        return dp[m][n][0];
    }
}
