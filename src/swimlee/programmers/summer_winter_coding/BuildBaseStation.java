package swimlee.programmers.summer_winter_coding;

/**
 * 투포인터 문제인 듯!
 */

public class BuildBaseStation {

    public static void main(String[] args) {
        int n = 11;
        int[] stations = {4, 11};
        int w = 1;

        BuildBaseStation buildBaseStation = new BuildBaseStation();
        int solution = buildBaseStation.solution(n, stations, w);
        System.out.println("solution = " + solution);
    }

    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        int start = 1;
        int end;
        final int MOD = 2 * w + 1;

        for (int i = 0; i < stations.length; i++) {
            end = stations[i] - w - 1;
            if (end >= start) {
                int len = end - start + 1;
                answer += len / MOD;
                if (len % MOD != 0) {
                    answer += 1;
                }
            }
            start = stations[i] + w + 1;
        }

        if (start <= n) {
            int len = n - start + 1;
            answer += len / MOD;
            if (len % MOD != 0) {
                answer += 1;
            }
        }

        return answer;
    }
}
