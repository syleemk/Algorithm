package swimlee.realtest.saramin;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Prob4 {

    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String text = scanner.nextLine().trim();

        LinkedHashMap<String, Integer> hm = new LinkedHashMap<>();

        Pattern pattern = Pattern.compile("\\[ \\S+(, \\S+)* \\]");
        Matcher matcher = pattern.matcher(text);

        int index = 0;
        while (matcher.find()) {
            String group = matcher.group();
            group = group.substring(1, group.length() - 1).trim();

            String[] split = group.split(",");
            for (String s : split) {
                s = s.trim();
                if (!hm.containsKey(s)) {
                    hm.put(s, ++index);
                }
            }
        }

        for (String key : hm.keySet()) {
            text = text.replaceAll(key, hm.get(key).toString());
        }

        Matcher newMatcher = pattern.matcher(text);
        while (newMatcher.find()) {
            String group = newMatcher.group();
            group = group.substring(1, group.length() - 1).trim();

            String[] split = group.split(",");
            if(split.length == 1) continue;

            ArrayList<Integer> list = new ArrayList<>();
            for (String s : split) {
                s = s.trim();
                list.add(Integer.valueOf(s));
            }
            Collections.sort(list);

            String replace =  "";
            for (int i = 0; i < list.size(); i++) {
                if (i == 0) {
                    replace += list.get(i);
                } else {
                    replace += ", " + list.get(i);
                }
            }

            text = text.replaceAll(group, replace);
        }

        System.out.println(text);
        for (String key : hm.keySet()) {
            System.out.printf("[%d] %s%n", hm.get(key), key);
        }
    }
}
