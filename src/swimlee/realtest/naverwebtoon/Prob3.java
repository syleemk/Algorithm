package swimlee.realtest.naverwebtoon;

import java.util.Stack;

/**
 * 문자열과 스택
 * 110 옮기기 문제와 비슷
 * 시간복잡도!!
 */

public class Prob3 {
    public static void main(String[] args) {
        String s = "aabcbcd";
        String t = "abc";

        Prob3 prob3 = new Prob3();
        int solution = prob3.solution(s, t);
        System.out.println("solution = " + solution);
    }

    public int solution(String s, String t) {
        int result = 0;

        Stack<Character> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            st.push(s.charAt(i));

            if (st.size() >= t.length()) {
                Stack<Character> temp = new Stack<>();
                boolean flag = true;

                for (int j = t.length() - 1; j >= 0; j--) {
                    temp.push(st.pop());
                    if (!temp.peek().equals(t.charAt(j))) {
                        while (!temp.isEmpty()) {
                            st.push(temp.pop());
                        }
                        flag = false;
                        break;
                    }
                }

                if(flag) result++;
            }
        }

        return result;
    }
}
