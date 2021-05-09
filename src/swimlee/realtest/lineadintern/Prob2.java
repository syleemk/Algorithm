package swimlee.realtest.lineadintern;

import java.util.Arrays;

public class Prob2 {

    public static void main(String[] args) {
        int[] array = {3, 5, 4, 1, 2};

        Prob2 prob2 = new Prob2();
        int[] solution = prob2.solution(array);
        System.out.println("Arrays.toString(solution) = " + Arrays.toString(solution));
    }

    public int[] solution(int[] array) {
        int[] answer = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            boolean flag = false;

            //왼쪽
            for (int l = i - 1; l >= 0; l--) {
                if(array[i] < array[l]) {
                    answer[i] = l;
                    flag = true;
                    break;
                }
            }

            if(flag) continue;

            //오른쪽
            for (int r = i + 1; r < array.length; r++) {
                if (array[i] < array[r]) {
                    answer[i] = r;
                    flag = true;
                    break;
                }
            }

            if(flag) continue;

            answer[i] = -1;
        }

        return answer;
    }
}
