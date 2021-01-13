package swimlee.programmers.stackqueue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class FunctionDevelop {

    public static void main(String[] args) {
        int[] progresses1 = {95, 90, 99, 99, 80, 99};
        int[] progresses2 = {93, 30, 55};
        int[] speeds1 = {1, 1, 1, 1, 1, 1};
        int[] speeds2 = {1, 30, 5};

        FunctionDevelop functionDevelop = new FunctionDevelop();
        int[] solution = functionDevelop.solution(progresses2, speeds2);
        System.out.println("solution = " + Arrays.toString(solution));
    }

    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};

        Stack<Integer> st = new Stack<>();
        LinkedList<Integer> list = new LinkedList<>();

        for (int i = progresses.length - 1; i >= 0; i--) {
            int num;
            if ((100 - progresses[i]) % speeds[i] == 0) {
                num = (100 - progresses[i]) / speeds[i];
            } else {
                num = (100 - progresses[i]) / speeds[i] + 1;
            }

            st.push(num);
        }

        int cnt = 0;
        while (!st.isEmpty()) {
            Integer pop = st.pop();
            cnt++;
            while (!st.isEmpty() && st.peek() <= pop) {
                st.pop();
                cnt++;
            }
            list.add(cnt);
            cnt = 0;
        }

        return list.stream().mapToInt(i -> i).toArray();
    }
}
