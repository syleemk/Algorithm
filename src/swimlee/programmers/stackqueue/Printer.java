package swimlee.programmers.stackqueue;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Printer {

    public static void main(String[] args) {
        int[] priorities = {1,1,9,1,1,1};
        int location = 0;

        Printer printer = new Printer();
        int solution = printer.solution(priorities, location);
        System.out.println("solution = " + solution);
    }

    public int solution(int[] priorities, int location) {
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < priorities.length; i++) {
            pq.add(priorities[i]);
            int[] tmp = {priorities[i], i};
            q.add(tmp);
        }

        int cnt = 0;
        while (!q.isEmpty()) {
            int[] front = q.remove();
            if (!pq.isEmpty() && pq.peek() == front[0]) {
                cnt++;
                pq.remove();
                if(front[1] == location) return cnt;
            }
            q.add(front);
        }

        return cnt;
    }
}
