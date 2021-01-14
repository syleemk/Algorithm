package swimlee.programmers.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MoreSpicy {
    public static void main(String[] args) {
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K = 7;
        MoreSpicy moreSpicy = new MoreSpicy();
        int solution = moreSpicy.solution(scoville, K);
        System.out.println("solution = " + solution);
    }

    public int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : scoville) {
            pq.add(i);
        }

        while (pq.size() >= 2) {
            if(pq.peek() >= K) return answer;
            Integer first = pq.remove();
            Integer second = pq.remove();
            Integer mix = first + 2 * second;
            pq.add(mix);
            answer++;
        }

        if(pq.peek() > K) return answer;

        return -1;
    }
}
