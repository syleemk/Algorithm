package swimlee.programmers.bruteforce;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class PrimeNumber {

    Set<Integer> set = new TreeSet<>();//중복 검사용
    boolean[] check; //방문 체크용
    int[] number;

    public static void main(String[] args) {
        PrimeNumber primeNumber = new PrimeNumber();
        int solution = primeNumber.solution("011");
        System.out.println("solution = " + solution);
    }

    public int solution(String numbers) {
        check = new boolean[numbers.length()]; //방문 체크용
        number = new int[numbers.length()];

        for (int i = 0; i < numbers.length(); i++) {
            number[i] = numbers.charAt(i) - '0';
        }

        Arrays.sort(number);

        for (int i = 1; i <= numbers.length(); i++) {
            permutation(i,0, "");
        }

        int cnt = 0;
        for (Integer i : set) {
            if(isPrime(i)) cnt++;
        }
        return cnt;
    }

    public void permutation(int cnt, int num, String s) {

        if (cnt == num) {
            set.add(Integer.valueOf(s));
        }

        String tmp;
        for (int i = 0; i < number.length; i++) {
            if(check[i]) continue;
            check[i] = true;
            tmp = s + number[i];
            permutation(cnt, num+1, tmp);
            check[i] = false; // 백트래킹의 개념
        }

    }

    public static boolean isPrime(int num) {
        if(num == 0) return false;
        if(num == 1) return false;
        if(num == 2) return true;

        for (int i = 2; i * i <= num; i++) {
            if(num%i==0) return false;
        }

        return true;
    }
}
