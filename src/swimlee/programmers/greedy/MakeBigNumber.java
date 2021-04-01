package swimlee.programmers.greedy;

public class MakeBigNumber {

    public static void main(String[] args) {
        String number = "4177252841";
        int k = 4;

        MakeBigNumber makeBigNumber = new MakeBigNumber();
        String solution = makeBigNumber.solution(number, k);
        System.out.println("solution = " + solution);
    }

    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();

        int max = 0;
        int idx = 0;

        for (int i = 0; i < number.length() - k; i++) {
            max = 0;
            for (int j = idx; j <= i + k; j++) {
                if(max < number.charAt(j)-'0') {
                    max = number.charAt(j) - '0';
                    idx = j + 1;
                }
            }
            answer.append(max);
        }

        return answer.toString();
    }
}
