package swimlee.programmers.kakaoblind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * regex와 IndexOf 메서드를 통해서 더 쉽게 파싱할 수 있을 듯!
 */

public class FileNameSort {

    public static void main(String[] args) {
        String[] files = {"img000012345", "img1.png","img2","IMG02"};
        FileNameSort fileNameSort = new FileNameSort();
        String[] solution = fileNameSort.solution(files);
        System.out.println("Arrays.toString(solution) = " + Arrays.toString(solution));
    }

    public String[] solution(String[] files) {
        String[] answer = new String[files.length];

        ArrayList<File> result = new ArrayList<>();

/*
        for (int s = 0; s < files.length; s++) {
            String head = "";
            String number = "";
            int headIdx = 0;
            int numberIdx = 0;
            for (int i = 0; i < files[s].length(); i++) {
                char ch = files[s].charAt(i);

                if (headIdx == 0 && ch >= '0' && ch <= '9') {
                    head = files[s].substring(0, i);
                    headIdx = i;
                }

                if (headIdx != numberIdx && !(ch >= '0' && ch <= '9')) {
                    numberIdx = i;
                    number = files[s].substring(headIdx, numberIdx);
                    break;
                }
            }

            if(numberIdx == 0) number = files[s].substring(headIdx);

            result.add(new File(head.toLowerCase(), Integer.parseInt(number), s));
        }
*/

        /**
         * regex와 indexOf 사용해지니까 완전 간단해짐
         * 시간은 더 걸리기는 함.. ㅋㅋ
         */
        for (int i = 0; i < files.length; i++) {
            String file = files[i];
            Pattern pattern = Pattern.compile("[0-9]+");
            Matcher matcher = pattern.matcher(file);
            matcher.find();
            String number = matcher.group();
            int headEndIndex = file.indexOf(number);
            String head = file.substring(0, headEndIndex);

            result.add(new File(head.toLowerCase(), Integer.valueOf(number), i));
        }

        result.sort(((o1, o2) -> {
            if (o1.head.equals(o2.head)) {
                if (o1.number.equals(o2.number)) {
                    return o1.index.compareTo(o2.index);
                }
                return o1.number.compareTo(o2.number);
            }
            return o1.head.compareTo(o2.head);
        }));

        for (int i = 0; i < result.size(); i++) {
            answer[i] = files[result.get(i).index];
        }

        return answer;
    }
}

class File {
    String head;
    Integer number;
    Integer index;

    File(String head, Integer number, Integer index) {
        this.head = head;
        this.number = number;
        this.index = index;
    }
}
