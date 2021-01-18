package swimlee.programmers.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 여벌 있는 학생도 도난당할 수 있음
 */
public class Uniform {
    public static void main(String[] args) {

        int n = 5;
        int[] lost = {1, 2, 4, 5};
        int[] reserve = {2, 3, 4};

        Uniform uniform = new Uniform();
        int solution = uniform.solution(n, lost, reserve);
        System.out.println("solution = " + solution);

    }

    public int solution(int n, int[] lost, int[] reserve) {
        int answer;

        Set<Integer> set = new HashSet<>();
        for (int re : reserve) {
            set.add(re);
        }

        ArrayList<Integer> arr = new ArrayList<>();
        for (int lo : lost) {
            if (set.contains(lo)) {
                set.remove(lo);
                continue;
            }
            arr.add(lo);
        }

        answer = n - arr.size();

        for (int s : arr) {
            if (set.contains(s - 1)) {
                set.remove(s - 1);
                answer++;
            } else if (set.contains(s + 1)) {
                set.remove(s + 1);
                answer++;
            }
        }

        return answer;
    }
}
