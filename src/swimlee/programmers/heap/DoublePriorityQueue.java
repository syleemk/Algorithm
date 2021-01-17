package swimlee.programmers.heap;

import java.util.*;

/**
 * 자바의 우선순위 큐는 remove()메서드 지원하므로
 * 원하는 값을 검색해서 삭제 가능!
 */

class Node {
    public int num;
    public int idx;

    public Node(int num, int idx) {
        this.num = num;
        this.idx = idx;
    }
}

public class DoublePriorityQueue {
    public static void main(String[] args) {
        String[] operatons = {"I 17", "I 5", "I -5", "D -1"};
        DoublePriorityQueue doublePriorityQueue = new DoublePriorityQueue();
        int[] solution = doublePriorityQueue.solution(operatons);
        System.out.println("solution = " + Arrays.toString(solution));
    }

    public int[] solution(String[] operations) {
        int[] answer = new int[2];

        HashSet<Integer> set = new HashSet<>(); //제거된 원소 체크용
        PriorityQueue<Node> maxHeap = new PriorityQueue<>((a, b) -> b.num - a.num);
        PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> a.num - b.num);

        for (int i = 0; i < operations.length; i++) {
            StringTokenizer st = new StringTokenizer(operations[i]);
            String op = st.nextToken();
            int num = Integer.parseInt(st.nextToken());

            if (op.equals("I")) {
                maxHeap.offer(new Node(num, i));
                minHeap.offer(new Node(num, i));
            } else if (op.equals("D")) {
                if (num == 1) { // 최대값 삭제
                    while (!maxHeap.isEmpty() && set.contains(maxHeap.peek().idx)) {
                        maxHeap.poll();
                    }

                    Node maxNode = maxHeap.poll();
                    if (maxNode != null) {
                        set.add(maxNode.idx); //삭제 인덱스 등록
                    }

                } else { // 최소값 삭제
                    while (!minHeap.isEmpty() && set.contains(minHeap.peek().idx)) {
                        minHeap.poll();
                    }

                    Node minNode = minHeap.poll();
                    if (minNode != null) {
                        set.add(minNode.idx); //삭제 인덱스 등록
                    }
                }
            }
        }

        while (!maxHeap.isEmpty() && set.contains(maxHeap.peek().idx)) {

            maxHeap.poll();
        }

        while (!minHeap.isEmpty() && set.contains(minHeap.peek().idx)) {
            minHeap.poll();
        }

        if (!maxHeap.isEmpty() && !minHeap.isEmpty()) {
            answer[0] = maxHeap.peek().num;
            answer[1] = minHeap.peek().num;
        }

        return answer;
    }
}
