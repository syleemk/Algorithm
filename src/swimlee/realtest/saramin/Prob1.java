package swimlee.realtest.saramin;

import java.util.HashSet;
import java.util.Scanner;

public class Prob1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long number = sc.nextInt();

        String answer = String.valueOf(number);

        if (isHappy(number)) {
            answer += " is Happy Number.";
        } else {
            answer += " is Unhappy Number.";
        }

        System.out.println(answer);
    }

    private static long getSquare(long number) {
        long result = 0;
        while (number > 0) {
            long remain = number % 10;
            result += (remain * remain);
            number /= 10;
        }
        return result;
    }

    private static boolean isHappy(long number) {
        HashSet<Long> hs = new HashSet<>();
        while (true) {
            hs.add(number);
            number = getSquare(number);
            if(number == 1) return true;
            if(hs.contains(number)) break;
        }
        return false;
    }

}
