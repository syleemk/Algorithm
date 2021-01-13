package swimlee.programmers.dp;

public class Thievery {

    public static void main(String[] args) {
        int[] money = {1, 2, 3, 1};

        Thievery thievery = new Thievery();
        int solution = thievery.solution(money);
        System.out.println("solution = " + solution);
    }

    public int solution(int[] money) {
        int answer = 0;

        int[][] dp = new int[2][money.length];

        dp[1][0] = money[0];
        dp[1][1] = money[0];
        dp[0][1] = money[1];

        for (int i = 0; i < 2; i++) {
            for (int j = 2; j < money.length; j++) {
                if(i==1 && j==money.length-1){
                    dp[i][j] = dp[i][j-1];
                    continue;
                }
                dp[i][j] = Math.max(dp[i][j - 1], dp[i][j - 2] + money[j]);
            }
        }

        return Math.max(dp[0][money.length - 1], dp[1][money.length - 1]);
    }
}
