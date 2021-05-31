package swimlee.realtest.devcarnival;

import java.io.IOException;
import java.util.*;

public class Prob1 {

    public static void main(String[] args) throws IOException {
        int N, K;
        List<Integer> player = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        for (int i = 0; i < N; i++) {
            player.add(sc.nextInt());
        }

        int idx = K - 1;
        for (int i = 0; i < N; i++) {
            q.add(new Node(idx + 1, player.get(idx)));
            idx = (idx + 1) % N;
        }

        while (!q.isEmpty()) {
            Node now = q.poll();
            if (now.food == 0) {
                result.add(now.idx);
            } else {
                now.food -= 1;
                q.add(now);
            }
        }

        for (Integer num : result) {
            System.out.print(num + " ");
        }
    }
}

class Node {
    int idx;
    int food;

    Node(int idx, int food) {
        this.idx = idx;
        this.food = food;
    }
}
