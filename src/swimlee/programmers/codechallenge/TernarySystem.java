package swimlee.programmers.codechallenge;

/**
 * parseInt로 진수변환
 */
public class TernarySystem {

    public static void main(String[] args) {
        TernarySystem ternarySystem = new TernarySystem();
        int solution = ternarySystem.solution(125);
        System.out.println("solution = " + solution);
    }

    public int solution(int n) {
        int answer = 0;

        StringBuilder sb = new StringBuilder();

        if (n / 3 == 0) {
            sb.insert(0, n);
        }

        while (n / 3 > 0) {
            int left = n % 3;
            sb.insert(0, left);

            n /= 3;
            if(n < 3) sb.insert(0, n);
        }

        String str = sb.reverse().toString();
        answer = Integer.parseInt(str, 3); 

        return answer;
    }
}
