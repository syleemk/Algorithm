package swimlee.programmers.tipsdown;

import java.util.Stack;

/**
 * 자료구조!!!
 * BruteForce(재귀) 보다 Stack !
 */

public class PairRemoval {
    public static void main(String[] args) {
        PairRemoval pairRemoval = new PairRemoval();
        int solution = pairRemoval.solution("baabaa");
        System.out.println("solution = " + solution);
    }

    public int solution(String s)
    {
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (!st.isEmpty() && st.peek() == s.charAt(i)) st.pop();
            else st.push(s.charAt(i));
        }

        if(st.isEmpty()) return 1;
        else return 0;
    }
}
