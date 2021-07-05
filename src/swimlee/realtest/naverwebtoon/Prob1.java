package swimlee.realtest.naverwebtoon;

import java.util.Arrays;

/**
 * 정렬과 포인터 문제
 */

public class Prob1 {
    public static void main(String[] args) {
        int[] prices = {13000, 88000, 10000};
        int[] discounts = {30, 20};

        Prob1 prob1 = new Prob1();
        int solution = prob1.solution(prices, discounts);
        System.out.println("solution = " + solution);
    }

    public int solution(int[] prices, int[] discounts) {
        int answer = 0;

        Arrays.sort(prices);
        Arrays.sort(discounts);

        int d = discounts.length - 1;
        int p = prices.length - 1;

        while (d >= 0 && p >= 0) {
            answer += prices[p--] * (100 - discounts[d--]) / 100;
        }

        while (p >= 0) {
            answer += prices[p--];
        }

        return answer;
    }
}
