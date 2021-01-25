package swimlee.codility.naver;

public class Test1 {
    public static void main(String[] args) {
//        String S = "baaabbaabbba";
//        String S = "baaaaa";
        String S = "baabab";

        Test1 test1 = new Test1();
        int solution = test1.solution(S);
        System.out.println("solution = " + solution);
    }

    public int solution(String S) {
        // write your code in Java SE 8

        int answer = 0;
        int cnt = 1;

        for (int i = 1; i < S.length(); i++) {
            if (S.charAt(i - 1) == S.charAt(i)) { //연속된 문자 나오는 경우
                cnt++;
            } else {
                answer += cnt / 3;
                cnt = 1;
            }
        }

        answer += cnt / 3;

        return answer;
    }
}
