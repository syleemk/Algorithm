package swimlee.programmers.dfsbfs;

public class TargetNumber {
    int cnt = 0;

    public static void main(String[] args) {
        TargetNumber targetNumber = new TargetNumber();

        int[] numbers = {1,1,1,1,1};
        int solution = targetNumber.solution(numbers, 3);
        System.out.println("solution = " + solution);
    }

    public int solution(int[] numbers, int target) {

        int sum =0;

        dfs(0, 0, numbers, target);

        return cnt;
    }

    public void dfs(int idx, int sum, int[] numbers, int target) {
        if(idx == numbers.length) {
            if(sum == target) cnt++;
            return;
        }

        dfs(idx + 1, sum + numbers[idx], numbers, target);
        dfs(idx + 1, sum - numbers[idx], numbers, target);

    }
}
