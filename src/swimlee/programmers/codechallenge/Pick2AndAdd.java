package swimlee.programmers.codechallenge;

import java.util.*;
import java.util.stream.Collectors;

public class Pick2AndAdd {

    public static void main(String[] args) {
        int[] numbers = {2,1,3,4,1};
        Pick2AndAdd pick2AndAdd = new Pick2AndAdd();
        int[] solution = pick2AndAdd.solution(numbers);
        System.out.println("solution = " + Arrays.toString(solution));
    }

    public int[] solution(int[] numbers) {
        Set<Integer> set = new TreeSet<>();

        for (int i = 0; i < numbers.length-1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                set.add(numbers[i] + numbers[j]);
            }
        }

        return set.stream().mapToInt(Integer::intValue).toArray();
    }
}
