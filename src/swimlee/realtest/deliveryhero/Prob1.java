package swimlee.realtest.deliveryhero;

import java.util.ArrayList;
import java.util.HashMap;

public class Prob1 {

    public static void main(String[] args) {
        String S = "John Doe; Peter Benjamin Parker; Mary Jane Watson-Parker; John Elvis Doe; John Evan Doe; Jane Doe; Peter Brian Parker";
        String C = "Example";

        Prob1 prob1 = new Prob1();
        String solution = prob1.solution(S, C);
        System.out.println("solution = " + solution);
    }

    public String solution(String S, String C) {
        String emailAddress = "@" + C.toLowerCase() + ".com";

        HashMap<String, Integer> hm = new HashMap<>();
        ArrayList<Email> list = new ArrayList<>();

        String[] splits = S.split("; ");
        for (String split : splits) {
            String[] s = split.split(" ");
            String first = "";
            String last = "";
            if (s.length == 2) {
                first = s[0].toLowerCase();
                last = s[1].toLowerCase();
            } else {
                first = s[0].toLowerCase();
                last = s[2].toLowerCase();
            }

            if (last.contains("-")) {
                last = last.replaceAll("-", "");
            }

            String key = last + "_" + first;
            String email = key;

            if (hm.containsKey(key)) {
                hm.put(key, hm.get(key) + 1);
                email = key + hm.get(key);
            } else {
                hm.put(key, 1);
            }

            email += emailAddress;

            list.add(new Email(split, email));
        }

        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            if (i != 0) answer.append("; ");

            Email email = list.get(i);
            answer.append(email.name).append(" <").append(email.email).append(">");
        }

        return answer.toString();
    }
}

class Email {
    String name;
    String email;

    Email(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
