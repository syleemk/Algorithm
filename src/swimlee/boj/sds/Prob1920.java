package swimlee.boj.sds;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Prob1920 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            set.add(sc.nextInt());
        }

        int M = sc.nextInt();
        for (int i = 0; i < M; i++) {
            if (set.contains(sc.nextInt())) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}
