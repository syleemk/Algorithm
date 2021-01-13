package swimlee.programmers.stackqueue;

import java.util.Arrays;
import java.util.Stack;

public class StockPrice {
    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 2, 3};
        StockPrice stockPrice = new StockPrice();
        int[] solution = stockPrice.solution(prices);
        System.out.println("solution = " + Arrays.toString(solution));
    }

    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        Stack<int[]> st = new Stack<>();

        for (int i = 0; i < prices.length; i++) {
            int[] now = {prices[i], i};
            
            //가격이 계속 증가하도록
            while (!st.isEmpty() && st.peek()[0] > now[0]) {
                int[] pop = st.pop();
                answer[pop[1]] = now[1] - pop[1];
            }

            st.push(now);
        }

        int[] last = st.pop();

        while (!st.isEmpty()) {
            int[] pop = st.pop();
            answer[pop[1]] = last[1] - pop[1];
        }

        return answer;
    }
}
