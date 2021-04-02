package swimlee.programmers.kakaoblind;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 정규식 연습용
 * 역시 카카오
 */
public class DartGame {

    public static void main(String[] args) {
        DartGame dartGame = new DartGame();
        int solution = dartGame.solution("1S2D*3T");
        System.out.println("solution = " + solution);
    }

    public int solution(String dartResult) {
        Pattern pattern = Pattern.compile("[0-9]{1,2}[SDT][*#]?");
        Matcher matcher = pattern.matcher(dartResult);

        List<String> split = new ArrayList<>();
        List<Integer> result = new ArrayList<>();

        while (matcher.find()) {// 패턴 분리 저장
            split.add(matcher.group());
        }

        for (int i = 0; i < split.size(); i++) {
            String oper = split.get(i);
            int pointer = 0;
            int num = oper.charAt(pointer++) - '0';
            if (num == 1 && oper.charAt(pointer) - '0' == 0) {// num == 10인경우
                num = 10;
                pointer++;
            }

            char bonus = oper.charAt(pointer++);
            if (bonus == 'D') {
                num = (int) Math.pow(num, 2);
            } else if (bonus == 'T') {
                num = (int) Math.pow(num, 3);
            }

            result.add(num);

            if (oper.length() > pointer) {
                char option = oper.charAt(pointer);
                if (option == '*') {// 스타상
                    result.set(i, result.get(i) * 2);
                    if (i > 0) {
                        result.set(i - 1, result.get(i - 1) * 2);
                    }
                } else {// 아차상
                    result.set(i, result.get(i) * (-1));
                }
            }
        }

        return result.stream().mapToInt(Integer::intValue).sum();
    }
}
