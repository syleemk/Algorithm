package swimlee.codility.iterations;

public class BinaryGap {

    public static void main(String[] args) {
        BinaryGap binaryGap = new BinaryGap();
        int solution = binaryGap.solution(529);
        System.out.println("solution = " + solution);
    }

    public int solution(int N) {
        //10진수를 2진수로 변환
        String binaryString = Integer.toBinaryString(N);

        boolean check = false;
        int count = 0;
        int answer = 0;

        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) == '1') { // 1인경우
                if (check) { // 끝내는 1인 경우
                    answer = Math.max(answer, count);
                    count = 0;
                } else { // 시작하는 1인경우
                    check = true;
                }
            } else { // 0인경우
                count++;
            }
        }

        return answer;
    }
}
