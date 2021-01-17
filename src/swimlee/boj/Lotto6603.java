package swimlee.boj;

import java.util.Arrays;
import java.util.Scanner;

public class Lotto6603 {

    public static void main(String[] args) {
        int[] arr;
        boolean[] visited;

        Scanner sc = new Scanner(System.in);

        while (true) {
            int n = sc.nextInt();

            if(n == 0) break;

            arr = new int[n];
            visited = new boolean[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            Arrays.sort(arr);

            for (int i = 0; i < n - 5; i++) {
                visited[i] = true;
                solution(arr, visited, i, 1);
                visited[i] = false;
            }
            System.out.println();
        }
    }

    public static void solution(int[] arr, boolean[] visited, int now, int cnt) {
        if (now >= arr.length){
            return;
        }

        if (cnt == 6) {
            for (int i = 0; i < arr.length; i++) {
                if(visited[i]) System.out.printf("%d ", arr[i]);
            }
            System.out.println();
            return;
        }

        for (int i = now + 1; i < arr.length; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            solution(arr, visited, i, cnt + 1);
            visited[i] = false;
        }
    }
}
