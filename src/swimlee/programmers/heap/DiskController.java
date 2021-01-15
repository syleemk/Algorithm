package swimlee.programmers.heap;

import java.util.*;

/**
 * 해설 보고 품
 */

public class DiskController {

    public static void main(String[] args) {
        int[][] jobs = {{0, 3}, {1, 9}, {2, 6}};
        DiskController diskController = new DiskController();
        int solution = diskController.solution(jobs);
        System.out.println("solution = " + solution);
    }

    public int solution(int[][] jobs) {
        int answer;

        Arrays.sort(jobs, (a, b) -> {
            if(a[0] == b[0]) return a[1] - b[1];
            else return a[0] - b[0];
        });
        Queue<int[]> q = new LinkedList<>(Arrays.asList(jobs));
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        int time = 0;
        int totalWait = 0;
        while (!q.isEmpty() || !pq.isEmpty()) {
            //현재 작업 진행도중 오는 요청은 pq에 담는다
            while (!q.isEmpty() && q.peek()[0] <= time) {
                pq.offer(q.poll());
            }
            
            if (!q.isEmpty() && pq.isEmpty()) {//대기 작업이 없는 경우 제일 우선순위 높은 작업 시간으로 업데이트
                time = q.peek()[0];
            } else {//대기 작업이 있는 경우, 가장 우선순위가 먼저인 작업 하나 수행
                int[] now = pq.poll();
                time += now[1];
                totalWait += time - now[0];
            }

        }

//        System.out.println(totalWait);
//        System.out.println(jobs.length);
        answer = totalWait / jobs.length;
        return answer;
    }
}
