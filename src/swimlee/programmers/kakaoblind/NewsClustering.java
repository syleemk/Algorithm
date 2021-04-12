package swimlee.programmers.kakaoblind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

/**
 * 정규식과 ArrayList 활용 문제
 * regex? Pattern.matches(regex, String)
 * ArrayList
 * remove(Object O) : 리스트에서 인자로 전달된 객체와 동일한 객체 제거, Boolean return
 * containsAll (부분집합 - A가 B의 부분집합인가? Boolean return)
 * addAll (합집합)
 * retainAll (교집합 - 하지만 중복 개수 고려 X, 종류만 고려)
 * removeAll (차집합 - 하지만 중복 개수 고려 X, 종류만 고려, 하나라도 겹치는 종류있으면 모두 제거)
 */

public class NewsClustering {

    public static void main(String[] args) {
        String str1 = "aa1+aa2";
        String str2 = "AAAA12";

        NewsClustering newsClustering = new NewsClustering();
        int solution = newsClustering.solution(str1, str2);
        System.out.println("solution = " + solution);
    }

    public int solution(String str1, String str2) {
        int answer = 0;

        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();

        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();

        for (int i = 0; i < str1.length() - 1; i++) {
            String sub = str1.substring(i, i + 2);
            if (Pattern.matches("[A-Z]{2}", sub)) {
                list1.add(sub);
            }
        }

        for (int i = 0; i < str2.length() - 1; i++) {
            String sub = str2.substring(i, i + 2);
            if (sub.matches("[A-Z]{2}")) {
                list2.add(sub);
            }
        }

        Collections.sort(list1);
        Collections.sort(list2);

        ArrayList<String> inter = new ArrayList<>();
        ArrayList<String> union = new ArrayList<>();

        for (int i = 0; i < list2.size(); i++) {
            if (list1.remove(list2.get(i))) {
                inter.add(list2.get(i));
            }
            union.add(list2.get(i));
        }

        union.addAll(list1);

        if (inter.size() == 0 && union.size() == 0) {
            return 65536;
        }

        answer = (int)((double) inter.size() / union.size() * 65536);

        return answer;
    }
}
