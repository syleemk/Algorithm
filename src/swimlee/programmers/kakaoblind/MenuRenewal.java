package swimlee.programmers.kakaoblind;

import java.util.*;

/**
 * 조합 문제!! + compareTo method로 number type 비교
 */

public class MenuRenewal {

    private List<String> combination = new ArrayList<>();

    public static void main(String[] args) {
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2,3,4};

        MenuRenewal menuRenewal = new MenuRenewal();
        String[] solution = menuRenewal.solution(orders, course);
        System.out.println("Arrays.toString(solution) = " + Arrays.toString(solution));
    }

    public String[] solution(String[] orders, int[] course) {
        String[] answer;

        for (String order : orders) {
            char[] chars = order.toCharArray();

            Arrays.sort(chars);

            for (int i = 0; i < chars.length - 1; i++) {
                for (int j = 0; j < course.length; j++) {
                    getCombination(chars, i, 1, course[j], String.valueOf(chars[i]));
                }
            }
        }

        Map<String, Integer> hm = new HashMap<>();
        for (String key : combination) {
            hm.put(key, hm.getOrDefault(key, 0) + 1);
        }

        List<String> candidate = new ArrayList<>(hm.keySet());

        candidate.sort((o1, o2) -> hm.get(o2).compareTo(hm.get(o1)));

        List<String> answerList = new ArrayList<>();

        for (int i = 0; i < course.length; i++) {
            int max = 0;
            for (String key : candidate) {
                if (hm.get(key) >= max && hm.get(key) >= 2 && key.length() == course[i]) {
                    answerList.add(key);
                    max = hm.get(key);
                }
            }
        }

        Collections.sort(answerList);
        return answerList.toArray(new String[0]);
    }

    public void getCombination(char[] arr, int idx, int cnt, int len, String str) {
        if (cnt == len) {
            combination.add(str);
            return;
        }

        for (int i = idx + 1; i < arr.length; i++) {
            getCombination(arr, i, cnt + 1, len, str + arr[i]);
        }
    }
}
