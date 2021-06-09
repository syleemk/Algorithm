package swimlee.programmers.kakaoblind;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 시간 다루는 방법
 * LocalTime
 * 혹은 String.format()과 나머지, 몫 연산
 * 시간, 분을 *60해서 Integer로 하나의 수로 표현
 *
 * 참고 :
 * https://iron-jin.tistory.com/entry/2018-%EC%B9%B4%EC%B9%B4%EC%98%A4-%EB%B8%94%EB%9D%BC%EC%9D%B8%EB%93%9C-%EC%BD%94%EB%94%A9%ED%85%8C%EC%8A%A4%ED%8A%B8-1%EC%B0%A8-%EC%85%94%ED%8B%80%EB%B2%84%EC%8A%A4-feat-Java
 */

public class ShuttleBus {

    public static void main(String[] args) {
        int n = 10; // 총 n회
        int t = 60; // t분 간격
        int m = 45; // 최대 m명
        String[] timetable = {"23:59","23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"};

        ShuttleBus shuttleBus = new ShuttleBus();
        String solution = shuttleBus.solution(n, t, m, timetable);
        System.out.println("solution = " + solution);
    }

    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";

        ArrayList<LocalTime> busTime = new ArrayList<>();
        PriorityQueue<LocalTime> crewTime = new PriorityQueue<>();

        LocalTime startTime = LocalTime.of(9, 0);
        for (int i = 0; i < n; i++) {
            LocalTime stopTime = startTime.plusMinutes((long) t * i);
            busTime.add(stopTime);
        }

        for (String crew : timetable) {
            crewTime.add(LocalTime.parse(crew));
        }

        for (int i = 0; i < n; i++) {
            LocalTime nowTime = busTime.get(i);

            int accept = m;
            LocalTime lastCrew = LocalTime.of(0,0);

            while (!crewTime.isEmpty()) {
                LocalTime peek = crewTime.peek();
                if ((peek.isBefore(nowTime) || peek.equals(nowTime)) && accept > 0) {
                    lastCrew = crewTime.poll();
                    accept--;
                } else break;
            }

            if (i == n - 1) { // 마지막 버스인 경우
                if (accept == 0) {
                    answer = lastCrew.minusMinutes(1).toString();
                } else {
                    return nowTime.toString();
                }
            } else {
                if (crewTime.isEmpty()) {
                    answer = busTime.get(n - 1).toString();
//                    break;
                }
            }
        }

        return answer;
    }
}
