package swimlee.realtest.kakao2021summerintern;

import java.util.Arrays;

public class Prob2 {

    int[] dx = {-2, -1, 0, 1, 2, 1, 0, -1};
    int[] dy = {0, 1, 2, 1, 0, -1, -2, -1};
    int[][] between = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) {
        String[][] places = {
                {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPXX", "OXXXP", "POOXX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
        };

        Prob2 prob2 = new Prob2();
        int[] solution = prob2.solution(places);
        System.out.println("Arrays.toString(solution) = " + Arrays.toString(solution));
    }

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        for (int i = 0; i < places.length; i++) {
            answer[i] = distance(places[i]);
        }

        return answer;
    }

    private int distance(String[] place) {
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                // 한 책상에서 맨해튼거리가 2가 나오는 지점은 총 8군데
                if (place[x].charAt(y) != 'P') continue;

                for (int b = 0; b < 4; b++) {
                    int nx = x + between[b][0];
                    int ny = y + between[b][1];

                    if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;

                    if (place[nx].charAt(ny) == 'P') return 0;
                }

                for (int d = 0; d < 8; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;
                    if (place[nx].charAt(ny) != 'P') continue;

                    int bx = x + between[d / 2][0];
                    int by = y + between[d / 2][1];
                    
                    if (place[bx].charAt(by) != 'X') return 0;

                    if (d % 2 == 1) { // 홀수인경우 한번 더
                        bx = x + between[(d / 2 + 1) % 4][0];
                        by = y + between[(d / 2 + 1) % 4][1];

                        if (place[bx].charAt(by) != 'X') return 0;
                    }
                }
            }
        }

        return 1;
    }
}
