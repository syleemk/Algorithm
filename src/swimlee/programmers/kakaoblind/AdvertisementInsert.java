package swimlee.programmers.kakaoblind;

/**
 * 시간 문제
 * -> LocalDateTime 사용
 * -> 혹은 시 분 초를 초단위로 변환해서 계산하는 방법! (하나의 숫자를 단순비교 가능)
 *
 * + "누적합" 개념을 이용해서 O(n)안에 구하기 가능!
 *
 * 시간 배열 관련 설명 잘해놓은 것 (왜 끝나는 시간 미포함인지)
 * https://real-012.tistory.com/203
 * 
 * 풀이과정 잘해놓은 것
 * https://girawhale.tistory.com/96
 */

public class AdvertisementInsert {

    public static void main(String[] args) {
        String play_time = "02:03:55";
        String adv_time = "00:14:15";
        String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};

        AdvertisementInsert advertisementInsert = new AdvertisementInsert();
        String solution = advertisementInsert.solution(play_time, adv_time, logs);
        System.out.println("solution = " + solution);
    }

    long[] views; //i번째 원소는 i초에서 i+1초까지 시청자들의 수

    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";

        int playTime = stringToTime(play_time);
        int advTime = stringToTime(adv_time);

        views = new long[playTime];

        for (int i = 0; i < logs.length; i++) {
            String[] split = logs[i].split("-");
            int startTime = stringToTime(split[0]);
            int endTime = stringToTime(split[1]);

            for (int j = startTime; j < endTime; j++) {
                views[j]++;
            }
        }

        // 누적합 계산
        for (int i = 0; i < playTime - 1; i++) {
            views[i + 1] += views[i];
        }

        long result = views[advTime - 1]; //advTime-1+1까지 누적합
        int start = 0;
        for (int i = advTime, j=0; i < playTime; i++, j++) {
            if (result < views[i] - views[j]) { // start+1에서 advTime+1 까지의 누적합
                result = views[i] - views[j];
                start = j + 1;
            }
        }

        return timeToString(start);
    }

    private int stringToTime(String timeStr) {
        String[] split = timeStr.split(":");

        return Integer.parseInt(split[0]) * 3600 + Integer.parseInt(split[1]) * 60 + Integer.parseInt(split[2]);
    }

    private String timeToString(int time) {
        int hour = time / 3600;
        time = time % 3600;
        int min = time / 60;
        time = time % 60;
        int second = time;

        return String.format("%02d:%02d:%02d", hour, min, second);
//        return String.join(":", String.format("%02d", hour), String.format("%02d", min), String.format("%02d", second));
    }

}
