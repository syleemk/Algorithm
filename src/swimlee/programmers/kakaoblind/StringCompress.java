package swimlee.programmers.kakaoblind;


/**
 * 재귀 + startsWith 사용해서 풀어볼 수 도 있음 (풀이 good 프머 참고)
 */
public class StringCompress {

    public static void main(String[] args) {
        StringCompress stringCompress = new StringCompress();
        int solution = stringCompress.solution("a");
        System.out.println("solution = " + solution);
    }

    public int solution(String s) {
        int answer = s.length();

        int len = s.length();

        for (int i = 1; i <= len / 2; i++) {
            String start = s.substring(0, i);
            String result = "";
            int cnt = 1;
            for (int j = i; j + i <= len; ) {
                String next = s.substring(j, j + i);
                if (start.equals(next)) {
                    cnt++;
                } else {
                    if (cnt > 1) result += cnt;
                    result += start;
                    start = next;
                    cnt = 1;
                }

                String last = s.substring(j);

                j += i;

                if (j + i > len) {
                    if (cnt > 1) result += cnt;

                    if (s.length() % i == 0) {
                        result += start;
                    } else {
                        result += last;
                    }
                }

            }
//            if (cnt > 1) result += cnt;
//            result += start;
            System.out.println("result = " + result);
            System.out.println(i + " result.length() = " + result.length());
            answer = Math.min(answer, result.length());
        }

        return answer;
    }
}
