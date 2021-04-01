package swimlee.programmers.exercise;

public class CenterLetter {

    public static void main(String[] args) {
        CenterLetter centerLetter = new CenterLetter();
        String solution = centerLetter.solution("qwer");
        System.out.println("solution = " + solution);
    }

    public String solution(String s) {
        String answer = "";

        int len = s.length();

        if (len % 2 == 1) {// 홀수 길이
            answer += s.charAt(len / 2);
        } else {// 짝수 길이
            answer = s.substring(len / 2 - 1, len / 2 + 1);
        }

        return answer;
    }
}
