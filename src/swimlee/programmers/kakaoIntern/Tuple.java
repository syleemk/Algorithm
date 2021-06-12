package swimlee.programmers.kakaoIntern;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 정규표현식 문자열 parsing
 * Comparator 정렬
 *
 * 굳이 괄호제거할 것 없이 그냥 숫자만 뽑아와도 됐음... (다시해보기)
 */

public class Tuple {

    public static void main(String[] args) {
        Tuple tuple = new Tuple();
        int[] solution = tuple.solution("{{2},{2,1},{2,1,3},{2,1,3,4}}");
        System.out.println("Arrays.toString(solution) = " + Arrays.toString(solution));
    }

    public int[] solution(String s) {
        int[] answer = {};

        String list = s.substring(1, s.length() - 1);

        Pattern pattern = Pattern.compile("\\{\\d[?,\\d]*\\}");
        Matcher matcher = pattern.matcher(list);

        List<String> parsedString = new ArrayList<>();
        HashMap<Integer, Integer> hm = new HashMap<>();

        while (matcher.find()) {
            parsedString.add(matcher.group());
        }

        for (String str : parsedString) {
            str = str.substring(1, str.length() - 1);
            String[] splits = str.split(",");
            for (String split : splits) {
                Integer key = Integer.valueOf(split);
                hm.put(key, hm.getOrDefault(key, 0) + 1);
            }
        }

        List<Integer> result = new ArrayList<>(hm.keySet());

        result.sort(((o1, o2) -> hm.get(o2).compareTo(hm.get(o1))));

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
