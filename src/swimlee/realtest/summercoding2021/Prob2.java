package swimlee.realtest.summercoding2021;

import java.util.*;

public class Prob2 {

    public static void main(String[] args) {

        int[] t = {7,6,8,1};
        int[] r = {0,1,2,3};

        Prob2 prob2 = new Prob2();
        int[] solution = prob2.solution(t, r);
        System.out.println("Arrays.toString(solution) = " + Arrays.toString(solution));
    }

    public int[] solution(int[] t, int[] r) {
        int[] answer = {};

        List<Customer> customers = new ArrayList<>();

        for (int i = 0; i < t.length; i++) {
            customers.add(new Customer(i, t[i], r[i]));
        }

        Collections.sort(customers, (o1, o2)-> {
            if (o1.time == o2.time) {
                return o1.priority - o2.priority;
            }

            return o1.time - o2.time;
        });

        PriorityQueue<Customer> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.priority == o2.priority) {
                if (o1.time == o2.time) {
                    return o1.id - o2.id;
                }
                return o1.time - o2.time;
            }
            return o1.priority - o2.priority;
        });

        List<Integer> result = new ArrayList<>();

        int time = 0;
        int idx = 0;
        int size = customers.size();

        while (true) {
            if(idx >= size) break;

            if (customers.get(idx).time == time) {
                pq.add(customers.get(idx));
                idx++;
            } else if (customers.get(idx).time > time) {
                time++;
                if(!pq.isEmpty()) result.add(pq.remove().id);
            }
        }

        while (!pq.isEmpty()) {
            result.add(pq.remove().id);
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}

class Customer {
    int id;
    int time;
    int priority;

    public Customer(int id, int time, int priority) {
        this.id = id;
        this.time = time;
        this.priority = priority;
    }
}
