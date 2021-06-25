package swimlee.programmers.codechallenge;

import java.util.Arrays;
import java.util.Stack;

/**
 * StringBuilder를 이용한 delete연산 시간복잡도 최적화 위한 Stack사용 풀이
 *
 * https://countrysides.tistory.com/90
 */

public class Move110Stack {
    public static void main(String[] args) {
        String[] s = {"1110","100111100","0111111010"};

        Move110Stack move110Stack = new Move110Stack();
        String[] solution = move110Stack.solution(s);
        System.out.println("Arrays.toString(solution) = " + Arrays.toString(solution));
    }

    static final String OOZ = "110";

    public String[] solution(String[] s) {
        String[] answer = new String[s.length];

        for (int i = 0; i < s.length; i++) {

            Stack<Character> st = new Stack<>();
            StringBuilder repeat = new StringBuilder();

            for (int j = 0; j < s[i].length(); j++) {
                char c = s[i].charAt(j);
                st.push(c);

                if (st.size() >= 3) {
                    char third = st.pop();
                    if (third != '0') {
                        st.push(third);
                        continue;
                    }

                    char second = st.pop();
                    if (second != '1') {
                        st.push(third);
                        st.push(second);
                        continue;
                    }

                    char first = st.pop();
                    if (first != '1') {
                        st.push(third);
                        st.push(second);
                        st.push(first);
                        continue;
                    }

                    repeat.append(OOZ);
                }
            }

            int idx = st.size();
            boolean flag = false;

            StringBuilder sb = new StringBuilder();
            while (!st.isEmpty()) {
                char pop = st.pop();
                if(!flag && pop == '1') idx--;
                if(pop == '0') flag = true;

                sb.insert(0, pop);
            }

            sb.insert(idx, repeat);

            answer[i] = sb.toString();
        }

        return answer;
    }
}
