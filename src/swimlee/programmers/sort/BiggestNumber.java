package swimlee.programmers.sort;

import java.util.Arrays;

public class BiggestNumber {
    public static void main(String[] args) {

        int[] numbers = {3, 30, 34, 5, 9};

        BiggestNumber biggestNumber = new BiggestNumber();
        String solution = biggestNumber.solution(numbers);
        System.out.println("solution = " + solution);

    }
    public String solution(int[] numbers) {
        String answer = "";

        String[] str = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            str[i] = Integer.toString(numbers[i]);
        }

        Arrays.sort(str, (a,b)-> (b+a).compareTo(a+b));

        StringBuilder num = new StringBuilder();

        for (String s : str) {
            num.append(s);
        }

        if(num.charAt(0) == '0') return "0";

        return num.toString();
    }
}
