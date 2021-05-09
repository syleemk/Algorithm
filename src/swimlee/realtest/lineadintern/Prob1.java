package swimlee.realtest.lineadintern;

import java.util.Stack;

public class Prob1 {

    public static void main(String[] args) {
        Prob1 prob1 = new Prob1();
        int solution = prob1.solution("ABC({ABC)ABC");
        System.out.println("solution = " + solution);
    }

    public int solution(String inputString) {
        int answer = 0;

        Stack<Character> st = new Stack<>();

        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);

            if (c == '(' || c == '{' || c == '[' || c == '<') {
                st.push(c);
            } else if (c == ')' || c == '}' || c == ']' || c == '>') {
                if (st.isEmpty()) {
                    return -i;
                }
                if (rightPair(st.peek(), c)) {
                    answer++;
                    st.pop();
                } else {
                    return -i;
                }

            }
        }

        if(!st.isEmpty()) return -(inputString.length()-1);

        return answer;
    }

    private boolean rightPair(char left, char right) {
        if (left == '(' && right == ')') {
            return true;
        } else if (left == '{' && right == '}') {
            return true;
        } else if (left == '[' && right == ']') {
            return true;
        } else if (left == '<' && right == '>') {
            return true;
        }

        return false;
    }
}
