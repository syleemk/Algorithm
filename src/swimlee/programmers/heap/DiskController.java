package swimlee.programmers.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DiskController {

    public static void main(String[] args) {
        int[][] jobs = {};
        DiskController diskController = new DiskController();
        int solution = diskController.solution(jobs);
        System.out.println("solution = " + solution);
    }

    public int solution(int[][] jobs) {
        int answer = 0;

        Queue<int[]> q = new LinkedList<>();
        for (int[] job : jobs) {
            q.add(job);
        }

        int time = 0;
//        while (!q.isEmpty()) {
//            if(q.peek())
//        }


        return answer;
    }
}
