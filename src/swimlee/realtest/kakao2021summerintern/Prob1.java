package swimlee.realtest.kakao2021summerintern;

public class Prob1 {

    public static void main(String[] args) {
        Prob1 prob1 = new Prob1();
        int solution = prob1.solution("one4seveneight");
        System.out.println("solution = " + solution);
    }

    public int solution(String s) {
        int answer = 0;

        String[] englishes = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        for (int i = 0; i < 10; i++) {
            s = change(s, englishes[i], numbers[i]);
        }

        return Integer.parseInt(s);
    }

    private String change(String s, String english, String number) {
        if (s.contains(english)) {
            s = s.replaceAll(english, number);
        }

        return s;
    }
}
