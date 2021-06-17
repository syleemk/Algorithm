package swimlee.programmers.kakaoblind;

import java.util.Arrays;

/**
 * 원형으로 이루어진 완전탐색 문제
 * bit mask 혹은 permutation 등을 활용할 수 있나 확인
 */

public class OuterWallCheck {

    public static void main(String[] args) {
        int n = 12;
        int[] weak = {1, 5, 6, 10};
        int[] dist = {1, 2, 3, 4};

        OuterWallCheck outerWallCheck = new OuterWallCheck();
        int solution = outerWallCheck.solution(n, weak, dist);
        System.out.println("solution = " + solution);
    }

    int[] weak;
    int[] dist;
    int[][] rotatedWeak;
    int weakLen;
    boolean finish = false;

    public int solution(int n, int[] weak, int[] dist) {
        int answer = -1;

        this.weak = weak;
        this.dist = dist;
        this.weakLen = weak.length;

        rotatedWeak = new int[weakLen][weakLen];

        Arrays.sort(dist);

        for (int i = 0; i < weakLen; i++) {
            for (int j = 0; j < weakLen; j++) {
                if (i + j < weakLen) rotatedWeak[i][j] = weak[i + j];
                else rotatedWeak[i][j] = weak[i + j - weakLen] + n;
            }
        }

        for (int i = 1; i <= dist.length; i++) {
            permutaion(0, new int[i], new boolean[dist.length]);
            if (finish) return i;
        }

        return answer;
    }

    private void permutaion(int cnt, int[] choice, boolean[] selected) {
        if(finish) return;
        if (cnt == choice.length) {
            check(choice);
            return;
        }

        for (int i = dist.length - 1; i >= 0; i--) {
            if (selected[i]) continue;
            selected[i] = true;
            choice[cnt] = dist[i];
            permutaion(cnt + 1, choice, selected);
            selected[i] = false;
        }
    }

    private void check(int[] choice) {
        for (int[] w : rotatedWeak) {
            boolean[] visited = new boolean[w.length];
            int start = 0;

            for (int ch : choice) {
                for (int i = start; i < w.length; i++) {
                    if (w[i] <= w[start] + ch) {
                        visited[i] = true;
                    } else {
                        start = i;
                        break;
                    }
                }
            }

            for (boolean visit : visited) {
                if (!visit) {
                    finish = false;
                    break;
                } else finish = true;
            }

            if (finish) return;
        }
    }
}
