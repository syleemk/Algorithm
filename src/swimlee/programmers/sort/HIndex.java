package swimlee.programmers.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class HIndex {

    public static void main(String[] args) {
        int[] citations = {3, 0, 6, 1, 5};
        int[] citations2 = {5, 5, 5, 5, 5};

        HIndex hIndex = new HIndex();
        int solution = hIndex.solution(citations2);
        System.out.println("solution = " + solution);
    }

    public int solution(int[] citations) {

        List<Integer> arr = new ArrayList<>();
        for (int n : citations) {
            arr.add(n);
        }

        arr.sort(Comparator.reverseOrder());

        int h = 0;
        for (int i = 1; i <= arr.size(); i++) {
            if(arr.get(i-1) >= i){
                if (i < arr.size()) {
                    if(arr.get(i) <= i) h = i;
                } else h = i;
            }
        }

        return h;
    }
}
