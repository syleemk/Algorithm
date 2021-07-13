package swimlee.realtest.kakaopay;

public class Prob3 {
    public static void main(String[] args) {
        String line1 = "abbbcbbb";
        String line2 = "bbb";

        Prob3 prob3 = new Prob3();
        int solution = prob3.solution(line1, line2);
        System.out.println("solution = " + solution);
    }

    public int solution(String line1, String line2) {
        int answer = 0;

        for (int s = 0; s <= line1.length() - line2.length(); s++) {
            for (int blank = 0; blank < line1.length(); blank++) {
                boolean flag = true;
                for (int e = 0; e < line2.length(); e++) {
                    if (s + blank * e + e >= line1.length()) {
                        flag = false;
                        break;
                    }

                    if (line1.charAt(s + blank * e + e) != line2.charAt(e)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    System.out.println("s = " + s);
                    answer++;
                }
            }

        }

        return answer;
    }
}
