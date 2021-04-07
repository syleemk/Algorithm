package swimlee.programmers.kakaocode;

import java.util.HashMap;

/**
 * 순열 Combination (N과 M)
 * 완전 탐색으로 모두 체크
 */

public class TakePicture {

    static final int NUM = 8;
    HashMap<Character, Integer> hm = new HashMap<>();
    boolean[] visited = new boolean[NUM];
    int[] position = new int[NUM];
    int answer = 0;

    public static void main(String[] args) {
        int n = 2;
        String[] data = {"N~F=0", "R~T>2"};

        TakePicture takePicture = new TakePicture();
        int solution = takePicture.solution(n, data);
        System.out.println("solution = " + solution);
    }

    public int solution(int n, String[] data) {

        // hm 초기화
        Character[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
        for (int i = 0; i < friends.length; i++) {
            hm.put(friends[i], i);
        }

        dfs(0, data);

        return answer;
    }

    private void dfs(int index, String[] data) {
        if (index == NUM) {
            if(check(data)) answer++;
            return;
        }

        for (int i = 0; i < NUM; i++) {
            if(visited[i]) continue;
            visited[i] = true; position[index] = i;
            dfs(index + 1, data);
            visited[i] = false;
        }
    }

    private boolean check(String[] data) {
        for (String str : data) {
            int first = hm.get(str.charAt(0));
            int second = hm.get(str.charAt(2));
            char oper = str.charAt(3);
            int interval = str.charAt(4) - '0';

            int num = Math.abs(position[first] - position[second]) - 1;

            if (oper == '=') {
                if(num != interval) return false;
            } else if (oper == '>') {
                if(num <= interval) return false;
            } else if (oper == '<') {
                if(num >= interval) return false;
            }
        }

        return true;
    }

}
