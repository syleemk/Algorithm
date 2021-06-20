package swimlee.realtest.deliveryhero;

import java.time.LocalDate;

public class Prob2 {

    public static void main(String[] args) {
        String S = "1988-08-29        956 system.zip\n1976-09-16     126976 old-photos.tgz\n1987-02-03     118784 logs.rar\n1961-12-04  703594496 very-long-filename.rar\n1980-08-03          2 DELETE-THIS.TXT\n2014-08-23        138 important.rar\n2001-08-26     595968 MOONLIGHT-SONATA.FLAC\n1967-11-30     245760 archive.rar\n1995-10-13        731 file.tgz";

        Prob2 prob2 = new Prob2();
        String solution = prob2.solution(S);
        System.out.println("solution = " + solution);
    }

    public String solution(String S) {
        int answer = 0;

        LocalDate dateCri = LocalDate.of(1995, 10, 13);
        int sizeCri = 240;
        for (int i = 0; i < 10; i++) {
            sizeCri *= 2;
        }

        String[] splits = S.split("\\n");

        for (String split : splits) {
            String[] s = split.split("\\s+");

            String dateString = s[0];
            String sizeString = s[1];
            String nameString = s[2];

            LocalDate date = LocalDate.parse(dateString);
            int size = Integer.parseInt(sizeString);
            String extension = nameString.split("\\.")[1];

            if(date.isAfter(dateCri) || date.isEqual(dateCri)) continue;
            if(size >= sizeCri) continue;
            if(!(extension.equals("zip")|| extension.equals("rar")|| extension.equals("tgz"))) continue;

            answer++;
        }

        if(answer == 0) return "NO FILES";

        return Integer.toString(answer);
    }
}
