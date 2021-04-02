package swimlee.programmers.kakaoblind;

/**
 * 정규표현식 기본 연습
 */
public class NewIdRecommend {

    public static void main(String[] args) {
        NewIdRecommend newIdRecommend = new NewIdRecommend();
        String solution = newIdRecommend.solution("=.=");
        System.out.println("solution = " + solution);
    }

    public String solution(String new_id) {
        String answer = new_id;

        // 1단계
        answer = answer.toLowerCase();

        // 2단계
        answer = answer.replaceAll("[^a-z0-9-_.]", "");

        // 3단계
        answer = answer.replaceAll("[.]{2,}", ".");

        // 4단계
        answer = answer.replaceAll("^[.]","");
        answer = answer.replaceAll("[.]$", "");

        // 5단계
        if(answer.isEmpty()) answer = "a";

        // 6단계
        if (answer.length() >= 16) {
            answer = answer.substring(0, 15)
                    .replaceAll("[.]$", "");
        }

        // 7단계
        while (answer.length() <= 2) {
            answer += answer.charAt(answer.length() - 1);
        }

        return answer;
    }
}
