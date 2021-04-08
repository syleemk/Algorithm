package swimlee.programmers.codechallenge;

import java.util.Arrays;

/**
 * regex and 재귀
 */

public class RepeatBinaryChange {

    int[] answer = new int[2];

    public static void main(String[] args) {
        RepeatBinaryChange repeatBinaryChange = new RepeatBinaryChange();
        int[] solution = repeatBinaryChange.solution("110010101001");
        System.out.println("Arrays.toString(solution) = " + Arrays.toString(solution));
    }

    public int[] solution(String s) {

        if(!s.equals("1")) recursive(s);

        return answer;
    }

    private void recursive(String s) {
        if(s.equals("1")) return;

        answer[0]++;

        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '0') answer[1]++;
        }

        s = s.replaceAll("0", "");
        s = Integer.toBinaryString(s.length());

        recursive(s);
    }
}
