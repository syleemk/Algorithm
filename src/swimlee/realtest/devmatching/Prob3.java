package swimlee.realtest.devmatching;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Prob3 {

    public static void main(String[] args) {
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};

//        int[] result = {360, 958, 108, 0, 450, 18, 180, 1080};

        Prob3 prob3 = new Prob3();
        int[] solution = prob3.solution(enroll, referral, seller, amount);
        System.out.println("Arrays.toString(solution) = " + Arrays.toString(solution));
    }

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];

        Map<String, Node> hm = new HashMap<>();
        for (int i = 0; i < enroll.length; i++) {
            hm.put(enroll[i], new Node(referral[i], i));
        }

        for (int i = 0; i < seller.length; i++) {
            Node node = hm.get(seller[i]);
            int index = node.index;
            answer[index] += amount[i] * 100 * 0.9;

            gotoParent(node.parent, hm, amount[i] * 10, answer);
        }

        return answer;
    }

    private void gotoParent(String parent, Map<String, Node> hm, int amount, int[] answer) {
        if(parent.equals("-")) return;

        Node node = hm.get(parent);
        int index = node.index;

        answer[index] += Math.floor(amount * 0.9);

        gotoParent(node.parent, hm, (int) Math.floor(amount * 0.1), answer);
    }
}

class Node {
    String parent;
    int index;

    Node(String parent, int index) {
        this.parent = parent;
        this.index = index;
    }
}
