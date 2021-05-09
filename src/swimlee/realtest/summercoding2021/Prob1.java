package swimlee.realtest.summercoding2021;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Prob1 {

    public static void main(String[] args) {

        String code = "012345";
        String day = "20190620";
        String[] data = {"price=80 code=987654 time=2019062113", "price=90 code=012345 time=2019062014", "price=120 code=987654 time=2019062010", "price=110 code=012345 time=2019062009", "price=95 code=012345 time=2019062111"};

        Prob1 prob1 = new Prob1();
        int[] solution = prob1.solution(code, day, data);
        System.out.println("Arrays.toString(solution) = " + Arrays.toString(solution));
    }

    public int[] solution(String code, String day, String[] data) {
        int[] answer = {};

        List<Node> result = new ArrayList<>();

        for (String str : data) {

            List<String> list = new ArrayList<>();

            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(str);

            while (matcher.find()) {
                list.add(matcher.group());
            }

            String priceStr = list.get(0);
            String codeStr = list.get(1);
            String timeStr = list.get(2);
            String dateStr = timeStr.substring(0, 8);
            String hourStr = timeStr.substring(8);

            if (code.equals(codeStr) && day.equals(dateStr)) {
                result.add(new Node(Integer.parseInt(hourStr), Integer.parseInt(priceStr)));
            }

        }

        Collections.sort(result, Comparator.comparingInt(o -> o.hour));

        return result.stream()
                .map(node -> {
                    return node.price;
                }).mapToInt(Integer::intValue)
                .toArray();
    }
}

class Node {
    int hour;
    int price;

    Node (int hour, int price) {
        this.hour = hour;
        this.price = price;
    }

}
