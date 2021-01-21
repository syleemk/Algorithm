package swimlee.codility.timecomplexity;

import java.util.Arrays;

public class PermMissingElem {
    public static void main(String[] args) {
        int[] A = {2,3,1,5};
        PermMissingElem permMissingElem = new PermMissingElem();
        int solution = permMissingElem.solution(A);
        System.out.println("solution = " + solution);
    }

    public int solution(int[] A) {
        Arrays.sort(A);
        int num = 1;
        for (int i : A) {
            if(i != num) break;
            num++;
        }

        return num;
    }
}
