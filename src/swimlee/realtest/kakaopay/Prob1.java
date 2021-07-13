package swimlee.realtest.kakaopay;

public class Prob1 {

    public static void main(String[] args) {
        int money = 12345678;
        int minratio = 10;
        int maxratio = 20;
        int ranksize = 250000;
        int thresold = 10000000;
        int months = 4;

        Prob1 prob1 = new Prob1();
        int solution = prob1.solution(money, minratio, maxratio, ranksize, thresold, months);
        System.out.println("solution = " + solution);
    }

    public int solution(int money, int minratio, int maxratio, int ranksize, int threshold, int months) {
        int answer = money;

        for (int i = 0; i < months; i++) {
            int assumptionAmount = answer / 100 * 100;

            int over = assumptionAmount - threshold;
            if (over < 0) return answer;

            int ratio = over / ranksize + minratio;
            System.out.println("ratio = " + ratio);

            if(ratio > maxratio) ratio = maxratio;

            int tax = assumptionAmount / 100 * ratio;
            answer -= tax;
        }

        return answer;
    }

}
