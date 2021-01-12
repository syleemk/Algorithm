package swimlee.programmers.sort;

import java.util.Arrays;

public class KthNumber {
    public static void main(String[] args) {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};

        KthNumber kthNumber = new KthNumber();
        int[] solution = kthNumber.solution(array, commands);
        System.out.println("solution = " + Arrays.toString(solution));
    }

    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for (int i = 0; i < commands.length; i++) {
            //배열 범위 복사 (끝 인덱스 미포함)
            int[] arr = Arrays.copyOfRange(array, commands[i][0] - 1, commands[i][1]);
            Arrays.sort(arr);
            answer[i] = arr[commands[i][2]-1];
        }

        return answer;
    }
}
