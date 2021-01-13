package swimlee.programmers.dp;

public class RoadToSchool {
    public static void main(String[] args) {
        int[][] puddles = {{2, 2}};

        RoadToSchool roadToSchool = new RoadToSchool();
        int solution = roadToSchool.solution(4, 3, puddles);
        System.out.println("solution = " + solution);
    }

    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;

        int[][] dp = new int[m+1][n+1];

        for (int[] tmp : puddles) {
            dp[tmp[0]][tmp[1]] = -1;
        }

        dp[1][1] = 1;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(dp[i][j] == 1) continue;
                if(dp[i][j] == -1) {
                    dp[i][j] = 0;
                    continue;
                }
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1000000007;
            }
        }

        return dp[m][n];
    }
}
