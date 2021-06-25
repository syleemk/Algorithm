package swimlee.programmers.codechallenge;

/**
 * 이것도 일종의 투포인터?
 */

public class PoppingBalloon {
    public static void main(String[] args) {
        int[] a = {-16, 27, 65, -2, 58, -92, -71, -68, -61, -33};

        PoppingBalloon poppingBalloon = new PoppingBalloon();
        int solution = poppingBalloon.solution(a);
        System.out.println("solution = " + solution);
    }

    public int solution(int[] a) {
        if (a.length <= 2) return a.length;

        int answer = 2;

        int[][] minArr = new int[a.length][2];

        int leftMin, rightMin;
        minArr[0][0] = leftMin = a[0];
        minArr[a.length - 1][1] = rightMin = a[a.length - 1];

        for (int i = 1; i < a.length; i++) {
            if (a[i] < minArr[i - 1][0]) {
                leftMin = a[i];
            }

            if (a[a.length - 1 - i] < minArr[a.length - i][1]) {
                rightMin = a[a.length - 1 - i];
            }

            minArr[i][0] = leftMin;
            minArr[a.length - 1 - i][1] = rightMin;
        }

        for (int i = 1; i < a.length - 1; i++) {
            leftMin = minArr[i - 1][0];
            rightMin = minArr[i + 1][1];

            if(leftMin < a[i] && rightMin < a[i]) continue;

            answer++;
        }

        return answer;
    }
}
