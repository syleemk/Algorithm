package swimlee.programmers.bruteforce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    int[] a = {1, 2, 3, 4, 5};
    int[] b = {2, 1, 2, 3, 2, 4, 2, 5};
    int[] c = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
    int cntA, cntB, cntC;

    public static void main(String[] args) {
        Test test = new Test();
        int[] answers = {1, 3,2,4,2};
        int[] solution = test.solution(answers);
        for (int i : solution) {
            System.out.println("i = " + i);
        }

    }

    public int[] solution(int[] answers) {
        List<Integer> temp = new ArrayList<>();

        for (int i = 0; i < answers.length; i++) {
            if(answers[i] == a[i%a.length]) cntA++;
            if(answers[i] == b[i%b.length]) cntB++;
            if(answers[i] == c[i%c.length]) cntC++;
        }

        int max = Math.max(Math.max(cntA, cntB), cntC);

        if(cntA == max) temp.add(1);
        if(cntB == max) temp.add(2);
        if(cntC == max) temp.add(3);

        int[] answer = new int[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            answer[i] = temp.get(i);
        }

        return answer;

        //깔끔하지만 시간오래걸림
//        return temp.stream().mapToInt(Integer::intValue).toArray();
    }
}
