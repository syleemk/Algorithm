package swimlee.programmers.kakaoblind;

import java.util.*;

/**
 * 이분탐색 리턴값 조건
 * 상한선 하한선
 * lower bound, upper bound
 */

public class RankingSearch {

    public static void main(String[] args) {

        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};

        RankingSearch rankingSearch = new RankingSearch();
        int[] solution = rankingSearch.solution(info, query);
        System.out.println("Arrays.toString(solution) = " + Arrays.toString(solution));
    }

    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        HashMap<String, ArrayList<Integer>> infoList = new HashMap<>();
        for (String str : info) {
            String[] arr = str.split(" ");

            String[] job = {arr[0], "-"};
            String[] position = {arr[1], "-"};
            String[] career = {arr[2], "-"};
            String[] food = {arr[3], "-"};

            for (int a = 0; a < 2; a++) {
                for (int b = 0; b < 2; b++) {
                    for (int c = 0; c < 2; c++) {
                        for (int d = 0; d < 2; d++) {
                            String key = job[a] + position[b] + career[c] + food[d];

                            if(!infoList.containsKey(key)){
                                infoList.put(key, new ArrayList<>());
                            }

                            infoList.get(key).add(Integer.parseInt(arr[4]));
                        }
                    }
                }
            }
        }

        for (String key : infoList.keySet()) {
            infoList.get(key).sort(Comparator.naturalOrder());
        }

        for (int i=0; i< query.length; i++) {
            String[] arr = query[i].split(" ");
            String language = arr[0];
            String position = arr[2];
            String career = arr[4];
            String food = arr[6];
            int score = Integer.parseInt(arr[7]);

            String key = language + position + career + food;

            if (infoList.containsKey(key)) {
                answer[i] = getCount(infoList.get(key), score);
            } else {
                answer[i] = 0;
            }
        }

        return answer;
    }

    private int getCount(ArrayList<Integer> scoreList, int score) {
        int left = 0, right = scoreList.size() - 1, mid;

        while (left <= right) {
            mid = (left + right) / 2;

            /**
             * 등호 없는 이유는 score랑 같은 경우 하한선을 올려버리면
             * score이랑 같은 값들은 포함 안되니까 (upper bound 되버림)
             * lower bound를 구해주려면 score랑 "값이 같은경우(등호인 경우)" 하한선을 올리는게 아니라 상한선을 내려야함
             */
            if (scoreList.get(mid) < score) {
                left = mid + 1; // 하한선을 올려준다
            } else {
                // 값이 같은 경우에도 상한선을 내려야지 하한선을 올리면 값이 같은경우는 포함안됨
                right = mid - 1; // 상한선을 내려준다
            }
        }

        /**
         * left 리턴하는 이유
         * left가 하한 right가 상한선이니까?
         * ~이상을 구하는거니까 하한선부터 시작해야함! 그리고 상한선이 하한보다 내려오는게 탈출조건이니까
         * right을 빼주면 이하인 값도 빼져버림
         * 그리고 위의 연산 모두 하한선을 기준으로 구했음 (문제에서도 하한선을 기준으로 ~이상인 값 개수 구하고)
         * 따라서 하한선 (left)를 리턴하는게 맞음
         */
        return scoreList.size() - left;
    }
}
