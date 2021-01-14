package swimlee.programmers.stackqueue;

import java.util.LinkedList;
import java.util.Queue;

public class TruckPassingBridge {

    public static void main(String[] args) {
        int bridge_length = 2;
        int weight = 10;
        int[] truck_weights = {7, 4, 5, 6};

        TruckPassingBridge truckPassingBridge = new TruckPassingBridge();
        int solution = truckPassingBridge.solution(bridge_length, weight, truck_weights);
        System.out.println("solution = " + solution);
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        Queue<int[]> bridge = new LinkedList<>();
        Queue<Integer> wait = new LinkedList<>();

        for (int truck_weight : truck_weights) {
            wait.add(truck_weight);
        }

        int time = 0;
        int cur_weight = 0;

        while (!wait.isEmpty() || !bridge.isEmpty()) {
            time++;

            if (!bridge.isEmpty() && (time - bridge.peek()[1] == bridge_length)) {
                cur_weight -= bridge.peek()[0];
                bridge.remove();
            }

            if (!wait.isEmpty() && cur_weight + wait.peek() <= weight) {
                int[] tmp = {wait.peek(), time};
                cur_weight += tmp[0];
                bridge.add(tmp);
                wait.remove();
            }
        }

        return time;
    }
}
