package swimlee.programmers.summer_winter_coding;

/**
 * dp 문제같음
 * 원형의 경우 첫번째 포함하는 경우 아닌 경우로 나누어서 계산
 */

public class CollectSticker {

    public static void main(String[] args) {
        int[] sticker = {14, 6, 5, 11, 3, 9, 2, 10};

        CollectSticker collectSticker = new CollectSticker();
        int solution = collectSticker.solution(sticker);
        System.out.println("solution = " + solution);
    }

    public int solution(int[] sticker) {
        if(sticker.length == 1) return sticker[0];

        int[][] dp = new int[sticker.length][2];

        dp[0][0] = 0; // 첫 번째 미포함 - 마지막 포함 가능
        dp[1][0] = sticker[1];
        dp[0][1] = sticker[0]; // 첫 번째 포함 - 마지막 포함 불가능
        dp[1][1] = dp[0][1];

        for (int i = 2; i < sticker.length; i++) {
            if (i == sticker.length - 1) {
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][1]);
            } else {
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][1] + sticker[i]);
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 2][0] + sticker[i]);
        }

        return Math.max(dp[sticker.length - 1][0], dp[sticker.length - 1][1]);
    }
}
