package swimlee.codility.arrays;

import java.util.HashSet;
import java.util.Set;

public class OddOccurrencesInArray {
    public static void main(String[] args) {
        int[] A = {9,3,9,3,9,7,9};
        OddOccurrencesInArray oddOccurrencesInArray = new OddOccurrencesInArray();
        int solution = oddOccurrencesInArray.solution(A);
        System.out.println("solution = " + solution);
    }

    public int solution(int[] A) {
        int answer = 0;

        Set<Integer> set = new HashSet<>();
        for (int num : A) {
            if (set.contains(num)) {
                set.remove(num);
            } else {
                set.add(num);
            }
        }

        for (int i : set) {
            answer = i;
        }

        return answer;
    }
}
