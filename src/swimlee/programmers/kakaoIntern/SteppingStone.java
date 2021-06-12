package swimlee.programmers.kakaoIntern;

/**
 * 최대 최소개수 ? 뭔가 경계값 구한다? -> 이진탐색 스멜
 * 어떤 기준 X를 가지고 Yes No로 나누어지는 것만 정답 찾을 수 있다.
 * 가능한 정답의 최솟값 (left) 가능한 정답의 최댓값 (right)
 * 정답을 하나 결정했을 때 이것이 문제의 조건에 맞는지 검사하는 방법 (go 함수)
 * 조건에 맞는 경우 정답을 더 크게해야하는지 작게해야하는지 결정
 *
 * https://woongsios.tistory.com/131
 */

public class SteppingStone {

    public static void main(String[] args) {
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 3;

        SteppingStone steppingStone = new SteppingStone();
        int solution = steppingStone.solution(stones, k);
        System.out.println("solution = " + solution);
    }

    public int solution(int[] stones, int k) {
        int answer = 0;

        int min = 200000001;
        int max = 0;

        for (int stone : stones) {
            min = Math.min(min, stone);
            max = Math.max(max, stone);
        }

        while (min <= max) {
            int mid = (min + max) / 2;
            if (canAcross(mid, stones, k)) {
                answer = mid;
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }

        return answer;
    }

    public boolean canAcross(int num, int[] stones, int k) {
        int count = 0;

        for (int stone : stones) {
            if (stone - num >= 0) {
                count = 0;
            } else {
                count++;
            }

            if (count >= k) return false;
        }

        return true;
    }
}
