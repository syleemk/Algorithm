package swimlee.realtest.todayhouse;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Prob1 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        Pattern pattern = Pattern.compile("[a-z]{1,1}\\d+");
        Matcher matcher = pattern.matcher(input);

        Map<Character, Integer> hm = new LinkedHashMap<>();
        while (matcher.find()) {
            String group = matcher.group();
            Character ch = group.charAt(0);
            Integer num = Integer.parseInt(group.substring(1));

            hm.put(ch, hm.getOrDefault(ch, 0) + num);
        }

        StringBuilder sb = new StringBuilder();

        hm.forEach((ch, num)-> {
            sb.append(String.valueOf(ch) + num);
        });

        System.out.println(sb.toString());

    }
}
