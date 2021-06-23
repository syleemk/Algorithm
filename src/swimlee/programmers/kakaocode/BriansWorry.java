package swimlee.programmers.kakaocode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 반드시 문자열을 처음 순회하면서 일을 처리할 필요 없다!!
 * 한번 먼저 순회해서 필요한 정보를 저장해두고 시작해도된다!
 *
 * 왜 통과안되는지 모르겠음;;
 */

public class BriansWorry {
    public static void main(String[] args) {
        String sentence = "SpIpGpOpNpGJqOqA";
//        String sentence = "HaEaLaLaObWORLDb";
//        String sentence = "AxAxAxAoBoBoB";
//        String sentence = "HELLOaWORLDa";
//        String sentence = "AAAAxAAx";

        BriansWorry briansWorry = new BriansWorry();
        String solution = briansWorry.solution(sentence);
        System.out.println("solution = " + solution + ".");
    }

    HashMap<Character, Smallcase> hm;
    ArrayList<String> words = new ArrayList<>();

    public String solution(String sentence) {
        String answer = "";

        if (sentence.contains(" ")) return "invalid";

        hm = new HashMap<>();

        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (Character.isUpperCase(c)) continue;
            if (hm.containsKey(c)) {
                Smallcase sc = hm.get(c);
                sc.cnt++;
                sc.lastIdx = i;
            } else {
                hm.put(c, new Smallcase(c, i, i, 1));
            }
        }

        String word = "";

        for (int i = 0; i < sentence.length(); i++) {
            char now = sentence.charAt(i);
            if (Character.isUpperCase(now)) {// 대문자 차례
                if (i + 1 < sentence.length()) {
                    char next = sentence.charAt(i + 1);
                    if (Character.isUpperCase(next)) {// 다음에 나오는 문자 대문자
                        word += now;
                    } else {// 다음에 나오는 문자 소문자
                        Smallcase smallcase = hm.get(next);
                        if (smallcase.cnt == 1 || smallcase.cnt >= 3) { // 1번 규칙 여부
                            if (!word.equals("")) {
                                words.add(word);
                                word = "";
                            }
                            if (!isFirst(sentence, next)) return "invalid";
                            i = smallcase.lastIdx + 1;
                        } else {
                            word += now;
                            words.add(word);
                            word = "";
                        }
                    }
                } else { //마지막에 나오는 대문자인 경우
                    word += now;
                    words.add(word);
                    word = "";
                }
            } else {// 소문자 차례
                Smallcase smallcase = hm.get(now);
                if (i + 1 < sentence.length()) {// 소문자만 연속으로 나오는 경우
                    char next = sentence.charAt(i + 1);
                    if(Character.isLowerCase(next)) return "invalid";
                }

                if (smallcase.cnt == 2) {
                    if (!isSecond(sentence, now)) return "invalid";
                    i = smallcase.lastIdx;
                } else {
                    return "invalid";
                }
            }
        }

        answer = words.get(0);
        for (int i = 1; i < words.size(); i++) {
            answer += " " + words.get(i);
        }

        return answer;
    }

    private boolean isFirst(String sentence, char c) {
        Smallcase smallcase = hm.get(c);

        if(smallcase.used) return false;
        smallcase.used = true;

        if (smallcase.cnt == 2) return false;

        if (smallcase.firstIdx - 1 < 0 || smallcase.lastIdx + 1 >= sentence.length())
            return false;

        boolean flag = false;
        for (int i = smallcase.firstIdx - 1; i <= smallcase.lastIdx + 1; i++) {
            char now = sentence.charAt(i);
            if (flag) { //소문자 차례
                if (Character.isUpperCase(now)) return false;
                if (now != c) return false;
                flag = false;
            } else { // 대문자 차례
                if (Character.isLowerCase(now)) return false;
                flag = true;
            }
        }

        String word = sentence.substring(smallcase.firstIdx - 1, smallcase.lastIdx + 2);
        word = word.replaceAll(Character.toString(c), "");
        words.add(word);

        return true;
    }

    private boolean isSecond(String sentence, char c) {
        Smallcase smallcase = hm.get(c);

        if(smallcase.used) return false;

        smallcase.used = true;

        if (smallcase.cnt != 2) return false;

        boolean flag = true;
        for (int i = smallcase.firstIdx + 1; i < smallcase.lastIdx - 1; i++) {
            char now = sentence.charAt(i);
            if(Character.isLowerCase(now)) flag = false;
        }

        if (flag) {
            String word = sentence.substring(smallcase.firstIdx + 1, smallcase.lastIdx);
            words.add(word);
            return true;
        }

        return isFirst(sentence, sentence.charAt(smallcase.firstIdx + 2));
    }
}

class Smallcase {
    char key;
    int firstIdx;
    int lastIdx;
    int cnt;
    boolean used;

    Smallcase(char key, int firstIdx, int lastIdx, int cnt) {
        this.key = key;
        this.firstIdx = firstIdx;
        this.cnt = cnt;
        this.lastIdx = lastIdx;
        this.used = false;
    }
}
