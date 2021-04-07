package swimlee.realtest.kakaomakers;

import java.util.ArrayList;
import java.util.List;

/**
 * 순열 조합 연습하기
 */

public class Prob2 {

    ArrayList<ArrayList<Integer>> comb = new ArrayList<>();

    public static void main(String[] args) {
        int[][] needs = { { 1, 0, 0 }, {1, 1, 0}, {1, 1, 0}, {1, 0, 1}, {1, 1, 0}, {0, 1, 1} };
        int r = 2;

        Prob2 prob2 = new Prob2();
        int solution = prob2.solution(needs, r);
        System.out.println("solution = " + solution);
    }

    public int solution(int[][] needs, int r) {
        int answer = 0;

        int partsNum = needs[0].length; // 부품 개수
        List<Integer> partsList = new ArrayList<>();
        //부품 목록 배열 초기화
        for (int i = 0; i < partsNum; i++) {
            partsList.add(i);
        }
        int[] parts = partsList.stream().mapToInt(Integer::intValue).toArray();

        Combination comb = new Combination(partsNum, r);
        comb.combination(parts, 0, 0, 0);
        ArrayList<ArrayList<Integer>> result = comb.getResult();

        for (int i = 0; i < result.size(); i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j = 0; j < result.get(i).size(); j++) {
                int now = result.get(i).get(j);
                temp.add(now);
            }

            int cnt = 0;

            for (int j = 0; j < needs.length; j++) {
                boolean check = true;
                for (int k = 0; k < needs[j].length; k++) {
                    if (!temp.contains(k) && needs[j][k] == 1) {
                        check = false;
                        break;
                    }
                }
                if(check) cnt++;
            }

            answer = Math.max(answer, cnt);
        }

        return answer;
    }
}

class Combination {
    private int n;
    private int r;
    private int[] now; // 현재 조합
    private ArrayList<ArrayList<Integer>> result; // 모든 조합

    public ArrayList<ArrayList<Integer>> getResult() {
        return result;
    }

    public Combination(int n, int r) {
        this.n = n;
        this.r = r;
        this.now = new int[r];
        this.result = new ArrayList<ArrayList<Integer>>();
    }

    public void combination(int[] arr, int depth, int index, int target) {
        if (depth == r) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int i = 0; i < now.length; i++) {
                temp.add(arr[now[i]]);
            }
            result.add(temp);
            return;
        }
        if (target == n) return;
        now[index] = target;
        combination(arr, depth + 1, index + 1, target + 1);
        combination(arr, depth, index, target + 1);
    }
}

