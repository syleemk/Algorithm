package swimlee.programmers.tipsdown;

public class PredictedMatch {

    int answer = 0;

    public static void main(String[] args) {
        PredictedMatch predictedMatch = new PredictedMatch();
        int solution = predictedMatch.solution(8, 4, 7);
        System.out.println("solution = " + solution);
    }

    public int solution(int n, int a, int b)
    {
        recursive(a, b);

        return answer;
    }

    private void recursive(int a, int b) {
        int big = Math.max(a, b);
        int small = Math.min(a, b);

        if (big - small == 1) {
            if (big % 2 == 0 && small % 2 == 1) {
                answer++;
                return;
            }
        }

        if (big % 2 == 1) big += 1;
        if (small % 2 == 1) small += 1;

        answer++;
        recursive(big / 2, small / 2);
    }

}
