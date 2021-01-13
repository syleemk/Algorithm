package swimlee.leetcode;

import java.util.*;

/**
 * 우선순위 큐에 대한 이해와
 * 자바에서는 배열 또한 인스턴스라는 것을 알고 이를 활용
 */

public class SlidingWindow {

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
//        int[] nums = {1, -1};
        int k = 3;

        SlidingWindow slidingWindow = new SlidingWindow();
        int[] ints = slidingWindow.maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(ints));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        int[] ans = new int[nums.length - k + 1];

        for (int i = 0; i < k; i++) {
            int[] tmp = {nums[i], i};
            pq.add(tmp);
        }

        ans[0] = pq.peek()[0];

        for (int i = k; i < nums.length; i++) {
            int[] tmp = {nums[i], i};
            pq.add(tmp);

            while (pq.peek()[1] <= i - k) {
                pq.remove();
            }

            ans[i-k+1] = pq.peek()[0];
        }

        return ans;
    }
}
