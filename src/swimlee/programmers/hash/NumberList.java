package swimlee.programmers.hash;

import java.util.Arrays;

public class NumberList {
    public static void main(String[] args) {
        String[] phone_book = {"119", "97674223", "1195524421"};
        NumberList numberList = new NumberList();
        boolean solution = numberList.solution(phone_book);
        System.out.println("solution = " + solution);
    }

    public boolean solution(String[] phone_book) {
        boolean answer = true;

        Arrays.sort(phone_book);
        for (int i = 1; i < phone_book.length; i++) {
            if (phone_book[i].startsWith(phone_book[i - 1])) {
                return false;
            }
        }

        return true;
    }
}
