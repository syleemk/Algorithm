package swimlee.realtest.devmatching;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Prob1 {


    public static void main(String[] args) {
        int[] lottos = {44, 1, 0, 0, 31, 25};
        int[] win_nums = {31, 10, 45, 1, 6, 19};

        Prob1 prob1 = new Prob1();
        int[] solution = prob1.solution(lottos, win_nums);
        System.out.println("Arrays.toString(solution) = " + Arrays.toString(solution));
    }

    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];

        Set<Integer> set = new HashSet<>();
        for (int win_num : win_nums) {
            set.add(win_num);
        }

        int zeroCnt = 0;
        int sameCnt = 0;

        for (int lotto : lottos) {
            if (lotto == 0) {
                zeroCnt++;
            } else if (set.contains(lotto)) {
                sameCnt++;
            }
        }

        // 최저순위 구하기
        switch (sameCnt) {
            case 2:
                answer[1] = 5;
                break;
            case 3:
                answer[1] = 4;
                break;
            case 4:
                answer[1] = 3;
                break;
            case 5:
                answer[1] = 2;
                break;
            case 6:
                answer[1] = 1;
                break;
            default:
                answer[1] = 6;
                break;
        }

        // 최고순위 구하기
        switch (sameCnt + zeroCnt) {
            case 2:
                answer[0] = 5;
                break;
            case 3:
                answer[0] = 4;
                break;
            case 4:
                answer[0] = 3;
                break;
            case 5:
                answer[0] = 2;
                break;
            case 6:
                answer[0] = 1;
                break;
            default:
                answer[0] = 6;
                break;
        }

        return answer;
    }
}
