package swimlee.programmers.kakaoblind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class OpenChatting {

    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};

        OpenChatting openChatting = new OpenChatting();
        String[] solution = openChatting.solution(record);
        System.out.println("Arrays.toString(solution) = " + Arrays.toString(solution));
    }

    public String[] solution(String[] record) {
        String[] answer = {};

        HashMap<String, String> hm = new HashMap<>();
        ArrayList<String> result = new ArrayList<>();

        for (String r : record) {
            String[] s = r.split(" ");
            String oper = s[0];
            String uid = s[1];

            if (oper.equals("Enter") || oper.equals("Change")) {
                String name = s[2];
                hm.put(uid, s[2]);
            }
        }

        for (String r : record) {
            String[] s = r.split(" ");
            String oper = s[0];
            String uid = s[1];

            if(oper.equals("Enter")) {
                result.add(hm.get(uid) + "님이 들어왔습니다.");
            } else if(oper.equals("Leave")) {
                result.add(hm.get(uid) + "님이 나갔습니다.");
            }
        }

        return result.toArray(new String[0]);
    }
}
