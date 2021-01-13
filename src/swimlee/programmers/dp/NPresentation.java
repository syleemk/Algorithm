package swimlee.programmers.dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 다시 풀어보기
 * 정답보고 품
 */

public class NPresentation {

    public static void main(String[] args) {
        NPresentation nPresentation = new NPresentation();
        int solution = nPresentation.solution(2, 11);
        System.out.println("solution = " + solution);
    }

    public int solution(int N, int number) {

        ArrayList<HashSet<Integer>> dp = new ArrayList<>();

        for (int i = 0; i <= 8; i++) {
            HashSet<Integer> set = new HashSet<>();
            set.add(getNumber(N, i));
            dp.add(set);
        }

        if(dp.get(1).contains(number)) return 1;

        for (int i = 2; i <= 8; i++) {
            for (int j = 1; j < i; j++) {
                for (Integer op1 : dp.get(j)) {
                    for (Integer op2 : dp.get(i - j)) {
                        dp.get(i).add(op1 + op2);
                        dp.get(i).add(op1 - op2);
                        dp.get(i).add(op1 * op2);
                        if (op2 != 0) dp.get(i).add(op1 / op2);
                    }
                }
            }

            if(dp.get(i).contains(number)) {
                return i;
            }
        }

        return -1;
    }

    public int getNumber(int num, int cnt) {
        if(cnt == 0) return 0;

        String ret = "";
        for (int i = 0; i < cnt; i++) {
            ret += Integer.toString(num);
        }

        return Integer.parseInt(ret);
    }
}
