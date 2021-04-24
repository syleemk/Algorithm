package swimlee.boj.samsung;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 배열 회전 + 순열 (조합 x, 순서 상관 있음!)
 * 
 * 완전탐색 N과 M 중요한듯
 * -> 여기서 조합이랑 순열 코드 다나옴
 * -> 순열 코드 하나 외워두면 조합으로도 응용 가능하다.
 *  (K개 선택 마쳤을 때 무엇을 실행할지 정의해두면됨, (idx == K),
 *   K개의 원소 선택해가는 과정에서 ->
 *  순열(혹은 조합)을 저장해둘 자료구조(배열, 문자열 등, [여기선 perm 배열])와 인덱스(순열 혹은 조합의 몇번째 원소를 선택하는지, [여기선 idx]) 필요)
 *
 * 배열 회전할 때는 가장 끝에서부터 시작해서 앞에 것을 하나씩 옮겨오는 방법으로 해야함!
 * 아니면 공간복잡도를 두배 늘려서 다른 배열에 복사하고 다시 옮겨오는 방식으로 해야하는데, 인덱스 다루기가 복잡함
 */

public class RotateArray {

    static int N, M, K;
    static int[][] map;
    static boolean[] visited;
    static int[] perm;
    static ArrayList<int[]> oper;

    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        oper = new ArrayList<>();

        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        map = new int[N][M];
        visited = new boolean[K];
        perm = new int[K];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        // 연산 입력
        for (int i = 0; i < K; i++) {
            int r = sc.nextInt() - 1;
            int c = sc.nextInt() - 1;
            int s = sc.nextInt();
            int[] temp = { r, c, s };
            oper.add(temp);
        }

        permutation(0);

        System.out.println(answer);

    }

    private static int getScore(int[][] copyArr) {

        int result = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < M; j++) {
                sum += copyArr[i][j];
            }
            result = Math.min(result, sum);
        }

        return result;
    }

    private static void permutation(int idx) {
        if (idx == K) { // 조합이 완성되면
            // 배열 복사
            int[][] copyArr = new int[N][M];

            for (int i = 0; i < N; i++) {
                if (M >= 0) System.arraycopy(map[i], 0, copyArr[i], 0, M);
            }

            // 순열대로 회전 연산 수행
            for (int i = 0; i < K; i++) {
                int[] operation = oper.get(perm[i]);
                int r = operation[0];
                int c = operation[1];
                int s = operation[2];

                rotate(copyArr, r - s, c - s, r + s, c + s);
            }

            answer = Math.min(answer, getScore(copyArr));

        }

        for (int i = 0; i < K; i++) {
            if (visited[i])
                continue;
            visited[i] = true;
            perm[idx] = i;
            permutation(idx + 1);
            visited[i] = false;
        }
    }

    private static void rotate(int[][] arr, int lx, int ly, int rx, int ry) {

        if (lx == rx && ly == ry)
            return;

        int[] temp = { arr[lx][ry], arr[rx][ry], arr[rx][ly], arr[lx][ly] };

        // 윗 변
        for (int y = ry; y > ly; y--) {
            arr[lx][y] = arr[lx][y - 1];
        }

        // 우 변
        for (int x = rx; x > lx; x--) {
            if (x == lx + 1)
                arr[x][ry] = temp[0];
            else
                arr[x][ry] = arr[x - 1][ry];
        }

        // 밑 변
        for (int y = ly; y < ry; y++) {
            if (y == ry - 1)
                arr[rx][y] = temp[1];
            else
                arr[rx][y] = arr[rx][y + 1];
        }

        // 좌 변
        for (int x = lx; x < rx; x++) {
            if (x == rx - 1)
                arr[x][ly] = temp[2];
            else
                arr[x][ly] = arr[x + 1][ly];
        }

        rotate(arr, lx + 1, ly + 1, rx - 1, ry - 1);
    }
}
