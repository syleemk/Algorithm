package swimlee.realtest.naverwebtoon;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 재귀 느낌?
 * 약간 브라이언의 고민 느낌
 */

public class Prob2 {
    public static void main(String[] args) {
//        String s = "abcxyasdfasdfxyabc";
        String s = "abcxyqwertyxyabc";

        Prob2 prob2 = new Prob2();
        String[] solution = prob2.solution(s);
        System.out.println("Arrays.toString(solution) = " + Arrays.toString(solution));
    }

    ArrayList<String> list = new ArrayList<>();

    public String[] solution(String s) {

        makePieces(s);

        return list.toArray(new String[0]);
    }

    private void makePieces(String s) {
        if(s.isEmpty()) return;

        int idx = s.lastIndexOf(s.charAt(0));
        if (idx == 0) {
            list.add(s);
            return;
        }

        int len = s.length() - idx;
        list.add(s.substring(0, len));
        makePieces(s.substring(len, idx));
        list.add(s.substring(idx));
    }
}
