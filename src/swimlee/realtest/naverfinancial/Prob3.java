package swimlee.realtest.naverfinancial;

public class Prob3 {
    public static void main(String[] args) {
//        int[] A = {1, 2, -3, 4, 5, -6};
//        int[] A = {-1, 2, 1, 2, 0, 2, 1, -3, 4, 3, 0, -1};
        int[] A = {1};

        Prob3 prob3 = new Prob3();
        int solution = prob3.solution(A);
        System.out.println("solution = " + solution);
    }

    public int solution(int[] A) {
        int answer = -1;

        int now = 0;
        int sum = 0;

        while (now < A.length) {
            if (A[now] < 0) {
                sum = 0;
            } else{
                sum += A[now];
                answer = Math.max(answer, sum);
            }
            now++;
        }

        return answer;
    }
}
