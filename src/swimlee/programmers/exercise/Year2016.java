package swimlee.programmers.exercise;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Year2016 {

    public static void main(String[] args) {
        Year2016 year2016 = new Year2016();
        String solution = year2016.solution(5, 24);
        System.out.println("solution = " + solution);
    }

    public String solution(int a, int b) {
        String answer = "";

        LocalDate today = LocalDate.of(2016, a, b);
        DayOfWeek dayOfWeek = today.getDayOfWeek();

        switch (dayOfWeek) {
            case MONDAY:
                answer = "MON";
                break;

            case TUESDAY:
                answer = "TUE";
                break;

            case WEDNESDAY:
                answer = "WED";
                break;

            case THURSDAY:
                answer = "THU";
                break;

            case FRIDAY:
                answer = "FRI";
                break;

            case SATURDAY:
                answer = "SAT";
                break;

            case SUNDAY:
                answer = "SUN";
                break;
        }

        return answer;
    }
}
