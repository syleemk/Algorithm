package swimlee.programmers.kakaoblind;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;

public class RecentSong {

    public static void main(String[] args) {
        String m = "CC#BCC#BCC#BCC#B";
        String[] musicinfos = {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};

        RecentSong recentSong = new RecentSong();
        String solution = recentSong.solution(m, musicinfos);
        System.out.println("solution = " + solution);
    }

    public String solution(String m, String[] musicinfos) {
        String answer = "";

        ArrayList<Music> result = new ArrayList<>();

        for (String musicinfo : musicinfos) {
            String[] split = musicinfo.split(",");

            LocalTime startTime = LocalTime.parse(split[0]);
            LocalTime endTime = LocalTime.parse(split[1]);

            //근데 어차피 분단위 차이 계산이니까
            //시간에 60곱해서 더한 후 차이 계산하면 됨
            int runningTime = (int) ChronoUnit.MINUTES.between(startTime, endTime);

            String title = split[2];
            String melody = substitute(split[3]);

            int length = melody.length();

            int repeatCnt = runningTime / length;
            int leftPlaying = runningTime % length;

            for (int i = 0; i < repeatCnt; i++) {
                melody += melody;
                if(melody.length() >= m.length() * 2) break;
            }

            if(repeatCnt == 0) melody = melody.substring(0,leftPlaying);
            else melody += melody.substring(0, leftPlaying);

            result.add(new Music(title, melody, runningTime));
        }

        Collections.sort(result);

        for (Music music : result) {
            if(music.melody.contains(substitute(m))) return music.title;
        }

        return "(None)";
    }

    public String substitute(String str) {
        str = str.replace("C#", "c");
        str = str.replace("D#", "d");
        str = str.replace("F#", "f");
        str = str.replace("G#", "g");
        str = str.replace("A#", "a");

        return str;
    }
}

class Music implements Comparable<Music>{

    String title;
    String melody;
    int runningTime;

    Music(String title, String melody, int runningTime) {
        this.title = title;
        this.melody = melody;
        this.runningTime = runningTime;
    }

    @Override
    public int compareTo(Music o) {
        return o.runningTime - this.runningTime;
    }
}

