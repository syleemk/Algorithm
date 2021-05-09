package swimlee.realtest.kakao2021summerintern;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class Prob3 {

    public static void main(String[] args) {
        int n = 8;
        int k = 2;
        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};

        Prob3 prob3 = new Prob3();
        String solution = prob3.solution(n, k, cmd);
        System.out.println("solution = " + solution);
    }

    public String solution(int n, int k, String[] cmd) {
        String answer = "";

        boolean[] result = new boolean[n];
        Arrays.fill(result, true);

        int end = n - 1;

        Stack<Node> st = new Stack<>();
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            //일종의 이름 부여, 왜냐하면 초기랑 비교해야하기 때문
            list.add(i);
        }

        for (String oper : cmd) {
            int c = oper.charAt(0);

            String[] s = oper.split(" ");

            if (s[0].equals("U")) {
                k -= Integer.parseInt(s[1]);
            } else if (s[0].equals("D")) {
                k += Integer.parseInt(s[1]);
            } else if (s[0].equals("C")) {
                end--;
                st.push(new Node(list.get(k), k));
                list.remove(k);
                if (k > end) k--;
            } else if (s[0].equals("Z")) {
                Node pop = st.pop();
                list.add(pop.idx, pop.name);
                if (pop.idx <= k) {
                    k++;
                }
                end++;
            }
        }

        while (!st.isEmpty()) {
            result[st.pop().name] = false;
        }

        StringBuilder sb = new StringBuilder();
        for (boolean r : result) {
            if (r) {
                sb.append("O");
            } else {
                sb.append("X");
            }
        }

        return sb.toString();
    }

}

class Node {
    int name;
    int idx;

    Node(int name, int idx) {
        this.name = name;
        this.idx = idx;
    }
}
