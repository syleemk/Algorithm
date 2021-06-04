package swimlee.programmers.kakaoblind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 문자열 파싱 (Date, Time 타입 파싱)
 * 
 * 날짜함수 없이 시간계산
 * -> 시간, 분, 초 단위로 각각 변수로 분리한 후,
 * -> 각각 60씩 곱해서 하나로 합쳐준 후 계산
 * -> 계산 후 다시 분리해서 저장
 *
 * 부동소수점 오차 (실수형 변수는 절대 정확한 값을 가지고 있지 않다!)
 * -> 왜냐하면 컴퓨터는 실수형 변수를 지수부와 가수부로 나누어 저장하는데, (실수도 정수처럼 2진수로 저장)
 * -> 가수부의 경우 1과 2사이의 소수형태이고, 무한소수의 경우 변수의 메모리 크기만큼만 저장 가능하기때문에
 * -> 손실되는 값이 생긴다. 따라서 오차 생김
 *
 * 참고 : https://ssoco.tistory.com/25
 * 
 * Double 형 소수 셋째자리로 반올림
 * -> Math.round(숫자*1000)/1000) 이용
 *
 * 참고 : https://coding-factory.tistory.com/250
 *
 * 부등호 범위 비교 주의!!!
 *
 */


public class ThanksgivingTraffic {

    public static void main(String[] args) {
        /*String[] lines = {
                "2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"
        };*/

        String[] lines = {
                "2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"
        };

        ThanksgivingTraffic thanksgivingTraffic = new ThanksgivingTraffic();
        int solution = thanksgivingTraffic.solution(lines);
        System.out.println("solution = " + solution);
    }

    public int solution(String[] lines) {
        int answer = 0;

        List<Traffic> list = new ArrayList<>();

        for (String line : lines) {
            String[] str = line.split(" ");

            String[] time = str[1].split(":");
            double duration = Double.parseDouble(str[2].replace("s", ""));

            double endTime = (Double.parseDouble(time[0]) * 3600.0)
                    + (Double.parseDouble(time[1]) * 60.0)
                    + Double.parseDouble(time[2]);

            double startTime = endTime - duration + 0.001;
            // 소수점 3째자리로 반올림
            startTime = Math.round(startTime * 1000) / 1000.0;

            list.add(new Traffic(startTime, endTime));

        }

        for (Traffic outer : list) {
            double startEnd = Math.round((outer.startTime + 0.999) * 1000) / 1000.0;
            double endEnd = Math.round((outer.endTime + 0.999) * 1000) / 1000.0;

            int startCnt =0;
            int endCnt = 0;

            for (Traffic inner : list) {

                /**
                 * 비교조건 생각한 사람 기가 막히는 듯!
                 * 포괄적으로 처리함!
                 */
                if (outer.startTime <= inner.endTime && startEnd >= inner.startTime) {
                    startCnt++;
                }

                if (outer.endTime <= inner.endTime && endEnd >= inner.startTime) {
                    endCnt++;
                }
            }

            answer = Math.max(answer, Math.max(startCnt, endCnt));
        }

        return answer;
    }
}

class Traffic {
    double startTime;
    double endTime;

    Traffic(double startTime, double endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
