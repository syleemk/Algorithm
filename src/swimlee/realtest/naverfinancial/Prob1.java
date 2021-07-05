package swimlee.realtest.naverfinancial;

public class Prob1 {
    public static void main(String[] args) {
        int[] A = {3, 2, 1, 1, 2, 3, 1};

        Prob1 prob1 = new Prob1();
        int solution = prob1.solution(A);
        System.out.println("solution = " + solution);
    }

    public int solution(int[] A) {
        // write your code in Java SE 8
        int[] sum = new int[4 + 1];

        for (int a : A) {
            for (int j = 1; j <= 4; j++) {
                sum[j] += Math.abs(a - j);
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= 4; i++) {
            answer = Math.min(answer, sum[i]);
        }

        return answer;
    }
}
