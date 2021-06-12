package swimlee.programmers.kakaoIntern;

import java.util.Stack;

public class CrainDolls {

    public static void main(String[] args) {
        int[][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
        int[] moves = {1,5,3,5,1,2,1,4};
        CrainDolls crainDolls = new CrainDolls();
        int solution = crainDolls.solution(board, moves);
        System.out.println("solution = " + solution);
    }

    public int solution(int[][] board, int[] moves) {
        int answer = 0;

        Stack<Integer> st = new Stack<>();

        for (int col : moves) {
            for (int i = 0; i < board.length; i++) {
                int now = board[i][col-1];
                if (now != 0) {
                    if(!st.isEmpty() && now == st.peek()) {
                        st.pop();
                        answer += 2;
                    } else st.push(now);
                    board[i][col-1] = 0;
                    break;
                }
            }
        }

        return answer;
    }
}
