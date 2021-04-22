package swimlee.boj.samsung;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 음수 모듈러 언어마다 음수 모듈러 연산의 결과가 다르게 나올 수 있다.
 *
 * 나머지는 양수로 나오는게 맞다고한다?
 *
 * 근데 자바 기본 모듈러연산을 통해 음수 모듈러를 하게 될 경우 음수로 나온다.
 *
 * 예시) -26%10의 결과 : -6 or 4 ? => 4가 맞으나 Java에서는 -6으로 나옴
 *
 * 이를 해결하려면 그냥 모듈러 결과가 음수면 제수 한번 더해주면 된다.
 *
 * + 적절한 자료구조 생각해내는 것도 중요!
 */

public class FireballShark {

    static int N, M, K;

    static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

    static List<FireBall> list = new LinkedList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        for (int i = 0; i < M; i++) {
            int r = sc.nextInt() - 1;
            int c = sc.nextInt() - 1;
            int m = sc.nextInt();
            int s = sc.nextInt();
            int d = sc.nextInt();

            list.add(new FireBall(r, c, m, d, s));
        }

        for (int i = 0; i < K; i++) {
            moveFireBall();
        }

        int sum = 0;

        for(FireBall fire : list) {
            sum += fire.m;
        }

        System.out.println(sum);
    }

    private static void moveFireBall() {
        LinkedList<FireBall>[][] tempMap = new LinkedList[N][N];

        // 링크드 리스트 이차원 배열 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tempMap[i][j] = new LinkedList<>();
            }
        }

        // 파이어볼 모두 차례로 이동
        for (FireBall fire : list) {

            int nextR = fire.r + dx[fire.d] * fire.s;
            int nextC = fire.c + dy[fire.d] * fire.s;

            nextR %= N;
            nextC %= N;

            if (nextR < 0)
                nextR += N;
            if (nextC < 0)
                nextC += N;

            tempMap[nextR][nextC].add(new FireBall(nextR, nextC, fire.m, fire.d, fire.s));
        }

        list.clear();

        // 파이어볼 합치기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tempMap[i][j].size() >= 2) {
                    int size = tempMap[i][j].size();
                    int m = 0, s = 0;
                    boolean even = true, odd = true;

                    for (FireBall fire : tempMap[i][j]) {
                        m += fire.m;
                        s += fire.s;
                        if (fire.d % 2 == 0)
                            odd = false;
                        else
                            even = false;
                    }

                    m /= 5;
                    s /= size;

                    if (m == 0)
                        continue;

                    // 파이어볼 나누기
                    for (int d = 0; d < 4; d++) {
                        if (even || odd) {
                            list.add(new FireBall(i, j, m, d * 2, s));
                        } else {
                            list.add(new FireBall(i, j, m, d * 2 + 1, s));
                        }
                    }
                } else if (tempMap[i][j].size() == 1) {
                    FireBall tempFire = tempMap[i][j].get(0);

                    if (tempFire.m == 0)
                        continue;

                    list.add(new FireBall(i, j, tempFire.m, tempFire.d, tempFire.s));
                }
            }
        }

    }

}

class FireBall {
    int r, c;
    int m, d, s;

    public FireBall(int r, int c, int m, int d, int s) {
        this.r = r;
        this.c = c;
        this.m = m;
        this.d = d;
        this.s = s;
    }

}