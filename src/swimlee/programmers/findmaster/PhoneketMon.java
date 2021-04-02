package swimlee.programmers.findmaster;

import java.util.HashSet;
import java.util.Set;

public class PhoneketMon {

    public static void main(String[] args) {
        int[] nums = {3,3,3,2,2,4};
        PhoneketMon phoneketMon = new PhoneketMon();
        int solution = phoneketMon.solution(nums);
        System.out.println("solution = " + solution);
    }

    public int solution(int[] nums) {
        int answer = 0;

        int len = nums.length;
        int cnt = len / 2;

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int size = set.size();

        answer = Math.min(size, cnt);

        return answer;
    }
}
